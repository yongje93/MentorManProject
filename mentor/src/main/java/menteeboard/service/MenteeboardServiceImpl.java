package menteeboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menteeboard.bean.MenteeboardDTO;
import menteeboard.bean.MenteeboardLikeDTO;
import menteeboard.dao.MenteeboardDAO;

@Service(value="menteeboardService")
public class MenteeboardServiceImpl implements MenteeboardService {
	@Autowired
	private MenteeboardDAO menteeboardDAO;
	
	
	@Override
	public List<MenteeboardDTO> getMenteeboardList(Map<String, Integer> map) {
		return menteeboardDAO.getMenteeboardList(map);
	}


	@Override
	public int getTotalA() {
		return menteeboardDAO.getTotalA();
	}


	@Override
	public void menteeboardWrite(Map<String, String> map) {
		menteeboardDAO.menteeboardWrite(map);
	}


	@Override
	public List<MenteeboardDTO> getMenteeboardListJob(Map<String, Object> map) {
		return menteeboardDAO.getMenteeboardListJob(map);
	}


	@Override
	public int getTotalAJob(String job_code) {
		return menteeboardDAO.getTotalA(job_code);
	}


	@Override
	public MenteeboardDTO getMenteeboard(int seq) {
		return menteeboardDAO.getMenteeboard(seq);
	}


	@Override
	public void menteeboardHit(int seq) {
		menteeboardDAO.menteeboardHit(seq);
	}


	@Override
	public void menteeboardDelete(int seq) {
		menteeboardDAO.menteeboardDelete(seq);
	}


	@Override
	public void menteeboardModify(Map<String, String> map) {
		menteeboardDAO.menteeboardModify(map);
	}


	@Override
	public void menteeboardLikeDelete(MenteeboardLikeDTO menteeboardLikeDTO) {
		menteeboardDAO.menteeboardLikeDelete(menteeboardLikeDTO);
		menteeboardDAO.menteeboardLikeUpdate(menteeboardLikeDTO.getMenteeboard_seq());
	}


	@Override
	public void menteeboardLikeInsert(MenteeboardLikeDTO menteeboardLikeDTO) {
		menteeboardDAO.menteeboardLikeInsert(menteeboardLikeDTO);
		menteeboardDAO.menteeboardLikeUpdate(menteeboardLikeDTO.getMenteeboard_seq());
	}


	@Override
	public int menteeboardSelect(MenteeboardLikeDTO menteeboardLikeDTO) {
		return menteeboardDAO.menteeboardSelect(menteeboardLikeDTO);
	}



	
	

}
