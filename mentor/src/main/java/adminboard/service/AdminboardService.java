package adminboard.service;

import java.util.List;
import java.util.Map;

import adminboard.bean.AdminnoticeboardDTO;
import essayboard.bean.EssayboardDTO;
import meetingboard.bean.MeetingboardDTO;
import menteeboard.bean.MenteeboardDTO;

/**
 * 
 * @Title : adminboard 서비스 인터페이스
 * @author : 안상구
 * @date : 2019. 11. 8./ 11.22 모임게시판추가
 */
public interface AdminboardService {

	public List<AdminnoticeboardDTO> getAdmninnoticeboardList(int startNum, int endNum);

	public int getTotalA();

	public void adminnoticeboardDelete(Map<String, String[]> map);

	public void adminnoticeboardWrite(Map<String, String> map);
	
	public AdminnoticeboardDTO adminnoticeboardView(int seq);
	
	public void noticeboardModify(Map<String, String> map);
	
	//모임 게시판
	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map);

	public int getMeetingboardTotalA();
	
	//에세이
	public List<EssayboardDTO> getNewEssay(Map<String, Object> map);

	public int getessayTotalA();
	
	//멘티게시판
	public List<MenteeboardDTO> getMenteeboardList(Map<String, Integer> map);

	public int getMenteeTotalA();

	public void menteeboardDelete(Map<String, String[]> map);
	
}
