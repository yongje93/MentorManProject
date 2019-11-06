package meetingboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meetingboard.bean.MeetingboardDTO;

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
	public MeetingboardDTO getMeetingboard(int meeting_seq) {
		return sqlSession.selectOne("meetingboardSQL.getMeetingboard", meeting_seq);
	}

}
