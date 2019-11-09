package participation.dao;

import member.bean.MemberDTO;
import participation.bean.ParticipationDTO;

public interface ParticipationDAO {

	public MemberDTO getMentorInfo(int meetingboard_seq);

}
