package adminmember.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmentorDTO;

@Repository("adminmameberDAO")
@Transactional
public class AdminmemberDAOMybatis implements AdminmemberDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<AdminmemberDTO> getAdminmemberList(Map<String, Integer> map) {
		return sqlSession.selectList("adminmemberSQL.getAdminmemberList", map);
	}

	@Override
	public int getMemeberTotalA() {
		return sqlSession.selectOne("adminmemberSQL.getMemeberTotalA");
	}
	
	@Override
	public List<AdminmemberDTO> getSearchadminmemberList(Map<String, Object> map) {
		return sqlSession.selectList("adminmemberSQL.getSearchadminmemberList", map);
	}
	
	@Override
	public int getSearchmemeberTotalA(Map<String, Object> map) {
		return sqlSession.selectOne("adminmemberSQL.getSearchmemeberTotalA",map);
	}
	
	//멘토
	@Override
	public List<AdminmentorDTO> getAdminmentorList(Map<String, Integer> map) {
		return sqlSession.selectList("adminmemberSQL.getAdminmentorList", map);
	}

	@Override
	public int getMentorTotalA() {
		return sqlSession.selectOne("adminmemberSQL.getMentorTotalA");
	}
	
	@Override
	public List<AdminmentorDTO> getSearchadminmentorList(Map<String, Object> map) {
		return sqlSession.selectList("adminmemberSQL.getSearchadminmentorList", map);
	}

	@Override
	public int getSearchmentorTotalA(Map<String, Object> map) {
		return sqlSession.selectOne("adminmemberSQL.getSearchmentorTotalA",map);
	}

	
	//멘토신청
	@Override
	public List<AdminmentorDTO> getAdminmentorApplyList(Map<String, Integer> map) {
		return sqlSession.selectList("adminmemberSQL.getAdminmentorApplyList", map);
	}

	@Override
	public int getMentorApplyTotalA() {
		return sqlSession.selectOne("adminmemberSQL.getMentorApplyTotalA");
	}
	
	@Override
	public List<AdminmentorDTO> getSearchadminmentorApplyList(Map<String, Object> map) {
		return sqlSession.selectList("adminmemberSQL.getSearchadminmentorApplyList", map);
	}

	@Override
	public int getSearchmentorApplyTotalA(Map<String, Object> map) {
		return sqlSession.selectOne("adminmemberSQL.getSearchmentorApplyTotalA",map);
	}

	@Override
	public void adminmentorSuccess(Map<String, String[]> map) {
		sqlSession.update("adminmemberSQL.adminmentorSuccess",map);
	}
	
	@Override
	public void adminmentorReject(Map<String, String[]> map) {
		sqlSession.delete("adminmemberSQL.adminmentorReject", map);
	}
	
	// 멘티
	@Override
	public List<AdminmemberDTO> getAdminmenteeList(Map<String, Integer> map) {
		return sqlSession.selectList("adminmemberSQL.getAdminmenteeList", map);
	}

	@Override
	public int getMenteeTotalA() {
		return sqlSession.selectOne("adminmemberSQL.getMenteeTotalA");
	}

	@Override
	public List<AdminmemberDTO> getSearchadminmenteeList(Map<String, Object> map) {
		return sqlSession.selectList("adminmemberSQL.getSearchadminmenteeList", map);
	}

	@Override
	public int getSearchmenteeTotalA(Map<String, Object> map) {
		return sqlSession.selectOne("adminmemberSQL.getSearchmenteeTotalA",map);
	}
	
	
}

