package com.example.springbootdemo.utils;

import com.alibaba.excel.EasyExcel;
import com.example.springbootdemo.domain.excel.StoreMaterialBlackListListener;
import com.example.springbootdemo.domain.excel.StoreMaterialBlacklist;

import java.io.File;

public class EasyExcelUtil {
    public static void main(String[] args) {
        String location = "C:\\Users\\admin\\Documents\\WXWork\\1688850952169629\\Cache\\File\\2022-03";
        String fileName = "0304需配置不订货的门店和物料.xlsx";
        String filePathName = location + File.separator +fileName;
        System.out.println(filePathName);
        EasyExcel.read(filePathName,
                StoreMaterialBlacklist.class,
                new StoreMaterialBlackListListener()).sheet().doRead();
    }

}
