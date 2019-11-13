package menteeboardReply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import menteeboard.bean.MenteeboardPaging;
import menteeboardReply.bean.MenteeboardReplyDTO;
import menteeboardReply.service.MenteeboardReplyService;

/**
 * 
 * @Title : 게시글의 댓글 기능
 * @author : yangjaewoo
 * @date : 2019. 11. 7.
 */
@Controller
@RequestMapping(value="menteeboardReply")
public class MenteeboardReplyController {

	@Autowired
	private MenteeboardReplyService menteeboardReplyService;
	@Autowired
	private MenteeboardPaging menteeboardPaging;
	
	@RequestMapping(value="menteeboardReplyWrite" , method=RequestMethod.POST)
	public ModelAndView menteeboardReplyWrite(@RequestParam Map<String, String> map) {
		
		int menteeboard_seq = Integer.parseInt(map.get("menteeboard_seq"));
		String email = map.get("email");
		String nickname = map.get("nickname");
		String content = map.get("content");
		String memEmail = map.get("memEmail"); //로그인 되어있는 이메일
		int pg = Integer.parseInt(map.get("pg"));
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("menteeboard_seq" , menteeboard_seq);
		map2.put("email" , email );
		map2.put("nickname" , nickname);
		map2.put("content" , content);
		System.out.println("map2 :: "+ map2 );
		//DB 댓글 저장
		menteeboardReplyService.replyWrite(map2);
		
		//페이징처리를 위한 사전단계
		int endNum = pg * 15; //한페이지당 5개
		int startNum = endNum - 14;
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("startNum", startNum);
		map3.put("endNum", endNum);
		map3.put("menteeboard_seq", menteeboard_seq);
		
		//게시물에 달려있는 댓글 모두 가져오기
		List<MenteeboardReplyDTO> list = menteeboardReplyService.getAllMenteeboardreply(map3);

		//댓글 페이징 처리
		int totalA = menteeboardReplyService.getTotalReplyA(menteeboard_seq);
		menteeboardPaging.setCurrentPage(pg);
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(15);
		menteeboardPaging.setTotalA(totalA);
		menteeboardPaging.makeReplyPagingHTML(menteeboard_seq);
		
		//댓글쓴 게시물의 reply +1
		menteeboardReplyService.menteeboardReplyUp(menteeboard_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging", menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
		
	}
	
	
	@RequestMapping(value="menteeboardReplyDelete" , method=RequestMethod.POST)
	public ModelAndView menteeboardReplyDelete(@RequestParam Map<String, String> map) {
		int menteeboard_seq = Integer.parseInt(map.get("menteeboard_seq"));
		int seq_trans = Integer.parseInt(map.get("seq_trans"));
		int pg = Integer.parseInt(map.get("pg"));
		
		//게시물 삭제
		menteeboardReplyService.replyDelete(seq_trans);
		
		// 페이징처리를 위한 사전단계
		int endNum = pg * 15; // 한페이지당 5개
		int startNum = endNum - 14;
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("startNum", startNum);
		map3.put("endNum", endNum);
		map3.put("menteeboard_seq", menteeboard_seq);
		List<MenteeboardReplyDTO> list = menteeboardReplyService.getAllMenteeboardreply(map3);
		
		//페이징처리
		int totalA = menteeboardReplyService.getTotalReplyA(menteeboard_seq);
		menteeboardPaging.setCurrentPage(pg);
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(15);
		menteeboardPaging.setTotalA(totalA);
		menteeboardPaging.makeReplyPagingHTML(menteeboard_seq);
		
		
		//댓글쓴 게시물의 reply -1
		menteeboardReplyService.menteeboardReplyDown(menteeboard_seq);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging", menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	
	@RequestMapping(value="menteeboardReplyModify" , method=RequestMethod.POST)
	public ModelAndView menteeboardReplyModify(@RequestParam Map<String, String> map) {
		int menteeboard_seq = Integer.parseInt(map.get("menteeboard_seq"));
		int pg = Integer.parseInt(map.get("pg"));
		String modifyText = map.get("modifyText"); //변경될 내용
		int seq_trans = Integer.parseInt(map.get("seq_trans"));
		
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("seq", seq_trans);
		map2.put("content", modifyText);
		//게시물 수정
		menteeboardReplyService.replyModify(map2);
		
		int endNum = pg * 15; // 한페이지당 5개
		int startNum = endNum - 14;
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("startNum", startNum);
		map3.put("endNum", endNum);
		map3.put("menteeboard_seq", menteeboard_seq);
		List<MenteeboardReplyDTO> list = menteeboardReplyService.getAllMenteeboardreply(map3);
		
		//페이징처리
		int totalA = menteeboardReplyService.getTotalReplyA(menteeboard_seq);
		menteeboardPaging.setCurrentPage(pg);
		menteeboardPaging.setPageBlock(5);
		menteeboardPaging.setPageSize(15);
		menteeboardPaging.setTotalA(totalA);
		menteeboardPaging.makeReplyPagingHTML(menteeboard_seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("menteeboardPaging", menteeboardPaging);
		mav.setViewName("jsonView");
		return mav;
		
	}
		
		
	
	
	
	
	
}
