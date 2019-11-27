package essayboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardScrapDTO;
import essayboard.dao.EssayboardDAO;
import mentor.bean.MentorDTO;

@Service
public class EssayboardServiceImpl implements EssayboardService {
	@Autowired
	private EssayboardDAO essayboardDAO;
	
	//(신규)에세이 멘토 리스트 출력
	@Override
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map) {
		return essayboardDAO.getNewEssay(map);
	}
	
	// 추천 에세이 리스트
	@Override
	public List<EssayboardDTO> getRecommendEssay(Map<String, Object> map) {
		return essayboardDAO.getRecommendEssay(map);
	}
	
	//에세이 글쓰기
	@Override
	public void essayboardWrite(Map<String, Object> map) {
		essayboardDAO.essayboardWrite(map);
	}
	
	// 에세이 직무 유형
	@Override
	public List<EssayboardDTO> essayjobType(Map<String, Object> map) {
		return essayboardDAO.essayjobType(map);
	}
	
	// 에세이 총 글 수
	@Override
	public int getTotalA() {
		return essayboardDAO.getTotalA();
	}
	
	// 에세이 글 가져오기 (수정)
	@Override
	public EssayboardDTO getEssayboard(int seq) {
		return essayboardDAO.getEssayboard(seq);
	}
	
	// 멘토 명함 출력
	@Override
	public MentorDTO getMentorBusinessCard(int member_seq) {
		return essayboardDAO.getMentorBusinessCard(member_seq);
	}
	
	
	// 에세이 뷰
	@Override
	public EssayboardDTO getEssayboardView(int seq) {
		return essayboardDAO.getEssayboardView(seq);
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
	
	// 직무유형 총 글 수
	@Override
	public int getEssayDuty(Map<String, Object> map) {
		return essayboardDAO.getEssayDuty(map);
	}
	
	// 추천 에세이 총 글 수
	@Override
	public int getRecommendTotal() {
		return essayboardDAO.getRecommendTotal();
	}

	@Override
	public List<EssayboardDTO> getBestEssay(Map<String, Object> essayMap) {
		return essayboardDAO.getBestEssay(essayMap);
	}
}
