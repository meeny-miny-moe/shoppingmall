package shoppingmall.shopping_mall.web.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import shoppingmall.shopping_mall.web.exception.exception.UserException;

@Slf4j
@RestController
public class ApiExceptionController {

    @RequestMapping(value = "/api/members/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public MemberDto getMember(@PathVariable("id") String id)  {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello "+ id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
