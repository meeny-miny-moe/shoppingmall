package shoppingmall.shopping_mall.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.file.FileStore;
import shoppingmall.shopping_mall.itemService.item.UploadFile;
import shoppingmall.shopping_mall.itemService.question.Question;
import shoppingmall.shopping_mall.itemService.question.QuestionRepository;
import shoppingmall.shopping_mall.itemService.question.QuestionType;
import shoppingmall.shopping_mall.member.Grade;
import shoppingmall.shopping_mall.web.session.SessionConst;
import shoppingmall.shopping_mall.web.validation.question.QuestionSaveForm;
import shoppingmall.shopping_mall.web.validation.question.QuestionUpdateForm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final QuestionRepository questionRepository;
    private final FileStore fileStore;

    @ModelAttribute("questionTypes")
    public QuestionType[] questionTypes(){
        return QuestionType.values();
    }

    // qna 질문 모두
    @GetMapping("product")
    public String qnalist(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);

        return "basic/board/qnalist";
    }
    // 질문 작성
    @GetMapping("product/write")
    public String questionForm(Model model) {
        model.addAttribute("question", new Question());
        return "basic/board/questionadd";
    }
    @PostMapping("product/write")
    public String addQuestion(@Validated @ModelAttribute("question") QuestionSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/board/questionadd";
        }
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        // 성공 로직
        Question question = new Question();
        question.setQuestionType(form.getQuestionType());
        question.setWriter(form.getWriter());
        question.setExplanation(form.getExplanation());
        question.setEmail(form.getEmail());
        question.setPassword(form.getPassword());
        question.setImageFiles(storeImageFiles);

        Question savedQuestion = questionRepository.save(question);
        redirectAttributes.addAttribute("questionId", savedQuestion.getId());

       return "redirect:/board/product";
    }
    // 해당 질문으로 이동 (상세보기)
    @GetMapping("article/{questionId}")
    public String question(@PathVariable long questionId, Model model,HttpSession session){
        Question question = questionRepository.findById(questionId);
        model.addAttribute("question", question);
        log.info("question = {}", question);
        // 비번 없는 경우
        Object loginGrade = session.getAttribute("loginGrade");

        log.info("loginGrade = {}", loginGrade);
        log.info("SessionConst.LOGIN_GRADE = {}", SessionConst.LOGIN_GRADE);
        log.info("Grade.MANAGER = {}", Grade.MANAGER);

        if(question.getPassword() == null || question.getPassword() == question.getQuestionCheck()
           || loginGrade == Grade.MANAGER ){
            return "basic/board/question";
        }
        // 비번 있는 경우
        return "basic/board/password";
    }
    @PostMapping("article/{questionId}")
    public String question(@PathVariable long questionId, @ModelAttribute("question") Question form, Model model){
        Question question = questionRepository.findById(questionId);
        model.addAttribute("question", question);

        if(question.getPassword().equals(form.getQuestionCheck())){
            log.info("same");
            return "basic/board/question";
        }
        return "basic/board/qnalist";
    }
    // 수정하기
    @GetMapping("article/{questionId}/edit")
    public String editForm(@PathVariable Long questionId, Model model){
        Question question = questionRepository.findById(questionId);
        model.addAttribute("question", question);
        return "basic/board/questionEditForm";
    }
    @PostMapping("article/{questionId}/edit")
    public String edit(@PathVariable Long questionId, @Validated @ModelAttribute("question") QuestionUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/board/questionEditForm";
        }
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        Question question = new Question();
        question.setId(form.getId());
        question.setQuestionType(form.getQuestionType());
        question.setWriter(form.getWriter());
        question.setEmail(form.getEmail());
        question.setExplanation(form.getExplanation());
        question.setPassword(form.getPassword());
        question.setImageFiles(storeImageFiles);

        //log.info("question = {}", question);
        questionRepository.update(questionId, question);

        return "redirect:/board/article/{questionId}";
    }

    // 삭제
    @GetMapping("article/{questionId}/delete")
    public String delete(@PathVariable Long questionId, Model model){
        questionRepository.delete(questionId);

        return "redirect:/board/product";
    }
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
