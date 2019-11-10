package participation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import meetingboard.bean.MeetingboardDTO;
import meetingboard.service.MeetingboardService;
import member.bean.MemberDTO;
import participation.bean.ParticipationDTO;
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
	/**
	 * @Title : 신청하기 버튼 눌렀을때 입력창
	 * @Author : yong
	 * @Date : 2019. 11. 9.
	 * @Method Name : participationWriteForm
	 */
	@RequestMapping(value = "participationWriteForm", method = RequestMethod.GET)
	public String participationWriteForm(@RequestParam String seq, Model model) {
		int meetingboard_seq = Integer.parseInt(seq);
		MemberDTO mentorDTO = participationService.getMentorInfo(meetingboard_seq);
		MeetingboardDTO meetingboardDTO = meetingboardService.getMeetingboard(meetingboard_seq);
		model.addAttribute("meetingboardDTO", meetingboardDTO);
		model.addAttribute("mentorDTO", mentorDTO);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/participation/participationWriteForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value = "participationWrite", method = RequestMethod.POST)
	@ResponseBody
	public void participationWrite(@ModelAttribute ParticipationDTO participationDTO) {
		participationService.participationWrite(participationDTO);
	}
}
