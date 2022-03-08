package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class StoreExt {
    @ExcelProperty("k3store_code")
    private String code;
    @ExcelProperty("default_dc_code")
    private String defaultDcCode;
    @ExcelProperty("financial_center_code")
    private String financialCenterCode;
    @ExcelProperty("is_bread")
    private Integer isBread;
    @ExcelProperty("virtual_dc_code")
    private String virtualDcCode;
    @ExcelProperty("distribution_distance")
    private BigDecimal distributionDistance;
    @ExcelProperty("transfer_strategy")
    private String transferStrategy;
}
