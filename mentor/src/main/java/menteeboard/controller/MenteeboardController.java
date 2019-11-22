package menteeboard.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import menteeboard.bean.MenteeboardDTO;
import menteeboard.bean.MenteeboardLikeDTO;
import menteeboard.bean.MenteeboardPaging;
import menteeboard.service.MenteeboardService;
import menteeboardReply.bean.MenteeboardReplyDTO;
import menteeboardReply.service.MenteeboardReplyService;

/**
 * 
 * @Title : 멘티 게시판 구현
 * @author : yangjaewoo
 * @date : 2019. 11. 3.
 */
@Controller
@RequestMapping(value="menteeboard")
public class MenteeboardController {
	
	@Autowired
	private MenteeboardService menteeboardService;
	@Autowired
	private MenteeboardReplyService menteeboardReplyService;
	@Autowired
	private MenteeboardPaging menteeboardPaging;
	@Autowired
	private MenteeboardLikeDTO menteeboardLikeDTO;
	@Autowired
	private MemberDTO memberDTO;

	@RequestMapping(value = "menteeboardList",method=RequestMethod.GET)
	public String menteeboardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
	    model.addAttribute("display","/menteeboard/menteeboardList.jsp");
	    return "/main/index";
	}
	
	
	/**
	 * 
	 * @Title : 리스트 출력
	 * @Author : yangjaewoo, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value="getMenteeboardList" , method=RequestMethod.POST)
	public ModelAndView getMenteeboardList(@RequestParam String pg,
			 							   				 HttpSession session,
			 							   				 HttpServletResponse response) {
		
		int endNum = Integer.parseInt(pg) * 10; //한페이지당 5개
		int startNum = endNum - 9;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<MenteeboardDTO> list = menteeboardService.getMenteeboardList(map);

		
		int totalA = menteeboardService.getTotalA();
		menteeboardPaging.setCurrentPage(Integer.parseInt(pg));
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(10);
		menteeboardPaging.setTotalA(totalA);
		menteeboardPaging.makePagingHTML();
		
		ModelAndView mav=new ModelAndView();
		
		memberDTO = (MemberDTO)session.getAttribute("memDTO"); 
		
		//로그인 여부에 따라
		if(memberDTO != null) {
			String nickname = memberDTO.getMember_nickname();
			mav.addObject("memNickname" , nickname);
			
			//조회수(쿠키 생성) 
			if(nickname != null) {
				Cookie cookie = new Cookie("memHit","0");
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}
		}
		
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging", menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="menteeboardWriteForm" , method=RequestMethod.GET)
	public ModelAndView menteeboardWriteForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("display","/menteeboard/menteeboardWriteForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	/**
	 * 
	 * @Title : 글작성 메소드
	 * @Author : yangjaewoo, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value="menteeboardWrite" , method=RequestMethod.POST)
	@ResponseBody
	public void boardWrite(@RequestParam Map<String, String> map,
							HttpSession session) {
		
		memberDTO = (MemberDTO)session.getAttribute("memDTO"); 
		
		map.put("nickname", memberDTO.getMember_nickname());
		map.put("email", memberDTO.getMember_email());
		map.put("profile" , "");
		System.out.println("map = " + map);
		menteeboardService.menteeboardWrite(map);
	}
	
	/**
	 * 
	 * @Title : 썸머노트 이미지 업로드시 사용하는 메소드
	 * @Author : yangjaewoo, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "menteeboardImage", method = RequestMethod.POST)
	@ResponseBody
	   public String noticeboardImage(@RequestParam("file") MultipartFile file) {
	      String filePath = "/Users/yangjaewoo/MentorMan/mentor/src/main/webapp/storage";
	      String fileName = file.getOriginalFilename();
	      File files = new File(filePath, fileName);
	      
	      System.out.println(fileName);
	      try {
	         FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(files));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      
	      return fileName;
	   }
	
	
	/**
	 * 
	 * @Title : 직무유형 변경되었을때 사용되는 메소드
	 * @Author : yangjaewoo, @Date : 2019. 11. 5.
	 */
	@RequestMapping(value = "getMenteeboardListJob", method = RequestMethod.GET)
	public ModelAndView getMenteeboardListJob(@RequestParam String pg,
											  @RequestParam String job_code) {
		
		int endNum = Integer.parseInt(pg) * 10; //한페이지당 5개
		int startNum = endNum - 9;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("job_code", job_code);
		
		List<MenteeboardDTO> list = menteeboardService.getMenteeboardListJob(map);
		
		int totalAJob = menteeboardService.getTotalAJob(job_code);
		menteeboardPaging.setCurrentPage(Integer.parseInt(pg));
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(10);
		menteeboardPaging.setTotalA(totalAJob);
		menteeboardPaging.makeJobPagingHTML(job_code);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging", menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	@RequestMapping(value="menteeboardView" , method=RequestMethod.GET)
	public ModelAndView menteeboardView(@RequestParam String seq,
										 @RequestParam String pg,
										 HttpSession session,
										 HttpServletRequest request,
										 HttpServletResponse response
										 ){
		//쿠키조회
		Cookie[] getCookie = request.getCookies();
		if(getCookie != null) {
			for(int i =0; i<getCookie.length; i++){
				if(getCookie[i].getName().equals("memHit")){
					//hit + 1
					menteeboardService.menteeboardHit(Integer.parseInt(seq));
					
					getCookie[i].setMaxAge(0);
					response.addCookie(getCookie[i]);
				}
			}
		}
		MenteeboardDTO menteeboardDTO= menteeboardService.getMenteeboard(Integer.parseInt(seq));
		
		memberDTO = (MemberDTO)session.getAttribute("memDTO");
		
		//좋아요테이블에 값이 있는지 조사
		menteeboardLikeDTO.setMenteeboardLike_mb_seq(Integer.parseInt(seq));
		menteeboardLikeDTO.setMenteeboardLike_mb_email(memberDTO.getMember_email());
		int heart = menteeboardService.menteeboardSelect(menteeboardLikeDTO);
		
		//게시글 댓글 리스트
		int endNum = Integer.parseInt(pg) * 15; //한페이지당 5개
		int startNum = endNum - 14;
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("startNum", startNum);
		map3.put("endNum", endNum);
		map3.put("menteeboard_seq", Integer.parseInt(seq));
		List<MenteeboardReplyDTO> list = menteeboardReplyService.getAllMenteeboardreply(map3);
		
		//게시글에 댓글 갯수
		int cnt = menteeboardReplyService.getTotalReplyA(Integer.parseInt(seq));
		
		menteeboardPaging.setCurrentPage(Integer.parseInt(pg));
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(15);
		menteeboardPaging.setTotalA(cnt);
		menteeboardPaging.makeReplyPagingHTML(Integer.parseInt(seq));
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt" , cnt);
		mav.addObject("menteeboardPaging" , menteeboardPaging);
		mav.addObject("list" , list);
		mav.addObject("memEmail" , memberDTO.getMember_email());
		mav.addObject("memNicname" , memberDTO.getMember_nickname());
		mav.addObject("heart" , heart);
		mav.addObject("seq" , Integer.parseInt(seq));
		mav.addObject("pg" , Integer.parseInt(pg));
		mav.addObject("menteeboardDTO", menteeboardDTO);
		mav.addObject("display","/menteeboard/menteeboardView.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	
	/**
	 * 
	 * @Title : 뷰 삭제 메소드
	 * @Author : yangjaewoo, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value="menteeboardDelete" , method=RequestMethod.GET)
	public ModelAndView boardDelete(@RequestParam String seq) {
		menteeboardService.menteeboardDelete(Integer.parseInt(seq));
		return new ModelAndView("redirect:/menteeboard/menteeboardList");
	}
	
	
	@RequestMapping(value="menteeboardModifyForm" , method=RequestMethod.GET)
	public ModelAndView menteeboardModifyForm(@RequestParam String pg,
										@RequestParam String seq) {
		MenteeboardDTO menteeboardDTO = menteeboardService.getMenteeboard(Integer.parseInt(seq));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("menteeboardDTO" , menteeboardDTO);
		mav.addObject("pg" , Integer.parseInt(pg));
		mav.addObject("seq" , Integer.parseInt(seq));
		mav.addObject("display","/menteeboard/menteeboardModifyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}
	@RequestMapping(value="menteeboardModify" , method=RequestMethod.POST)
	@ResponseBody
	public void menteeboardModify(@RequestParam String seq,
							@RequestParam String menteeModifyFormSubject,
							@RequestParam String summernote,
							@RequestParam String job_code_ModifyForm) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("seq",seq);
		map.put("subject",menteeModifyFormSubject);
		map.put("content",summernote);
		map.put("job_code" , job_code_ModifyForm);
		
		menteeboardService.menteeboardModify(map);
	}
	
	
	@RequestMapping(value = "menteeboardLike", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
	public int menteeboardLike(HttpServletRequest httpRequest, HttpSession session) throws Exception {
        int heart = Integer.parseInt(httpRequest.getParameter("heart"));
        int menteeboard_seq = Integer.parseInt(httpRequest.getParameter("menteeboard_seq"));
        
        memberDTO = (MemberDTO)session.getAttribute("memDTO");
        
        
        menteeboardLikeDTO.setMenteeboardLike_mb_seq(menteeboard_seq);
        menteeboardLikeDTO.setMenteeboardLike_mb_email(memberDTO.getMember_email());

        
        if(heart >= 1) {
            menteeboardService.menteeboardLikeDelete(menteeboardLikeDTO);
            heart=0;
        } else {
        	menteeboardService.menteeboardLikeInsert(menteeboardLikeDTO);
            heart=1;
        }
        
        return heart;
	}
	/**
	 * 
	 * @Title : 게시판 검색
	 * @Author : yangjaewoo, @Date : 2019. 11. 20.
	 */
	
	@RequestMapping(value = "menteeboardSearch", method = RequestMethod.POST)
	public ModelAndView menteeboardSearch(@RequestParam Map<String,String> map){
		
		int endNum = Integer.parseInt(map.get("pgInput"))*10;
		int startNum = endNum-9;
		
		map.put("startNum", startNum+"");
		map.put("endNum", endNum+"");
		
		List<MenteeboardDTO> list = menteeboardService.menteeboardSearch(map);
		
		int totalA = menteeboardService.getSearchTotalA(map);
		menteeboardPaging.setCurrentPage(Integer.parseInt(map.get("pgInput")));
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(10);
		menteeboardPaging.setTotalA(totalA);
		menteeboardPaging.makeSearchContentPagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("search_text",map.get("search_text"));
		mav.addObject("totalA", totalA);
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging",menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
		
	}
 
	
}
