package shoppingmall.shopping_mall.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    public String mainPage(){
        return "basic/main";
    }
    // 마이페이지
    @GetMapping("/myshop/index")
    public String MyPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        return "basic/myshop/index";
    }

    // 물건창
    @GetMapping("/shop")
    public String ShopPage(@RequestParam("value") String value, Model model) {
        model.addAttribute("value", value); // /shop?value=top
        log.info("value = {}", value);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/shop";
    }

}


