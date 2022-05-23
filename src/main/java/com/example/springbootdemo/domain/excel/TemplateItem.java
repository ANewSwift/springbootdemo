package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TemplateItem {
    @ExcelProperty("模板条目Id")
    private Long id;
    @ExcelProperty("更新值")
    private BigDecimal maxQuantity;
}
