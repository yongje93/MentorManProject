package essayboard.dao;

import java.util.List;
import java.util.Map;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;
import mentor.bean.MentorDTO;

public interface EssayboardDAO {
	
	//(신규)에세이 멘토 리스트 출력
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map);
	
	// 추천 에세이 리스트
	public List<EssayboardDTO> getRecommendEssay(Map<String, Object> map);
		
	//에세이 글쓰기
	public void essayboardWrite(Map<String, Object> map);
	
	// 에세이 글 가져오기 (수정)
	public EssayboardDTO getEssayboard(int seq);
	
	// 에세이 직무 유형
	public List<EssayboardDTO> essayjobType(Map<String, Object> map);
	
	// 에세이 총 글 수
	public int getTotalA();
	
	// 에세이 뷰
	public EssayboardDTO getEssayboardView(int seq);
	
	// 에세이 정보 수정 처리
	public void essayboardModify(Map<String, Object> map);
	
	// 에세이 글 삭제
	public void essayboardDelete(int seq);
	
	// 멘토 명함 출력
	public MentorDTO getMentorBusinessCard(int member_seq);
	
	public int getEssayboardScrap(Map<String, Object> scrapMap);
	
	public void essayboardScrapUpdate(int essayboardScrap_es_seq);

	public void essayboardScrapInsert(EssayboardScrapDTO essayboardScrapDTO);

	public void essayboardScrapDelete(EssayboardScrapDTO essayboardScrapDTO);

	public int getTotalScrap(int essayboardScrap_es_seq);

	public List<EssayboardDTO> getEssayboardAttention(String memEmail);
	
	// 직무유형 총 글 수
	public int getEssayDuty(Map<String, Object> map);
	
	// 추천 에세이 총 글 수
	public int getRecommendTotal();

	public List<EssayboardDTO> getBestEssay(Map<String, Object> essayMap);

}
