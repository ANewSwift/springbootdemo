package com.example.springbootdemo.utils;

import com.example.springbootdemo.domain.excel.StoreMaterialBlacklist;
import com.example.springbootdemo.domain.excel.StoreMaterialBlacklistRequest;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class OkHttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "https://scm.pin-dao.cn/procurement/v1/storeBlacklist/batchInsert";
    public static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjZkOGZkNzJkLTQ5MTktNGJiMS04MWNlLThhNzRiNmQ1YzcwNyJ9.Xm1QmuFn5Apo7SIBmfN8FZReh-Dc903DY8UBwXmOvHqTBmKu2_OgkzjIvOYOYsY-33PrKQlFe873N0cgqjPVyQ";


    public static void main(String[] args) {

    }

    public void batchInsertStoreMaterialBlackList(List<StoreMaterialBlacklist> storeMaterialBlacklists) {
        if (CollectionUtils.isEmpty(storeMaterialBlacklists)) {
            return;
        }
        Map<String, List<StoreMaterialBlacklist>> storeBlackMap = storeMaterialBlacklists.stream().collect(
                Collectors.groupingBy(StoreMaterialBlacklist::getK3storeCode));
        storeBlackMap.forEach((k3storeCode, blackList) -> {
            List<String> materialCodes = blackList.stream().map(
                    StoreMaterialBlacklist::getMaterialCode).distinct().collect(Collectors.toList());
            StoreMaterialBlacklistRequest smBlackReq = StoreMaterialBlacklistRequest.builder()
                    .k3storeCodes(Collections.singletonList(k3storeCode))
                    .materialCodes(materialCodes)
                    .comment("批量操作:业务规则整体变更")
                    .build();
            String body = JsonUtil.convertJsonString(smBlackReq);
            System.out.println(body);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", TOKEN);
            sendPostRequest(URL, headers, body);
        });


    }

    public void batchSendPostRequest(String url, Map<String, String> headers, List<String> bodyList) {
        if (CollectionUtils.isEmpty(bodyList)) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        bodyList.forEach(body ->{
            RequestBody requestBody = RequestBody.create(JSON, body);
            Request request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //请求失败的处理
                    System.out.println("fail: " + e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //请求成功返回结果
                    //如果希望返回的是字符串
                    final String responseData = response.body().string();
                    System.out.println("success: " + responseData);
                }
            });
        });
    }

    public void sendPostRequest(String url, Map<String, String> headers, String body) {
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的处理
                System.out.println("fail: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功返回结果
                //如果希望返回的是字符串
                final String responseData = response.body().string();
                System.out.println("success: " + response.isSuccessful() + ", " +responseData);
            }
        });
    }

    private void sendGetRequest(String url) {
        //创建okHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建request,首先要有一个url
        Request request = new Request.Builder().url(url).build();
        //通过request的对象去构造得到一个Call对象，
        // 类似于将你的请求封装成了任务，既然是任务，就会有execute()和cancel()等方法。
        Call call = okHttpClient.newCall(request);
        //以异步的方式去执行请求,调用的是call.enqueue，将call加入调度队列，
        // 然后等待任务执行完成，我们在Callback中即可得到结果。
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的处理
                System.out.println("fail: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功返回结果
                //如果希望返回的是字符串
                final String responseData = response.body().string();
                System.out.println("success: " + responseData);
//                //如果希望返回的是二进制字节数组
//                byte[] responseBytes = response.body().bytes();
//                //如果希望返回的是inputStream,有inputStream我们就可以通过IO的方式写文件.
//                InputStream responseStream = response.body().byteStream();
            }
        });
        /*//上面用到的enqueue是异步的方式，当然也可以同步，
        //同步--Call有一个execute()方法，你也可以直接调用call.execute()通过返回一个Response。
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                //同步方式下得到返回结果
                String responseByExecute = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
