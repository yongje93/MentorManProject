package participation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import meetingboard.bean.MeetingboardDTO;
import meetingboard.service.MeetingboardService;
import participation.service.ParticipationService;

/**
 * 모임 신청 관련 controller
 * @author : yong
 * @date : 2019. 11. 8.
 */
@Controller
@RequestMapping(value = "participation")
public class ParticipationController {
	@Autowired
	private ParticipationService participationService;
	@Autowired
	private MeetingboardService meetingboardService;
	
	@RequestMapping(value = "participationWriteForm", method = RequestMethod.GET)
	public String participationWriteForm(@RequestParam String seq, Model model) {
		int meeting_seq = Integer.parseInt(seq);
		MeetingboardDTO meetingboardDTO = meetingboardService.getMeetingboard(meeting_seq);	// 모임 정보
		model.addAttribute("meetingboardDTO", meetingboardDTO);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/participation/participationWriteForm.jsp");
		return "/main/index";
	}
	
}
