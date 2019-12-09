package adminReply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adminReply.bean.AdminreplyPaging;
import adminReply.service.AdminreplyService;
import meetingboard.bean.ReviewDTO;
import menteeboardReply.bean.MenteeboardReplyDTO;



/** 
 * @Title : 관리자페이지 댓글관리 컨트롤러입니다
 * @author : 안상구
 * @date : 2019. 12. 3.
 * 이해 안가시는 부분이있으면 연락주세요
 */


@Controller
@RequestMapping(value="adminreply")
public class AdminReplyController {

	@Autowired
	private AdminreplyService adminreplyService;
	
	@Autowired
	private AdminreplyPaging adminreplyPaging;
	
	
	/* description : 고맙습니다댓글 화면페이지 & 리스트 뿌리기 & 페이지 처리 */
	@RequestMapping(value="adminThankyou",method = RequestMethod.GET)
	public ModelAndView adminThankyou(ModelAndView mav,
										@RequestParam (required=false,defaultValue="1") String pg) {
		
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<ReviewDTO> list = adminreplyService.getAdminThankyou(map);
		//페이징 처리
		int totalA = adminreplyService.getThankyouTotalA();
		adminreplyPaging.setCurrentPage(Integer.parseInt(pg));
		adminreplyPaging.setPageBlock(3);
		adminreplyPaging.setPageSize(10);
		adminreplyPaging.setTotalA(totalA);
		adminreplyPaging.tankyouPagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminreplyPaging", adminreplyPaging);
		mav.addObject("display", "/adminreply/adminThankyou.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 고맙습니다 댓글 삭제 12.03추가 */
	@RequestMapping(value="meetingReviewDelete",method = RequestMethod.POST)
	@ResponseBody
	public void meetingReviewDelete(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		adminreplyService.meetingReviewDelete(map);
	}
	
	/* description : 고맙습니다 댓글 view 12.04추가 */
	@RequestMapping(value="thankyouView",method = RequestMethod.GET)
	public ModelAndView thankyouView(@RequestParam String seq) {
		ModelAndView mav = new ModelAndView();
		int review_seq = Integer.parseInt(seq);
		ReviewDTO reviewDTO = adminreplyService.thankyouView(review_seq);
		mav.addObject("reviewDTO", reviewDTO);
		mav.addObject("display", "/adminreply/thankyouView.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	
	/* description : 멘티게시판댓글 화면페이지 & 리스트 뿌리기 & 페이지 처리 */
	@RequestMapping(value="adminmenteeReply",method = RequestMethod.GET)
	public ModelAndView adminmenteeReply(ModelAndView mav,
										@RequestParam (required=false,defaultValue="1") String pg) {
		
		int endNum = Integer.parseInt(pg)*10;
		int startNum = endNum-9;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);


		List<MenteeboardReplyDTO> list= adminreplyService.getAdminmenteeReply(map);
		//페이징 처리
		int totalA = adminreplyService.getMenteeReplyTotalA();
		
		adminreplyPaging.setCurrentPage(Integer.parseInt(pg));
		adminreplyPaging.setPageBlock(3);
		adminreplyPaging.setPageSize(10);
		adminreplyPaging.setTotalA(totalA);
		adminreplyPaging.menteeReplyPagingHTML();
		
		mav.addObject("list", list);
		mav.addObject("pg", pg);
		mav.addObject("adminreplyPaging", adminreplyPaging);
		mav.addObject("display", "/adminreply/adminmenteeReply.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
	/* description : 멘티 댓글 삭제 12.03추가 */
	@RequestMapping(value="menteeReplyDelete",method = RequestMethod.POST)
	@ResponseBody
	public void menteeReplyDelete(@RequestParam String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		adminreplyService.menteeReplyDelete(map);
	}
	
	/* description : 고맙습니다 댓글 view 12.05추가 */
	@RequestMapping(value="menteeReplyView",method = RequestMethod.GET)
	public ModelAndView menteeReplyView(@RequestParam String seq) {
		ModelAndView mav = new ModelAndView();
		int menteeboardReply_seq = Integer.parseInt(seq);
		MenteeboardReplyDTO menteeboardReplyDTO = adminreplyService.menteeReplyView(menteeboardReply_seq);
		mav.addObject("menteeboardReplyDTO", menteeboardReplyDTO);
		mav.addObject("display", "/adminreply/menteeReplyView.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
	
}
