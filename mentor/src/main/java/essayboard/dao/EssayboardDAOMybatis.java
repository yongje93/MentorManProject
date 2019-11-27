package essayboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;
import mentor.bean.MentorDTO;

@Repository
@Transactional
public class EssayboardDAOMybatis implements EssayboardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//(신규)에세이 멘토 리스트 출력
	@Override
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map) {
		return sqlSession.selectList("essaySQL.getNewEssay", map);
	}
	
	// 추천 에세이 리스트
	@Override
	public List<EssayboardDTO> getRecommendEssay(Map<String, Object> map) {
		return sqlSession.selectList("essaySQL.getRecommendEssay", map);
	}
	
	//에세이 글쓰기
	@Override
	public void essayboardWrite(Map<String, Object> map) {
		sqlSession.insert("essaySQL.essayboardWrite", map);	
	}
	
	// 에세이 직무 유형
	@Override
	public List<EssayboardDTO> essayjobType(Map<String, Object> map) {
		return sqlSession.selectList("essaySQL.essayjobType", map);
	}
	
	// 에세이 총 글 수
	@Override
	public int getTotalA() {
		return sqlSession.selectOne("essaySQL.getTotalA");
	}
	
	// 에세이 글 가져오기 (수정)
	@Override
	public EssayboardDTO getEssayboard(int seq) {
		return sqlSession.selectOne("essaySQL.getEssayboard", seq);
	}
	
	// 에세이 정보 수정 처리
	@Override
	public void essayboardModify(Map<String, Object> map) {
		System.out.println("map = " + map);
		sqlSession.update("essaySQL.essayboardModify", map);
	}
	
	// 멘토 명함 출력
	@Override
	public MentorDTO getMentorBusinessCard(int member_seq) {
		return sqlSession.selectOne("essaySQL.getMentorBusinessCard", member_seq);
	}
	
	// 에세이 멘토 뷰
	@Override
	public EssayboardDTO getEssayboardView(int seq) {
		return sqlSession.selectOne("essaySQL.getEssayboardView", seq);
	}
	
	// 에세이 글 삭제
	@Override
	public void essayboardDelete(int seq) {
		sqlSession.delete("essaySQL.essayboardDelete", seq);
	}
	
	// 에세이 보드 조회수
	@Override
	public void essayboardHit(int seq) {
		sqlSession.update("essaySQL.essayboardHit", seq);
	}
	
	// 에세이 보드 조회수 출력
	@Override
	public int getessayboardHit(int seq) {
		return sqlSession.selectOne("essaySQL.getessayboardHit", seq);
	}
	
	@Override
	public int getEssayboardScrap(Map<String, Object> scrapMap) {
		return sqlSession.selectOne("essaySQL.getEssayboardScrap" , scrapMap);
	}

	@Override
	public void essayboardScrapUpdate(int essayboardScrap_es_seq) {
		sqlSession.update("essaySQL.essayboardScrapUpdate" , essayboardScrap_es_seq);
	}
	@Override
	public void essayboardScrapInsert(EssayboardScrapDTO essayboardScrapDTO) {
		sqlSession.insert("essaySQL.essayboardScrapInsert" , essayboardScrapDTO);
	}
	@Override
	public void essayboardScrapDelete(EssayboardScrapDTO essayboardScrapDTO) {
		sqlSession.delete("essaySQL.essayboardScrapDelete" , essayboardScrapDTO);
	}

	@Override
	public int getTotalScrap(int essayboardScrap_es_seq) {
		return sqlSession.selectOne("essaySQL.getTotalScrap" , essayboardScrap_es_seq);
	}

	@Override
	public List<EssayboardDTO> getEssayboardAttention(String memEmail) {
		return sqlSession.selectList("essaySQL.getEssayboardAttention" , memEmail);
	}
	
	// 직무유형 총 글 수
	@Override
	public int getEssayDuty(Map<String, Object> map) {
		return sqlSession.selectOne("essaySQL.getEssayDuty", map);
	}
	
	// 추천 에세이 총 글 수
	@Override
	public int getRecommendTotal() {
		return sqlSession.selectOne("essaySQL.getRecommendTotal");
	}

	@Override
	public List<EssayboardDTO> getBestEssay(Map<String, Object> essayMap) {
		return sqlSession.selectList("essaySQL.getBestEssay", essayMap);
	}
}
