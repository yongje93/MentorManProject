package member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.AlarmDTO;
import member.bean.MemberDTO;
import mentor.bean.MentorDTO;

@Repository("memberDAO")
@Transactional
/**
 * @Title : MemberDAO클래스
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private MemberDTO memberDTO;

	@Autowired
	private SqlSession sqlSession;

	/** @Title : 닉네임 중복확인
 	   @author : ginkgo1928  @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return sqlSession.selectOne("memberSQL.writeNicknamecheck", member_nickname);
	}

	/** @Title : 이름 중복확인
	 * @author : ginkgo1928  @date : 2019. 11. 5.*/
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return sqlSession.selectOne("memberSQL.writeEmailCheck", member_email);
	}

	/** @Title : 회원가입 처리
	 * @author : ginkgo1928 @date : 2019. 11. 5.*/
	@Override
	public void write(Map<String, String> map) {
		sqlSession.insert("memberSQL.write",map);
	}

	/** @Title : 로그인 처리
	 * @author : ginkgo1928  @date : 2019. 11. 5.*/
	@Override
	public MemberDTO login(Map<String, String> map) {
		memberDTO=sqlSession.selectOne("memberSQL.login",map);
		return  memberDTO;
	}
	/**
	 *  질문 리스트
	 */
	@Override
	public List<MentorDTO> getQandA(Map<String, String> map) {
		return sqlSession.selectList("memberSQL.getQandA", map);
	}

	/** @Title : 비밀번호 설정
	 * @author : ginkgo1928  @date : 2019. 11. 12.*/
	@Override
	public MemberDTO setsetmemberpwd(Map<String, String> map) {
		memberDTO=sqlSession.selectOne("memberSQL.setsetmemberpwd",map);
		return memberDTO;
	}
	/** @Title : 비밀번호 변경
	 * @author : ginkgo1928  @date : 2019. 11. 12.*/
	@Override
	public MemberDTO newPwdCommit(Map<String, String> map) {
		sqlSession.update("memberSQL.newPwdCommit", map);
		return memberDTO;
	}

	/**
	 * Q&A 페이징
	 */
	@Override
	public int getTotalA(String member_email) {
		return sqlSession.selectOne("memberSQL.getTotalA", member_email);
	}
	/**
	 * 멘토 정보
	 */
	@Override
	public MentorDTO getMentor_info(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.getMentor_info", map);
	}

	/**
	 * 멘토링 정보
	 */
	@Override
	public List<MentorDTO> getMentoring_type(Map<String, String[]> arrayMap) {
		return sqlSession.selectList("memberSQL.getMentoring_type", arrayMap);
	}
	/**
	 * 질문 삭제
	 */

	@Override
	public void questionDelete(int question_seq) {
		sqlSession.delete("memberSQL.questionDelete", question_seq);
	}

	// 이메일로 회원 정보 가지고 오기.
	@Override
	public MemberDTO getMemberByEmail(String member_email) {
		return sqlSession.selectOne("memberSQL.getMemberByEmail", member_email);
	}

	// 이메일로 이메일 인증키 추가하기
	@Override
	public void createAuthKey(String member_email, String authKey) {
		MemberDTO member = new MemberDTO();
		member.setMember_email(member_email);
		member.setMemberAuthKey(authKey);
		sqlSession.update("memberSQL.createAuthKey", member);
	}

	// 이메일 인증키로 검사
	@Override
	public MemberDTO checkAuthKey(MemberDTO memberDTO) {
		return sqlSession.selectOne("memberSQL.checkAuthKey", memberDTO);
	}

	// 이메일 인증키로 검사 성공시 1로 상태 바꿈
	@Override
	public void updateMemberAuthState(MemberDTO memberDTO) {
		sqlSession.update("memberSQL.updateMemberAuthState", memberDTO);
	}

	/**
	 * 멘토 로그인시 flag와 seq 가져옴
	 */
	@Override
	public int getMentor_seq(String member_email) {
		return sqlSession.selectOne("memberSQL.getMentor_seq", member_email);
	}

	@Override
	public int getMember_flag(String member_email) {
		return sqlSession.selectOne("memberSQL.getMember_flag", member_email);
	}

	@Override
	public List<MentorDTO> getMemtee_question(int mentor_seq) {
		return sqlSession.selectList("memberSQL.getMemtee_question", mentor_seq);
	}

	@Override
	public String getMember_email(int qsseq) {
		return sqlSession.selectOne("memberSQL.getMember_email", qsseq);
	}

	@Override
	public void answerSave(Map<String, String> map) {
		sqlSession.insert("memberSQL.answerSave", map);
	}

	@Override
	public MentorDTO getMentor_auswer(int qsseq) {
		return sqlSession.selectOne("memberSQL.getMentor_auswer", qsseq);
	}

	@Override
	public void answerModify(Map<String, String> map) {
		sqlSession.update("memberSQL.answerModify", map);
	}

	@Override
	public List<AlarmDTO> getAlarm(String memEmail) {
		return sqlSession.selectList("memberSQL.getAlarm" , memEmail);
	}
	@Override
	public void checkSubscribe(String memEmail) {
		sqlSession.update("memberSQL.checkSubscribe" , memEmail);
	}

	@Override
	public void saveAlarm(Map<String, String> map) {
		sqlSession.insert("memberSQL.saveAlarm" , map);
	}

	@Override
	public void deleteAlarm(int seq) {
		sqlSession.delete("memberSQL.deleteAlarm" , seq);
	}

	@Override
	public int getCountAlarm(String member_email) {
		return sqlSession.selectOne("memberSQL.getCountAlarm" , member_email);
	}

	@Override
	public int getTotalAlarm(String memEmail) {
		return sqlSession.selectOne("memberSQL.getTotalAlarm" , memEmail );
	}


}
