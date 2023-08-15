package shoppingmall.shopping_mall.itemService.item;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName; // 사용자가 저장한 사진 이름
    private String storeFileName; // uuid

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
