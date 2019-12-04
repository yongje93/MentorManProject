package mentor.dao;

import java.util.List;
import java.util.Map;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;
import mentor.bean.MentorFollowDTO;

public interface MentorDAO {

	public void mentorapplyWrite(Map<String, String> map);

	public MentorDTO getEmail(String member_email);
	
	public List<MentorDTO> getMentorList(Map<String, String> map);

	public int getTotal(int mentor_flag);

	public MentorDTO getMentor_info(int seq);

	public List<MentorDTO> getMentoring_code(Map<String, String[]> map);

	public void mentorQuestionsSuccess(Map<String, String> map);

	public List<MentorDTO> getQuestion_flag(Map<String, String> flagCheck_map);

	public List<MentorDTO> getMentorEssayList(int mentor_seq);

	public List<ReviewDTO> getMentorReviewList(int mentor_seq);

	public MentorDTO getMentorInfomation(int mentor_seq);

	public MentorDTO questionModifyForm(int qsseq);

	public int questionModify(Map<String, String> map);

	public int getFollowCheck(Map<String, String> followMap);

	public void mentorFollowInsert(MentorFollowDTO mentorFollowDTO);

	public void mentorFollowDelete(MentorFollowDTO mentorFollowDTO);

	public List<MentorDTO> getMentorAttentionList(Map<String, Object> map);

	public List<MentorDTO> getHonorMentor(Map<String, Object> map);

	public int getJobCode(Map<String, Object> map);

	public List<MentorDTO> getJobType(Map<String, Object> map);

	public int getMemberCount(int mentorFlag);

	public List<MentorDTO> getMentor(Map<String, Object> map);

	public int getAnswer(int mentor_seq);

	public int getQuestion(int mentor_seq);
	
	public void mentorInfoModify(Map<String, String> map);

	public int getFollow(int mentor_seq);

	public int getMenteeInfo_count(String member_email);
}
