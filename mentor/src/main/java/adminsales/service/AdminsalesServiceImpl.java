package adminsales.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adminsales.dao.AdminsalesDAO;

@Service
public class AdminsalesServiceImpl implements AdminsalesService{

	@Autowired
	AdminsalesDAO adminsalesDAO;

	@Override
	public List<Map<String, String>> getAdminsalesChart() {
		return adminsalesDAO.getAdminsalesChart();
	}
	
	

}
