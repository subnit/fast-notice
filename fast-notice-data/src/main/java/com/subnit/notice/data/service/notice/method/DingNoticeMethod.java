package com.subnit.notice.data.service.notice.method;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * date : create in 下午11:28 2019/12/25
 * modified by :
 *
 * @author subo
 */
@Service("ding")
public class DingNoticeMethod implements NoticeMethod{

    private static String dingMessageTemplate = "{\"msgtype\":\"text\",\"text\":{\"content\": \"%s\" },\"at\":{\"atMobiles\": \"[%s]\",\"isAtAll\":false}}";

    @Override
    public Boolean sendNotice(String title, String content, String target) {
        JSONObject jo = JSONObject.parseObject(target);
        String atMobiles = jo.getString("atMobiles");
        String webHook = jo.getString("webHook");
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        String message = String.format(dingMessageTemplate, content, atMobiles);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(webHook);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        StringEntity requestEntity = new StringEntity(message,"UTF-8");

        httpPost.setEntity(requestEntity);
        try {
            CloseableHttpResponse response=httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
