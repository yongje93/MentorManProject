package adminReply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meetingboard.bean.ReviewDTO;
import menteeboardReply.bean.MenteeboardReplyDTO;

@Repository("AdminReplyDAO")
@Transactional
public class AdminReplyDAOMybatis implements AdminReplyDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ReviewDTO> getAdminThankyou(Map<String, Integer> map) {
		return sqlSession.selectList("adminreplySQL.getAdminThankyou", map);
	}

	@Override
	public int getThankyouTotalA() {
		return sqlSession.selectOne("adminreplySQL.getThankyouTotalA");
	}

	@Override
	public void meetingReviewDelete(Map<String, String[]> map) {
		sqlSession.update("adminreplySQL.meetingReviewDelete", map);
	}

	//멘티게시판
	@Override
	public List<MenteeboardReplyDTO> getAdminmenteeReply(Map<String, Integer> map) {
		return sqlSession.selectList("adminreplySQL.getAdminmenteeReply", map);
	}

	@Override
	public int getMenteeReplyTotalA() {
		return sqlSession.selectOne("adminreplySQL.getMenteeReplyTotalA");
	}

	@Override
	public void menteeReplyDelete(Map<String, String[]> map) {
		sqlSession.update("adminreplySQL.menteeReplyDelete", map);
	}

	
}
