package essayboard.service;

import java.util.List;
import java.util.Map;

import essayboard.bean.EssayboardScrapDTO;
import essayboard.bean.EssayboardDTO;
import mentor.bean.MentorDTO;

public interface EssayboardService {
	
	//(신규)에세이 멘토 리스트 출력
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map);
	
	// 추천 에세이 리스트
	public List<EssayboardDTO> getRecommendEssay(Map<String, Object> map);
	
	// 에세이 글쓰기
	public void essayboardWrite(Map<String, Object> map);
	
	// 에세이 직무 유형
	public List<EssayboardDTO> essayjobType(Map<String, Object> map);
	
	// 에세이 총 글 수
	public int getTotalA();
	
	// 에세이 글 가져오기 (수정)
	public EssayboardDTO getEssayboard(int seq);
	
	// 멘토 명함 출력
	public MentorDTO getMentorBusinessCard(int member_seq);
		
	// 에세이 뷰
	public EssayboardDTO getEssayboardView(int seq);
	
	// 에세이 정보 수정 처리
	public void essayboardModify(Map<String, Object> map);
	
	// 에세이 글 삭제
	public void essayboardDelete(int seq);

	// 에세이 보드 조회수
	public void essayboardHit(int seq);
	
	// 에세이 보드 조회수 출력
	public int getessayboardHit(int seq);
	
	
    /**
     * 
     * @Title : 스크랩 기능 구현
     * @Author : yangjaewoo, @Date : 2019. 11. 15.
     */
	//스크랩 정보를 가져온다
	public int getEssayboardScrap(Map<String, Object> scrapMap);
    // 에세이 스크랩시 데이터 저장
	public void essayboardScrapInsert(EssayboardScrapDTO essayboardScrapDTO);
	// 에세이 스크랩 취소시 데이터 삭제
	public void essayboardScrapDelete(EssayboardScrapDTO essayboardScrapDTO);
	// 에세이보드의 총 스크랩 수
	public int getTotalScrap(int essayboardScrap_es_seq);
	// 내가 스크랩했던 내용 전부 가져오기
	public List<EssayboardDTO> getEssayboardAttention(String memEmail);
	
	// 직무유형 총 글 수
	public int getEssayDuty(Map<String, Object> map);
	
	// 추천 에세이 총 글 수
	public int getRecommendTotal();

	public List<EssayboardDTO> getBestEssay(Map<String, Object> essayMap);
	
	
}
