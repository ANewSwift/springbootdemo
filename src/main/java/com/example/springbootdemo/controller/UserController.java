package com.example.springbootdemo.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo.domain.excel.ExcelExportTest;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haoshaolong
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("create")
    public Boolean create(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("batchCreate")
    public Boolean batchCreate(@RequestBody List<User> users) {
        return userService.saveBatch(users);
    }

    @GetMapping("getById")
    public User getById(Long id) {
        return userService.getById(id);
    }

    @PostMapping("page")
    public IPage<User> page() {
        return userService.page(new Page<>());
    }

    @PostMapping("updateById")
    public Boolean updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    @PostMapping("deleteByIds")
    public Boolean deleteByIds(@RequestBody List<Long> ids) {
        return userService.removeByIds(ids);
    }

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        String fileName = URLEncoder.encode(String.format("%s.xlsx", "订单支付数据"),
                StandardCharsets.UTF_8.toString());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ExcelWriter writer = new ExcelWriterBuilder()
                .autoCloseStream(true)
                .file(response.getOutputStream())
                .head(ExcelExportTest.class)
                .build();
        // xlsx文件上上限是104W行左右,这里如果超过104W需要分Sheet
        WriteSheet writeSheet = new WriteSheet();
        for (int i=0; i<100; i++) {
            writer.write(getData(), writeSheet);
        }
        writer.finish();
    }

    private List<ExcelExportTest> getData() {
        List<ExcelExportTest> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add(ExcelExportTest.getEntity(i,i+10));
        }
        return list;
    }
}
