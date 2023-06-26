package shoppingmall.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "basic/main";
    }

    @GetMapping("/shop")
    public String ShopPage(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/shop";
    }

    @GetMapping("/board")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/shop";

    }
}


