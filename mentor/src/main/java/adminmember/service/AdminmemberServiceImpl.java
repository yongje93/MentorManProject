package adminmember.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminmember.bean.AdminmemberDTO;
import adminmember.dao.AdminmemberDAO;

@Service
public class AdminmemberServiceImpl implements AdminmemberService{
	
	@Autowired
	private AdminmemberDAO adminmemberDAO;
	
	@Override
	public List<AdminmemberDTO> getAdminmemberList(int startNum, int endNum) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum); 
		map.put("endNum", endNum); 
		return adminmemberDAO.getAdminmemberList(map);
	}

	@Override
	public int getTotalA() {
		return adminmemberDAO.getTotalA();
	}

}