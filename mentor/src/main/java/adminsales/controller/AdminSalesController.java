package adminsales.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adminmember.bean.AdminmentorBoardListDTO;
import adminmember.bean.AdminmentorSalesListDTO;
import adminsales.service.AdminsalesService;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="adminsales")
public class AdminSalesController {
	
	@Autowired
	AdminsalesService adminsalesService;
	
	
	@RequestMapping(value="daysSales",method=RequestMethod.GET)
	public String daysSales(Model model) {
		model.addAttribute("display", "/adminsales/daysSales.jsp");
		return "/admin/adminMain";
	}
	@RequestMapping(value="monthSales",method=RequestMethod.GET)
	public String monthSales(Model model) {
		model.addAttribute("display", "/adminsales/monthSales.jsp");
		return "/admin/adminMain";
	}
	
	@RequestMapping(value="mentorSalesRatio",method=RequestMethod.GET)
	public String mentorSalesRatio(Model model) {
		model.addAttribute("display", "/adminsales/mentorSalesRatio.jsp");
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
	
	@RequestMapping(value="mentorSalesChart",method=RequestMethod.POST)
	@ResponseBody
	public JSONArray mentorSalesChart() {
		List<Map<String, String>> list = adminsalesService.mentorSalesChart();
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray;
	}
	
	@RequestMapping(value="mentorSales",method=RequestMethod.GET)
	public ModelAndView mentorSales(ModelAndView mav,
									@RequestParam (required=false,defaultValue="1") String pg) {
		//사람 매출
		List<AdminmentorSalesListDTO> salesList = adminsalesService.getMentorSales();
		//사람 리스트 개수
		
		mav.addObject("salesList", salesList);
		mav.addObject("display", "/adminsales/mentorSales.jsp");
		mav.setViewName("/admin/adminMain");
		return mav;
	}
}
