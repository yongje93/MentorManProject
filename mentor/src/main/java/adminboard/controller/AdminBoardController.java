package adminboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * @Title : 관리자페이지 게시판관리 컨트롤러입니다
 * @author : 안상구
 * @date : 2019. 11. 5.
 * 이해 안가시는 부분이있으면 연락주세요
 */

@Controller
@RequestMapping(value="adminboard")
public class AdminBoardController {
	
	@RequestMapping(value="admincommuList",method = RequestMethod.GET)
	public String admincommuList(Model model) {
		model.addAttribute("display", "/adminboard/admincommuList.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="adminessayList",method = RequestMethod.GET)
	public String adminessayList(Model model) {
		model.addAttribute("display", "/adminboard/adminessayList.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="admingroupList",method = RequestMethod.GET)
	public String admingroupList(Model model) {
		model.addAttribute("display", "/adminboard/admingroupList.jsp");
		return "/admin/adminMain";
	}
	@RequestMapping(value="adminnoticeboardList",method = RequestMethod.GET)
	public String admninnoticeboardList(Model model) {
		model.addAttribute("display", "/adminboard/adminnoticeboardList.jsp");
		return "/admin/adminMain";
	}

}
