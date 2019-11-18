package essayboard.dao;

import java.util.List;
import java.util.Map;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;

public interface EssayboardDAO {
	
	//에세이 멘토 리스트 출력
	public List<EssayboardDTO> essayboardList(Map<String, Integer> map);
	
	//에세이 글쓰기
	public void essayboardWrite(Map<String, Object> map);
	
	// 에세이 직무 유형
	public List<EssayboardDTO> essayjobType(Map<String, List<String>> map);
	
	// 에세이 총 글 수
	public int getTotalA(Map<String, Integer> map);
	
	// 에세이 글 수정
	public EssayboardDTO essayboardModifyForm(int seq);
	
	// 에세이 멘토 바디 뷰
	public EssayboardDTO essaymentorBodyView(int seq);
	
	// 에세이 정보 수정 처리
	public void essayboardModify(Map<String, Object> map);
	
	// 에세이 글 삭제
	public void essayboardDelete(int seq);
	
	// 해당 멘토가 작성한 에세이 리스트 출력
	public List<EssayboardDTO> getessayList(int member_seq);
	
	// 해당 멘토가 작성한 에세이 수 
	public int getessayMentorTotal(int member_seq);
	
	// 에세이 멘토 헤드 뷰
	public EssayboardDTO essaymentorHeadView(int member_seq);
	
	// 모임 후기 (고맙습니다)
	public List<EssayboardDTO> getessayReview();
	
	// 모임 후기 글 수
	public int getreTotal();
	
	// 에세이 보드 조회수
	public void essayboardHit(int seq);
	
	// 에세이 보드 조회수 출력
	public int getessayboardHit(int seq);
	
	// 최신 에세이 리스트
	public List<EssayboardDTO> getNewEssay(Map<String, Integer> map);

	public int getEssayboardScrap(Map<String, Object> scrapMap);
	
	public void essayboardScrapUpdate(int essayboardScrap_es_seq);

	public void essayboardScrapInsert(EssayboardScrapDTO essayboardScrapDTO);

	public void essayboardScrapDelete(EssayboardScrapDTO essayboardScrapDTO);

	public int getTotalScrap(int essayboardScrap_es_seq);

	public List<EssayboardDTO> getEssayboardAttention(String memEmail);

}
