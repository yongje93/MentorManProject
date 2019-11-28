package adminsales.controller;

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
	
	@RequestMapping(value="adminsalesChart",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray adminsalesChart() {
		List<Map<String, String>> list = adminsalesService.getAdminsalesChart();
		System.out.println(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
}
