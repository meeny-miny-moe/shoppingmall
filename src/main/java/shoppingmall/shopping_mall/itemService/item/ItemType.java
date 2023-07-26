package shoppingmall.shopping_mall.itemService.item;

public enum ItemType {
    TOP("상의"), BOTTOM("하의"), DRESS("원피스"), SKIRT("스커트"), ACC("그 외");
    private final String description;

    ItemType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
