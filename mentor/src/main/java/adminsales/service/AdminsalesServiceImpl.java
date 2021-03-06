package adminsales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminmember.bean.AdminmentorSalesListDTO;
import adminsales.dao.AdminsalesDAO;

@Service
public class AdminsalesServiceImpl implements AdminsalesService{

	@Autowired
	AdminsalesDAO adminsalesDAO;

	@Override
	public List<Map<String, String>> getDaysalesChart() {
		return adminsalesDAO.getDaysalesChart();
	}

	@Override
	public List<Map<String, String>> getMonthsalesChart() {
		return adminsalesDAO.getMonthsalesChart();
	}

	@Override
	public List<Map<String, String>> mentorSalesChart() {
		return adminsalesDAO.mentorSalesChart();
	}

	@Override
	public List<AdminmentorSalesListDTO> getMentorSales(Map<String, Integer> map) {
		return adminsalesDAO.getMentorSales(map);
	}

	@Override
	public int getMentorSalesTotalA() {
		return adminsalesDAO.getMentorSalesTotalA();
	}

	@Override
	public List<AdminmentorSalesListDTO> getMentorView(String member_name) {
		return adminsalesDAO.getMentorView(member_name);
	}
	
	

}
