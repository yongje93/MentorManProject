package meetingboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meetingboard.bean.MeetingboardDTO;
import meetingboard.dao.MeetingboardDAO;

@Service
public class MeetingboardServiceImpl implements MeetingboardService {
	@Autowired
	private MeetingboardDAO meetingboardDAO;
	
	@Override
	public void meetingboardWrite(MeetingboardDTO meetingboardDTO) {
		meetingboardDAO.meetingboardWrite(meetingboardDTO);
	}

}
