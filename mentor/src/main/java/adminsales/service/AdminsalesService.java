package adminsales.service;

import java.util.List;
import java.util.Map;


public interface AdminsalesService {

	public List<Map<String, String>> getDaysalesChart();

	public List<Map<String, String>> getMonthsalesChart();

	public List<Map<String, String>> mentorSalesChart();
}
