package mentee.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;

@Transactional
@Repository
public class MenteeDAOMybatis implements MenteeDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void mentorUserModify(Map<String, String> map) {
		sqlSession.update("menteeSQL.mentorUserModify", map);
	}

	@Override
	public MemberDTO getSaveMember(String member_email) {
		return sqlSession.selectOne("menteeSQL.getSaveMember",member_email);
	}

	@Override
	public MenteeDTO getStudentEmail(String member_email) {
		return sqlSession.selectOne("menteeSQL.getStudentEmail", member_email);
	}

	@Override
	public void menteeStudentInput(Map<String, String> map) {
		if(map.get("member_email")!=null) {
			sqlSession.insert("menteeSQL.menteeStudentInput", map);
		}else {
			sqlSession.update("menteeSQL.menteeStudentSave", map);
		}
	}

	@Override
	public MenteeDTO getEmployeeEmail(String member_email) {
		return sqlSession.selectOne("menteeSQL.getEmployeeEmail", member_email);
	}
	
	@Override
	public void menteeEmployeeInput(Map<String, String> map) {
		if(map.get("member_email")!=null) {
			sqlSession.insert("menteeSQL.menteeEmployeeInput", map);
		}else {
			sqlSession.update("menteeSQL.menteeEmployeeSave", map);
		}
	}
	
	@Override
	public MemberDTO menteePasswordCheck(String member_email) {
		return sqlSession.selectOne("menteeSQL.menteePasswordCheck", member_email);
	}
	
	@Override
	public void menteePasswordSave(Map<String, String> map) {
		sqlSession.update("menteeSQL.menteePasswordSave", map);
	}
}
