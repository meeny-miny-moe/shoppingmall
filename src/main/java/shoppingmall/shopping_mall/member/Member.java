package shoppingmall.shopping_mall.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    @NotEmpty
    private String loginId; // 로그인 id
    @NotEmpty
    private String password;
    @NotEmpty
    private String name; // 사용자 이름
    @NotEmpty
    private String number; // 사용자 전화번호

    private String email; // 사용자 이메일

    private Grade grade; // 관리자 or 고객
}
