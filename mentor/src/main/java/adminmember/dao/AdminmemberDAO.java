package adminmember.dao;

import java.util.List;
import java.util.Map;

import adminmember.bean.AdminmemberDTO;

public interface AdminmemberDAO {

	public List<AdminmemberDTO> getAdminmemberList(Map<String, Integer> map);

	public int getTotalA();

}
