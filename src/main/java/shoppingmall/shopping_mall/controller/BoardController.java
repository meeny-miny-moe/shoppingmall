package shoppingmall.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.question.Question;
import shoppingmall.shopping_mall.itemService.question.QuestionRepository;
import shoppingmall.shopping_mall.itemService.question.QuestionType;
import shoppingmall.shopping_mall.web.validation.item.ItemUpdateForm;
import shoppingmall.shopping_mall.web.validation.question.QuestionSaveForm;
import shoppingmall.shopping_mall.web.validation.question.QuestionUpdateForm;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final QuestionRepository questionRepository;
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
    public String addQuestion(@Validated @ModelAttribute("question") QuestionSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/board/questionadd";
        }
        // 성공 로직
        Question question = new Question();
        question.setQuestionType(form.getQuestionType());
        question.setWriter(form.getWriter());
        question.setExplanation(form.getExplanation());
        question.setEmail(form.getEmail());
        question.setImage(form.getImage());
        question.setPassword(form.getPassword());

        Question savedQuestion = questionRepository.save(question);
        redirectAttributes.addAttribute("questionId", savedQuestion.getId());

       return "redirect:/board/product";
    }
    // 해당 질문으로 이동
    @GetMapping("article/{questionId}")
    public String question(@PathVariable long questionId, Model model){
        Question question = questionRepository.findById(questionId);
        model.addAttribute("question", question);
        return "basic/board/question";
    }
    // 수정하기
    @GetMapping("article/{questionId}/edit")
    public String editForm(@PathVariable Long questionId, Model model){
        Question question = questionRepository.findById(questionId);
        model.addAttribute("question", question);
        return "basic/board/questionEditForm";
    }
    @PostMapping("article/{questionId}/edit")
    public String edit(@PathVariable Long questionId, @Validated @ModelAttribute("question") QuestionUpdateForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/board/questionEditForm";
        }
        Question question = new Question();
        question.setId(form.getId());
        question.setQuestionType(form.getQuestionType());
        question.setWriter(form.getWriter());
        question.setEmail(form.getEmail());
        question.setExplanation(form.getExplanation());
        question.setImage(form.getImage());
        question.setPassword(form.getPassword());

        questionRepository.update(questionId, question);
        return "redirect:/board/article/{questionId}";
    }

    // 삭제
    @GetMapping("article/{questionId}/delete")
    public String delete(@PathVariable Long questionId, Model model){
        questionRepository.delete(questionId);

        return "redirect:/board/product";
    }
}
