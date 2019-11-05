package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository("memberDAO")
@Transactional
/**
 * @Title : MemberDAO클래스
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	//Nick중복확인
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return sqlSession.selectOne("memberSQL.writeNicknamecheck", member_nickname);
	}
	//Name중복확인
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return sqlSession.selectOne("memberSQL.writeEmailCheck", member_email);
	}
	//회원가입 처리
	@Override
	public void write(Map<String, String> map) {
		 sqlSession.insert("memberSQL.write",map);
	}
	//로그인 처리
	@Override
	public MemberDTO login(Map<String, String> map) {
		MemberDTO memberDTO=sqlSession.selectOne("memberSQL.login",map);
		return  memberDTO;
	}


}
