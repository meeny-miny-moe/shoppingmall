package shoppingmall.shopping_mall.web.validation.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberNow {
    private String loginId; // 로그인 id
    private String password;
}
