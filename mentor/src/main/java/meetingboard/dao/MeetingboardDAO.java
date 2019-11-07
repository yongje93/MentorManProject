package meetingboard.dao;

import java.util.List;
import java.util.Map;

import meetingboard.bean.MeetingboardDTO;

public interface MeetingboardDAO {
	public void meetingboardWrite(MeetingboardDTO meetingboardDTO);
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map);
	public MeetingboardDTO getMeetingboard(int meeting_seq);
	public int getTotalA();
	public void meetingboardModify(MeetingboardDTO meetingboardDTO);
	public void meetingboardDelete(int meeting_seq);

}
