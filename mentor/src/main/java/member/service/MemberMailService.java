package member.service;

import java.util.Random;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.Setter;
@Setter
@Service("mailService")
public class MemberMailService {

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
		String mailContent ="<body><div style='width:540px; height:600px;'>" 
				+ "<h1 style='margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;'>"
				+ "<span>멘토맨</span> 비밀번호 재설정 서비스 입니다."
				+ "</h1> <p style='font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;'>"
				+ "비밀번호 재설정을 원하실 경우 아래의 인증번호를 입력해주세요.<br /></p>"
				+ "<p style='font-size: 16px; margin: 40px 5px 20px; line-height: 28px;'>"
				+ "인증번호 :["+ authKey +"]<span style='font-size: 24px;'></span></p> "
				+ "<div style='border-top: 1px solid #DDD; padding: 5px;'>"
				+ "<p style='font-size: 13px; line-height: 21px; color: #555;'>본 이메일은 발신 전용으로 회신되지 않습니다. 궁금한 사항은 고객센터로 문의해 주세요.</p></div>"
				+ "</div></body>";
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