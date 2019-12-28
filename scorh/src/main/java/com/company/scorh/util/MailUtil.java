package com.company.scorh.util;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private static Properties properties;
    private static Message msg;
    private static Transport transport;

    //初始化Mail信息
    public MailUtil(){
        properties = new Properties();
        properties.setProperty("mail.debug", "true");//调试模式发送
        properties.setProperty("mail.smtp.auth", "true");//身份验证设置
        properties.setProperty("mail.host", "smtp.qq.com");//发件人邮箱主机名
        properties.setProperty("mail.transport.protocol", "smtp");//发件协议

        Session session = Session.getInstance(properties);

        msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress("uestclcl@qq.com"));//设置发件人
            transport = session.getTransport();
            transport.connect("uestclcl@qq.com", "tfzmtueobjksggji");//设置发件人在该邮箱主机上的用户名密码
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     * @param address 邮箱地址
     * @param subject 短信主题
     * @param text 邮箱内容
     * @throws MessagingException 发送消息异常
     */
    public void sendMail(String address,String subject,String text) throws  MessagingException{
        msg.setContent(text,"text/html;charset=utf-8");
        msg.setSubject(subject);
        transport.sendMessage(msg, new Address[] {new InternetAddress(address)});
        transport.close();
    }


}
