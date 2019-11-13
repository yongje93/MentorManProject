package adminboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import adminboard.bean.AdminboardPaging;
import adminboard.bean.AdminnoticeboardDTO;
import adminboard.service.AdminboardService;

/** 
 * @Title : 관리자페이지 게시판관리 컨트롤러입니다
 * @author : 안상구
 * @date : 2019. 11. 5.
 * 이해 안가시는 부분이있으면 연락주세요
 */

@Controller
@RequestMapping(value="adminboard")
public class AdminBoardController {
	
	@Autowired
	private AdminboardService adminboardService;
	@Autowired
	private AdminboardPaging adminboardPaging;
	
	/* description : 공지사항게시판 List뿌리고 paging처리*/
	@RequestMapping(value="adminnoticeboardList",method = RequestMethod.GET)
	public String admninnoticeboardList(@RequestParam (required=false,defaultValue="1") String pg, 
										Model model, 
										HttpSession session ) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		List<AdminnoticeboardDTO> list = adminboardService.getAdmninnoticeboardList(startNum,endNum);
		//페이징처리
		int totalA = adminboardService.getTotalA();
		adminboardPaging.setCurrentPage(Integer.parseInt(pg));
		adminboardPaging.setPageBlock(3);
		adminboardPaging.setPageSize(10);
		adminboardPaging.setTotalA(totalA);
		adminboardPaging.makePagingHTML();
		
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		model.addAttribute("adminboardPaging", adminboardPaging);
		model.addAttribute("display", "/adminboard/adminnoticeboardList.jsp");
		return "/admin/adminMain";
	}
	/* description : 공지사항 글삭제*/
	@RequestMapping(value="adminnoticeboardDelete",method = RequestMethod.POST)
	@ResponseBody
	public void adminnoticeboardDelete(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		adminboardService.adminnoticeboardDelete(map);
	}
	
	/* description : 공지사항 글 보기*/
	@RequestMapping(value="adminnoticeboardView",method = RequestMethod.GET)
	public String adminnoticeboardView(@RequestParam String pg, 
									   @RequestParam String seq,
									   Model model) {
		AdminnoticeboardDTO adminnoticeboardDTO = adminboardService.adminnoticeboardView(Integer.parseInt(seq));
		model.addAttribute("display", "/adminboard/adminnoticeboardView.jsp");
		model.addAttribute("pg", pg);
		model.addAttribute("adminnoticeboardDTO", adminnoticeboardDTO);
		return "/admin/adminMain";
	}
	
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
}
