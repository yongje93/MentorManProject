package adminReply.service;

import java.util.List;
import java.util.Map;

import meetingboard.bean.ReviewDTO;
import menteeboardReply.bean.MenteeboardReplyDTO;

public interface AdminreplyService {

	//고맙습니다 댓글
	public List<ReviewDTO> getAdminThankyou(Map<String, Integer> map);

	public int getThankyouTotalA();
	
	public void meetingReviewDelete(Map<String, String[]> map);
		
	//멘티게시판 댓글
	public List<MenteeboardReplyDTO> getAdminmenteeReply(Map<String, Integer> map);

	public int getMenteeReplyTotalA();

	public void menteeReplyDelete(Map<String, String[]> map);
}
