package shoppingmall.shopping_mall.itemService.question;

import lombok.Data;
import shoppingmall.shopping_mall.itemService.item.ItemType;
import shoppingmall.shopping_mall.itemService.item.UploadFile;

import java.io.File;
import java.util.List;

@Data
public class Question {
    private Long id;
    private QuestionType questionType;
    private String writer;
    private String explanation;
    private String email;
    //private File image;
    private List<UploadFile> imageFiles; // 업로드 이미지 여러개
    private Integer password;
    private Integer questionCheck; // 비밀번호 확인

    private String Answer;


    public Question(){}
}
