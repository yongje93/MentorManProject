package adminsales.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import adminsales.service.AdminsalesService;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="adminsales")
public class AdminSalesController {
	
	@Autowired
	AdminsalesService adminsalesService;
	
	@RequestMapping(value="adminsales",method=RequestMethod.GET)
	public String adminsales(Model model) {
		model.addAttribute("display", "/adminsales/adminsales.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="daySalesChart",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray daySalesChart() {
		List<Map<String, String>> list = adminsalesService.getDaysalesChart();
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
	
	@RequestMapping(value="monthSalesChart",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray monthSalesChart() {
		List<Map<String, String>> list = adminsalesService.getMonthsalesChart();
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
}
