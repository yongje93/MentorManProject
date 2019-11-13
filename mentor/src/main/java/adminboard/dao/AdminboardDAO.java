package adminboard.dao;

import java.util.List;
import java.util.Map;

import adminboard.bean.AdminnoticeboardDTO;


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

}
