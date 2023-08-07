package shoppingmall.shopping_mall.itemService.question;

public enum QuestionType {
    product("상품 문의"), deliver("배송 문의"), exchange("교환 및 반품 문의");
    private final String description;

    QuestionType(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
