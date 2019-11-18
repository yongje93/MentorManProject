package mentor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import member.bean.MemberDTO;
import mentor.bean.MentorDTO;
import mentor.bean.MentorfindPaging;
import mentor.service.MentorService;

@Controller
@RequestMapping("/mentor")
public class MentorController {
	@Autowired 
	private MentorService mentorService;
	@Autowired
	private MentorfindPaging mentorfindPaging;
	
	/**
	 * @Title : 멘토 지원하기에 회사명, 부서, 직무
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyForm", method = RequestMethod.GET)
	public String mentorapplyForm(Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MentorDTO mentorapplyDTO = mentorService.getEmail(memberDTO.getMember_email());
		model.addAttribute("mentorapplyDTO", mentorapplyDTO);
		model.addAttribute("display", "/mentor/mentorapplyForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 멘토 지원하기 모든 최종 정보 입력
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyWriteForm", method = RequestMethod.POST)
	public String mentorapplyStart(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("mentor_company", map.get("mentor_company"));
		model.addAttribute("mentor_department", map.get("mentor_department"));
		model.addAttribute("mentor_position", map.get("mentor_position"));
		model.addAttribute("display", "/mentor/mentorapplyWriteForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 지원하기 버튼 누른 후 관리자의 동의 대기
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyWrite", method = RequestMethod.POST)
	public String mentorapply(@RequestParam Map<String, String> map, @RequestParam("mentoring_code") String mentoring_code,@RequestParam("mentor_businesscard") MultipartFile mentor_businesscard, Model model) {
		map.put("mentoring_code", mentoring_code);
		System.out.println(map);
		String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage/"+map.get("mentor_email");
		String fileName = mentor_businesscard.getOriginalFilename();
		File filemake = new File(filePath);
		if(!filemake.exists()) {
			filemake.mkdirs();
		}
		if(fileName != "") {
			File file = new File(filePath, fileName);
			try {
				FileCopyUtils.copy(mentor_businesscard.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("mentor_businesscard",fileName);
		mentorService.mentorapplyWrite(map);
		model.addAttribute("display", "/mentor/mentorapplyWrite.jsp");
		return "/main/index";
	}
	
	
	/**
	 * @Title : 멘토 찾기
	 * @Author : kujun95, @Date : 2019. 11. 15.
	 */
	@RequestMapping(value = "mentorfindForm", method = RequestMethod.GET)
	public String mentorfindForm(@RequestParam int pg, Model model) {
		int mentor_flag = 1;
		int endNum = pg*12;
		int startNum = endNum-11;
		Map<String, String> map = new HashMap<String, String>();
		map.put("endNum", endNum+"");
		map.put("startNum", startNum+"");
		map.put("mentor_flag",mentor_flag+"");
		List<MentorDTO> list = mentorService.getMentorList(map);
		int totalA = mentorService.getTotalA(mentor_flag);
		mentorfindPaging.setCurrentPage(pg);
		mentorfindPaging.setPageBlock(3);
		mentorfindPaging.setPageSize(12);
		mentorfindPaging.setTotalA(totalA);
		mentorfindPaging.makePagingHTML();
		model.addAttribute("pg", pg);
		model.addAttribute("mentorfindPaging", mentorfindPaging);
		model.addAttribute("list", list);
		model.addAttribute("display", "/mentor/mentorfindForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 멘토에게 질문하기
	 * @Author : kujun95, @Date : 2019. 11. 15.
	 */
	@RequestMapping(value = "mentorQuestionsForm", method = RequestMethod.GET)
	public String mentorQuestionsForm(@RequestParam int seq, @RequestParam int pg, Model model) {
		MentorDTO mentorDTO = mentorService.getMentor_info(seq);
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("mentoring_code", mentoringArray);
		List<MentorDTO> list = mentorService.getMentoring_code(map);
		model.addAttribute("list", list);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("pg",pg);
		model.addAttribute("seq",seq);
		model.addAttribute("display", "/mentor/mentorQuestionsForm.jsp");
		return"/main/index";
	}
	
	/**
	 * @Title : 질문 완료
	 * @Author : kujun95, @Date : 2019. 11. 17.
	 */
	@RequestMapping(value = "mentorQuestionsSuccess", method = RequestMethod.POST)
	public String mentorQuestionsSuccess(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		map.put("member_email", memberDTO.getMember_email());
		mentorService.mentorQuestionsSuccess(map);
		model.addAttribute("pg", map.get("pg"));
		model.addAttribute("display", "/mentor/mentorQuestionSuccess.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 멘토 상세 페이지
	 * @Author : yong
	 * @Date : 2019. 11. 18.
	 * @Method Name : mentorInfoView
	 */
	@RequestMapping(value = "mentorInfoView", method = RequestMethod.GET)
	public String mentorInfoView(@RequestParam String pg, @RequestParam String mentors, Model model) {
		int mentor_seq = Integer.parseInt(mentors);
		MentorDTO mentorDTO = mentorService.getMentorInfomation(mentor_seq);
		List<MentorDTO> essayList = mentorService.getMentorEssayList(mentor_seq);
		List<MentorDTO> reviewList = mentorService.getMentorReviewList(mentor_seq);
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("mentoring_code", mentoringArray);
		List<MentorDTO> mentoringList = mentorService.getMentoring_code(map);
				
		model.addAttribute("mentoringList", mentoringList);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("essayList", essayList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("essayTotal", essayList.size());
		model.addAttribute("reviewTotal", reviewList.size());
		model.addAttribute("display", "/mentor/mentorInfoView.jsp");
		return "/main/index";
	}
}
