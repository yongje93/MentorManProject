package adminboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adminboard.bean.AdminnoticeboardDTO;
import essayboard.bean.EssayboardDTO;
import meetingboard.bean.MeetingboardDTO;
import menteeboard.bean.MenteeboardDTO;

/**
 * 
 * @Title : adminboard mybatis
 * @author : 안상구
 * @date : 2019. 11. 8.
 */
@Repository("adminboardDAO")
@Transactional
public class AdminDAOMybatis implements AdminboardDAO{
	
	@Autowired
	SqlSession sqlsession;
	
	/* description : 공지사항 List가져오기*/
	@Override
	public List<AdminnoticeboardDTO> getAdmninnoticeboardList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		return sqlsession.selectList("adminboardSQL.getAdmninnoticeboardList", map);
	}
	/* description : 공지사항 페이징*/
	@Override
	public int getTotalA() {
		return sqlsession.selectOne("adminboardSQL.getTotalA");
	}
	/* description : 공지사항 글삭제*/
	@Override
	public void adminnoticeboardDelete(Map<String, String[]> map) {
		sqlsession.delete("adminboardSQL.adminnoticeboardDelete", map);
	}
	/* description : 공지사항 보자*/
	@Override
	public AdminnoticeboardDTO adminnoticeboardView(int seq) {
		return sqlsession.selectOne("adminboardSQL.adminnoticeboardView",seq);
	}
	
	@Override
	public void adminnoticeboardWrite(Map<String, String> map) {
		sqlsession.insert("adminboardSQL.adminnoticeboardWrite",map);
	}
	
	
	@Override
	public void noticeboardModify(Map<String, String> map) {
		sqlsession.update("adminboardSQL.noticeboardModify",map);
	}
	/* description : 모임게시판 리스트*/
	@Override
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map) {
		return sqlsession.selectList("adminboardSQL.getMeetingboardList", map);
	}
	@Override
	public int getMeetingboardTotalA() {
		return sqlsession.selectOne("adminboardSQL.getMeetingboardTotalA");
	}
	@Override
	public void adminmeetingboardDelete(Map<String, String[]> map) {
		sqlsession.delete("adminboardSQL.adminmeetingboardDelete", map);
	}
	
	//에세이
	@Override
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map) {
		return sqlsession.selectList("adminboardSQL.getNewEssay", map);
	}
	@Override
	public int getessayTotalA() {
		return sqlsession.selectOne("adminboardSQL.getessayTotalA");
	}
	@Override
	public List<MenteeboardDTO> getMenteeboardList(Map<String, Integer> map) {
		return sqlsession.selectList("adminboardSQL.getMenteeboardList", map);
	}
	@Override
	public int getMenteeTotalA() {
		return sqlsession.selectOne("adminboardSQL.getMenteeTotalA");
	}
}
