package adminmain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Title : 관리자페이지 모든 List controller
 * @author : 안상구
 * @date : 2019. 11. 5.
 */

@Controller
@RequestMapping(value="admin")
public class AdminController {
	
	
	/* description : 관리자페이지 Main화면*/
	@RequestMapping(value="adminMain",method = RequestMethod.GET)
	public String adminMain(Model model) {
		model.addAttribute("display", "/admin/adminView.jsp");
		return "/admin/adminMain";
	}
	/* description : 관리자페이지 회원관리화면*/
	@RequestMapping(value="adminMemberList",method = RequestMethod.GET)
	public String adminMemberList(Model model) {
		model.addAttribute("display", "/admin/adminMemberList.jsp");
		return "/admin/adminMain";
	}
}
