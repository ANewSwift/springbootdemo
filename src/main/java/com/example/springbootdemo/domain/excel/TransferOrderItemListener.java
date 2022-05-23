package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.springbootdemo.utils.JsonUtil;
import com.example.springbootdemo.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

public class TransferOrderItemListener extends AnalysisEventListener<TransferOrderItemRead> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    List<TransferOrderItemRead> list = new ArrayList<>();

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(TransferOrderItemRead transferOrderItemRead, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据: "  + JsonUtil.convertJsonString(transferOrderItemRead));
        list.add(transferOrderItemRead);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成！");
        System.out.println("开始处理");
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.batchCreateTransferOrder(list);
    }
}
