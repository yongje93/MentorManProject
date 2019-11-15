package meetingboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meetingboard.bean.GuideDTO;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.bean.ReviewDTO;

@Repository
@Transactional
public class MeetingboardDAOMybatis implements MeetingboardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void meetingboardWrite(MeetingboardDTO meetingboardDTO) {
		sqlSession.insert("meetingboardSQL.meetingboardWrite", meetingboardDTO);
	}

	@Override
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map) {
		return sqlSession.selectList("meetingboardSQL.getMeetingboardList", map);
	}

	@Override
	public MeetingboardDTO getMeetingboard(int meetingboard_seq) {
		return sqlSession.selectOne("meetingboardSQL.getMeetingboard", meetingboard_seq);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("meetingboardSQL.getTotalA");
	}

	@Override
	public void meetingboardModify(MeetingboardDTO meetingboardDTO) {
		sqlSession.update("meetingboardSQL.meetingboardModify", meetingboardDTO);
	}

	@Override
	public void meetingboardDelete(int meetingboard_seq) {
		sqlSession.delete("meetingboardSQL.meetingboardDelete", meetingboard_seq);
	}
	
	@Override
	public List<GuideDTO> getGuideList() {
		return sqlSession.selectList("meetingboardSQL.getGuideList");
	}

	@Override
	public void meetingReviewWrite(ReviewDTO reviewDTO) {
		sqlSession.insert("meetingboardSQL.meetingReviewWrite", reviewDTO);
	}

}
