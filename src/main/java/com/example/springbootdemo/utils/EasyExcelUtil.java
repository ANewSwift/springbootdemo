package com.example.springbootdemo.utils;

import com.alibaba.excel.EasyExcel;
import com.example.springbootdemo.domain.excel.StoreExt;
import com.example.springbootdemo.domain.excel.StoreExtListener;
import com.example.springbootdemo.domain.excel.StoreMaterialBlackListListener;
import com.example.springbootdemo.domain.excel.StoreMaterialBlacklist;

import java.io.File;

public class EasyExcelUtil {
    public static void main(String[] args) {
        EasyExcelUtil easyExcelUtil = new EasyExcelUtil();
//        easyExcelUtil.handleStoreMaterialBlackList();
//        easyExcelUtil.handleStoreDistance();
    }

    private void handleStoreDistance() {
//        String location = "C:\\Users\\admin\\Documents\\WXWork\\1688850952169629\\Cache\\File\\2022-03";
        String location = "C:\\Users\\admin\\Downloads";
        String fileName = "export_result (10).xlsx";
        String filePathName = location + File.separator +fileName;
        System.out.println(filePathName);
        EasyExcel.read(filePathName,
                StoreExt.class,
                new StoreExtListener()).sheet().doRead();
    }

    private void handleStoreMaterialBlackList() {
        String location = "C:\\Users\\admin\\Documents\\WXWork\\1688850952169629\\Cache\\File\\2022-03";
        String fileName = "0304需配置不订货的门店和物料.xlsx";
        String filePathName = location + File.separator +fileName;
        System.out.println(filePathName);
        EasyExcel.read(filePathName,
                StoreMaterialBlacklist.class,
                new StoreMaterialBlackListListener()).sheet().doRead();
    }

}
