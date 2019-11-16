package member.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

import kakao.controller.KakaoApi;
import member.bean.MemberDTO;
import member.service.MemberService;

/**
 * @Title : 회원가입 컨트롤.
 * @author : ginkgo1928
 * @date : 2019. 11. 1.
 */
@Controller
@RequestMapping(value = "member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;

	// WriteForm 화면
	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		model.addAttribute("display", "/member/writeForm.jsp");
		return "/main/index";
	}

	/**
	 * @Title : 닉네임 중복확인.
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 */
	@RequestMapping(value = "writeNicknamecheck", method = RequestMethod.POST)
	@ResponseBody
	public String writeNicknamecheck(@RequestParam String member_nickname, Model model) {
		memberDTO = memberService.writeNicknamecheck(member_nickname);
		if (memberDTO == null)
			return "exist";
		else
			return "not_exist";
	}

	/**
	 * @Title : 이메일 중복확인.
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 */
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
	 * @date : 2019. 11. 7.
	 * 2019. 11. 13 용제 수정
	 */
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(@RequestParam Map<String, String> map, @RequestParam MultipartFile member_profile, Model model) {
		//회원 이메일 폴더가 자동생성으로 생성된게 아니라 회원이메일 폴더 만들어주고 넣어야 한다.
		String filePath="C:/Users/yong/Documents/GitHub/MentorMan/mentor/src/main/webapp/storage/"+map.get("member_email");
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

	// LoginForm
	/**
	 * @Title : 카카오 로그인 + 네이버 로그인  url 추가
	 * @Author : yong
	 * @Date : 2019. 11. 16.
	 * @Method Name : loginForm
	 */
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String loginForm(Model model, HttpSession session) {
		// 카카오 url
		String kakaoUrl = KakaoApi.getAuthorizationUrl(session);
		model.addAttribute("kakaoUrl", kakaoUrl);
		model.addAttribute("display", "/member/loginForm.jsp");
		return "/main/index";
	}
	/**
	 * @Title : 로그인 처리.
	 * @author : ginkgo1928
	 * @date : 2019. 11. 1.
	 * 2019. 11. 13 용제 수정
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String member_email, @RequestParam String member_pwd, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_email", member_email);
		map.put("member_pwd", member_pwd);

		MemberDTO memberDTO = memberService.login(map);
		
		if (memberDTO != null) {
			session.setAttribute("memDTO", memberDTO);
			session.setMaxInactiveInterval(60*60*24); // 세션 1일 유지
			return "login_ok";
		} else {
			return "login_fail";
		}
	}
	// 로그아웃 처리 
	// 카카오 로그아웃 추가
	@RequestMapping(value = "logout", method = RequestMethod.GET, produces="application/json")
	public ModelAndView logout(HttpSession session) {
	    JsonNode node =  KakaoApi.kakaoLogout((JsonNode) session.getAttribute("access_token"));
	    System.out.println("로그아웃 후 반환되는 아이디 : " + node.get("id"));
		session.invalidate();
		return new ModelAndView("redirect:/main/index");
	}
 }
