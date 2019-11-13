package noticeboard.service;

import java.util.List;

import java.util.Map;

import noticeboard.bean.NoticeboardDTO;

public interface NoticeboardService {

	public List<NoticeboardDTO> getBoardList(Map<String, Integer> map);

	public void noticeboardWrite(Map<String, String> map);

	public int getTotalA();

	public void noticeboardDelete(Map<String, String[]> map);

	public NoticeboardDTO getNoticeboardView(int seq);

	public List<NoticeboardDTO> noticeboardSearch(Map<String, String> map);

	public int getSearchTotalA(Map<String, String> map);

	public void noticeboardUpdate(Map<String, String> map);

	public void noticeboardViewHit(int seq);

}
