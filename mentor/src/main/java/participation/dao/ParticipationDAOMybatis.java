package participation.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import participation.bean.ParticipationDTO;

@Repository
@Transactional
public class ParticipationDAOMybatis implements ParticipationDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDTO getMentorInfo(int meetingboard_seq) {
		return sqlSession.selectOne("participationSQL.getMentorInfo", meetingboard_seq);
	}
}
