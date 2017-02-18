package com.sttx.bookmanager.util.email;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

import com.sttx.bookmanager.po.User;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class EmailUtil {
    public static String sendEmail(User user, HttpServletRequest request) throws MessagingException, IOException {
        /**
         * 发邮件 准备配置文件！
         */
        Properties props = new Properties();
        try {
            props.load(EmailUtil.class.getClassLoader().getResourceAsStream("email_template.properties"));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } // 获取配置文件内容
        String host = props.getProperty("host");// 获取服务器主机
        String uname = props.getProperty("uname");// 获取用户名
        String pwd = props.getProperty("pwd");// 获取密码
        String from = props.getProperty("from");// 获取发件人
        String to = user.getUserEmail();// 获取收件人
        // String to = "873692191@qq.com";// 获取收件人
        String subject = props.getProperty("subject");// 获取主题
        String content = props.getProperty("content");// 获取邮件内容
        content = MessageFormat.format(content, user.getUserCode());// 替换{0}

        Session session = MailUtils.createSession(host, uname, pwd);// 得到session
        Mail mail = new Mail(from, to, subject, content);// 创建邮件对象
        MailUtils.send(session, mail);// 发邮件

        /**
         * 网络正常，发送邮件成功了 1. 保存成功信息 2. 转发到msg.jsp
         * <meta http-equiv="Refresh" content="3;url=http://${requestScope.tz}">
         * 设置响应头，3秒后跳转
         */
        request.setAttribute("msg", "注册成功！请前往您的邮箱点击激活链接激活。3秒后自动跳转到邮箱登录页面......");

        String email = user.getUserEmail();
        request.setAttribute("user", user);
        request.setAttribute("tz", "mail." + email.substring(email.lastIndexOf("@") + 1));
        return "";
    }
}
