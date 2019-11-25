package member.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
import member.handler.MailHandler;
import mentor.bean.MentorDTO;
/**
 * @Title : 회원가입 Service.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired 
	private MemberDAO memberDAO;
	@Inject
	private JavaMailSender mailSender;
	@Inject
	private PasswordEncoder passwordEncoder;
	
	
	/** @Title : 닉네임 중복확인.
	 * @author : ginkgo1928 @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return memberDAO.writeNicknamecheck(member_nickname);
	}
	
	/** @Title : 이메일 중복확인
	 * @author : ginkgo1928  @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return memberDAO.writeEmailCheck(member_email);
	}
	
	/* 회원가입 완료 */
	@Override
	public void write(Map<String, String> map) throws MessagingException, UnsupportedEncodingException {
		System.out.println("암호화 전 : " + map.get("member_pwd"));		
		String encPassword = passwordEncoder.encode(map.get("member_pwd"));
		System.out.println("암호화 후 : " + encPassword);
		map.replace("member_pwd", encPassword);
		// 암호화 후 디비에 저장
		memberDAO.write(map);
		
		// 임의의 authkey 생성
		String authKey = new member.handler.TempKey().getKey(50, false);
		// 인증키 db 저장
		memberDAO.createAuthKey(map.get("member_email"), authKey);
		// 메일 전송
		MailHandler sendMail = new MailHandler(mailSender);
		String mailContent = "<table class='body' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; height: 100%; width: 100%; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;' bgcolor='#fafafa'>"
			     + "<tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='center' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; float: none; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'>"
			     + "<center style='width: 100%; min-width: 580px;'>"
			     + "<table align='center' class='spacer float-center' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; width: 100%; float: none; margin: 0 auto; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
			     + "<table align='center' class='top-header wrapper float-center' style='width: 100%; border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; float: none; margin: 0 auto; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 16px;'>"
			     + "<table align='center' class='container' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: inherit; width: 580px; margin: 0 auto; padding: 0;' bgcolor='#fafafa'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
			     + "<table class='collapse row' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; position: relative; display: table; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='width: 298px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
			     + "<a href='#' style='color: #ff2d55; font-family: -apple-system, blinkmacsystemfont, segoe ui, roboto, helvetica neue, arial, sans-serif, apple color emoji, segoe ui emoji, segoe ui symbol !important; font-weight: normal; text-align: left; line-: 1.4; text-decoration: none; margin: 0; padding: 0;' rel='noreferrer noopener'>"
			     + "<h1>멘토맨</h1>"
			     + "</a></th></tr></tbody></table></th><th align='left' colspan='1' rowspan='1' style='width: 298px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
			     + "<p align='right' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></p>"
			     + "</th></tr></tbody></table></th></tr></tbody></table>"
			     + "</td></tr></tbody></table>"
			     + "</td></tr></tbody></table>"
			     + "<table align='center' class='container float-center' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: center; width: 580px; float: none; margin: 0 auto; padding: 0;' bgcolor='#fefefe'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
			     + "<table class='row' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; position: relative; display: table; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='width: 564px; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 auto; padding: 0 16px 16px;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><th align='left' colspan='1' rowspan='1' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'>"
			     + "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
				 + "<h3 align='left' style='color: inherit; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: 600; line-height: 1.4; word-wrap: normal; font-size: 28px; margin: 0 0 10px; padding: 0;'>이메일 인증하기</h3>"
				 + "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
				 + "<p align='left' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 0 10px; padding: 0;'>"
				 + "아래 링크를 클릭하여 이메일 인증을 완료하세요."
				 + "</p>"
				 + "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
				 + "<table class='large expand button' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; margin: 0 0 16px; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'><table style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; color: #fefefe; font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0; border: 2px solid #ff2d55;' bgcolor='#ff2d55'><center style='width: 100%; min-width: 0;'>"
				 + "<a class='float-center' href='http://localhost:8080/mentor/member/emailConfirm?member_email="
				 + map.get("member_email")+"&memberAuthKey="
				 + authKey + "' align='center' style='color: #fefefe; font-family: -apple-system, blinkmacsystemfont, segoe ui, roboto, helvetica neue, arial, sans-serif, apple color emoji, segoe ui emoji, segoe ui symbol !important; font-weight: bold; text-align: center; line-: 1.4; text-decoration: none; font-size: 20px; display: inline-block; border-radius: 3px; : 100%; margin: 0; padding: 10px 0; border: 0 solid #ff2d55;' rel='noreferrer noopener' target='_blank'>" 
			 	 + "이메일 인증"
			 	 + "</a></center></td></tr></tbody></table></td><td align='left' valign='top' style='word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; visibility: hidden; width: 0; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></td></tr></tbody></table>"
			 	 + "<table class='spacer' style='border-spacing: 0; border-collapse: collapse; vertical-align: top; text-align: left; width: 100%; padding: 0;'><tbody><tr align='left' style='vertical-align: top; padding: 0;'><td align='left' valign='top' height='16px' style='font-size: 16px; line-height: 16px; word-wrap: break-word; -webkit-hyphens: auto; -ms-hyphens: auto; hyphens: auto; border-collapse: collapse !important; mso-line-height-rule: exactly; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; margin: 0; padding: 0;'>&nbsp;</td></tr></tbody></table>"
				 + "<p align='left' style='color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0 0 10px; padding: 0;'>"
		 	 	 + "요청하지 않은 경우 이 이메일을 무시하십시오."
				 + "</p>"
		 		 + "<hr>"
				 + "<small style='font-size: 80%; color: #cacaca;'>본 이메일은 발신 전용으로 회신되지 않습니다. 궁금한 사항은 고객센터로 문의해 주세요.<br> <strong>멘토맨</strong></small>"
			     + "</th><th align='left' colspan='1' rowspan='1' style='visibility: hidden; width: 0; color: rgba(0,0,0,0.84); font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica Neue, Arial, sans-serif, Apple Color Emoji, Segoe UI Emoji, Segoe UI Symbol !important; font-weight: normal; line-height: 1.4; font-size: 15px; margin: 0; padding: 0;'></th></tr></tbody></table></th></tr></tbody></table>"
			     + "</td></tr></tbody></table>"
			     + "</center>"
			     + "</td></tr>"
			     + "</tbody></table>";
		
		sendMail.setSubject("[멘토맨 서비스 이메일 인증]");
		sendMail.setText(mailContent);
		sendMail.setFrom("yongje1211@gmail.com", "멘토맨 고객센터");
		sendMail.setTo(map.get("member_email"));
		sendMail.send();
	}
	
	// 이메일 인증키 검사
	@Override
	public MemberDTO checkAuthKey(MemberDTO memberDTO) {
		MemberDTO chkMember = new MemberDTO();
		chkMember = memberDAO.checkAuthKey(memberDTO);
		
		if(chkMember != null) {
			memberDTO.setMemberAuthStatus(1);
			memberDAO.updateMemberAuthState(memberDTO);
		}
		return chkMember;
	}
	
	/* 로그인 */
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}
	/* 나의 질문 답변 */
	@Override
	public List<MentorDTO> getQandA(Map<String, String> map) {
		return memberDAO.getQandA(map);
	}
	
	/* 비밀번호 찾기 */
	@Override
	public MemberDTO setmemberpwd(Map<String, String> map) {
		return memberDAO.setsetmemberpwd(map);
	}
	
	/** @Title : 이메일 인증을 하고 새로운 비밀번호로 변경
	 * @author : ginkgo1928
	 * @date : 2019. 11. 13. */
	@Override
	public MemberDTO newPwdCommit(Map<String, String> map) {
		return memberDAO.newPwdCommit(map);
	}
	
	/**
	 * Q&A페이징
	 */
	@Override
	public int getTotalA(String member_email) {
		return memberDAO.getTotalA(member_email);
	}
	
	/**
	 * Q&A 멘토 정도 및 질문 내용
	 */
	@Override
	public MentorDTO getMentor_info(Map<String, String> map) {
		return memberDAO.getMentor_info(map);
	}

	@Override
	public List<MentorDTO> getMentoring_type(Map<String, String[]> arrayMap) {
		return memberDAO.getMentoring_type(arrayMap);
	}

	@Override
	public void questionDelete(int question_seq) {
		memberDAO.questionDelete(question_seq);
	}


}

	

	


	

	