package participation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import meetingboard.bean.MeetingboardDTO;
import meetingboard.service.MeetingboardService;
import member.bean.MemberDTO;
import participation.bean.OrderDTO;
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
	
	/**
	 * @Title : 모임 신청하기
	 * @Author : yong
	 * @Date : 2019. 11. 10.
	 * @Method Name : participationWrite
	 */
	@RequestMapping(value = "participationWrite", method = RequestMethod.POST)
	@ResponseBody
	public void participationWrite(@ModelAttribute ParticipationDTO participationDTO) {
		participationService.participationWrite(participationDTO);
	}
	
	/**
	 * @Title : 모임 바구니 화면
	 * @Author : yong
	 * @Date : 2019. 11. 11.
	 * @Method Name : order
	 */
	@RequestMapping(value = "order", method = RequestMethod.GET)
	public String order(Model model, HttpSession session) {
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mentee_email", memDTO.getMember_email());
		
		List<ParticipationDTO> participationList = participationService.getParticipation(map);
		model.addAttribute("participationList", participationList);
		model.addAttribute("display", "/participation/participationOrder.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 모임 신청 삭제
	 * @Author : yong
	 * @Date : 2019. 11. 11.
	 * @Method Name : orderDelete
	 */
	@RequestMapping(value = "orderDelete", method = RequestMethod.POST)
	@ResponseBody
	public void orderDelete(@RequestParam String seq, Model model) {
		int participation_seq = Integer.parseInt(seq);
		participationService.orderDelete(participation_seq);
	}
	
	/**
	 * @Title : 모임 결제
	 * @Author : yong
	 * @Date : 2019. 11. 12.
	 * @Method Name : orderComplete
	 */
	@RequestMapping(value = "orderComplete", method = RequestMethod.POST)
	@ResponseBody
	public void orderComplete(@RequestBody Map<String, Object> order) {
		//System.out.println(order);
		ArrayList<Integer> meeting_seq = (ArrayList<Integer>) order.get("meetingboard_list");
		ArrayList<Integer> participation_seq = (ArrayList<Integer>) order.get("participation_list");
		
		for(int i = 0; i < meeting_seq.size(); i++) {
			order.put("meetingboard_seq", meeting_seq.get(i));
			order.put("participation_seq", participation_seq.get(i));
			participationService.orderComplete(order);
		}
	}
	
	/**
	 * @Title : 결제 완료 페이지
	 * @Author : yong
	 * @Date : 2019. 11. 12.
	 * @Method Name : paymentComplete
	 */
	@RequestMapping(value = "paymentComplete", method = RequestMethod.GET)
	public String paymentComplete(@RequestParam String order_id, Model model) {
		List<OrderDTO> orderList = participationService.getOrderHistoryUsingOrderId(order_id);
		model.addAttribute("order_id", order_id);
		model.addAttribute("orderList", orderList);
		model.addAttribute("display", "/participation/participationPaymentComplete.jsp");
		return "/main/index";
	}
}
