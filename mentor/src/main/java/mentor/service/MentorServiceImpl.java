package mentor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;
import mentor.bean.MentorFollowDTO;
import mentor.dao.MentorDAO;

@Service
public class MentorServiceImpl implements MentorService {
	@Autowired
	private MentorDAO mentorDAO;

	@Override
	public void mentorapplyWrite(Map<String, String> map) {
		mentorDAO.mentorapplyWrite(map);
	}

	@Override
	public MentorDTO getEmail(String member_email) {
		return mentorDAO.getEmail(member_email);
	}

	@Override
	public List<MentorDTO> getMentorList(Map<String, String> map) {
		return mentorDAO.getMentorList(map);
	}

	@Override
	public int getTotalA(int mentor_flag) {
		return mentorDAO.getTotal(mentor_flag);
	}

	@Override
	public MentorDTO getMentor_info(int seq) {
		return mentorDAO.getMentor_info(seq);
	}

	@Override
	public List<MentorDTO> getMentoring_code(Map<String, String[]> map) {
		return mentorDAO.getMentoring_code(map);
	}

	@Override
	public void mentorQuestionsSuccess(Map<String, String> map) {
		mentorDAO.mentorQuestionsSuccess(map);
	}

	@Override
	public List<MentorDTO> getQuestion_flag(Map<String, String> flagCheck_map) {
		return mentorDAO.getQuestion_flag(flagCheck_map);
	}

	@Override
	public List<MentorDTO> getMentorEssayList(int mentor_seq) {
		return mentorDAO.getMentorEssayList(mentor_seq);
	}

	@Override
	public List<ReviewDTO> getMentorReviewList(int mentor_seq) {
		return mentorDAO.getMentorReviewList(mentor_seq);
	}

	@Override
	public MentorDTO getMentorInfomation(int mentor_seq) {
		return mentorDAO.getMentorInfomation(mentor_seq);
	}

	@Override
	public MentorDTO questionModifyForm(int qsseq) {
		return mentorDAO.questionModifyForm(qsseq);
	}

	@Override
	public int questionModify(Map<String, String> map) {
		return mentorDAO.questionModify(map);
	}

	@Override
	public int getFollowCheck(Map<String, String> followMap) {
		return mentorDAO.getFollowCheck(followMap);
	}

	@Override
	public void mentorFollowInsert(MentorFollowDTO mentorFollowDTO) {
		mentorDAO.mentorFollowInsert(mentorFollowDTO);
	}

	@Override
	public void mentorFollowDelete(MentorFollowDTO mentorFollowDTO) {
		mentorDAO.mentorFollowDelete(mentorFollowDTO);
	}

	@Override
	public List<MentorDTO> getMentorAttentionList(Map<String, Object> map) {
		return mentorDAO.getMentorAttentionList(map);
	}

	@Override
	public List<MentorDTO> getHonorMentor(Map<String, Object> map) {
		return mentorDAO.getHonorMentor(map);
	}

	@Override
	public int getJobCode(Map<String, Object> map) {
		return mentorDAO.getJobCode(map);
	}

	@Override
	public List<MentorDTO> getJobType(Map<String, Object> map) {
		return mentorDAO.getJobType(map);
	}

	@Override
	public int getMemberCount(int mentorFlag) {
		return mentorDAO.getMemberCount(mentorFlag);
	}

	@Override
	public List<MentorDTO> getMentor(Map<String, Object> map) {
		return mentorDAO.getMentor(map);
	}

	@Override
	public int getAnswer(int mentor_seq) {
		return mentorDAO.getAnswer(mentor_seq);
	}

	@Override
	public int getQuestion(int mentor_seq) {
		return mentorDAO.getQuestion(mentor_seq);
	}

	@Override
	public void mentorInfoModify(Map<String, String> map) {
		mentorDAO.mentorInfoModify(map);
	}

	@Override
	public int getFollow(int mentor_seq) {
		return mentorDAO.getFollow(mentor_seq);
	}

	@Override
	public int getMenteeInfo_count(String member_email) {
		return mentorDAO.getMenteeInfo_count(member_email);
	}

}
