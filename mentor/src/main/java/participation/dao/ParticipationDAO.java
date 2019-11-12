package participation.dao;

import java.util.List;
import java.util.Map;

import member.bean.MemberDTO;
import participation.bean.ParticipationDTO;

public interface ParticipationDAO {
	public MemberDTO getMentorInfo(int meetingboard_seq);
	public void participationWrite(ParticipationDTO participationDTO);
	public List<ParticipationDTO> getParticipation(Map<String, Object> map);
	public void orderDelete(int participation_seq);
	public void orderComplete(Map<String, Object> order);

}
