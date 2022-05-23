package com.example.springbootdemo.utils;

import com.example.springbootdemo.domain.excel.*;
import com.example.springbootdemo.entity.TransferItemRequest;
import com.example.springbootdemo.entity.TransferRequest;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import okhttp3.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class OkHttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String PROD_URL = "http://scm.pin-dao.cn/procurement/v1/template/batchUpdateItemByIdSelective";
    public static final String TEST_URL = "http://scm-test.pin-dao.cn/procurement/v1/template/batchUpdateItemByIdSelective";
    public static final String PROD_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6Ijg0OGE4NzExLWExM2YtNDQzMC04NDJlLTFjYTNjOWU3NzZlMiJ9.xdQfySPZj4Bl2AmzeoCpdvCEiuyG9eWsVTfgHh8s0XWa12xmAaEPNybCxVbxv1ndcIz9pae8_VzxdON7GQeUNA";
    public static final String TEST_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjFhNjJlYmE2LWM5MDYtNGY2OS05YjcxLWViZTc5ZTEzMTBjMiJ9.qWLAJkus_dzJzzSuyCEFEz3u-NWN52I8I1f8XMH9Yh9Bn5Srso3D5qtEBSYHaUhHFd52t29AiUGVJ8sUMCCGGg";


    public static void main(String[] args) {
        Multiset<String> multiSet = HashMultiset.create(Arrays.asList("5010100201","5010100301","5010100401","5010100501","5010100601","5020100101","5020100301","5020200201","5020300101","5020300401","5020400101","5020500301","5020500401","5020500501","5030200201","5030200401","5030400201","5030400701","5030401101","5030410003","5030500302","5030500501","5030500601","5030500701","5030500801","5030501101","5030600101","5030600301","5030600901","5030601301","5030601501","5030601601","5030700101","5030700102","5030700301","5030700401","5030700601","5030700801","5030701201","5030701301","5030701401","5030800501","5030800601","5030800701","5030800901","5030801301","5030801501","5030801701","5030900301","5030900401","5050200301","5050200601","5050200901","5050201401","5050201001","5080100601","5080100801","5080101901","5080200201","5080200301","5080200801","5080201801","5080202301","5080202501","5080400101","5080400201","5080500301","5080500401","5080600101","5080600201","5080700201","5080700501","5080800101","5080800201","5030700451","5030700452","5020509001","5050201701","5050200802","5030501401","5030501601","5030602301","5030600702","5030802002","5010201201","5010201401","5010201402","5010201403","5080201702","5080801102","5080800902","5010100101","5030900601","5030900701","5030901201","5030501001","5030511008","5030300101","5990100401","5990100402","5990100403","5030501801","5030501803","5990200503","5030900202","5020600103","5050301502","5030801401","5080500101","5010200301","5010200401","5010200901","5060200201","5060200211","5030800401","5030800300","5030800301","5030602001","5030800201","5030801202","5080202201","5010300301","5030613001","5030601702","5050200201","5050200101","5010300402","5010300502","5010301102","5010300503","5010301101","5010301201","5010301105","5010301202","5010301203","5010301204","5010301205","5010301104","5010300201","5030600401","5030800101","5070301101","5070301201","5070301301","5070301401","5010100902","5070100101","5030501201","5060100101","5060100301","5030801101","5030900901","5050100301","5050100401","5050100501","5050100601","5050100801","5050100901","5070202909","5070202910","5060300302","5030901001","5060300111","5060201201","5060300801","5060301001","5070100102","5070301501","5030720006","5030803003","5050200701","5050200801","5050201501","5050201601","5060200101","5060200301","5060200501","5060200601","5060200701","5060200801","5060300601","5070100201","5070200501","5080201201","5080201501","5080200601","5080202001","5080202101","5080202201","5990200101","5990200201","5990200301","5990200401","5990300501","5990400301","5990400501","5990500101","5990500201","5990500301","5990700301","5990700401","5990700501","5080800802","5050201901","5020300501","5060200102","5050300403","5990700101","5080300602","5990600511","5990700201","5050301501","5080300201","5080300301","5080700401","5050300801","5060201102","5070203501","5030800102","5070202502","5080801202","5030803049","5030803052","5990100904","5030200106","5010110201","5070200512","5070200513","5050301504","5050301505","5030800202","5080202801","5080202802","4060800604","5990201005","5990201006","5030501807","5010100903","5030802001","5030601201","5080800701","5030400601","5050200401","5050200501","5050400401","5080100101","5080100201","5080100501","5080101101","5080101401","5080101501","5080101601","5080101701","5080200101","5080300401","5080300501","5080801001","5990100101","5070200502","5070200505","5030602402","5050201304","5050201305","5030910200","5070201101","5070200104","5070200105","5990200605","5990200504","5990200505","5020300402","5080800401","5050300402","5030802006","5050201302","5050201303","5030513001","5030513002","5990100301","5030613001","5050302101","5030500303","5010100102","5030900501","5990100804","5030802005","5050200502","5030801403","5070301603","5070200509","5030400501","5050300405","5050300503","5030602501","5030609202","5030706903","6010000013","5020510101","5050202201","5050202202","5080500701","5080500801","5080500901","5060301101","5030601801","5030601701","5030200104","5990400102","5990600601","5010100901"));
        multiSet.entrySet().forEach(m -> {
            if (m.getCount() > 1) {
                System.out.println(m.getElement());
            }
        });
        LocalDate yyyyMMdd = LocalDate.parse("20220522", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime localDateTime = yyyyMMdd.atTime(LocalTime.MIN);
        System.out.println(localDateTime);
//        OkHttpUtil okHttpUtil = new OkHttpUtil();
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Authorization", TEST_TOKEN);
//        okHttpUtil.sendPostRequest(TEST_URL, headers, "{\"code\":\"TGHNSZ0058\",\"defaultDcCode\":\"HBBJ03\",\"financialCenterCode\":\"00.09\",\"isBread\":1,\"virtualDcCode\":\"HBBJ92\",\"distributionDistance\":66.8,\"transferStrategy\":\"DEFAULT\"}");
    }

    public void batchUpdateTempItemMaxQuantity(List<TemplateItem> templateItems) {
        List<List<TemplateItem>> partitions = Lists.partition(templateItems, 100);
        partitions.forEach(items -> {
            String body = JsonUtil.convertJsonString(items);
            System.out.println(body);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", PROD_TOKEN);
            sendPostRequest(PROD_URL, headers, body);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public void batchCreateTransferOrder(List<TransferOrderItemRead> transferOrderItems) {
        if (CollectionUtils.isEmpty(transferOrderItems)) {
            return;
        }
        //
        Map<String, List<TransferOrderItemRead>> byCodeMap = transferOrderItems.stream().collect(Collectors.groupingBy(TransferOrderItemRead::getCode));
        List<TransferRequest> transferRequests = new ArrayList<>(byCodeMap.size());
        byCodeMap.forEach((code,items) -> {
            List<TransferItemRequest> transferItemRequests = items.stream().map(item -> new TransferItemRequest(
                    item.getMaterial_code(), item.getRequired_quantity(), item.getReason())).collect(Collectors.toList());
            TransferOrderItemRead transferOrderItemRead = items.get(0);
            TransferRequest transferRequest = TransferRequest.builder()
                    .provideStoreCode(transferOrderItemRead.getProvide_k3store_code())
                    .demandStoreCode(transferOrderItemRead.getDemand_k3store_code())
                    .expectArrivalTime("2022-05-18 16:00:00")
                    .comment("反向建调拨单(原因:物料禁用修复)")
                    .items(transferItemRequests)
                    .build();
            transferRequests.add(transferRequest);

        });
        transferRequests.forEach(transferRequest -> {
            String body = JsonUtil.convertJsonString(transferRequest);
            System.out.println(body);
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Authorization", PROD_TOKEN);
            sendPostRequest(PROD_URL, headers, body);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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
