package adminboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import adminboard.bean.AdminboardPaging;
import adminboard.bean.AdminnoticeboardDTO;
import adminboard.service.AdminboardService;
import essayboard.bean.EssayboardDTO;
import meetingboard.bean.MeetingboardDTO;
import menteeboard.bean.MenteeboardDTO;

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
										Model model) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		List<AdminnoticeboardDTO> list = adminboardService.getAdmninnoticeboardList(startNum,endNum);
		//페이징처리
		int totalA = adminboardService.getTotalA();
		adminboardPaging.setCurrentPage(Integer.parseInt(pg));
		adminboardPaging.setPageBlock(3);
		adminboardPaging.setPageSize(10);
		adminboardPaging.setTotalA(totalA);
		adminboardPaging.noticeboardPagingHTML();
		
		model.addAttribute("list", list);
		model.addAttribute("pg", pg);
		model.addAttribute("adminboardPaging", adminboardPaging);
		model.addAttribute("display", "/adminboard/adminnoticeboardList.jsp");
		return "/admin/adminMain";
	}
	
	/* description : 공지사항 글작성 페이지*/
	@RequestMapping(value = "adminnoticeboardWriteForm", method = RequestMethod.GET)
	public ModelAndView adminnoticeboardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/adminboard/adminnoticeboardWriteForm.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 공지사항 글작성*/
	@RequestMapping(value = "adminnoticeboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void adminnoticeboardWrite(@RequestParam Map<String,String> map) {
		adminboardService.adminnoticeboardWrite(map); 
	}
	
	@RequestMapping(value = "adminnoticeboardImage", method = RequestMethod.POST)
	@ResponseBody
	public String adminnoticeboardImage(@RequestParam("file") MultipartFile image) {
		String filePath = "C:/Users/dkstk/github/MentorMan/mentor/src/main/webapp/storage/admin";
		String fileName = image.getOriginalFilename();
		File files = new File(filePath);
		if(!files.exists()) {
			files.mkdirs();
		}
		if(fileName != "") {
			File file = new File(filePath, fileName);
			try {
				FileCopyUtils.copy(image.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
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
	// 수정 폼
	@RequestMapping(value = "adminnoticeboardmodifyForm", method = RequestMethod.GET)
	public String adminnoticeboardmodifyForm(@RequestParam String seq ,@RequestParam String pg,
											Model model) {
		AdminnoticeboardDTO adminnoticeboardDTO = adminboardService.adminnoticeboardView(Integer.parseInt(seq));
		
		model.addAttribute("adminnoticeboardDTO",adminnoticeboardDTO);
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/adminboard/adminnoticeboardmodifyForm.jsp");
		return "/admin/adminMain";
	} 
	
	@RequestMapping(value = "noticeboardModify", method = RequestMethod.POST)
	@ResponseBody
	public void noticeboardModify(@RequestParam Map<String,String> map) {
		adminboardService.noticeboardModify(map);
	}
	
/*멘티 게시판---------------------------------------------------------------------------------------------*/
	/* description : 멘티게시판 */
	@RequestMapping(value="adminmenteeList",method = RequestMethod.GET)
	public ModelAndView adminmenteeList(@RequestParam (required=false,defaultValue="1") String pg) {
		
		int endNum = Integer.parseInt(pg) * 10; //한페이지당 5개
		int startNum = endNum - 9;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<MenteeboardDTO> list = adminboardService.getMenteeboardList(map);

		
		int totalA = adminboardService.getMenteeTotalA();
		adminboardPaging.setCurrentPage(Integer.parseInt(pg));
		adminboardPaging.setPageBlock(5);
		adminboardPaging.setPageSize(10);
		adminboardPaging.setTotalA(totalA);
		adminboardPaging.menteePagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("menteeboardPaging", adminboardPaging);
		mav.addObject("display", "/adminboard/adminmenteeList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
/*에세이보드 리스트------------------------------------------------------------------------------------------*/
	/* description : 에세이보드 리스트 */
	@RequestMapping(value="adminessayList",method = RequestMethod.GET)
	public ModelAndView adminessayList(@RequestParam(required = false, defaultValue = "1") String pg,
			   						   @RequestParam(required = false, defaultValue = "0") String flag) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 페이지 당 9개씩
		int endNum = Integer.parseInt(pg) * 9;
		int startNum = endNum - 8;
		
		map.put("endNum", endNum);
		map.put("startNum", startNum);
		
		// 총 글 수
		int totalA = adminboardService.getessayTotalA();
	
		map.put("totalA", totalA);
		
		ModelAndView modelAndView = new ModelAndView();
		//  신규 에세이 리스트
		 List<EssayboardDTO>list = adminboardService.getNewEssay(map);	


		adminboardPaging.setCurrentPage(Integer.parseInt(pg));
		adminboardPaging.setPageBlock(3);
		adminboardPaging.setPageSize(9);
		adminboardPaging.setTotalA(totalA);
		adminboardPaging.essayboardPagingHTML();
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("essayboardPaging", adminboardPaging);
		modelAndView.addObject("list", list);
		modelAndView.addObject("display", "/adminboard/adminessayList.jsp");
		modelAndView.setViewName("/admin/adminMain");

		return modelAndView;
	}
/*모임 게시판---------------------------------------------------------------------------------------------*/	
	/* description : 모임게시판 리스트 11.22수정 */
	@RequestMapping(value="adminmeetingboardList",method = RequestMethod.GET)
	public ModelAndView adminmeetingboardList(@RequestParam(required = false, defaultValue = "1") String pg) {
		// 1페이지당 9개
		int endNum = Integer.parseInt(pg) * 9;
		int startNum = endNum - 8;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<MeetingboardDTO> meetingboardList = adminboardService.getMeetingboardList(map);

		// 페이징 처리
		int totalA = adminboardService.getMeetingboardTotalA();
		adminboardPaging.setCurrentPage(Integer.parseInt(pg));
		adminboardPaging.setPageBlock(3);
		adminboardPaging.setPageSize(9);
		adminboardPaging.setTotalA(totalA);
		adminboardPaging.meetingPagingHTML();

		ModelAndView mav = new ModelAndView();
		mav.addObject("totalA", totalA);
		mav.addObject("pg", pg);
		mav.addObject("meetingboardList", meetingboardList);
		mav.addObject("meetingboardPaging", adminboardPaging);
		mav.addObject("display", "/adminboard/adminmeetingboardList.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
//	/* description : 모임게시판 삭제 11.22수정 */
//	@RequestMapping(value="adminmeetingboardDelete",method = RequestMethod.POST)
//	@ResponseBody
//	public void adminmeetingboardDelete(@RequestParam String[] check) {
//		Map<String, String[]> map = new HashMap<String, String[]>();
//		for (String seq : check) {
//			System.out.println(seq);
//		}
//		map.put("check", check);
//		adminboardService.adminmeetingboardDelete(map);
//	}

}
