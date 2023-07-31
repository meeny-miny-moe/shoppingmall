package shoppingmall.shopping_mall.itemService.item;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.awt.*;
import java.io.File;

@Data
public class Item {
    //@NotNull(groups = UpdateCheck.class)
    private Long id;
    //@NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
    //@NotNull(groups = SaveCheck.class)
    //@Min(value = 1, groups = SaveCheck.class)
    private Integer price;
    private String explanation;
    //@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    //@Min(value = 1, groups = SaveCheck.class)
    //@Min(value = 0, groups = UpdateCheck.class)
    private Integer quantity;
    private File image;
    //@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    private ItemType itemType; // 상품 분류 종류

    public Item(){}

    public Item(String itemName, Integer price, String explanation, Integer quantity, File image, ItemType itemType) {
        this.itemName = itemName;
        this.price = price;
        this.explanation = explanation;
        this.quantity = quantity;
        this.image=image;
        this.itemType = itemType;
    }
}
