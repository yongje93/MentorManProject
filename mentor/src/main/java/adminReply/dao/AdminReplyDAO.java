package adminReply.dao;

import java.util.List;
import java.util.Map;

import meetingboard.bean.ReviewDTO;
import menteeboardReply.bean.MenteeboardReplyDTO;

public interface AdminReplyDAO {

	public List<ReviewDTO> getAdminThankyou(Map<String, Integer> map);

	public int getThankyouTotalA();

	public ReviewDTO thankyouView(int review_seq);

	//멘티게시판
	public List<MenteeboardReplyDTO> getAdminmenteeReply(Map<String, Integer> map);

	public int getMenteeReplyTotalA();

	public void meetingReviewDelete(Map<String, String[]> map);

	public void menteeReplyDelete(Map<String, String[]> map);

	public MenteeboardReplyDTO menteeReplyView(int menteeboardReply_seq);




}
