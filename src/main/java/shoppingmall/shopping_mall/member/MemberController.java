package shoppingmall.shopping_mall.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member){
        return "basic/members/joinMemberForm";
    }
    @PostMapping("/join")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "basic/members/joinMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
