package adminmember.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminmember.bean.AdminmemberDTO;
import adminmember.bean.AdminmentorBoardListDTO;
import adminmember.bean.AdminmentorDTO;
import adminmember.bean.AdminmentorSalesListDTO;
import adminmember.dao.AdminmemberDAO;

@Service
public class AdminmemberServiceImpl implements AdminmemberService{
	
	@Autowired
	private AdminmemberDAO adminmemberDAO;
	
	/* description : 회원전체 리스트*/
	@Override
	public List<AdminmemberDTO> getAdminmemberList(Map<String, Integer> map) {
		return adminmemberDAO.getAdminmemberList(map);
	}
	
	@Override
	public void deleteMember(Map<String, String[]> map) {
		adminmemberDAO.deleteMember(map);
	}

	@Override
	public List<AdminmemberDTO> memberClassfication(Map<String, Integer> map) {
		return adminmemberDAO.memberClassfication(map);
	}
	
	/* description : 회원전체  수*/
	@Override
	public int getMemeberTotalA() {
		return adminmemberDAO.getMemeberTotalA();
	}
	
	/* description : 검색회원전체 리스트*/
	@Override
	public List<AdminmemberDTO> getSearchadminmemberList(Map<String, Object> map) {
		return adminmemberDAO.getSearchadminmemberList(map);
	}
	/* description : 검색회원전체 수*/
	@Override
	public int getSearchmemeberTotalA(Map<String, Object> map) {
		return adminmemberDAO.getSearchmemeberTotalA(map);
	}
	
	/* description : 멘토리스트*/
	@Override
	public List<AdminmentorDTO> getAdminmentorList(Map<String, Integer> map) {
		return adminmemberDAO.getAdminmentorList(map);
	}
	/* description : 멘토회원 전체 수*/
	@Override
	public int getMentorTotalA() {
		return adminmemberDAO.getMentorTotalA();
	}

	@Override
	public List<AdminmentorDTO> getSearchadminmentorList(Map<String, Object> map) {
		return adminmemberDAO.getSearchadminmentorList(map);
	}
	@Override
	public int getSearchmentorTotalA(Map<String, Object> map) {
		return adminmemberDAO.getSearchmentorTotalA(map);
	}
	
	@Override
	public List<AdminmentorDTO> mentorClassfication(Map<String, Integer> map) {
		return adminmemberDAO.mentorClassfication(map);
	}
	/* description : 멘토회원신청 리스트*/
	@Override
	public List<AdminmentorDTO> getAdminmentorApplyList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum); 
		map.put("endNum", endNum); 
		return adminmemberDAO.getAdminmentorApplyList(map);
	}
	
	@Override
	public int getMentorApplyTotalA() {
		return adminmemberDAO.getMentorApplyTotalA();
	}
	
	@Override
	public List<AdminmentorDTO> getSearchadminmentorApplyList(Map<String, Object> map) {
		return adminmemberDAO.getSearchadminmentorApplyList(map);
	}
	@Override
	public int getSearchmentorApplyTotalA(Map<String, Object> map) {
		return adminmemberDAO.getSearchmentorApplyTotalA(map);
	}
	
	@Override
	public AdminmentorDTO adminmentorView(int mentor_seq) {
		return adminmemberDAO.adminmentorView(mentor_seq);
	}

	@Override
	public void adminmentorSuccess(Map<String, String[]> map) {
		adminmemberDAO.adminmentorSuccess(map);
	}
	
	@Override
	public void adminflagMentor(Map<String, String[]> map) {
		adminmemberDAO.adminflagMentor(map);
	}

	@Override
	public void adminmentorReject(Map<String, String[]> map) {
		adminmemberDAO.adminmentorReject(map);
	}
	
	//명예멘토
	@Override
	public List<AdminmentorSalesListDTO> getMentorSales(Map<String, Integer> map) {
		return adminmemberDAO.getMentorSales(map);
	}
	@Override
	public List<AdminmentorBoardListDTO> getMentorBoard(Map<String, Integer> map) {
		return adminmemberDAO.getMentorBoard(map);
	}
	
	@Override
	public int getmentorSalesTotalA() {
		return adminmemberDAO.getmentorSalesTotalA();
	}

	@Override
	public void honorMentor(Map<String, String[]> map) {
		adminmemberDAO.honorMentor(map);
	}
	
	//멘티리스트
	@Override
	public List<AdminmemberDTO> getAdminmenteeList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum); 
		map.put("endNum", endNum); 
		return adminmemberDAO.getAdminmenteeList(map);
	}

	@Override
	public int getMenteeTotalA() {
		return adminmemberDAO.getMenteeTotalA();
	}
	@Override
	public List<AdminmemberDTO> getSearchadminmenteeList(Map<String, Object> map) {
		return adminmemberDAO.getSearchadminmenteeList(map);
	}
	@Override
	public int getSearchmenteeTotalA(Map<String, Object> map) {
		return adminmemberDAO.getSearchmenteeTotalA(map);
	}
	@Override
	public void setMentorUpdateAlarm(int check) {
		adminmemberDAO.setMentorUpdateAlarm(check);
	}

	@Override
	public void setMenteeUpdateAlarm(int check) {
		adminmemberDAO.setMenteeUpdateAlarm(check);
	}

	@Override
	public void setHonormentorUpdateAlarm(int check) {
		adminmemberDAO.setHonormentorUpdateAlarm(check);
	}
	
	
}
