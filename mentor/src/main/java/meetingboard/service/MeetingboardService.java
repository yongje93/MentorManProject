package meetingboard.service;

import java.util.List;
import java.util.Map;

import meetingboard.bean.GuideDTO;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.bean.ReviewDTO;

public interface MeetingboardService {
	public void meetingboardWrite(MeetingboardDTO meetingboardDTO);
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map);
	public MeetingboardDTO getMeetingboard(int meetingboard_seq);
	public int getTotalA();
	public void meetingboardModify(MeetingboardDTO meetingboardDTO);
	public void meetingboardDelete(int meetingboard_seq);
	public List<GuideDTO> getGuideList();
	public void meetingReviewWrite(ReviewDTO reviewDTO);

}
