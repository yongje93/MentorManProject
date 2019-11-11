package participation.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public void participationWrite(ParticipationDTO participationDTO) {
		sqlSession.insert("participationSQL.participationWrite", participationDTO);
	}
	
	@Override
	public List<ParticipationDTO> getParticipation(Map<String, Object> map) {
		return sqlSession.selectList("participationSQL.getParticipation", map);
	}

	@Override
	public void orderDelete(int participation_seq) {
		sqlSession.delete("participationSQL.orderDelete", participation_seq);
	}
}
