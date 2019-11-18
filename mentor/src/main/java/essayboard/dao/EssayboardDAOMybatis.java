package essayboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;

@Repository
@Transactional
public class EssayboardDAOMybatis implements EssayboardDAO {
	@Autowired
	private SqlSession sqlSession;

	//에세이 멘토 리스트 출력
	@Override
	public List<EssayboardDTO> essayboardList(Map<String, Integer> map) {
		return sqlSession.selectList("essaySQL.essayboardList", map);
	}

	//에세이 글쓰기
	@Override
	public void essayboardWrite(Map<String, Object> map) {
		sqlSession.insert("essaySQL.essayboardWrite", map);
	}

	// 에세이 직무 유형
	@Override
	public List<EssayboardDTO> essayjobType(Map<String, List<String>> map) {
		System.out.println("매앱 " + map.toString());
		return sqlSession.selectList("essaySQL.essayjobType", map);
	}

	// 에세이 총 글 수
	@Override
	public int getTotalA(Map<String, Integer> map) {
		return sqlSession.selectOne("essaySQL.getTotalA", map);
	}

	// 에세이 글 수정
	@Override
	public EssayboardDTO essayboardModifyForm(int seq) {
		return sqlSession.selectOne("essaySQL.essayboardModifyForm", seq);
	}

	// 에세이 멘토 바디 뷰
	@Override
	public EssayboardDTO essaymentorBodyView(int seq) {
		return sqlSession.selectOne("essaySQL.essaymentorBodyView", seq);
	}

	// 에세이 정보 수정 처리
	@Override
	public void essayboardModify(Map<String, Object> map) {
		sqlSession.update("essaySQL.essayboardModify", map);
	}

	// 에세이 글 삭제
	@Override
	public void essayboardDelete(int seq) {
		sqlSession.delete("essaySQL.essayboardDelete", seq);
	}

	// 해당 멘토가 작성한 에세이 리스트 출력
	@Override
	public List<EssayboardDTO> getessayList(int member_seq) {
		return sqlSession.selectList("essaySQL.getessayList", member_seq);
	}

	// 해당 멘토가 작성한 에세이 수
	@Override
	public int getessayMentorTotal(int member_seq) {
		return sqlSession.selectOne("essaySQL.getessayMentorTotal", member_seq);
	}

	// 에세이 멘토 헤드 뷰
	@Override
	public EssayboardDTO essaymentorHeadView(int member_seq) {
		return sqlSession.selectOne("essaySQL.essaymentorHeadView", member_seq);
	}

	// 모임 후기 (고맙습니다)
	@Override
	public List<EssayboardDTO> getessayReview() {
		return sqlSession.selectList("essaySQL.getessayReview");
	}

	// 모임 후기 글 수
	@Override
	public int getreTotal() {
		return sqlSession.selectOne("essaySQL.getreTotal");
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
	
	// 최신 에세이 리스트
	@Override
	public List<EssayboardDTO> getNewEssay(Map<String, Integer> map) {
		return sqlSession.selectList("essaySQL.getNewEssay", map);
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
}
