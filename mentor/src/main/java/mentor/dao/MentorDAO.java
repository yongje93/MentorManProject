package mentor.dao;

import java.util.List;
import java.util.Map;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;

public interface MentorDAO {

	public void mentorapplyWrite(Map<String, String> map);

	public MentorDTO getEmail(String member_email);
	
	public List<MentorDTO> getMentorList(Map<String, String> map);

	public int getTotal(int mentor_flag);

	public MentorDTO getMentor_info(int seq);

	public List<MentorDTO> getMentoring_code(Map<String, String[]> map);

	public void mentorQuestionsSuccess(Map<String, String> map);

	public List<MentorDTO> getMentorEssayList(int mentor_seq);

	public List<ReviewDTO> getMentorReviewList(int mentor_seq);

	public MentorDTO getMentorInfomation(int mentor_seq);

}
