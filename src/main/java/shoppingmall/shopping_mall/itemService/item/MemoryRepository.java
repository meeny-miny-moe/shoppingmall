package shoppingmall.shopping_mall.itemService.item;

import org.springframework.stereotype.Component;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemoryRepository implements ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    @Override
    public Item findById(Long id) {
       return store.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setExplanation(updateParam.getExplanation());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setImageFiles(updateParam.getImageFiles());
    }
    @Override
    public void clearStore() {
        store.clear();
    }
}
