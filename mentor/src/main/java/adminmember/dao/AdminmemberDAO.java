package adminmember.dao;

import java.util.List;
import java.util.Map;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmentorBoardListDTO;
import adminmember.bean.AdminmentorDTO;
import adminmember.bean.AdminmentorSalesListDTO;

public interface AdminmemberDAO {
	//회원리스트
	public List<AdminmemberDTO> getAdminmemberList(Map<String, Integer> map);

	public List<AdminmemberDTO> memberClassfication(Map<String, Integer> map);

	public int getMemeberTotalA();
	//회원서치리스트
	public List<AdminmemberDTO> getSearchadminmemberList(Map<String, Object> map);

	public int getSearchmemeberTotalA(Map<String, Object> map);
	//멘토리스트
	public List<AdminmentorDTO> getAdminmentorList(Map<String, Integer> map);

	public int getMentorTotalA();
	//멘토서치리스트
	public List<AdminmentorDTO> getSearchadminmentorList(Map<String, Object> map);

	public int getSearchmentorTotalA(Map<String, Object> map);
	//멘토승낙
	public void adminmentorSuccess(Map<String, String[]> map);
	
  public void adminflagMentor(Map<String, String[]> map);

	public void adminmentorReject(Map<String, String[]> map);
	//멘토신청리스트
	public List<AdminmentorDTO> getAdminmentorApplyList(Map<String, Integer> map);

	public int getMentorApplyTotalA();
	
	public AdminmentorDTO adminmentorView(int mentor_seq);
	//명예멘토
	public List<AdminmentorSalesListDTO> getMentorSales(Map<String, Integer> map);

	public List<AdminmentorBoardListDTO> getMentorBoard(Map<String, Integer> map);

	public void honorMentor(Map<String, String[]> map);

	//멘티리스트
	public List<AdminmemberDTO> getAdminmenteeList(Map<String, Integer> map);

	public int getMenteeTotalA();

	public List<AdminmemberDTO> getSearchadminmenteeList(Map<String, Object> map);

	public int getSearchmenteeTotalA(Map<String, Object> map);

	public List<AdminmentorDTO> getSearchadminmentorApplyList(Map<String, Object> map);

	public int getSearchmentorApplyTotalA(Map<String, Object> map);
	
	public void setMentorUpdateAlarm(int check);

	public List<AdminmentorDTO> mentorClassfication(Map<String, Integer> map);

	public void deleteMember(Map<String, String[]> map);

	public void setMenteeUpdateAlarm(int check);

	public int getmentorSalesTotalA();

	public void setHonormentorUpdateAlarm(int check);



}
