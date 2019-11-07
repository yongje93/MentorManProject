package adminmember.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/** 
 * @Title : 관리자페이지 회원관리 컨트롤러입니다
 * @author : 안상구
 * @date : 2019. 11. 5.
 * 이해 안가시는 부분이있으면 연락주세요
 */


@Controller
@RequestMapping(value="adminmember")
public class AdminMemberController {
	
	@RequestMapping(value="adminmemberList",method = RequestMethod.GET)
	public String adminmemberList(Model model) {
		model.addAttribute("display", "/adminmember/adminmemberList.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="adminmenteeList",method = RequestMethod.GET)
	public String adminmenteeList(Model model) {
		model.addAttribute("display", "/adminmember/adminmenteeList.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="adminmentoList",method = RequestMethod.GET)
	public String adminmentoList(Model model) {
		model.addAttribute("display", "/adminmember/adminmentoList.jsp");
		return "/admin/adminMain";
	}
}
