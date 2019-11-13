package noticeboard.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noticeboard.bean.NoticeboardDTO;
import noticeboard.dao.NoticeboardDAO;

@Service("boardService")
public class NoticeboardServiceImpl implements NoticeboardService {
	@Autowired
	private NoticeboardDAO noticeboardDAO;
	
	@Override
	public void noticeboardWrite(Map<String, String> map) {
		noticeboardDAO.noticeboardWrite(map);
	}
	
	@Override
	public List<NoticeboardDTO> getBoardList(Map<String, Integer> map) {
		return noticeboardDAO.getBoardList(map);
	}

	@Override
	public int getTotalA() {
		return noticeboardDAO.getTotalA();
	}

	@Override
	public void noticeboardDelete(Map<String, String[]> map) {
		noticeboardDAO.noticeboardDelete(map);
	}

	@Override
	public NoticeboardDTO getNoticeboardView(int seq) {
		return noticeboardDAO.getNoticeboardView(seq);
	}

	@Override
	public List<NoticeboardDTO> noticeboardSearch(Map<String, String> map) {
		return noticeboardDAO.noticeboardSearch(map);
	}

	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return noticeboardDAO.getSearchTotalA(map);
	}

	@Override
	public void noticeboardUpdate(Map<String, String> map) {
		noticeboardDAO.noticeboardUpdate(map);
	}

	@Override
	public void noticeboardViewHit(int seq) {
		noticeboardDAO.noticeboardViewHit(seq);
		
	}

}
