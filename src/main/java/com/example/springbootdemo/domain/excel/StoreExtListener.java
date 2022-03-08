package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.springbootdemo.utils.JsonUtil;
import com.example.springbootdemo.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreExtListener extends AnalysisEventListener<StoreExt> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    List<StoreExt> list = new ArrayList<StoreExt>();

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(StoreExt storeExt, AnalysisContext analysisContext) {
        String financialCenterCode = storeExt.getFinancialCenterCode();
        storeExt.setFinancialCenterCode("0"+financialCenterCode);
        System.out.println("解析到一条数据: "  + JsonUtil.convertJsonString(storeExt));
        list.add(storeExt);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成！");
        System.out.println("开始处理");
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.batchUpdateStore(list);
    }
}
