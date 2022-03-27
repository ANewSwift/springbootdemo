package com.example.springbootdemo.domain.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.springbootdemo.utils.JsonUtil;
import com.example.springbootdemo.utils.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class StoreMaterialBlackListListener extends AnalysisEventListener<StoreMaterialBlacklist> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<StoreMaterialBlacklist> list = new ArrayList<StoreMaterialBlacklist>();

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(StoreMaterialBlacklist storeMaterialBlacklist, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据: "  + JsonUtil.convertJsonString(storeMaterialBlacklist));
        list.add(storeMaterialBlacklist);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成！");
        System.out.println("开始处理");
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.batchInsertStoreMaterialBlackList(list);
    }
}
