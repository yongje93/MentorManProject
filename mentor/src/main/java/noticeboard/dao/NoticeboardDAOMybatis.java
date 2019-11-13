package noticeboard.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import noticeboard.bean.NoticeboardDTO;

@Transactional
@Repository
public class NoticeboardDAOMybatis implements NoticeboardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void noticeboardWrite(Map<String, String> map) {
		sqlSession.insert("noticeSQL.noticeboardWrite",map);
		
	}
	
	@Override
	public List<NoticeboardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("noticeSQL.getBoardList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("noticeSQL.getTotalA");
	}

	@Override
	public void noticeboardDelete(Map<String, String[]> map) {
		sqlSession.delete("noticeSQL.noticeboardDelete", map);
	}

	@Override
	public NoticeboardDTO getNoticeboardView(int seq) {
		return sqlSession.selectOne("noticeSQL.getNoticeboardView", seq);
	}

	@Override
	public List<NoticeboardDTO> noticeboardSearch(Map<String, String> map) {
		return sqlSession.selectList("noticeSQL.noticeboardSearch", map);
	}

	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("noticeSQL.getSearchTotalA", map);
	}

	@Override
	public void noticeboardUpdate(Map<String, String> map) {
		sqlSession.update("noticeSQL.noticeboardUpdate", map);
	}

	@Override
	public void noticeboardViewHit(int seq) {
		sqlSession.update("noticeSQL.noticeboardViewHit", seq);
	}
	
}
