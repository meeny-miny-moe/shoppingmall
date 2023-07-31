package shoppingmall.shopping_mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shoppingmall.shopping_mall.member.Member;
import shoppingmall.shopping_mall.web.validation.login.LoginForm;
import shoppingmall.shopping_mall.web.validation.login.LoginService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginFrom(@ModelAttribute("loginForm") LoginForm form){
        return "basic/members/login";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/members/login";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        if(loginMember==null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
            log.info("errors = {}", bindingResult);
            model.addAttribute("msg","아이디 또는 비밀번호가 맞지 않습니다");
            return "basic/members/login";
        }
        // 로그인 성공 처리
        return "redirect:/";
    }
}
