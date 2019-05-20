package yummy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements  MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void cstRegisterConfirm(int id) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("498656288@qq.com");
            helper.setTo("498656288@qq.com");
            helper.setSubject("YUMMY注册验证");
            helper.setText("<html><body><p>点击<a href=\"http://localhost:8080/yummy/cstRegister/" +String.valueOf(id)+ "\">链接</a>完成邮件认证</p></body></html>", true);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
