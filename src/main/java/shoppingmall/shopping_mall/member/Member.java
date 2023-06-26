package shoppingmall.shopping_mall.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private Long password;
    private String name;
    private Who who;
    public Member(Long id, Long password, String name, Who who) {
        this.id = id;
        this.password=password;
        this.name = name;
        this.who = who;
    }
}
