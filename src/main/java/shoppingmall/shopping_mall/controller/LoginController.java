package shoppingmall.shopping_mall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import shoppingmall.shopping_mall.web.session.SessionConst;
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
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, Model model, HttpServletRequest request){
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
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 반환
        HttpSession session = request.getSession(true);
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout1(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
