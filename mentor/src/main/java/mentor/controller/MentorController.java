package mentor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import meetingboard.bean.ReviewDTO;
import member.bean.MemberDTO;
import mentor.bean.MentorDTO;
import mentor.bean.MentorFollowDTO;
import mentor.bean.MentorfindPaging;
import mentor.service.MentorService;

@Controller
@RequestMapping("/mentor")
public class MentorController {
	@Autowired
	private MentorService mentorService;
	@Autowired
	private MentorfindPaging mentorfindPaging;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private MentorFollowDTO mentorFollowDTO;

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
	 * @Title : 직무유형에 맞는 멘토
	 * @Author : kujun95, @Date : 2019. 11. 25.
	 */
	@RequestMapping(value = "mentorjobFind", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView essayjobType(@RequestBody Map<String, Object> jsonData ,
										  HttpServletResponse response ,
										  HttpSession session) {
		List<MentorDTO> list = null;
		// job_code
		ArrayList<String> joblist = (ArrayList<String>) jsonData.get("job_code");
		// 현재 페이지
		int pg = (Integer) jsonData.get("pg");
		// 멘토, 명예멘토
		int flag = (Integer) jsonData.get("flag");
		// job_code 유무 체크
		String check = null;

		for (String jobs : joblist) {
			if(!jobs.equals(null)) {
				check = "success";
			}
		}

		memberDTO = (MemberDTO)session.getAttribute("memDTO");
		Map<String, Object> map = new HashMap<String, Object>();
//
		// 페이지 당 9개씩
		int endNum = pg * 12;
		int startNum = endNum - 11;

		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("job_code", joblist);
		map.put("flag", flag);
		map.put("mentor_flag", 1);

		// 직무유형 총 글 수
		int jobCodeTotal = 0;

		// 직무 유형 찾기
		if(check == "success") {
			//멘토 리스트
			list = mentorService.getJobType(map);
			//멘토 인원수
			jobCodeTotal = mentorService.getJobCode(map);
		}

		// 멘토 테이블 플래그
		int mentorFlag = 1;

		// job_code가 없을 경우 아래 코드를 실행한다.
		if(check == null && flag == 0){
			list = mentorService.getMentor(map);
			jobCodeTotal = mentorService.getMemberCount(mentorFlag);
		} else if(check == null && flag == 1) {
			map.put("mentor_badge", 1);
			list = mentorService.getHonorMentor(map);
//			jobCodeTotal = mentorService.getRecommendTotal();
		}

		ModelAndView modelAndView = new ModelAndView();

		mentorfindPaging.setCurrentPage(pg);
		mentorfindPaging.setPageBlock(3);
		mentorfindPaging.setPageSize(12);
		mentorfindPaging.setTotalA(jobCodeTotal);
		mentorfindPaging.makePagingHTML();

		if(memberDTO != null) {
			modelAndView.addObject("memberDTO", memberDTO);
			//멘티 정보를 입력했는지 확인
			int menteeInfo_count = mentorService.getMenteeInfo_count(memberDTO.getMember_email());
			modelAndView.addObject("menteeInfo_count", menteeInfo_count);

		}

		modelAndView.addObject("pg", pg);
		modelAndView.addObject("flag", flag);
		modelAndView.addObject("pageSize", mentorfindPaging.getPageSize());
		modelAndView.addObject("boardpaging", mentorfindPaging.getCurrentPage());
		modelAndView.addObject("jobCodeTotal", jobCodeTotal);
		modelAndView.addObject("list", list);
		modelAndView.setViewName("jsonView");

		return modelAndView;
	}



	/**
	 * @Title : 멘토 찾기
	 * @Author : kujun95, @Date : 2019. 11. 15.
	 */
	@RequestMapping(value = "mentorfindForm", method = RequestMethod.GET)
	public String mentorfindForm(@RequestParam(required = false, defaultValue = "1") int pg, Model model, HttpSession session) {
		memberDTO = (MemberDTO) session.getAttribute("memDTO");
		//승인된 멘토
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

		
		model.addAttribute("flag", 0);
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("pg", pg);
		model.addAttribute("mentorfindPaging", mentorfindPaging);
		model.addAttribute("list", list);
		model.addAttribute("display", "/mentor/mentorfindForm.jsp");
		return "/main/index";
	}


	@RequestMapping(value = "question_flag", method = RequestMethod.POST)
	@ResponseBody
	public String question_flag(@RequestParam String seq, @RequestParam(required = false, defaultValue = "1") String pg, HttpSession session) {
		String reQuestion = "/mentor/mentor/mentorQuestionsForm?pg="+pg+"&seq="+seq;
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		Map<String, String> flagCheck_map = new HashMap<String, String>();
		flagCheck_map.put("member_email", memberDTO.getMember_email());
		flagCheck_map.put("mentor_seq", seq);

		List<MentorDTO> list = mentorService.getQuestion_flag(flagCheck_map);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getQuestion_flag() == 0) {
					return "/mentor/member/myQuestionsForm?pg="+pg+"&seq="+seq+"&qsseq="+list.get(i).getQuestion_seq();
				}
			}
		}
		return reQuestion;
	}

	/**
	 * @Title : 멘토에게 질문하기
	 * @Author : kujun95, @Date : 2019. 11. 15.
	 */
	@RequestMapping(value = "mentorQuestionsForm", method = RequestMethod.GET)
	public String mentorQuestionsForm(@RequestParam(required = false, defaultValue = "1") int seq, @RequestParam(required = false, defaultValue = "1") int pg, @RequestParam(required = false, defaultValue = "0") int qsseq, Model model, HttpSession session) {
		//멘토정보 가져오기
		MentorDTO questionDTO =  mentorService.questionModifyForm(qsseq);
		MentorDTO mentorDTO = mentorService.getMentor_info(seq);
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("mentoring_code", mentoringArray);
		List<MentorDTO> list = mentorService.getMentoring_code(map);

		//로그인 세션
		memberDTO = (MemberDTO) session.getAttribute("memDTO");

		Map<String, String> followMap = new HashMap<String, String>();
		followMap.put("memEmail" , memberDTO.getMember_email());
		followMap.put("mentorEmail" , mentorDTO.getMentor_email());
		//팔로우 찾기
		int follow = mentorService.getFollowCheck(followMap);
		model.addAttribute("memNickname" , memberDTO.getMember_nickname());
		model.addAttribute("list", list);
		model.addAttribute("follow" , follow);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("questionDTO",questionDTO);
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
	 * 추가 : 양재우
	 */
	@RequestMapping(value = "mentorInfoView", method = RequestMethod.GET)
	public String mentorInfoView(@RequestParam String mentors, @RequestParam(required = false, defaultValue = "1") String pg, Model model, HttpSession sesstion) {
		int mentor_seq = Integer.parseInt(mentors);
		MentorDTO mentorDTO = mentorService.getMentorInfomation(mentor_seq);
		List<MentorDTO> essayList = mentorService.getMentorEssayList(mentor_seq);
		List<ReviewDTO> reviewList = mentorService.getMentorReviewList(mentor_seq);

		int mentor_answer = mentorService.getAnswer(mentor_seq); // 답변수
		int mentor_question = mentorService.getQuestion(mentor_seq);// 질문수
		int mentor_follow = mentorService.getFollow(mentor_seq); //팔로워수
		double questionPercent = (double)mentor_answer/(double)mentor_question;
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("mentoring_code", mentoringArray);
		List<MentorDTO> mentoringList = mentorService.getMentoring_code(map);

		memberDTO = (MemberDTO) sesstion.getAttribute("memDTO");

		//follow - 재우
		Map<String, String> followMap = new HashMap<String, String>();
		followMap.put("memEmail" , memberDTO.getMember_email());
		followMap.put("mentorEmail" , mentorDTO.getMentor_email());
	  	int follow = mentorService.getFollowCheck(followMap);

	  	//follow Modal - 재우
	  	List<MemberDTO> followerList = mentorService.getFollowerList(mentorDTO.getMentor_email());

	  	model.addAttribute("follow", follow);
		model.addAttribute("pg", pg);
		if(memberDTO!= null) {
			model.addAttribute("email_check", memberDTO.getMember_email());
		}
		if(Double.isNaN(questionPercent)) {
			model.addAttribute("questionPercent", 0);
		}else {
			model.addAttribute("questionPercent", questionPercent);
		}
		model.addAttribute("followerList" , followerList);

		model.addAttribute("pg", pg);

		model.addAttribute("mentor_answer",mentor_answer);
		model.addAttribute("mentor_follow",mentor_follow);
		model.addAttribute("mentor_seq", mentor_seq);
		model.addAttribute("mentoringList", mentoringList);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("essayList", essayList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("essayTotal", essayList.size());
		model.addAttribute("reviewTotal", reviewList.size());
		model.addAttribute("display", "/mentor/mentorInfoView.jsp");
		return "/main/index";
	}
	/**
	 * @Title : 질문 수정
	 * @Author : kujun95, @Date : 2019. 11. 20.
	 */
	@RequestMapping(value = "questionModify", method = RequestMethod.POST)
	@ResponseBody
	public String questionModify(@RequestParam Map <String, String> map) {
		int success = mentorService.questionModify(map);
		if(success == 0) {
			return "error";
		}else {
			return "success";
		}
	}

	/**
	 *
	 * @Title : 멘토 팔로우 기능 구현
	 * @Author : yangjaewoo, @Date : 2019. 11. 21.
	 */
	@RequestMapping(value = "mentorFollow", method = RequestMethod.POST)
	@ResponseBody
	public int mentorFollow(HttpServletRequest httpRequest, HttpSession session) {
		int follow = Integer.parseInt(httpRequest.getParameter("follow"));
        String followed_email = httpRequest.getParameter("followed_email");

        memberDTO = (MemberDTO)session.getAttribute("memDTO");
        String follower_email = memberDTO.getMember_email();

        mentorFollowDTO.setFollower_email(follower_email);
        mentorFollowDTO.setFollowed_email(followed_email);

        if(follow >= 1) {
        	mentorService.mentorFollowDelete(mentorFollowDTO);
        	follow=0;
        } else {
        	mentorService.mentorFollowInsert(mentorFollowDTO);
        	follow=1;
        }

        return follow;
	}

	@RequestMapping(value = "mentorAttention", method = RequestMethod.GET)
	public String mentorAttention(Model model , HttpSession session) {
		memberDTO = (MemberDTO) session.getAttribute("memDTO");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memEmail" , memberDTO.getMember_email());
		map.put("mentor_flag", 1);
		//승인된 멘토 이면서 나를 팔로우한 팔로워 list
		List<MentorDTO> list = mentorService.getMentorAttentionList(map);

		model.addAttribute("list", list);
		model.addAttribute("display", "/mentor/mentorAttention.jsp");
		return "/main/index";
	}
	/**
	 * @Title : 멘토 정보 수정
	 * @Author : kujun95, @Date : 2019. 11. 27.
	 */
	@RequestMapping(value = "mentorInfoForm", method = RequestMethod.GET)
	public String mentorInfoForm(Model model, HttpSession session) {
		memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MentorDTO mentorDTO = mentorService.getEmail(memberDTO.getMember_email());
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("memberDTO", memberDTO);		
		model.addAttribute("display", "/mentee/menteeUserForm.jsp");
		model.addAttribute("display2", "/mentor/mentorInfoForm.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "mentorInfoModify", method = RequestMethod.POST)
	public String mentorInfoModify(@RequestParam("mentoring_code") String mentoring_code,@RequestParam("mentor_businesscard") MultipartFile mentor_businesscard, @RequestParam Map<String, String> map , HttpSession session) {
		map.put("mentoring_code", mentoring_code);
		if(mentor_businesscard.getOriginalFilename()!="") {
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
		}else {
			map.put("mentor_businesscard", null);
		}
		mentorService.mentorInfoModify(map);
		session.invalidate();
		return "redirect:/main/index";
	}





	@RequestMapping(value = "userInfoCheck", method = RequestMethod.GET)
	public String userInfoCheck(Model model) {
		model.addAttribute("display", "/mentor/userInfoCheck.jsp");
		return "/main/index";
	}
}
