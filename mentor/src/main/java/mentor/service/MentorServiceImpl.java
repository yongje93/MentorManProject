package mentor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;
import mentor.dao.MentorDAO;

@Service
public class MentorServiceImpl implements MentorService{
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
}
