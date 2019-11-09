package participation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
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
}
