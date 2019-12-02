package adminmember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmemberPaging;
import adminmember.bean.AdminmentorBoardListDTO;
import adminmember.bean.AdminmentorDTO;
import adminmember.bean.AdminmentorSalesListDTO;
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
		int totalA = adminmemberService.getMemeberTotalA();
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.memberPagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmemberList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	/* description : 회원리스트 서치 */
	@RequestMapping(value="adminmemberSearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminmemberSearch(ModelAndView mav,
									@RequestParam (required=false,defaultValue="1") String pg,
									@RequestParam String adminmemberKeyword) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminmemberKeyword", adminmemberKeyword);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<AdminmemberDTO> list = adminmemberService.getSearchadminmemberList(map);
		//페이징 처리
		int totalA = adminmemberService.getSearchmemeberTotalA(map);
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.memberSearchPagingHTML();
		
		mav.addObject("adminmemberKeyword",adminmemberKeyword);
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmemberList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
/*----멘토--------------------------------------------------------------------------------------------------*/
	/* description : 멘토리스트 화면페이지 */
	@RequestMapping(value="adminmentorList",method = RequestMethod.GET)
	public ModelAndView adminmentorList(ModelAndView mav,
									   @RequestParam (required=false,defaultValue="1") String pg,
									   @RequestParam (required=false,defaultValue="0")int state) {
		List<AdminmentorDTO> list = null;
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("state", state);
		if(state == 0)
			list = adminmemberService.getAdminmentorList(map);
		else if(state == 1)
			list = adminmemberService.getAdminmentorList(map);
		else if(state == 2) {
			list = adminmemberService.getAdminmentorList(map);
			state=2;
		}

		//페이징 처리
		int totalA = adminmemberService.getMentorTotalA();
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.mentorPagingHTML();
		
		mav.addObject("state",state);
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmentorList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘토리스트 서치 */
	@RequestMapping(value="adminmentorSearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminmentorSearch(ModelAndView mav,
									@RequestParam (required=false,defaultValue="1") String pg,
									@RequestParam String adminmentorKeyword) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminmentorKeyword", adminmentorKeyword);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<AdminmentorDTO> list = adminmemberService.getSearchadminmentorList(map);
		//페이징 처리
		int totalA = adminmemberService.getSearchmentorTotalA(map);
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.mentorSearchPagingHTML();
		
		mav.addObject("adminmentorKeyword",adminmentorKeyword);
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmentorList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘토신청리스트 화면페이지 */
	@RequestMapping(value="adminmentorApplyList",method = RequestMethod.GET)
	public ModelAndView adminmentorApplyList(ModelAndView mav,
									   @RequestParam (required=false,defaultValue="1") String pg ) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		List<AdminmentorDTO> list = adminmemberService.getAdminmentorApplyList(startNum,endNum);
		
		//페이징 처리
		int totalA = adminmemberService.getMentorApplyTotalA();
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.mentorApplyPagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmentorApplyList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘토리스트 서치 */
	@RequestMapping(value="adminmentorApplySearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminmentorApplySearch(ModelAndView mav,
									@RequestParam (required=false,defaultValue="1") String pg,
									@RequestParam String adminmentorApplyKeyword) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminmentorApplyKeyword", adminmentorApplyKeyword);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<AdminmentorDTO> list = adminmemberService.getSearchadminmentorApplyList(map);
		//페이징 처리
		int totalA = adminmemberService.getSearchmentorApplyTotalA(map);
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.mentorSearchPagingHTML();
		
		mav.addObject("adminmentorApplyKeyword",adminmentorApplyKeyword);
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmentorList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘토 승인 */
	@RequestMapping(value="adminmentorSuccess",method = RequestMethod.POST)
	@ResponseBody
	public void adminmentorSuccess(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		adminmemberService.adminmentorSuccess(map);
	}
	
	/* description : 멘토승인 거절 */
	@RequestMapping(value="adminmentorReject",method = RequestMethod.POST)
	@ResponseBody
	public void adminmentorReject(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		adminmemberService.adminmentorReject(map);
	}
	/* description : 명예멘토 리스트 */
	@RequestMapping(value="adminmentorSales",method = RequestMethod.GET)
	public ModelAndView adminmentorSales(ModelAndView mav,
			   							@RequestParam (required=false,defaultValue="1") String pg ) {
		//사람 매출
		List<AdminmentorSalesListDTO> salesList = adminmemberService.getMentorSales();
		//사람 리스트 개수
		List<AdminmentorBoardListDTO> boardList = adminmemberService.getMentorBoard();
		
		mav.addObject("salesList", salesList);
		mav.addObject("boardList", boardList);
		mav.addObject("display", "/adminmember/adminmentorSales.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 명예멘토업글 */
	@RequestMapping(value="honorMentor",method = RequestMethod.POST)
	@ResponseBody
	public void honorMentor(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		System.out.println(check[0]);
		adminmemberService.honorMentor(map);
	}
	
/*----멘티--------------------------------------------------------------------------------------------------*/	
	/* description : 멘티리스트 화면페이지 */
	@RequestMapping(value="adminmenteeList",method = RequestMethod.GET)
	public ModelAndView adminmenteeList(ModelAndView mav,
										@RequestParam (required=false,defaultValue="1") String pg ) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		List<AdminmemberDTO> list = adminmemberService.getAdminmenteeList(startNum,endNum);
		
		//페이징 처리
		int totalA = adminmemberService.getMenteeTotalA();
		
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.menteePagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmenteeList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘티리스트 서치 */
	@RequestMapping(value="adminmenteeSearch", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView adminmenteeSearch(ModelAndView mav,
									@RequestParam (required=false,defaultValue="1") String pg,
									@RequestParam String adminmenteeKeyword) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminmenteeKeyword", adminmenteeKeyword);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		List<AdminmemberDTO> list = adminmemberService.getSearchadminmenteeList(map);
		//페이징 처리
		int totalA = adminmemberService.getSearchmenteeTotalA(map);
		adminmemberPaging.setCurrentPage(Integer.parseInt(pg));
		adminmemberPaging.setPageBlock(3);
		adminmemberPaging.setPageSize(10);
		adminmemberPaging.setTotalA(totalA);
		adminmemberPaging.menteeSearchPagingHTML();
		
		mav.addObject("adminmenteeKeyword",adminmenteeKeyword);
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminmemberPaging", adminmemberPaging);
		mav.addObject("display", "/adminmember/adminmenteeList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
}
