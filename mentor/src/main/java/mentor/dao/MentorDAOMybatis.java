package mentor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import meetingboard.bean.ReviewDTO;
import mentor.bean.MentorDTO;
import mentor.bean.MentorFollowDTO;

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
		return sqlSession.selectOne("mentorSQL.getEmail", member_email);
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
	public List<MentorDTO> getQuestion_flag(Map<String, String> flagCheck_map) {
		return sqlSession.selectList("mentorSQL.getQuestion_flag", flagCheck_map);
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

	@Override
	public MentorDTO questionModifyForm(int qsseq) {
		return sqlSession.selectOne("mentorSQL.questionModifyForm", qsseq);
	}

	@Override
	public int questionModify(Map<String, String> map) {
		return sqlSession.update("mentorSQL.questionModify", map);
	}

	@Override
	public int getFollowCheck(Map<String, String> followMap) {
		return sqlSession.selectOne("mentorSQL.getFollowCheck", followMap);
	}

	@Override
	public void mentorFollowInsert(MentorFollowDTO mentorFollowDTO) {
		sqlSession.insert("mentorSQL.mentorFollowInsert", mentorFollowDTO);
	}

	@Override
	public void mentorFollowDelete(MentorFollowDTO mentorFollowDTO) {
		sqlSession.delete("mentorSQL.mentorFollowDelete", mentorFollowDTO);
	}

	@Override
	public List<MentorDTO> getMentorAttentionList(Map<String, Object> map) {
		return sqlSession.selectList("mentorSQL.getMentorAttentionList", map);
	}

	@Override
	public int getJobCode(Map<String, Object> map) {
		return sqlSession.selectOne("mentorSQL.getJobCode", map);
	}

	@Override
	public List<MentorDTO> getJobType(Map<String, Object> map) {
		return sqlSession.selectList("mentorSQL.getJobType", map);
	}

	@Override
	public List<MentorDTO> getMentor(Map<String, Object> map) {
		return sqlSession.selectList("mentorSQL.getMentor", map);
	}

	@Override
	public int getMemberCount(int mentorFlag) {
		return sqlSession.selectOne("mentorSQL.getMemberCount", mentorFlag);
	}

	@Override
	public List<MentorDTO> getHonorMentor(Map<String, Object> map) {
		return sqlSession.selectList("mentorSQL.getHonorMentor", map);
	}

	@Override
	public int getAnswer(int mentor_seq) {
		return sqlSession.selectOne("mentorSQL.getAnswer", mentor_seq);
	}

	@Override
	public int getQuestion(int mentor_seq) {
		return sqlSession.selectOne("mentorSQL.getQuestion", mentor_seq);
	}

	@Override
	public void mentorInfoModify(Map<String, String> map) {
		sqlSession.update("mentorSQL.mentorInfoModify",map);
	}

	@Override
	public int getFollow(int mentor_seq) {
		return sqlSession.selectOne("mentorSQL.getFollow" , mentor_seq);
	}

}
