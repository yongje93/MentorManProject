package essayboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;
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
	public List<EssayboardDTO> essayjobType(Map<String, List<String>> map) {
		return essayboardDAO.essayjobType(map);
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
	public List<EssayboardDTO> getessayList(int member_seq) {
		return essayboardDAO.getessayList(member_seq);
	}
	
	// 해당 멘토가 작성한 에세이 수 
	@Override
	public int getessayMentorTotal(int member_seq) {
		return essayboardDAO.getessayMentorTotal(member_seq);
	}
	
	// 에세이 멘토 헤드 뷰
	@Override
	public EssayboardDTO essaymentorHeadView(int member_seq) {
		return essayboardDAO.essaymentorHeadView(member_seq);
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
	
	// 에세이 보드 조회수
	@Override
	public void essayboardHit(int seq) {
		essayboardDAO.essayboardHit(seq);
	}
	
	// 에세이 보드 조회수 출력
	@Override
	public int getessayboardHit(int seq) {
		return essayboardDAO.getessayboardHit(seq);
	}
	
	// 최신 에세이 리스트
	@Override
	public List<EssayboardDTO> getNewEssay(Map<String, Integer> map) {
		return essayboardDAO.getNewEssay(map);
	}

	@Override
	public int getEssayboardScrap(Map<String, Object> scrapMap) {
		return essayboardDAO.getEssayboardScrap(scrapMap);
	}

	@Override
	public void essayboardScrapInsert(EssayboardScrapDTO essayboardScrapDTO) {
		essayboardDAO.essayboardScrapInsert(essayboardScrapDTO);
		essayboardDAO.essayboardScrapUpdate(essayboardScrapDTO.getEssayboardScrap_es_seq());
	}

	@Override
	public void essayboardScrapDelete(EssayboardScrapDTO essayboardScrapDTO) {
		essayboardDAO.essayboardScrapDelete(essayboardScrapDTO);
		System.out.println("essayboardScrapDTO.getEssayScrap_es_seq() : " + essayboardScrapDTO.getEssayboardScrap_es_seq());
		essayboardDAO.essayboardScrapUpdate(essayboardScrapDTO.getEssayboardScrap_es_seq());
	}

	@Override
	public int getTotalScrap(int essayboardScrap_es_seq) {
		return essayboardDAO.getTotalScrap(essayboardScrap_es_seq);
	}

	@Override
	public List<EssayboardDTO> getEssayboardAttention(String memEmail) {
		return essayboardDAO.getEssayboardAttention(memEmail);
	}

}
