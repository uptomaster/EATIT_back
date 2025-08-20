package com.bapseguen.app.dto.view;

import java.util.List;

public class StoreDetailDTO {
    private String businessNumber;
    private String storeName;
    private String storeAddress;
    private String storeTel;

    private List<ItemSimpleDTO> items; // 음식/재료 목록 (간단 정보)
}
