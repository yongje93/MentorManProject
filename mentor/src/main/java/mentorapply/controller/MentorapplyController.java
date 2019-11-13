package mentorapply.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;
import mentorapply.bean.MentorapplyDTO;
import mentorapply.service.MentorapplyService;

@Controller
@RequestMapping("/mentorapply")
public class MentorapplyController {
	@Autowired 
	private MentorapplyService mentorapplyService;
	
	/**
	 * @Title : 멘토 지원하기에 회사명, 부서, 직무
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyForm", method = RequestMethod.GET)
	public String mentorapplyForm(Model model, HttpSession session) {
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		MentorapplyDTO mentorapplyDTO = mentorapplyService.getEmail(memberDTO.getMember_email());
		model.addAttribute("mentorapplyDTO", mentorapplyDTO);
		model.addAttribute("display", "/mentorapply/mentorapplyForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 멘토 지원하기 모든 최종 정보 입력
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyWriteForm", method = RequestMethod.POST)
	public String mentorapplyStart(@RequestParam Map<String, String> map, Model model, HttpSession session) {
		
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("memDTO");
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("mentor_company", map.get("mentor_company"));
		model.addAttribute("mentor_department", map.get("mentor_department"));
		model.addAttribute("mentor_position", map.get("mentor_position"));
		model.addAttribute("display", "/mentorapply/mentorapplyWriteForm.jsp");
		return "/main/index";
	}
	
	/**
	 * @Title : 지원하기 버튼 누른 후 관리자의 동의 대기
	 * @Author : kujun95, @Date : 2019. 11. 11.
	 */
	@RequestMapping(value = "mentorapplyWrite", method = RequestMethod.POST)
	public String mentorapply(@RequestParam Map<String, String> map, @RequestParam("mentoring_code") String mentoring_code,@RequestParam("mentor_businesscard") MultipartFile mentor_businesscard, Model model) {
		map.put("mentoring_code", mentoring_code);
		System.out.println(map);
		String filePath = "C:/github/MentorMan/mentor/src/main/webapp/storage/"+map.get("mentor_email");
		String fileName = mentor_businesscard.getOriginalFilename();
		File filemake = new File(filePath);
		if(!filemake.exists()) {
			filemake.mkdirs();
		}
		File file = new File(filePath, fileName);
		try {
			FileCopyUtils.copy(mentor_businesscard.getInputStream(), new FileOutputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("mentor_businesscard",fileName);
		mentorapplyService.mentorapplyWrite(map);
		model.addAttribute("display", "/mentorapply/mentorapplyWrite.jsp");
		return "/main/index";
	}
}
