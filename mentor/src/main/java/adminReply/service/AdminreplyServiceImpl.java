package adminReply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminReply.dao.AdminReplyDAO;
import meetingboard.bean.ReviewDTO;
import menteeboardReply.bean.MenteeboardReplyDTO;

@Service
public class AdminreplyServiceImpl implements AdminreplyService{

	@Autowired
	private AdminReplyDAO adminReplyDAO;
	
	@Override
	public List<ReviewDTO> getAdminThankyou(Map<String, Integer> map) {
		return adminReplyDAO.getAdminThankyou(map);
	}

	@Override
	public int getThankyouTotalA() {
		return adminReplyDAO.getThankyouTotalA();
	}

	@Override
	public void meetingReviewDelete(Map<String, String[]> map) {
		adminReplyDAO.meetingReviewDelete(map);
	}

	//멘티게시판
	@Override
	public List<MenteeboardReplyDTO> getAdminmenteeReply(Map<String, Integer> map) {
		return adminReplyDAO.getAdminmenteeReply(map);
	}

	@Override
	public int getMenteeReplyTotalA() {
		return adminReplyDAO.getMenteeReplyTotalA();
	}

	@Override
	public void menteeReplyDelete(Map<String, String[]> map) {
		adminReplyDAO.menteeReplyDelete(map);
	}
}
