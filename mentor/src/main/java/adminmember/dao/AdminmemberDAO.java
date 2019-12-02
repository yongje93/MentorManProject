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
	
	public void adminmentorReject(Map<String, String[]> map);
	//멘토신청리스트
	public List<AdminmentorDTO> getAdminmentorApplyList(Map<String, Integer> map);

	public int getMentorApplyTotalA();
	//명예멘토
	public List<AdminmentorSalesListDTO> getMentorSales();

	public List<AdminmentorBoardListDTO> getMentorBoard();
	
	public void honorMentor(Map<String, String[]> map);
	
	//멘티리스트
	public List<AdminmemberDTO> getAdminmenteeList(Map<String, Integer> map);
	
	public int getMenteeTotalA();

	public List<AdminmemberDTO> getSearchadminmenteeList(Map<String, Object> map);

	public int getSearchmenteeTotalA(Map<String, Object> map);

	public List<AdminmentorDTO> getSearchadminmentorApplyList(Map<String, Object> map);

	public int getSearchmentorApplyTotalA(Map<String, Object> map);

}

