package adminboard.service;

import java.util.List;
import java.util.Map;

import adminboard.bean.AdminnoticeboardDTO;

/**
 * 
 * @Title : adminboard 서비스 인터페이스
 * @author : 안상구
 * @date : 2019. 11. 8.
 */
public interface AdminboardService {

	public List<AdminnoticeboardDTO> getAdmninnoticeboardList(int startNum, int endNum);

	public int getTotalA();

	public void adminnoticeboardDelete(Map<String, String[]> map);

	public AdminnoticeboardDTO adminnoticeboardView(int seq);
	
}
