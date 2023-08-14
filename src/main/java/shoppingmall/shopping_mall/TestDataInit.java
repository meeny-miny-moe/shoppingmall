package shoppingmall.shopping_mall;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;
import shoppingmall.shopping_mall.itemService.item.ItemType;
import shoppingmall.shopping_mall.itemService.question.Question;
import shoppingmall.shopping_mall.itemService.question.QuestionMemory;
import shoppingmall.shopping_mall.itemService.question.QuestionRepository;
import shoppingmall.shopping_mall.itemService.question.QuestionType;
import shoppingmall.shopping_mall.member.Grade;
import shoppingmall.shopping_mall.member.Member;
import shoppingmall.shopping_mall.member.MemberRepository;

import java.io.File;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    private final QuestionRepository questionRepository;
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("superstar", 1200000, "240 size", 2, new File("../image/superstar"), ItemType.acc));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");
        member.setEmail("test@gmail.com");
        member.setNumber("01012341234");
        member.setGrade(Grade.MANAGER);

        memberRepository.save(member);

        Question question = new Question();
        question.setQuestionType(QuestionType.product);
        question.setWriter("정수민");
        question.setExplanation("질문 있어요");
        question.setPassword(1120);

        questionRepository.save(question);
    }
}
