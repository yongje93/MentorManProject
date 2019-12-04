package participation.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import participation.bean.OrderDTO;
import participation.bean.ParticipationDTO;

public interface ParticipationDAO {
	public MemberDTO getMentorInfo(int meetingboard_seq);
	public void participationWrite(ParticipationDTO participationDTO);
	public List<ParticipationDTO> getParticipation(Map<String, Object> map);
	public void orderDelete(int participation_seq);
	public void orderComplete(Map<String, Object> order);
	public List<OrderDTO> getOrderHistoryUsingOrderId(String order_id);
	public List<OrderDTO> getOrderHistoryUsingMemEmail(Map<String, Object> map);
	public int getTotalHistory(String member_email);
	public void paymentCancel(Map<String, Object> map);
	public ParticipationDTO getMenteeParticipation(Map<String, Integer> map);
	public List<OrderDTO> getOrderHistorySearch(Map<String, Object> map);
	public int getSearchHistory(Map<String, Object> map);

}
