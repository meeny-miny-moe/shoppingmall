package shoppingmall.shopping_mall.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shoppingmall.shopping_mall.itemService.item.Item;
import shoppingmall.shopping_mall.itemService.item.ItemRepository;

import java.util.List;

import static shoppingmall.shopping_mall.itemService.item.ItemType.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "basic/main";
    }

    @GetMapping("/shop")
    public String ShopPage(@RequestParam("value") String value, Model model) {
        model.addAttribute("value", value); // /shop?value=top
        log.info("value = {}", value);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/shop";
    }

    @GetMapping("/board")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/board";

    }
}


