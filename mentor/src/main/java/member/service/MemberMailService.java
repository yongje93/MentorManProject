package member.service;

import java.util.Random;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.Setter;
@Setter
@Service("mailService")
public class MemberMailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private int size;
	//인증 코드
	private String getKey(int size) {
		this.size = size;
		return getAuthCode();
	}
	
	private String getAuthCode() {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0; 
		
		while(buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}
		
		return buffer.toString();
	}
	
	public String mailSendWithUserKey(String member_email, String member_name) {
		//6자리 난수 인증번호 생성
		String authKey = getKey(6);
		//인증메일 보내기
		MimeMessage mail = javaMailSender.createMimeMessage();
		String mailContent = "<table class='body' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; height: 100%; width: 100%; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;' bgcolor='#fafafa'>"
	     + " <tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='center' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; float: none; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'>"
	        + "  <center style='width: 100%; min-width: 580px;'>"
	           + " <table align='center' class='spacer float-center' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; width: 100%; float: none; margin: 0 auto; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
	+ "<table align='center' class='top-header wrapper float-center' style='width: 100%; border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; float: none; margin: 0 auto; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 16px;'>"
	  + "<table align='center' class='container' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: inherit; width: 580px; margin: 0 auto; padding: 0;' bgcolor='#fafafa'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
	    + "<table class='collapse row' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; position: relative; display: table; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='width: 298px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
	        + "<a target='_blank' href='http://email.mg.itdaa.net/c/eJwNjLsKxCAQAL8mlqKrRi0srrnfCKubJUJeJHL-_gnTzBRDCZ0l0qImR-RxztFFTQsQe0C2o9DsTQlRs5uswvtelJYH1l1sicFEB5kDqMDssi_REuiSQZmsDYk9ba3d72Q-E3wHvXdZGyHKc23DxZOoUr_oDDDuJ_7WR5br-AMCAy1i' style='color: #ff2d55; font-family: -apple-system, blinkmacsystemfont, segoe ui, roboto, helvetica neue, arial, sans-serif, apple color emoji, segoe ui emoji, segoe ui symbol !important; font-weight: normal; text-align: left; line-: 1.4; text-decoration: none; margin: 0; padding: 0;' rel='noreferrer noopener'>"
	          + "<h1>멘토맨</h1>"
	+ "</a>      </th></tr></tbody></table></th><th align='left' colspan='1' rowspan='1' style='width: 298px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
	        + "<p align='right' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></p>"
	      + "</th></tr></tbody></table></th></tr></tbody></table>"
	  + "</td></tr></tbody></table>"
	+ "</td></tr></tbody></table>"
	            + "<table align='center' class='container float-center' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; width: 580px; float: none; margin: 0 auto; padding: 0;' bgcolor='#fefefe'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
	              + "<table class='row' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; position: relative; display: table; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='width: 564px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0 16px 16px;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
	                + "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
	+ "<h3 align='left' style='color: inherit; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: 600; line-height: 1.4; word-wrap: normal; font-size: 28px; margin: 0 0 10px; padding: 0;'>비밀번호를 잊으셨나요?</h3>"
	+ "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
	+ "<p align='left' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 0 10px; padding: 0;'>"
	  + "아래 링크를 클릭하여 비밀번호를 재설정하십시오."
	+ "</p>"
	+ "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
	+ "<table class='large expand button' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; margin: 0 0 16px; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: #fefefe; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0; border: 2px solid #ff2d55;' bgcolor='#ff2d55'><center style='width: 100%; min-width: 0;'><span class='float-center' align='center' style='color: #fefefe; font-family: -apple-system, blinkmacsystemfont, segoe ui, roboto, helvetica neue, arial, sans-serif, apple color emoji, segoe ui emoji, segoe ui symbol !important; font-weight: bold; text-align: center; line-: 1.4; text-decoration: none; font-size: 20px; display: inline-block; border-radius: 3px; : 100%; margin: 0; padding: 10px 0; border: 0 solid #ff2d55;'>"
	+ authKey + "</span></center></td></tr></tbody></table></td><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; visibility: hidden; width: 0; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></td></tr></tbody></table>"
	+ "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
	+ "<p align='left' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 0 10px; padding: 0;'>"
	  + "요청하지 않은 경우 이 이메일을 무시하십시오. 위 링크에 접속하여 새 비밀번호를 설정하기 전까지 비밀번호가 변경되지 않습니다."
	+ "</p>"
	                + "<hr>"
	+ "<small style='font-size: 80%; color: #cacaca;'>본 이메일은 발신 전용으로 회신되지 않습니다. 궁금한 사항은 고객센터로 문의해 주세요.<br> <strong>멘토맨</strong></small>"
	               + " </th><th align='left' colspan='1' rowspan='1' style='visibility: hidden; width: 0; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></th></tr></tbody></table></th></tr></tbody></table>"
	            + "</td></tr></tbody></table>"
	          + "</center>"
	       + " </td></tr>"
	    + "</tbody></table>";

		try {
			mail.setSubject("[멘토맨] 멘토맨 비밀 번호 찾기 서비스 입니다.", "utf-8");
			mail.setText(mailContent, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
			javaMailSender.send(mail);
		
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return authKey;
	}
	

}