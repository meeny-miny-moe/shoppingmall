package shoppingmall.shopping_mall.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;
import shoppingmall.shopping_mall.itemService.item.ItemType;

import java.io.File;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ItemRepository itemRepository;

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes(){
        // [TOP, BOTTOM, DRESS, SKIRT, ACC]
        return ItemType.values();
    }

    // 아이템 상세보기 화면
    @GetMapping("item/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    // 아이템 추가
    @GetMapping("item/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "basic/addForm";
    }

    @PostMapping("/item/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes){
        log.info("item.itemType={}", item.getItemType());
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status",true);
        System.out.println("savedItem = " + savedItem);
        Item findItem = itemRepository.findById(item.getId());
        log.info(findItem.getItemName());
        return "redirect:/item/{itemId}";
    }

    // 수정하기
    @GetMapping("item/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
    @PostMapping("item/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/item/{itemId}";
    }
}
