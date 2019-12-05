package main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import essayboard.bean.EssayboardDTO;
import essayboard.service.EssayboardService;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.service.MeetingboardService;
import member.bean.MemberDTO;
import mentor.bean.MentorDTO;
import mentor.service.MentorService;

@Controller
@RequestMapping("/main")
public class IndexController {
	@Autowired
	private MeetingboardService meetingboardService;
	@Autowired
	private MentorService mentorService;
	@Autowired
	private EssayboardService essayboardService;

	@RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		// 모임
		Map<String, Integer> meetingMap = new HashMap<String, Integer>();
		meetingMap.put("startNum", 1);
		meetingMap.put("endNum", 6);
		List<MeetingboardDTO> meetingboardList = meetingboardService.getMeetingboardList(meetingMap);

		// 신규멘토
		int mentor_flag = 1;
		int endNum = 8;
		int startNum = 1;
		Map<String, String> mentorMap = new HashMap<String, String>();
		mentorMap.put("endNum", endNum+"");
		mentorMap.put("startNum", startNum+"");
		mentorMap.put("mentor_flag",mentor_flag+"");
		List<MentorDTO> mentorList = mentorService.getMentorList(mentorMap);

		Map<String, Object> honorMap = new HashMap<String, Object>();
		honorMap.put("startNum", 1);
		honorMap.put("endNum", 8);
		honorMap.put("mentor_badge", 1);
		honorMap.put("mentor_flag", 1);
		// 명예멘토
		List<MentorDTO> honorMentorList = mentorService.getHonorMentor(honorMap);

		Map<String, Object> essayMap = new HashMap<String, Object>();
		essayMap.put("startNum", 1);
		essayMap.put("endNum", 3);
		// 신규에세이
		List<EssayboardDTO> newEssayList = essayboardService.getNewEssay(essayMap);
		// 추천에세이
		List<EssayboardDTO> bestEssayList = essayboardService.getBestEssay(essayMap);
		ModelAndView mav = new ModelAndView();
		//회원 멘티 정보를 입력하지 않은 회원 체크
		if(memberDTO != null) {
			int menteeInfo_count = mentorService.getMenteeInfo_count(memberDTO.getMember_email());
			mav.addObject("menteeInfo_count", menteeInfo_count);
		}

		if(memberDTO != null) {
			String nickname = memberDTO.getMember_nickname();
			mav.addObject("memNickname" , nickname);
			//양재우 scrap 기능 구현
	         for (EssayboardDTO essayboardDTO : bestEssayList) {

	        	 int seq = essayboardDTO.getEssayboard_seq();
	        	 Map<String, Object> scrapMap = new HashMap<String, Object>();
	        	 scrapMap.put("seq", seq);
	        	 scrapMap.put("memEmail" , memberDTO.getMember_email());

	        	 int cnt = essayboardService.getEssayboardScrap(scrapMap);
	        	 //스크랩을 눌렀다면
	        	 if(cnt == 1) {
	        		 //flag 1 저장
	        		 essayboardDTO.setEssayboard_scrapFlag(cnt);
	        	 }
	         }
		}

		mav.addObject("meetingboardList", meetingboardList);
		mav.addObject("mentorList", mentorList);
		mav.addObject("honorMentorList", honorMentorList);
		mav.addObject("newEssayList", newEssayList);
		mav.addObject("bestEssayList", bestEssayList);
		mav.addObject("display", "/template/container.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

}
