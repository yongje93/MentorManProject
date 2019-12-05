package mentee.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import meetingboard.bean.ReviewDTO;
import meetingboard.service.MeetingboardService;
import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;
import mentee.service.MenteeService;
import participation.bean.OrderDTO;
import participation.bean.OrderHistoryPaging;
import participation.service.ParticipationService;

@Controller
@RequestMapping("/mentee")
public class MenteeController {
	@Autowired
	private MenteeService menteeService;
	@Autowired
	private ParticipationService participationService;
	@Autowired
	private OrderHistoryPaging orderHistoryPaging;
	@Autowired
	private MeetingboardService meetingboardService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * @Title : 계정설정 Form
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "menteeUserForm", method = RequestMethod.GET)
	public String menteeWriteForm(Model model, HttpSession session) { //세션으로 값을 뿌려줘야됨
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		MemberDTO memberDTO = menteeService.getSaveMember(memDTO.getMember_email());
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2", "/mentee/menteeUserSetting.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 닉네임 중복확인
	 * @Author : kujun95, @Date : 2019. 11. 18.
	 */
	@RequestMapping(value = "chackNickname", method = RequestMethod.POST)
	@ResponseBody
	public String chackNickname(@RequestParam String member_nickname, @RequestParam String nickname) {
		MemberDTO members = menteeService.getNickname(member_nickname);
		if(member_nickname.equals(nickname)){
			return "eq";
		}
		
		if(member_nickname.length() < 3 || member_nickname.length() > 22){
			return "length_error";
		}else if(members != null){
			return "no";
		}else {
			return "ok";
		}
	}
	
	/**
	 * @Title : 계정설정 프로필 수정
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "mentorUserModify", method = RequestMethod.POST)
	public String mentorUserModify(@RequestParam Map<String, String> map, @RequestParam("member_profile") MultipartFile member_profile, Model model, HttpSession session, HttpServletRequest request) {
		if(member_profile.getOriginalFilename()!="") {
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
			String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage/"+memberDTO.getMember_email();
			String fileName = member_profile.getOriginalFilename();
			File filemake = new File(filePath);
			if(!filemake.exists()) {
				filemake.mkdirs();
			}
			File file = new File(filePath, fileName);
			try {
					FileCopyUtils.copy(member_profile.getInputStream(), new FileOutputStream(file));				
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put("member_profile", fileName);
			menteeService.mentorUserModify(map);
		}else {
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
			String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage/"+memberDTO.getMember_email();
			String fileName = memberDTO.getMember_profile();
			File filemake = new File(filePath);
			if(!filemake.exists()) {
				filemake.mkdirs();
			}
			File file = new File(filePath, fileName);
			try {
					FileCopyUtils.copy(member_profile.getInputStream(), new FileOutputStream(file));				
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put("member_profile", fileName);
			menteeService.mentorUserModify(map);
			
			//세션을 새로 생성
			memberDTO.setMember_nickname(map.get("member_nickname"));
			HttpSession session2 = request.getSession();
			session2.setAttribute("memDTO", memberDTO);
		}
		
		
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
	public void menteeStudentInput(@RequestParam Map<String, String> map, Model model, HttpSession session) {
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
			MenteeDTO mentee = menteeService.getStudentEmail(memberDTO.getMember_email());
			if(mentee != null) {
				map.put("mentee_email", mentee.getMenteeStudent_email());
				menteeService.menteeStudentInput(map);
			}else {
				map.put("mentee_email", null);
				menteeService.menteeStudentInput(map);
			}
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
	public void menteeEmployeeInput(@RequestParam Map<String, String> map, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MenteeDTO mentee = menteeService.getEmployeeEmail(memberDTO.getMember_email());
		if(mentee != null) {
			map.put("mentee_email", mentee.getMenteeEmployee_email());
			menteeService.menteeEmployeeInput(map);
		}else {
			map.put("mentee_email", null);
			menteeService.menteeEmployeeInput(map);
		}
	}
	
	/**
	 * @Title : member 비밀번호 변경
	 * @Author : kujun95, @Date : 2019. 11. 12.
	 */
	@RequestMapping(value = "menteePassword", method = RequestMethod.GET)
	public String menteePassword(Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		model.addAttribute("memberDTO", memberDTO);
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
		
		if(passwordEncoder.matches(currentPassword, memberDTO.getMember_pwd())) {
			return "ok";
		} else {
			return "no";
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
		String encPassword = passwordEncoder.encode(map.get("member_pwd"));
		map.replace("member_pwd", encPassword);
		menteeService.menteePasswordSave(map);
	}
	
	//계정설정에서 비슷한 코드가 들어가서 세션 함수로 만듬
	public MemberDTO memberDTO(HttpSession session) {
		MemberDTO memberEmail = (MemberDTO) session.getAttribute("memDTO");
		MemberDTO memberDTO = menteeService.getSaveMember(memberEmail.getMember_email());
		return memberDTO;
	}
	
	/**
	 * @Title : 내 결제 내역 보여주기
	 * @Author : yong
	 * @Date : 2019. 11. 14.
	 * @Method Name : menteeOrder
	 */
	@RequestMapping(value = "menteeOrderHistory", method = RequestMethod.GET)
	public String menteeOrderHistory(@RequestParam(required = false, defaultValue = "1") String pg, Model model, HttpSession session) {
		// 1페이지당 5개
		int endNum = Integer.parseInt(pg) * 5;
		int startNum = endNum - 4;
		
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		String member_email = memDTO.getMember_email();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("member_email", member_email);
		
		List<OrderDTO> orderHistoryList = participationService.getOrderHistoryUsingMemEmail(map);

		// 페이징 처리
		int totalOrderHistory = participationService.getTotalHistory(member_email);
		orderHistoryPaging.setCurrentPage(Integer.parseInt(pg));
		orderHistoryPaging.setPageBlock(3);
		orderHistoryPaging.setPageSize(5);
		orderHistoryPaging.setTotalA(totalOrderHistory);
		orderHistoryPaging.makePagingHTML();
		model.addAttribute("memberDTO", memDTO);
		model.addAttribute("orderHistoryPaging", orderHistoryPaging);
		model.addAttribute("orderHistoryList", orderHistoryList);
		model.addAttribute("display","/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/menteeOrderHistory.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value = "orderHistorySearch", method = RequestMethod.POST)
	public ModelAndView orderHistorySearch(@RequestParam Map<String, Object> map, HttpSession session) {
		// 1페이지당 5개
		int pg =  Integer.parseInt((String) map.get("pg"));
		int endNum = pg * 5;
		int startNum = endNum - 4;
		
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		String member_email = memDTO.getMember_email();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("member_email", member_email);
		
		List<OrderDTO> orderHistorySearchList = participationService.getOrderHistorySearch(map);
		
		// 페이징 처리
		int totalSearchHistory = participationService.getSearchHistory(map);
		orderHistoryPaging.setCurrentPage(pg);
		orderHistoryPaging.setPageBlock(3);
		orderHistoryPaging.setPageSize(5);
		orderHistoryPaging.setTotalA(totalSearchHistory);
		orderHistoryPaging.makeSearchPagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("totalSearchHistory", totalSearchHistory);
		mav.addObject("orderHistorySearchList", orderHistorySearchList);
		mav.addObject("orderHistoryPaging", orderHistoryPaging);
		mav.setViewName("jsonView");
		return mav;
	}	
	
	/**
	 * @Title : 모임 후기 작성 창
	 * @Author : yong
	 * @Date : 2019. 11. 15.
	 * @Method Name : meetingReviewWriteForm
	 */
	@RequestMapping(value = "meetingReviewWriteForm", method = RequestMethod.GET)
	public String meetingReviewWriteForm(@RequestParam String seq, Model model) {
		int meetingboard_seq = Integer.parseInt(seq);
		model.addAttribute("meetingboard_seq", meetingboard_seq);
		model.addAttribute("display","/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/meetingboard/meetingReviewWriteForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 모임 후기 작성 완료
	 * @Author : yong
	 * @Date : 2019. 11. 15.
	 * @Method Name : meetingReviewWrite
	 */
	@RequestMapping(value = "meetingReviewWrite", method = RequestMethod.POST)
	public String meetingReviewWrite(@ModelAttribute ReviewDTO reviewDTO, HttpSession session) {
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		reviewDTO.setMentee_email(memDTO.getMember_email());
		meetingboardService.meetingReviewWrite(reviewDTO);
		return "redirect:/mentee/menteeOrderHistory";
	}
	
	/**
	 * @Title : 모임 후기 수정 창
	 * @Author : yong
	 * @Date : 2019. 11. 20.
	 * @Method Name : meetingReviewModifyForm
	 */
	@RequestMapping(value = "meetingReviewModifyForm", method = RequestMethod.GET)
	public String meetingReviewModifyForm(@RequestParam String seq, @RequestParam String mentors, Model model) {
		int review_seq = Integer.parseInt(seq);
		int mentor_seq = Integer.parseInt(mentors);
		ReviewDTO reviewDTO = meetingboardService.getMeetingReview(review_seq);
		model.addAttribute("reviewDTO", reviewDTO);
		model.addAttribute("mentor_seq", mentor_seq);
		model.addAttribute("display","/meetingboard/meetingReviewModifyForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 모임 후기 수정 완료
	 * @Author : yong
	 * @Date : 2019. 11. 20.
	 * @Method Name : meetingReviewModify
	 */
	@RequestMapping(value = "meetingReviewModify", method = RequestMethod.POST)
	public String meetingReviewModify(@ModelAttribute ReviewDTO reviewDTO, @RequestParam String mentors, Model model) {
		int mentor_seq = Integer.parseInt(mentors);
		meetingboardService.meetingReviewModify(reviewDTO);
		return "redirect:/mentor/mentorInfoView?mentors=" + mentor_seq;
	}
	
	/**
	 * @Title : 모임 후기 삭제
	 * @Author : yong
	 * @Date : 2019. 11. 20.
	 * @Method Name : meetingReviewDelete
	 */
	@RequestMapping(value = "meetingReviewDelete", method = RequestMethod.GET)
	public String meetingReviewDelete(@RequestParam String seq, @RequestParam String mentors) {
		int review_seq = Integer.parseInt(seq);
		int mentor_seq = Integer.parseInt(mentors);
		meetingboardService.meetingReviewDelete(review_seq);
		return "redirect:/mentor/mentorInfoView?mentors=" + mentor_seq;
	}
	
	@RequestMapping(value = "memberDelete", method = RequestMethod.GET)
	public String memberDelete(Model model, HttpSession sesstion) {
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2","/mentee/memberDelete.jsp");
		return "/main/index";
	}
	@RequestMapping(value = "memberPasswordCheck", method = RequestMethod.POST)
	@ResponseBody
	public String memberPasswordCheck(@RequestParam String currentPassword, HttpSession session) {
		MemberDTO user_info = (MemberDTO) session.getAttribute("memDTO");
		
		if(user_info.getMember_pwd().equals(currentPassword)) {
			return "right";
		}else {
			return "wrong";
		}
	}
	
	@RequestMapping(value = "memberDeleteSuccess", method = RequestMethod.POST)
	@ResponseBody
	public void memberDeleteSuccess(HttpSession session) {
		MemberDTO user_info = (MemberDTO) session.getAttribute("memDTO");
		menteeService.memberDeleteSuccess(user_info.getMember_seq());
	}

}
