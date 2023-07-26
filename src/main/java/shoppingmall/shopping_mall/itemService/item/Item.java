package shoppingmall.shopping_mall.itemService.item;

import lombok.Data;

import java.awt.*;
import java.io.File;

@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private String explanation;
    private Integer quantity;
    private File image;
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
