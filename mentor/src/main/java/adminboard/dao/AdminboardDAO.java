package adminboard.dao;

import java.util.List;
import java.util.Map;

import adminboard.bean.AdminnoticeboardDTO;
import essayboard.bean.EssayboardDTO;
import meetingboard.bean.MeetingboardDTO;
import menteeboard.bean.MenteeboardDTO;


/**
 * 
 * @Title : adminboard DAO
 * @author : 안상구
 * @date : 2019. 11. 8.
 */
public interface AdminboardDAO {

	public List<AdminnoticeboardDTO> getAdmninnoticeboardList(int startNum, int endNum);

	public int getTotalA();

	public void adminnoticeboardDelete(Map<String, String[]> map);

	public AdminnoticeboardDTO adminnoticeboardView(int seq);
	
	public void noticeboardModify(Map<String, String> map);

	public List<MeetingboardDTO> getMeetingboardList(Map<String, Integer> map);

	public int getMeetingboardTotalA();

	public void adminnoticeboardWrite(Map<String, String> map);

	public List<EssayboardDTO> getNewEssay(Map<String, Object> map);

	public int getessayTotalA();

	public List<MenteeboardDTO> getMenteeboardList(Map<String, Integer> map);

	public int getMenteeTotalA();

	public void menteeboardDelete(Map<String, String[]> map);

}
