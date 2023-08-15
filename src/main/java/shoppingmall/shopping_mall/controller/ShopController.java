package shoppingmall.shopping_mall.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shoppingmall.shopping_mall.itemService.file.FileStore;
import shoppingmall.shopping_mall.itemService.item.*;
import shoppingmall.shopping_mall.web.validation.item.ItemForm;
import shoppingmall.shopping_mall.web.validation.item.ItemSaveForm;
import shoppingmall.shopping_mall.web.validation.item.ItemUpdateForm;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ShopController {
    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes(){
        // [TOP, BOTTOM, DRESS, SKIRT, ACC]
        return ItemType.values();
    }

    // 아이템 상세보기 화면
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    // 아이템 추가
    @GetMapping("/add")
    public String addForm(@ModelAttribute("item") ItemForm form){
        return "basic/shop/item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        // 검증에 실패한 경우 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/shop/item/addForm";
        }
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        // 데이터베이스에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setExplanation(form.getExplanation());
        item.setItemType(form.getItemType());
        item.setImageFiles(storeImageFiles);

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/item/{itemId}";
    }
    // 수정하기
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/shop/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "basic/shop/editForm";
        }
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        Item item = new Item();
        item.setId(form.getId());
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setExplanation(form.getExplanation());
        item.setItemType(form.getItemType());
        item.setImageFiles(storeImageFiles);

        itemRepository.update(itemId, item);
        return "redirect:/item/{itemId}";
    }
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
