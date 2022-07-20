package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExcelExportTest {

    @ExcelProperty("ID")
    private Integer id;
    @ExcelProperty("名称0")
    private String name0;
    @ExcelProperty("名称1")
    private String name1;
    @ExcelProperty("名称2")
    private String name2;
    @ExcelProperty("名称3")
    private String name3;
    @ExcelProperty("名称4")
    private String name4;
    @ExcelProperty("名称5")
    private String name5;
    @ExcelProperty("名称6")
    private String name6;
    @ExcelProperty("名称7")
    private String name7;
    @ExcelProperty("名称8")
    private String name8;
    @ExcelProperty("名称9")
    private String name9;

    @ExcelProperty("地址0")
    private String address0;
    @ExcelProperty("地址1")
    private String address1;
    @ExcelProperty("地址2")
    private String address2;
    @ExcelProperty("地址3")
    private String address3;
    @ExcelProperty("地址4")
    private String address4;
    @ExcelProperty("地址5")
    private String address5;
    @ExcelProperty("地址6")
    private String address6;
    @ExcelProperty("地址7")
    private String address7;
    @ExcelProperty("地址8")
    private String address8;
    @ExcelProperty("地址9")
    private String address9;

    public static ExcelExportTest getEntity(int i, int j) {
        return ExcelExportTest.builder()
                .id(i)
                .name0("我是中国人我是中国人我是中国人我是中国人" + i)
                .name1("我是中国人我是中国人我是中国人我是中国人" + i)
                .name2("我是中国人我是中国人我是中国人我是中国人" + i)
                .name3("我是中国人我是中国人我是中国人我是中国人" + i)
                .name4("我是中国人我是中国人我是中国人我是中国人" + i)
                .name5("我是中国人我是中国人我是中国人我是中国人" + i)
                .name6("我是中国人我是中国人我是中国人我是中国人" + i)
                .name7("我是中国人我是中国人我是中国人我是中国人" + i)
                .name8("我是中国人我是中国人我是中国人我是中国人" + i)
                .name9("我是中国人我是中国人我是中国人我是中国人" + i)
                .address0("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address1("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address2("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address3("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address4("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address5("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address6("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address7("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address8("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .address9("北京市北京市通州区玉桥街道哈哈哈哈哈哈哈哈哈哈" + j)
                .build();
    }
}
