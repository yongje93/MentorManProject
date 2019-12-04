package faq.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import faq.bean.FaqDTO;
import faq.service.FaqService;

@Controller
@RequestMapping("/faq")
public class FAQController {
	@Autowired
	private FaqService faqService;
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * @Title : 고객센터 메인화면
	 * @Author : kujun95, @Date : 2019. 11. 29.
	 */
	@RequestMapping(value = "faqIndex", method = RequestMethod.GET)
	public String customerService(Model model) {
	
		int catalog_count_1 = faqService.getCount_1("faq_Catalog_1");
		int catalog_count_2 = faqService.getCount_2("faq_Catalog_2");
		int catalog_count_3 = faqService.getCount_3("faq_Catalog_3");
		int catalog_count_4 = faqService.getCount_4("faq_Catalog_4");
		int catalog_count_5 = faqService.getCount_5("faq_Catalog_5");
		int catalog_count_6 = faqService.getCount_6("faq_Catalog_6");
		int catalog_count_7 = faqService.getCount_7("faq_Catalog_7");
		model.addAttribute("catalog_count_1", catalog_count_1);
		model.addAttribute("catalog_count_2", catalog_count_2);
		model.addAttribute("catalog_count_3", catalog_count_3);
		model.addAttribute("catalog_count_4", catalog_count_4);
		model.addAttribute("catalog_count_5", catalog_count_5);
		model.addAttribute("catalog_count_6", catalog_count_6);
		model.addAttribute("catalog_count_7", catalog_count_7);
		model.addAttribute("display", "/faq/faqMain.jsp");
		return "/faq/faqIndex";
	}
	/**
	 * @Title : 카탈로그 클릭 후 페이지
	 * @Author : kujun95, @Date : 2019. 11. 29.
	 */
	@RequestMapping(value = "faqIntegration", method = RequestMethod.GET)
	public String faqCatalog(@RequestParam String catalog, Model model) {
		List<FaqDTO> list = faqService.getFaqList(catalog);
		
		model.addAttribute("catalog", list.get(0).getCatalog_type());
		model.addAttribute("list", list);
		model.addAttribute("display", "/faq/faqCatalog.jsp");
		model.addAttribute("display2", "/faq/faqIntegration.jsp");
		return "/faq/faqIndex";
	}
	
	@RequestMapping(value = "faqToCntactWrite", method = RequestMethod.POST)
	public String faqToCntactWrite(@RequestParam Map<String, String> map, Model model) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				System.out.println(map);
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
				helper.setTo("didwodn8822@gmail.com");// 받는 사람
				helper.setFrom(map.get("faqToContact_email"));// 보내는 사람
				helper.setSubject("<"+map.get("faqToContact_email")+"> 문의");
				helper.setText(map.get("faqToContact_content"), true);
			};
		};
		
		mailSender.send(preparator);
		
		
		model.addAttribute("display", "/faq/faqMain.jsp");
		return "/faq/faqIndex";
	}

}
