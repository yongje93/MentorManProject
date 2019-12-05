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
		// 지원한 멘티수
		int menteeCount = sqlSession.selectOne("participationSQL.menteeCount", order);
		int menteeLimit = sqlSession.selectOne("participationSQL.menteeLimit", order);
		if (menteeLimit == menteeCount) {
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

	@Override
	public void paymentCancel(Map<String, Object> map) {
		// 일단 전체 금액에서 빼기
		sqlSession.update("participationSQL.paymentCanelTotal", map);
		// 삭제한 항목 flag 0으로 바꾸기
		sqlSession.update("participationSQL.paymentCancelFlag", map);
		// 신청서 삭제
		sqlSession.delete("participationSQL.deleteParticipation", map);
		// 지원한 멘티수
		int menteeCount = sqlSession.selectOne("participationSQL.menteeCount", map);
		int menteeLimit = sqlSession.selectOne("participationSQL.menteeLimit", map);
		if (menteeLimit > menteeCount) {
			sqlSession.update("participationSQL.updateStateZero", map);
		}
	}

	@Override
	public ParticipationDTO getMenteeParticipation(Map<String, Integer> map) {
		return sqlSession.selectOne("participationSQL.getMenteeParticipation", map);
	}

	@Override
	public List<OrderDTO> getOrderHistorySearch(Map<String, Object> map) {
		if (!("").equals(map.get("startDate")) && ("all").equals(map.get("option"))) { // 모두 보여주기
			return sqlSession.selectList("participationSQL.getOrderHistorySearchAllDate", map);
		} else if (!("").equals(map.get("startDate")) && ("complete").equals(map.get("option"))) { // 주문 내역만 보여주기
			return sqlSession.selectList("participationSQL.getOrderHistorySearchCompleteDate", map);
		} else if (!("").equals(map.get("startDate")) && ("cancel").equals(map.get("option"))) {// 취소 내역만 보여주기
			return sqlSession.selectList("participationSQL.getOrderHistorySearchCancelDate", map);
		} else if (("").equals(map.get("startDate")) && ("all").equals(map.get("option"))) { // 전체기간 모든 내역
			return sqlSession.selectList("participationSQL.getOrderHistorySearchAll", map);
		} else if (("").equals(map.get("startDate")) && ("complete").equals(map.get("option"))) { // 전체기간 주문내역
			return sqlSession.selectList("participationSQL.getOrderHistorySearchComplete", map);
		} else { // 전체기간 취소내역
			return sqlSession.selectList("participationSQL.getOrderHistorySearchCancel", map);
		}
	}

	@Override
	public int getSearchHistory(Map<String, Object> map) {
		if (!("").equals(map.get("startDate")) && ("all").equals(map.get("option"))) { // 모두 보여주기
			return sqlSession.selectOne("participationSQL.getSearchHistoryAllDate", map);
		} else if (!("").equals(map.get("startDate")) && ("complete").equals(map.get("option"))) { // 주문 내역만 보여주기
			return sqlSession.selectOne("participationSQL.getSearchHistoryCompleteDate", map);
		} else if (!("").equals(map.get("startDate")) && ("cancel").equals(map.get("option"))) {// 취소 내역만 보여주기
			return sqlSession.selectOne("participationSQL.getSearchHistoryCancelDate", map);
		} else if (("").equals(map.get("startDate")) && ("all").equals(map.get("option"))) { // 전체기간 모든 내역
			return sqlSession.selectOne("participationSQL.getSearchHistoryAll", map);
		} else if (("").equals(map.get("startDate")) && ("complete").equals(map.get("option"))) { // 전체기간 주문내역
			return sqlSession.selectOne("participationSQL.getSearchHistoryComplete", map);
		} else { // 전체기간 취소내역
			return sqlSession.selectOne("participationSQL.getSearchHistoryCancel", map);
		}
	}
}
