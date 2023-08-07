package shoppingmall.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemType;
import shoppingmall.shopping_mall.itemService.question.Question;
import shoppingmall.shopping_mall.itemService.question.QuestionRepository;
import shoppingmall.shopping_mall.itemService.question.QuestionType;
import shoppingmall.shopping_mall.web.validation.question.QuestionSaveForm;

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

    @GetMapping("/product")
    public String qnalist(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);

        return "basic/board/qnalist";
    }
    @GetMapping("/product/write")
    public String questionForm(Model model) {
        model.addAttribute("question", new Question());
        return "basic/board/questionadd";
    }
    @PostMapping("/product/write")
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
}
