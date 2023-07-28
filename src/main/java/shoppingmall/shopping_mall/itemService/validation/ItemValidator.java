package shoppingmall.shopping_mall.itemService.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import shoppingmall.shopping_mall.itemService.item.Item;

@Slf4j
@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        // item == class
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
        }
        if(item.getPrice() == null){
            errors.rejectValue("price", "required");
        }
        if(item.getQuantity()==null || item.getQuantity()<=0){
            errors.rejectValue("quantity", "min",new Object[]{1}, null);
        }
        if(item.getItemType()==null){
            errors.rejectValue("itemType", "required");
        }
    }
}
