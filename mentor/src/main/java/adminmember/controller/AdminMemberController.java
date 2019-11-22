package adminmember.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmemberPaging;
import adminmember.service.AdminmemberService;


/** 
 * @Title : 관리자페이지 회원관리 컨트롤러입니다
 * @author : 안상구
 * @date : 2019. 11. 5.
 * 이해 안가시는 부분이있으면 연락주세요
 */


@Controller
@RequestMapping(value="adminmember")
public class AdminMemberController {
	
	@Autowired
	private AdminmemberService adminmemberService;
	
	@Autowired
	private AdminmemberPaging adminmemberPaging;
	
	/* description : 회원리스트 화면페이지 & 리스트 뿌리기 & 페이지 처리 */
	@RequestMapping(value="adminmemberList",method = RequestMethod.GET)
	public ModelAndView adminmemberList(ModelAndView mav,
										@RequestParam (required=false,defaultValue="1") String pg ) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		List<AdminmemberDTO> list = adminmemberService.getAdminmemberList(startNum,endNum);
		//페이징 처리
		int totalA = adminmemberService.getTotalA();
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.makePagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmemberList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	
	
	/* description : 멘티리스트 화면페이지 */
	@RequestMapping(value="adminmenteeList",method = RequestMethod.GET)
	public String adminmenteeList(Model model) {
		model.addAttribute("display", "/adminmember/adminmenteeList.jsp");
		return "/admin/adminMain";
	}
	
	
	/* description : 멘토리스트 화면페이지 */
	@RequestMapping(value="adminmentoList",method = RequestMethod.GET)
	public String adminmentoList(Model model) {
		model.addAttribute("display", "/adminmember/adminmentoList.jsp");
		return "/admin/adminMain";
	}
}
