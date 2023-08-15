package shoppingmall.shopping_mall.itemService.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemRepository {
    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();

    void update(Long itemId, Item updateParam);

    void clearStore();


}
