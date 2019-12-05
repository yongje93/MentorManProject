package participation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import participation.bean.OrderDTO;
import participation.bean.ParticipationDTO;
import participation.dao.ParticipationDAO;

@Service
public class ParticipationServiceImpl implements ParticipationService {
	@Autowired
	private ParticipationDAO participationDAO;

	@Override
	public MemberDTO getMentorInfo(int meetingboard_seq) {
		return participationDAO.getMentorInfo(meetingboard_seq);
	}

	@Override
	public void participationWrite(ParticipationDTO participationDTO) {
		participationDAO.participationWrite(participationDTO);
	}

	@Override
	public List<ParticipationDTO> getParticipation(Map<String, Object> map) {
		return participationDAO.getParticipation(map);
	}

	@Override
	public void orderDelete(int participation_seq) {
		participationDAO.orderDelete(participation_seq);
	}

	@Override
	public void orderComplete(Map<String, Object> order) {
		participationDAO.orderComplete(order);
	}

	@Override
	public List<OrderDTO> getOrderHistoryUsingOrderId(String order_id) {
		return participationDAO.getOrderHistoryUsingOrderId(order_id);
	}

	@Override
	public List<OrderDTO> getOrderHistoryUsingMemEmail(Map<String, Object> map) {
		return participationDAO.getOrderHistoryUsingMemEmail(map);
	}

	@Override
	public int getTotalHistory(String member_email) {
		return participationDAO.getTotalHistory(member_email);
	}

	@Override
	public void paymentCancel(Map<String, Object> map) {
		participationDAO.paymentCancel(map);
	}

	@Override
	public ParticipationDTO getMenteeParticipation(Map<String, Integer> map) {
		return participationDAO.getMenteeParticipation(map);
	}

	@Override
	public List<OrderDTO> getOrderHistorySearch(Map<String, Object> map) {
		return participationDAO.getOrderHistorySearch(map);
	}

	@Override
	public int getSearchHistory(Map<String, Object> map) {
		return participationDAO.getSearchHistory(map);
	}
}
