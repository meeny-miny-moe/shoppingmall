package shoppingmall.shopping_mall.member.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;
import shoppingmall.shopping_mall.member.Member;
import shoppingmall.shopping_mall.member.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    /**
     * @return null 로그인 실패
     */
    public Member login(String longinId, String password){
        return memberRepository.findByLoginId(longinId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
