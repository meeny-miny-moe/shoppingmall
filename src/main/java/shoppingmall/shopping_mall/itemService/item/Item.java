package shoppingmall.shopping_mall.itemService.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private String explanation;
    private Integer quantity;
    private List<UploadFile> imageFiles; // 업로드 이미지 여러개
    private ItemType itemType; // 상품 분류 종류

    public Item(){}

    public Item(String itemName, Integer price, String explanation, Integer quantity, ItemType itemType) {
        this.itemName = itemName;
        this.price = price;
        this.explanation = explanation;
        this.quantity = quantity;
        this.itemType = itemType;
    }
}
