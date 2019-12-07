package adminsales.dao;

import java.util.List;
import java.util.Map;

import adminmember.bean.AdminmentorSalesListDTO;

public interface AdminsalesDAO {

	public List<Map<String, String>> getDaysalesChart();

	public List<Map<String, String>> getMonthsalesChart();

	public List<Map<String, String>> mentorSalesChart();

	public List<AdminmentorSalesListDTO> getMentorSales();
}
