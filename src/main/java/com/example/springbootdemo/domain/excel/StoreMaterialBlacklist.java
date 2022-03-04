package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StoreMaterialBlacklist {
    @ExcelProperty("store_code")
    private String k3storeCode;
    @ExcelProperty("sku_code")
    private String materialCode;
}
