package noticeboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import noticeboard.bean.BoardPaging;
import noticeboard.bean.NoticeboardDTO;
import noticeboard.service.NoticeboardService;

@Controller
@RequestMapping("/noticeboard")
public class NoticeboardController {
	@Autowired
	private NoticeboardService noticeboardService;
	@Autowired
	private BoardPaging boardPaging;
	/**
	 * 
	 * @Title : 공지사항 글 작성
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	@RequestMapping(value = "noticeboardWriteForm", method = RequestMethod.GET)
	public ModelAndView noticeBoardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/noticeboard/noticeboardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	/**
	 * 
	 * @Title : 공지사항 글 DB저장
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	 //HttpSession session 나중에 관리자 email 받아서 써야됨
	@RequestMapping(value = "noticeboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void noticeboardWrite(@RequestParam Map<String,String> map) {
		
		noticeboardService.noticeboardWrite(map); 
	}
	
	@RequestMapping(value = "noticeboardImage", method = RequestMethod.POST)
	@ResponseBody
	public String noticeboardImage(@RequestParam("file") MultipartFile file) {
		String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage";
		String fileName = file.getOriginalFilename();
		File files = new File(filePath, fileName);
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	/**
	 * @Title : List 창
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	@RequestMapping(value = "noticeboardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model, HttpSession session, HttpServletResponse response) {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("memDTO"); 
	      
	      //로그인 여부에 따라
	      if(memberDTO != null) {
	         String nickname = memberDTO.getMember_nickname();
	         model.addAttribute("memNickname" , nickname);
	         
	         //조회수(쿠키 생성) 
	         if(nickname != null) {
	            Cookie cookie = new Cookie("memHit","0");
	            cookie.setMaxAge(60*60*24);
	            response.addCookie(cookie);
	         }
	      }
		model.addAttribute("pg", pg);
		model.addAttribute("display","/noticeboard/noticeboardList.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : List의 값 가져오기 및 페이징 처리
	 * @Author : kujun95, @Date : 2019. 11. 2.
	 */
	//공지글은 로그인 하지 않아도 확인이 가능하게 하는가? 아니면 로그인 하면 볼 수 있게하는지 논의하기
	@RequestMapping(value = "getBoardList", method = RequestMethod.POST)
	public ModelAndView getBoardList(@RequestParam(required = false, defaultValue = "1") String pg) {
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", endNum);
		map.put("startNum", startNum);
		List<NoticeboardDTO> list = noticeboardService.getBoardList(map);
		
		int totalA = noticeboardService.getTotalA();
		boardPaging.setCurrentPage(Integer.parseInt(pg));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		boardPaging.makePagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardPaging", boardPaging);
		mav.addObject("totalA", totalA);
		mav.addObject("startNum", startNum);
		mav.addObject("endNum", endNum);
		mav.addObject("list", list);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * @Title : List 글 삭제
	 * @Author : kujun95, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "noticeboardDelete", method = RequestMethod.POST)
	public String noticeboardDelete(@RequestParam String[] check, Model model) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("array", check);
		noticeboardService.noticeboardDelete(map);
		model.addAttribute("display","/noticeboard/noticeboardDelete.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 공지글 view
	 * @Author : kujun95, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "noticeboardView", method = RequestMethod.GET)
	public String noticeboardView(@RequestParam String seq, @RequestParam String pg, Model model, HttpServletResponse response, HttpServletRequest request) {
		NoticeboardDTO noticeboardDTO = noticeboardService.getNoticeboardView(Integer.parseInt(seq));
		Cookie[] getCookie = request.getCookies();
	      if(getCookie != null) {
	         for(int i =0; i<getCookie.length; i++){
	            if(getCookie[i].getName().equals("memHit")){
	               //hit + 1
	               noticeboardService.noticeboardViewHit(Integer.parseInt(seq));
	               
	               getCookie[i].setMaxAge(0);
	               response.addCookie(getCookie[i]);
	            }
	         }
	      }
		model.addAttribute("pg", pg);
		model.addAttribute("noticeboardDTO", noticeboardDTO);
		model.addAttribute("display", "/noticeboard/noticeboardView.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : List 검색
	 * @Author : kujun95, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "noticeboardSearch", method = RequestMethod.POST)
	public ModelAndView noticeboardSearch(@RequestParam Map<String,String> map) {
		int endNum = Integer.parseInt(map.get("pg"))*10;
		int startNum = endNum-9;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		List<NoticeboardDTO> list = noticeboardService.noticeboardSearch(map);
		
		int totalA = noticeboardService.getSearchTotalA(map);
		boardPaging.setCurrentPage(Integer.parseInt(map.get("pg")));
		boardPaging.setPageBlock(3);
		boardPaging.setPageSize(10);
		boardPaging.setTotalA(totalA);
		boardPaging.makeSelectPagingHTML();
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchText",map.get("searchText"));
		mav.addObject("totalA", totalA);
		mav.addObject("startNum", startNum);
		mav.addObject("endNum", endNum);
		mav.addObject("list", list);
		mav.addObject("boardPaging",boardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	/**
	 * @Title : 공지글 수정 Form
	 * @Author : kujun95, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "noticeboardUpdateForm", method = RequestMethod.GET)
	public String noticeboardUpdateForm(@RequestParam String seq ,@RequestParam String pg, Model model) {
		NoticeboardDTO noticeboardDTO = noticeboardService.getNoticeboardView(Integer.parseInt(seq));
		
		model.addAttribute("noticeboardDTO",noticeboardDTO);
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/noticeboard/noticeboardUpdateForm.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value = "noticeboardUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void noticeboardUpdate(@RequestParam Map<String,String> map) {
		noticeboardService.noticeboardUpdate(map);
	}
	
}
