package meetingboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 모임 게시판 관련 컨트롤러
 * @author : yong
 * @date : 2019. 11. 2.
 */
@Controller
@RequestMapping(value="meetingboard")
public class meetingboardController {
	/**
	 * @Title : 모임 게시판 리스트. head 영역의 모임 버튼 눌렀을때 화면
	 * @Author : yong
	 * @Date : 2019. 11. 2.
	 * @Method Name : meetingboardList
	 */
	@RequestMapping(value="meetingboardList", method=RequestMethod.GET)
	public ModelAndView meetingboardList(@RequestParam(required=false, defaultValue="1") String pg, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display", "/meetingboard/meetingboardList.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	/**
	 * @Title : 모임 작성 폼 열기(멘토일때)
	 * @Author : yong
	 * @Date : 2019. 11. 3.
	 * @Method Name : meetingboardWriteForm
	 */
	@RequestMapping(value="meetingboardWriteForm", method=RequestMethod.GET)
	public String meetingboardWriteForm(Model model) {
		model.addAttribute("display","/meetingboard/meetingboardWriteForm.jsp");
		return "/main/index";
	}
	
}
