package essayboard.service;

import java.util.List;
import java.util.Map;

import essayboard.bean.EssayboardDTO;

public interface EssayboardService {
	
	//에세이 멘토 리스트 출력
	public List<EssayboardDTO> essayboardList(Map<String, Integer> map);
	
	// 에세이 글쓰기
	public void essayboardWrite(Map<String, Object> map);
	
	// 에세이 직무 유형
	public List<EssayboardDTO> essayjobType(String jobType);
	
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
	public List<EssayboardDTO> getessayList(String name);
	
	// 해당 멘토가 작성한 에세이 수 
	public int getessayMentorTotal(String name);
	
	// 에세이 멘토 헤드 뷰
	public EssayboardDTO essaymentorHeadView(String name);
	
	// 모임 후기 (고맙습니다)
	public List<EssayboardDTO> getessayReview();
	
	// 모임 후기 글 수
	public int getreTotal();

}
