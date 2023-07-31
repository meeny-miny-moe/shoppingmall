package shoppingmall.shopping_mall.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.item.ItemType;
import shoppingmall.shopping_mall.member.Grade;
import shoppingmall.shopping_mall.member.Member;
import shoppingmall.shopping_mall.member.MemberRepository;
import shoppingmall.shopping_mall.web.validation.member.MemberJoinForm;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class JoinController {
    private final MemberRepository memberRepository;

    @ModelAttribute("grades")
    public Grade[] grade(){
        return Grade.values();
    }

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member){
        return "basic/members/joinMemberForm";
    }

    @PostMapping("/join")
    public String save(@Validated @ModelAttribute("member") MemberJoinForm form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/members/joinMemberForm";
        }

        // 성공 로직
        Member member= new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setName(form.getName());
        member.setNumber(form.getNumber());
        member.setEmail(form.getEmail());
        member.setGrade(form.getGrade());

        Member joinMember = memberRepository.save(member);
        return "redirect:/";
    }
}
