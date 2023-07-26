package shoppingmall.shopping_mall.itemService.item;

public enum ItemType {
    top("top"), bottom("bottom"), dress("dress"), acc("acc");
    private final String description;

    ItemType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
