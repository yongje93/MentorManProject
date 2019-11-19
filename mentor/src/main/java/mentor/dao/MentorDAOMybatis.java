package mentor.dao;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;

@Transactional
@Repository
public class MentorDAOMybatis implements MentorDAO {
	@Autowired 
	private SqlSession sqlSession;

	@Override
	public void mentorapplyWrite(Map<String, String> map) {
		sqlSession.insert("mentorSQL.mentorapplyWrite", map);
	}

	@Override
	public MentorDTO getEmail(String member_email) {
		return sqlSession.selectOne("mentorSQL.getEmail",member_email);
	}
	
	@Override
	public List<MentorDTO> getMentorList(Map<String, String> map) {
		return sqlSession.selectList("mentorSQL.getMentorList", map);
	}

	@Override
	public int getTotal(int mentor_flag) {
		return sqlSession.selectOne("mentorSQL.getTotalA", mentor_flag);
	}

	@Override
	public MentorDTO getMentor_info(int mentor_seq) {
		return sqlSession.selectOne("mentorSQL.getMentor_info", mentor_seq);
	}
	
	@Override
	public List<MentorDTO> getMentoring_code(Map<String, String[]> map) {
		return sqlSession.selectList("mentorSQL.getMentoring_code", map);
	}
	@Override
	public void mentorQuestionsSuccess(Map<String, String> map) {
		sqlSession.insert("mentorSQL.mentorQuestionsSuccess", map);
	}

	@Override
	public List<MentorDTO> getMentorEssayList(int mentor_seq) {
		return sqlSession.selectList("mentorSQL.getMentorEssayList", mentor_seq);
	}

	@Override
	public List<ReviewDTO> getMentorReviewList(int mentor_seq) {
		return sqlSession.selectList("mentorSQL.getMentorReviewList", mentor_seq);
	}

	@Override
	public MentorDTO getMentorInfomation(int mentor_seq) {
		return sqlSession.selectOne("mentorSQL.getMentorInfomation", mentor_seq);
	}
}
