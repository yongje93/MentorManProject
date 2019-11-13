package essayboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import essayboard.bean.EssayboardDTO;
import essayboard.dao.EssayboardDAO;

@Service
public class EssayboardServiceImpl implements EssayboardService {
	@Autowired
	private EssayboardDAO essayboardDAO;
	
	// 에세이 멘토 리스트 출력
	@Override
	public List<EssayboardDTO> essayboardList(Map<String, Integer> map) {
		return essayboardDAO.essayboardList(map);
	}
	
	//에세이 글쓰기
	@Override
	public void essayboardWrite(Map<String, Object> map) {
		essayboardDAO.essayboardWrite(map);
	}
	
	// 에세이 직무 유형
	@Override
	public List<EssayboardDTO> essayjobType(String jobType) {
		return essayboardDAO.essayjobType(jobType);
	}
	
	// 에세이 총 글 수
	@Override
	public int getTotalA(Map<String, Integer> map) {
		return essayboardDAO.getTotalA(map);
	}
	
	// 에세이 글 수정
	@Override
	public EssayboardDTO essayboardModifyForm(int seq) {
		return essayboardDAO.essayboardModifyForm(seq);
	}
	
	// 에세이 멘토 바디 뷰
	@Override
	public EssayboardDTO essaymentorBodyView(int seq) {
		return essayboardDAO.essaymentorBodyView(seq);
	}
	
	// 에세이 정보 수정 처리
	@Override
	public void essayboardModify(Map<String, Object> map) {
		essayboardDAO.essayboardModify(map);
	}
	
	// 에세이 글 삭제
	@Override
	public void essayboardDelete(int seq) {
		essayboardDAO.essayboardDelete(seq);
	}
	
	// 해당 멘토가 작성한 에세이 리스트 출력
	@Override
	public List<EssayboardDTO> getessayList(String name) {
		return essayboardDAO.getessayList(name);
	}
	
	// 해당 멘토가 작성한 에세이 수 
	@Override
	public int getessayMentorTotal(String name) {
		return essayboardDAO.getessayMentorTotal(name);
	}
	
	// 에세이 멘토 헤드 뷰
	@Override
	public EssayboardDTO essaymentorHeadView(String name) {
		return essayboardDAO.essaymentorHeadView(name);
	}
	
	// 모임 후기 (고맙습니다)
	@Override
	public List<EssayboardDTO> getessayReview() {
		return essayboardDAO.getessayReview();
	}
	
	// 모임 후기 글 수
	@Override
	public int getreTotal() {
		return essayboardDAO.getreTotal();
	}
}
