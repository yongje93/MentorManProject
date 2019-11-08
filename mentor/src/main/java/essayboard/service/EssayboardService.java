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

}
