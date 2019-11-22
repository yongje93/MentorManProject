package adminsales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="adminsales")
public class AdminSalesController {
	
	@RequestMapping(value="adminsales",method=RequestMethod.GET)
	public String adminsales(Model model) {
		model.addAttribute("display", "/adminsales/adminsales.jsp");
		return "/admin/adminMain";
	}
}
