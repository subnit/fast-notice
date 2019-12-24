package com.subnit.fastnotice.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * description:
 * date : create in 下午10:26 2019/12/23
 * modified by :
 *
 * @author subo
 */
@Component
public class NoticeUtils {

    private static String dingMessageTemplate = "{\"msgtype\":\"text\",\"text\":{\"content\": \"%s\" },\"at\":{\"atMobiles\": \"[%s]\",\"isAtAll\":false}}";

    private static String username;

    private static String password;

    private static String smtpHost;

    private static String from;

    @Value("${notice.email.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    @Value("${notice.email.password}")
    public void setassword(String password) {
        this.password = password;
    }

    @Value("${notice.email.smtpHost}")
    public void setmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    @Value("${notice.email.from}")
    public void setrom(String from) {
        this.from = from;
    }

    public static void sendEmail(String title, String emailContent, String email) throws MessagingException {

        SmtpAuth auth = new SmtpAuth(username, password);
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", smtpHost);
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.port", "25");
        Session ses = Session.getDefaultInstance(pro, auth);
        ses.setPasswordAuthentication(new URLName(smtpHost), auth.getPasswordAuthentication());
        MimeMessage message = new MimeMessage(ses);
        message.setFrom(new InternetAddress(from));
        String[] toList = email.trim().split(",");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
        for (String toAddress : toList) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        }
        message.setSubject(title);
        message.setContent(emailContent, "text/html;charset=gb2312");
        Transport.send(message);
    }


    public static void sendDingMessage(String webHook, String content, String atMobiles) {
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
        //HttpClient4.getHttpClient().postBody(webHook, message, headerMap);
    }


}
