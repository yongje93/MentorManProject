package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kakao.controller.KakaoApi;
import member.bean.MemberDTO;
import member.bean.QandAPaging;
import member.service.MemberMailService;
import member.service.MemberService;
import mentor.bean.MentorDTO;
import naver.controller.NaverLoginBO;


/**
 * @Title : 회원가입 컨트롤.
 * @author : ginkgo1928
 * @date : 2019. 11. 1.
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {
	private NaverLoginBO naverLoginBO;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private MemberMailService mailService; 
	@Autowired
	private QandAPaging QandAPag;
	
	
	// WriteForm 화면
	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}
	
	/** @Title : 닉네임 중복확인.
	 * @author : ginkgo1928  @date : 2019. 11. 1*/
	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeNicknamecheck(@RequestParam String member_nickname, Model model) {
		memberDTO = memberService.writeNicknamecheck(member_nickname);
		if (memberDTO == null)
			return "exist";
		else
			return "not_exist";
	}

	/** @Title : 이메일 중복확인.
	 * @author : ginkgo1928  @date : 2019. 11. 1.*/
	@RequestMapping(value = "writeEmailCheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeEmailCheck(@RequestParam String member_email, Model model) {
		memberDTO = memberService.writeEmailCheck(member_email);
		if (memberDTO == null)
			return "email_ok";
		else
			return "email_fail";
	}

	/**
	 * @Title : 회원가입 완료 & 프로필 이미지 storage 연결.
	 * @author : ginkgo1928
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @date : 2019. 11. 7.
	 * 2019. 11. 13 용제 수정
	 */
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_profile, Model model) throws UnsupportedEncodingException, MessagingException {
		//회원 이메일 폴더가 자동생성으로 생성된게 아니라 회원이메일 폴더 만들어주고 넣어야 한다.
		String filePath="C:/github/MentorMan/mentor/src/main/webapp/storage/"+map.get("member_email");
		String fileName = member_profile.getOriginalFilename();
		System.out.println("프로필 이미지 파일명: " + fileName);
		// 폴더만들기
		File filemake = new File(filePath);
		if(!filemake.exists()) {
			filemake.mkdirs();
		}
		// 파일명이 있을때 이미지 저장
		if(fileName != "") {
			File file = new File(filePath, fileName);
			try {
				FileCopyUtils.copy(member_profile.getInputStream(), new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("member_profile", fileName);
		memberService.write(map);
		model.addAttribute("member_email", map.get("member_email"));
		model.addAttribute("display", "/member/write.jsp");
		return "/main/index";
	}
	
	// 이메일 인증 코드 검증
	@RequestMapping(value = "emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(@ModelAttribute MemberDTO memberDTO, Model model) {
		MemberDTO chkMember = memberService.checkAuthKey(memberDTO);
		if(chkMember == null) { // 이메일+인증키로 맞는 회원이 없음
			model.addAttribute("member_email", memberDTO.getMember_email());
			model.addAttribute("display", "/member/write.jsp");
			return "/main/index";
		} else {
			model.addAttribute("member_email", memberDTO.getMember_email());
			model.addAttribute("display", "/member/emailOk.jsp");
			return "/main/index";
		}
	}
	
	// LoginForm
	/**
	 * @Title : 카카오 로그인 + 네이버 로그인  url 추가 
	 * @Author : yong
	 * @Date : 2019. 11. 16.
	 * @Method Name : loginForm
	 */
	@RequestMapping(value = "loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm(Model model, HttpSession session, @RequestParam(required = false) String status) {
		// 카카오 url
		String kakaoUrl = KakaoApi.getAuthorizationUrl(session);
		// 네이버 url
		String naverUrl = naverLoginBO.getAuthorizationUrl(session);
		
		model.addAttribute("status", status);
		model.addAttribute("kakaoUrl", kakaoUrl);
		model.addAttribute("naverUrl", naverUrl);
		model.addAttribute("display", "/member/loginForm.jsp");
		return "/main/index";
	}


	/** @Title : 로그인 처리,세션 기간 설정(1일 유지).
	 * @author : ginkgo1928 @date : 2019. 11. 09. 
   	2019. 11. 13 용제 수정*/
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	@ResponseBody
//	public String login(@RequestParam String member_email, @RequestParam String member_pwd, HttpSession session) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("member_email", member_email);
//		map.put("member_pwd", member_pwd);
//		MemberDTO memberDTO = memberService.login(map);
//		
//		if (memberDTO != null) {
//			memberDTO.setMember_pwd("");
//			session.setAttribute("memDTO", memberDTO);
//			session.setMaxInactiveInterval(60*60*24); // 세션 1일 유지
//			return "login_ok";
//		} else {
//			return "login_fail";
//		}
//	}
	
	// 로그아웃 처리 
	// 카카오 로그아웃 추가
//	@RequestMapping(value = "logout", method = RequestMethod.GET, produces="application/json")
//	public ModelAndView logout(HttpSession session) {
//		KakaoApi.kakaoLogout((JsonNode) session.getAttribute("access_token"));
//	  	session.invalidate();
//		return new ModelAndView("redirect:/main/index");
//	}

	/**
	 * @Title : 질문 답변
	 * @Author : kujun95, @Date : 2019. 11. 18.
	 */
	@RequestMapping(value = "myQandA", method = RequestMethod.GET)
	public String myQandA(@RequestParam int pg ,Model model, HttpSession session){
		
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		int endNum = pg*3;
		int startNum = endNum-2;
		Map<String, String> map = new HashMap<String, String>();
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		map.put("member_email", memberDTO.getMember_email());
		List<MentorDTO> list = memberService.getQandA(map);
		int totalA = memberService.getTotalA(memberDTO.getMember_email());
		QandAPag.setCurrentPage(pg);
		QandAPag.setPageBlock(3);
		QandAPag.setPageSize(3);
		QandAPag.setTotalA(totalA);
		QandAPag.makePagingHTML();
		model.addAttribute("pg", pg);
		model.addAttribute("QandAPag", QandAPag);
		model.addAttribute("list", list);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/member/myQandA.jsp");
		return "/main/index";
	}
	/**
	 * 나의 질문
	 * @Title : 메소드 간단히 설명
	 * @Author : kujun95, @Date : 2019. 11. 19.
	 */
	@RequestMapping(value = "myQuestionsForm", method = RequestMethod.GET)
	public String myQuestionsForm(@RequestParam int seq, @RequestParam int pg, @RequestParam int qsseq, Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", memberDTO.getMember_email());
		map.put("mentor_seq", seq+"");
		map.put("question_seq", qsseq+"");
		MentorDTO mentorDTO = memberService.getMentor_info(map);
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> arrayMap = new HashMap<String, String[]>();
		arrayMap.put("mentoring_code", mentoringArray);
		List<MentorDTO> list = memberService.getMentoring_type(arrayMap);
		
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("qsseq", qsseq);
		model.addAttribute("list", list);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("display", "/member/myQuestionsForm.jsp");
		return "/main/index";
	}
	
	/** @Title : 계정설정 화면.
	 * @author : ginkgo1928  @date : 2019. 11. 10.*/
	@RequestMapping(value = "modifyForm", method = RequestMethod.GET)
 	public String modifyForm(Model model) {
		model.addAttribute("display", "/member/modifyForm.jsp");
		return "/main/index";
 	}
	/** @Title : 비밀번호 재설정.
	 * @author : ginkgo1928  @date : 2019. 11. 12.*/
	@RequestMapping(value ="setpwdForm", method = RequestMethod.GET)
	public String setpwdForm(Model model) {
		model.addAttribute("display","/member/setpwdForm.jsp");
		return "/main/index";	
	}
	/** @Title : 비밀번호 재설정(회원정보 입력 후  회원여부 확인하고 메일 발송,인증번호 유효시간 3분)
	 * @author : ginkgo1928 @date : 2019. 11. 12. */
	@RequestMapping(value = "setmemberpwd", method = RequestMethod.POST)
	@ResponseBody
	public String setmemberpwd(@RequestParam String member_name, String member_email, HttpServletRequest request,
			HttpServletResponse response,Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_name", member_name);
		map.put("member_email", member_email);
		memberDTO = memberService.setmemberpwd(map);
		if (memberDTO != null) {
			//인증 코드 생성
			String auauthKey=mailService.mailSendWithUserKey(member_email, member_name);
			Cookie cookie = new Cookie("Cookie_Email", auauthKey);
			cookie.setMaxAge(60 * 3);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "get_member";
		} else {
			return "not_member";
		}
	}
	/** @Title : 메일을 발송한 인증값과 맞는지 확인.
	 * @author : ginkgo1928 @date : 2019. 11. 13. */
	@RequestMapping(value = "setmemberpwdrandom", method = RequestMethod.POST)
	@ResponseBody
	public String setmemberpwdrandom(@RequestParam int set_pwdrandom, HttpServletRequest request) {		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Cookie_Email")) {
					if (Integer.parseInt(cookie.getValue()) == set_pwdrandom) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						return "set_randomOk";
					}
				}
			}
		} else {

		}
		return "set_randomFail";
	}
	
	/** @Title : 새로운 비밀번호 화면을 show 활성화 후 비밀번호 변경
	 * @author : ginkgo1928 @date : 2019. 11. 13. */
	@RequestMapping(value = "newPwdCommit", method=RequestMethod.POST)
	@ResponseBody
	public void newPwdCommit(@RequestParam String member_name,String member_email,String member_pwd,Model model) {	
		Map<String, String>map=new HashMap<String, String>();
		map.put("member_email",member_email);
		map.put("member_name",member_name);
		map.put("member_pwd",member_pwd);
		memberService.newPwdCommit(map);	
	}
	

	/**
	 * @Title : 질문 삭제
	 * @Author : kujun95, @Date : 2019. 11. 20.
	 */
	@RequestMapping(value = "questionDelete", method=RequestMethod.POST)
	@ResponseBody
	public void questionDelete(@RequestParam int question_seq) {
		memberService.questionDelete(question_seq);
	}
	
	
}

