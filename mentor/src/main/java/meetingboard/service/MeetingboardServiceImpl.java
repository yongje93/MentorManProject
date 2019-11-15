package meetingboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meetingboard.bean.GuideDTO;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.bean.ReviewDTO;
import meetingboard.dao.MeetingboardDAO;

@Service
public class MeetingboardServiceImpl implements MeetingboardService {
	@Autowired
	private MeetingboardDAO meetingboardDAO;
	
	@Override
	public void meetingboardWrite(MeetingboardDTO meetingboardDTO) {
		meetingboardDAO.meetingboardWrite(meetingboardDTO);
	}

	@Override
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map) {
		return meetingboardDAO.getMeetingboardList(map);
	}

	@Override
	public MeetingboardDTO getMeetingboard(int meetingboard_seq) {
		return meetingboardDAO.getMeetingboard(meetingboard_seq);
	}

	@Override
	public int getTotalA() {
		return meetingboardDAO.getTotalA();
	}

	@Override
	public void meetingboardModify(MeetingboardDTO meetingboardDTO) {
		meetingboardDAO.meetingboardModify(meetingboardDTO);
	}

	@Override
	public void meetingboardDelete(int meetingboard_seq) {
		meetingboardDAO.meetingboardDelete(meetingboard_seq);
	}
	
	@Override
	public List<GuideDTO> getGuideList() {
		return meetingboardDAO.getGuideList();
	}

	@Override
	public void meetingReviewWrite(ReviewDTO reviewDTO) {
		meetingboardDAO.meetingReviewWrite(reviewDTO);
	}
}
