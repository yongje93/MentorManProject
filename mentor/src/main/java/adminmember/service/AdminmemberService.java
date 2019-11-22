package adminmember.service;

import java.util.List;

import adminmember.bean.AdminmemberDTO;

public interface AdminmemberService {

	public List<AdminmemberDTO> getAdminmemberList(int startNum, int endNum);

	public int getTotalA();

}
