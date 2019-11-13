package essayboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import essayboard.bean.EssayboardDTO;

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
	public List<EssayboardDTO> essayjobType(String jobType) {
		return sqlSession.selectList("essaySQL.essayjobType", jobType);
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
	public List<EssayboardDTO> getessayList(String name) {
		return sqlSession.selectList("essaySQL.getessayList", name);
	}

	// 해당 멘토가 작성한 에세이 수
	@Override
	public int getessayMentorTotal(String name) {
		return sqlSession.selectOne("essaySQL.getessayMentorTotal", name);
	}

	// 에세이 멘토 헤드 뷰
	@Override
	public EssayboardDTO essaymentorHeadView(String name) {
		return sqlSession.selectOne("essaySQL.essaymentorHeadView", name);
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
}
