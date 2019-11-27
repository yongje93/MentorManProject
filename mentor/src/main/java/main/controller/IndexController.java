package main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import essayboard.bean.EssayboardDTO;
import essayboard.service.EssayboardService;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.service.MeetingboardService;
import mentor.bean.MentorDTO;
import mentor.service.MentorService;

@Controller
public class IndexController {
	@Autowired
	private MeetingboardService meetingboardService;
	@Autowired
	private MentorService mentorService;
	@Autowired
	private EssayboardService essayboardService;
	
	@RequestMapping(value = "/main/index", method = RequestMethod.GET)
	public ModelAndView index() {
		// 모임
		Map<String, Integer> meetingMap = new HashMap<String, Integer>();
		meetingMap.put("startNum", 1);
		meetingMap.put("endNum", 6);
		List<MeetingboardDTO> meetingboardList = meetingboardService.getMeetingboardList(meetingMap);
		
		// 멘토
		//승인된 멘토
		int mentor_flag = 1;
		int endNum = 8;
		int startNum = 1;
		Map<String, String> mentorMap = new HashMap<String, String>();
		mentorMap.put("endNum", endNum+"");
		mentorMap.put("startNum", startNum+"");
		mentorMap.put("mentor_flag",mentor_flag+"");
		List<MentorDTO> mentorList = mentorService.getMentorList(mentorMap);
		
		// 명예멘토 
		// 넣어야됨
		
		// 신규에세이
		Map<String, Object> essayMap = new HashMap<String, Object>();
		// 3개
		essayMap.put("endNum", 3);
		essayMap.put("startNum", 1);
		List<EssayboardDTO> newEssayList = essayboardService.getNewEssay(essayMap);
		
		// 추천에세이
		List<EssayboardDTO> bestEssayList = essayboardService.getBestEssay(essayMap);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("meetingboardList", meetingboardList);
		mav.addObject("mentorList", mentorList);
		mav.addObject("newEssayList", newEssayList);
		mav.addObject("bestEssayList", bestEssayList);
		mav.addObject("display", "/template/container.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

}
