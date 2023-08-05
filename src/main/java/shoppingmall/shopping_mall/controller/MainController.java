package shoppingmall.shopping_mall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;
import shoppingmall.shopping_mall.member.Member;
import shoppingmall.shopping_mall.member.MemberRepository;
import shoppingmall.shopping_mall.web.session.SessionConst;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session == null){
            log.info("session is null");
            return "basic/main";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 main
        if(loginMember == null){
            log.info("세션 만료");
            return "basic/main";
        }
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember);
        session.setAttribute("member", loginMember.getName());
        return "basic/loginMain";
    }
    @GetMapping("/myshop/index")
    public String MyPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        return "basic/myshop/index";
    }

    @GetMapping("/shop")
    public String ShopPage(@RequestParam("value") String value, Model model) {
        model.addAttribute("value", value); // /shop?value=top
        log.info("value = {}", value);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/shop";
    }

    @GetMapping("/board")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/board";

    }
}


