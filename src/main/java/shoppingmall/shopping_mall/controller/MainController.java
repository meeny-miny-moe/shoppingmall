package shoppingmall.shopping_mall.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String mainPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        // 세션에 회원 데이터가 없으면 main
        if(loginMember == null){
            log.info("세션 만료");
            return "basic/main";
        }
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember.getId());
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

}


