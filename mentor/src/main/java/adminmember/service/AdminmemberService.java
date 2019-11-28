package adminmember.service;

import java.util.List;
import java.util.Map;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmentorDTO;

public interface AdminmemberService {
	//회원리스트
	public List<AdminmemberDTO> getAdminmemberList(int startNum, int endNum);

	public int getMemeberTotalA();
	
	public List<AdminmemberDTO> getSearchadminmemberList(Map<String, Object> map);

	public int getSearchmemeberTotalA(Map<String, Object> map);
	
	//멘토리스트
	public List<AdminmentorDTO> getAdminmentorList(int startNum, int endNum);

	public List<AdminmentorDTO> getSearchadminmentorList(Map<String, Object> map);

	public int getSearchmentorTotalA(Map<String, Object> map);

	public int getMentorTotalA();
	//멘토신청리스트
	public List<AdminmentorDTO> getAdminmentorApplyList(int startNum, int endNum);

	public int getMentorApplyTotalA();
	
	public void adminmentorSuccess(Map<String, String[]> map);

	public void adminmentorReject(Map<String, String[]> map);
	//멘티리스트
	public List<AdminmemberDTO> getAdminmenteeList(int startNum, int endNum);

	public int getMenteeTotalA();
	
	public List<AdminmemberDTO> getSearchadminmenteeList(Map<String, Object> map);

	public int getSearchmenteeTotalA(Map<String, Object> map);

	public List<AdminmentorDTO> getSearchadminmentorApplyList(Map<String, Object> map);

	public int getSearchmentorApplyTotalA(Map<String, Object> map);


	

}

