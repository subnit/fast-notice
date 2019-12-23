package com.subnit.fastnotice.util;

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
 *
 * @author subo177693
 * @date : create in 19:25 2019/12/21
 */
public class NoticeUtils {

    private static String dingMessageTemplate = "{\"msgtype\":\"text\",\"text\":{\"content\": \"%s\" },\"at\":{\"atMobiles\": \"[%s]\",\"isAtAll\":false}}";

    public static void sendEmail(String title, String emailContent, String email) throws MessagingException {

        String smtpHost = "smtp.163.com";
        String from = "subnit_notice@163.com";
        SmtpAuth auth = new SmtpAuth(userName, password);
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", smtpHost);
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.port", "25");
        Session ses = Session.getDefaultInstance(pro, auth);
        ses.setPasswordAuthentication(new URLName(smtpHost), auth.getPasswordAuthentication());
        MimeMessage message = new MimeMessage(ses);
        message.setFrom(new InternetAddress(from));
        String[] toList = email.trim().split(",");
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
        HttpClient4.getHttpClient().postBody(webHook, message, headerMap);
    }


}
