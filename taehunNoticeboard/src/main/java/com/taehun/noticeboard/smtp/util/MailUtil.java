package com.taehun.noticeboard.smtp.util;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    private final JavaMailSender javaMailSender;

    public boolean sendMail(String ToEmail, String code) {
        try {
            MimeMessage message = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject("Code Crews 이메일 인증 안내");
            helper.setTo(ToEmail);
            helper.setFrom("swseokitec@gmail.com");
            String BODY = String.join(System.getProperty("line.separator"), "<head>\n        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n        <title>Code Crews 이메일 인증 안내</title>\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n    </head>\n\n    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"550px\" style=\"border: 1px solid #d7d7d7; border-radius: 20px; text-align: center; font-family:'Malgun Gothic', '맑은 고딕'; letter-spacing: -0.04em; color: #333333;\">\n        <tr>\n            <td style=\"width: 40px;\"></td>\n            <td>\n                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"470px;\">\n                    <tr><td style=\"height: 60px;\"></td></tr>\n                    <tr><td style=\"height: 18px;\"></td></tr>\n                    <tr><td><span style=\"font-size: 32px; font-weight: bold;\">Code Crews 이메일 인증 안내</span></td></tr>\n                    <tr><td style=\"height: 34px;\"></td></tr>\n                    <tr><td style=\"height: 1px; background: #eaeaea;\"></td></tr>\n                    <tr><td style=\"height: 30px;\"></td></tr>\n                    <tr>\n                        <td>\n                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"340px;\" style=\"margin: 0 auto;\">\n                                <tr>\n                                    <td>\n                                        <span style=\"font-size: 14px;\">\n                        \"사용자 [" + ToEmail + "]님에 대한 인증 코드는 [" + code + "] 입니다. 인증 코드란에 정확히 기입해주세요.                                        </span>\n                                    </td>\n                                </tr>\n<tr><td style=\"height: 30px;\"></td></tr>                            </table>\n                        </td>\n                    \n                </table>\n            </td>\n            <td style=\"width: 40px;\"></td>\n        </tr>\n    </table>", "<p></p>");
            helper.setText(BODY, true);
            this.javaMailSender.send(message);
            return true;
        } catch (Exception var6) {
            Exception e = var6;
            e.printStackTrace();
            return false;
        }
    }

    public MailUtil(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}