package com.example.springbootdemo.utils;

import com.example.springbootdemo.domain.excel.StoreExt;
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
    public static final String PROD_URL = "https://scm.pin-dao.cn/procurement/v1/store/update";
    public static final String TEST_URL = "https://scm-test.pin-dao.cn/procurement/v1/store/update";
    public static final String PROD_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImJlYjAzMzVmLWNkMTQtNGQ4NS1iYWYzLTI1M2Q0MTI1ODYzMyJ9.WVjyQBA0oBi4s8FzmxFShbZXl_npYEiOey_bey1CeqwB413PALnCWcWeVI6sL6hI99u23Kz6Kc0LCAtmFcQCvw";
    public static final String TEST_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjJkODRlM2Y4LWNkZmEtNGFlZi1iMTJkLWY4Zjk2NzRlOTIxNiJ9.xLaie14IXzKg3N6BWh2spFg7wJwx8jLUtkqWD3YTTrWWb1_BsJk4t3I_VcsD6xvJmwCZzNMg26XlZOiKLpbSCg";


    public static void main(String[] args) {
//        OkHttpUtil okHttpUtil = new OkHttpUtil();
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Authorization", TEST_TOKEN);
//        okHttpUtil.sendPostRequest(TEST_URL, headers, "{\"code\":\"TGHNSZ0058\",\"defaultDcCode\":\"HBBJ03\",\"financialCenterCode\":\"00.09\",\"isBread\":1,\"virtualDcCode\":\"HBBJ92\",\"distributionDistance\":66.8,\"transferStrategy\":\"DEFAULT\"}");
    }

    public void batchUpdateStore(List<StoreExt> storeExtList) {
        if (CollectionUtils.isEmpty(storeExtList)) {
            return;
        }
        storeExtList.forEach(storeExt -> {
            String body = JsonUtil.convertJsonString(storeExt);
            System.out.println(body);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", PROD_TOKEN);
            sendPostRequest(PROD_URL, headers, body);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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
                    .comment("批量操作:业务规则整体变更, 取消")
                    .build();
            String body = JsonUtil.convertJsonString(smBlackReq);
            System.out.println(body);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", TEST_TOKEN);
            sendPostRequest(TEST_URL, headers, body);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
