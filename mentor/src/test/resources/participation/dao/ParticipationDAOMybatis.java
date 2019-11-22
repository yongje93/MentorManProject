package participation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import participation.bean.OrderDTO;
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
	
	// 결제 완료
	@Override
	public void orderComplete(Map<String, Object> order) {
		sqlSession.insert("participationSQL.orderComplete", order);
		sqlSession.update("participationSQL.meetingStateUpdate", order);
		//지원한 멘티수
		int menteeCount = sqlSession.selectOne("participationSQL.menteeCount", order);
		int menteeLimit = sqlSession.selectOne("participationSQL.menteeLimit", order);
		if(menteeLimit == menteeCount) {
			sqlSession.update("participationSQL.updateState", order);
		}
	}
	
	@Override
	public List<OrderDTO> getOrderHistoryUsingOrderId(String order_id) {
		return sqlSession.selectList("participationSQL.getOrderHistoryUsingOrderId", order_id);
	}

	@Override
	public List<OrderDTO> getOrderHistoryUsingMemEmail(Map<String, Object> map) {
		return sqlSession.selectList("participationSQL.getOrderHistoryUsingMemEmail", map);
	}
	
	@Override
	public int getTotalHistory(String member_email) {
		return sqlSession.selectOne("participationSQL.getTotalHistory", member_email);
	}
}
