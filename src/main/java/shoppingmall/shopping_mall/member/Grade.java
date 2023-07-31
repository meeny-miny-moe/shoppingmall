package shoppingmall.shopping_mall.member;

public enum Grade {
        MANAGER("관리자"), CUSTOMER("고객");
        private final String description;

        Grade(String description){
                this.description = description;
        }

        public String getDescription(){
                return description;
        }
}
