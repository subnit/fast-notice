package fastnotice.subnit.service.notice.method;

import fastnotice.subnit.util.SmtpAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * description:
 * date : create in 下午11:23 2019/12/25
 * modified by :
 *
 * @author subo
 */
@Service
public class EmailNoticeMethod implements NoticeMethod{
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

    @Override
    public Boolean sendNotice(String title, String content, String target) {
        SmtpAuth auth = new SmtpAuth(username, password);
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", smtpHost);
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.port", "25");
        Session ses = Session.getDefaultInstance(pro, auth);
        ses.setPasswordAuthentication(new URLName(smtpHost), auth.getPasswordAuthentication());
        MimeMessage message = new MimeMessage(ses);
        try {
            message.setFrom(new InternetAddress(from));
            String[] toList = target.trim().split(",");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
            for (String toAddress : toList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            }
            message.setSubject(title);
            message.setContent(content, "text/html;charset=gb2312");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
