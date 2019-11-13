package mentee.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;
import mentee.service.MenteeService;

@Controller
@RequestMapping("/mentee")
public class MenteeController {
	@Autowired
	private MenteeService menteeService;
	
	/**
	 * @Title : 계정설정 Form
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "menteeUserForm", method = RequestMethod.GET)
	public String menteeWriteForm(Model model, HttpSession session) { //세션으로 값을 뿌려줘야됨
		model.addAttribute("memberDTO", memberDTO(session));
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2", "/mentee/menteeUserSetting.jsp");
		return "/main/index";
	}
	/**
	 * @Title : 계정설정 프로필 수정
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "mentorUserModify", method = RequestMethod.POST)
	public String mentorUserModify(@RequestParam Map<String, String> map, @RequestParam("member_profile") MultipartFile member_profile, Model model, HttpSession session) {
		String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage/"+memberDTO(session).getMember_email();
		String fileName = member_profile.getOriginalFilename();
		File filemake = new File(filePath);
		if(!filemake.exists()) {
			filemake.mkdirs();
		}
		File file = new File(filePath, fileName);
		try {
			FileCopyUtils.copy(member_profile.getInputStream(), new FileOutputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("member_profile", fileName);
		menteeService.mentorUserModify(map);
		model.addAttribute("memberDTO", memberDTO(session)); 
		model.addAttribute("display","/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/menteeUserSetting.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : mentee 학생 프로필
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "menteeStudentProfile", method = RequestMethod.GET)
	public String menteeStudentProfile(Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MenteeDTO mentee = menteeService.getStudentEmail(memberDTO.getMember_email());
		model.addAttribute("menteeDTO", mentee);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display","/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/menteeStudentProfile.jsp");
		return "/main/index";
	}
	/**
	 * @Title : mentee 학생 info 입력 및 수정
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteeStudentInput", method = RequestMethod.POST)
	@ResponseBody
	public void menteeStudentInput(@RequestParam Map<String, String> map, Model model) {
		menteeService.menteeStudentInput(map);
	}
	
	/**
	 * @Title : mentee 직장인 프로필
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteeEmployeeProfile", method = RequestMethod.GET)
	public String menteeEmployeeProfile(Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MenteeDTO mentee = menteeService.getEmployeeEmail(memberDTO.getMember_email());
		model.addAttribute("menteeDTO", mentee);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/menteeEmployeeProfile.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : mentee 직장인 info 입력 및 수정
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteeEmployeeInput", method = RequestMethod.POST)
	@ResponseBody
	public void menteeEmployeeInput(@RequestParam Map<String, String> map) {
		menteeService.menteeEmployeeInput(map);
	}
	
	/**
	 * @Title : member 비밀번호 변경
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteePassword", method = RequestMethod.GET)
	public String menteePassword(Model model) {
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/menteePassword.jsp");
		return "/main/index";
	}
	/**
	 * @Title : 비밀번호 체크
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteePasswordCheck", method = RequestMethod.POST)
	@ResponseBody
	public String menteePasswordCheck(@RequestParam String currentPassword, HttpSession session) {
		MemberDTO memberEmail = (MemberDTO) session.getAttribute("memDTO");
		MemberDTO memberDTO = menteeService.menteePasswordCheck(memberEmail.getMember_email());
		
		if(!(memberDTO.getMember_pwd().equals(currentPassword))) {
			return "no";
		}else {
			return "ok";
		}
	}
	/**
	 * @Title : 패스워드 저장
	 * @Author : kujun95, @Date : 2019. 11. 13.
	 */
	@RequestMapping(value = "menteePasswordSave", method = RequestMethod.POST)
	@ResponseBody
	public void menteePasswordSave(@RequestParam Map<String, String> map ,HttpSession session) {
		MemberDTO memberEmail = (MemberDTO) session.getAttribute("memDTO");
		map.put("member_email", memberEmail.getMember_email());
		menteeService.menteePasswordSave(map);
	}
	
	
	
	//계정설정에서 비슷한 코드가 들어가서 세션 함수로 만듬
	public MemberDTO memberDTO(HttpSession session) {
		MemberDTO memberEmail = (MemberDTO) session.getAttribute("memDTO");
		MemberDTO memberDTO = menteeService.getSaveMember(memberEmail.getMember_email());
		return memberDTO;
	}
	
}
