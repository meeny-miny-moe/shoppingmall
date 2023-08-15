package shoppingmall.shopping_mall.web.validation.question;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import shoppingmall.shopping_mall.itemService.item.ItemType;
import shoppingmall.shopping_mall.itemService.question.QuestionType;

import java.io.File;
import java.util.List;

@Data
public class QuestionSaveForm {
    @NotNull
    private QuestionType questionType;
    @NotBlank
    private String writer;
    private String email;
    @NotBlank
    private String explanation;
    private List<MultipartFile> imageFiles;
    private File image;
    private Integer password;
}
