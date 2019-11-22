package menteeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import menteeboard.bean.MenteeboardDTO;
import menteeboard.bean.MenteeboardLikeDTO;

@Transactional
@Repository(value="menteeboardDAO")
public class MenteeboardDAOMybatis implements MenteeboardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MenteeboardDTO> getMenteeboardList(Map<String, Integer> map) {
		return sqlSession.selectList("menteeboardSQL.getMenteeboardList" , map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("menteeboardSQL.getTotalA");
	}

	@Override
	public void menteeboardWrite(Map<String, String> map) {
		sqlSession.insert("menteeboardSQL.menteeboardWrite", map);
	}

	@Override
	public List<MenteeboardDTO> getMenteeboardListJob(Map<String, Object> map) {
		return sqlSession.selectList("menteeboardSQL.getMenteeboardListJob" , map);
	}

	@Override
	public int getTotalA(String job_code) {
		return sqlSession.selectOne("menteeboardSQL.getTotalAJob" , job_code);
	}

	@Override
	public MenteeboardDTO getMenteeboard(int seq) {
		return sqlSession.selectOne("menteeboardSQL.getMenteeboard" , seq);
	}

	@Override
	public void menteeboardHit(int seq) {
		sqlSession.update("menteeboardSQL.menteeboardHit" , seq);
	}

	@Override
	public void menteeboardDelete(int seq) {
		sqlSession.delete("menteeboardSQL.menteeboardDelete" , seq);
	}

	@Override
	public void menteeboardModify(Map<String, String> map) {
		sqlSession.update("menteeboardSQL.menteeboardModify" , map);
	}

	@Override
	public void menteeboardLikeDelete(MenteeboardLikeDTO menteeboardLikeDTO) {
		sqlSession.delete("menteeboardSQL.menteeboardLikeDelete", menteeboardLikeDTO);
	}
	@Override
	public void menteeboardLikeInsert(MenteeboardLikeDTO menteeboardLikeDTO) {
		sqlSession.insert("menteeboardSQL.menteeboardLikeInsert" , menteeboardLikeDTO);
	}
	@Override
	public void menteeboardLikeUpdate(int seq) {
		sqlSession.update("menteeboardSQL.menteeboardLikeUpdate" , seq);
	}

	@Override
	public int menteeboardSelect(MenteeboardLikeDTO menteeboardLikeDTO) {
		return sqlSession.selectOne("menteeboardSQL.menteeboardSelect" , menteeboardLikeDTO );
	}

	@Override
	public List<MenteeboardDTO> menteeboardSearch(Map<String, String> map) {
		return sqlSession.selectList("menteeboardSQL.menteeboardSearch", map);
	}

	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("menteeboardSQL.getSearchTotalA", map);
	}

}
