package adminsales.service;

import java.util.List;
import java.util.Map;

import adminmember.bean.AdminmentorSalesListDTO;


public interface AdminsalesService {

	public List<Map<String, String>> getDaysalesChart();

	public List<Map<String, String>> getMonthsalesChart();

	public List<Map<String, String>> mentorSalesChart();

	public List<AdminmentorSalesListDTO> getMentorSales(Map<String, Integer> map);

	public int getMentorSalesTotalA();

	public List<AdminmentorSalesListDTO> getMentorView(String member_name);
}
