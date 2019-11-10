package participation.service;

import member.bean.MemberDTO;
import participation.bean.ParticipationDTO;

public interface ParticipationService {
	public MemberDTO getMentorInfo(int meetingboard_seq);
	public void participationWrite(ParticipationDTO participationDTO);

}
