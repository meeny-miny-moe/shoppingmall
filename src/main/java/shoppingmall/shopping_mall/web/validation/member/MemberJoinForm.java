package shoppingmall.shopping_mall.web.validation.member;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shoppingmall.shopping_mall.member.Grade;

@Data
public class MemberJoinForm {
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

    @NotNull
    private Grade grade; // 관리자 or 고객
}
