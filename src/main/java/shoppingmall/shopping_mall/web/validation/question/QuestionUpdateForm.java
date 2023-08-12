package shoppingmall.shopping_mall.web.validation.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shoppingmall.shopping_mall.itemService.question.QuestionType;

import java.io.File;

@Data
public class QuestionUpdateForm {
    @NotNull
    private Long id;
    @NotNull
    private QuestionType questionType;
    @NotBlank
    private String writer;
    private String email;
    @NotBlank
    private String explanation;
    private File image;
    private Integer password;
}
