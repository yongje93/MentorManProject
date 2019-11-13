package adminboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminboard.bean.AdminnoticeboardDTO;
import adminboard.dao.AdminboardDAO;

/**
 * 
 * @Title : adminboard 서비스
 * @author : 안상구
 * @date : 2019. 11. 8.
 */
@Service
public class AdminboardServiceImpl implements AdminboardService{
	@Autowired
	private AdminboardDAO adminboardDAO;
	
	@Override
	public List<AdminnoticeboardDTO> getAdmninnoticeboardList(int startNum, int endNum) {
		return adminboardDAO.getAdmninnoticeboardList(startNum,endNum);
	}

	@Override
	public int getTotalA() {
		return adminboardDAO.getTotalA();
	}

	@Override
	public void adminnoticeboardDelete(Map<String, String[]> map) {
		adminboardDAO.adminnoticeboardDelete(map);
	}
	
	@Override
	public AdminnoticeboardDTO adminnoticeboardView(int seq) {
		return adminboardDAO.adminnoticeboardView(seq);
	}
}
