package essayboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardPaging;
import essayboard.service.EssayboardService;
import member.bean.MemberDTO;

@Controller
@RequestMapping("/essayboard")
public class EssayboardController {
	@Autowired
	private EssayboardService essayboardService;
	@Autowired
	private EssayboardPaging essayboardPaging;

	/*
	 * 
	 * @Title : 에세이 리스트 출력
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essayboardList", method = RequestMethod.GET)
	public ModelAndView essayboardForm(@RequestParam(required = false, defaultValue = "1") String pg) {
		//List<EssayboardDTO> genogem = essayboardService.essayboardList();
		Map<String, Integer> map = new HashMap<String, Integer>();
		// 페이지 당 9개씩
		int endNum = Integer.parseInt(pg) * 9;
		int startNum = endNum - 8;
		
		map.put("endNum", endNum);
		map.put("startNum", startNum);
		
		// 총 글 수
		int totalA = essayboardService.getTotalA(map);
		
		map.put("totalA", totalA);
		
		//  멘토 리스트 가져오기
		List<EssayboardDTO> list = essayboardService.essayboardList(map);
		System.out.println("밤 늦게 뻘짓의 성과 " + list);
		ModelAndView modelAndView = new ModelAndView();
		
		essayboardPaging.setCurrentPage(Integer.parseInt(pg));
		essayboardPaging.setPageBlock(3);
		essayboardPaging.setPageSize(9);
		essayboardPaging.setTotalA(totalA);
		essayboardPaging.makePagingHTML();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("boardpaging", essayboardPaging);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("display", "/essayboard/essayboardList.jsp");
		modelAndView.setViewName("/main/index");

		return modelAndView;
	}
	
	
	/*
	 * 
	 * @Title : 에세이 글쓰기
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essayboardWriteForm", method = RequestMethod.GET)
	public String essayboardWriteForm(Model model) {
		model.addAttribute("display", "/essayboard/essayboardWriteForm.jsp");
		return "/main/index";
	}
	 
	/*
	 * 
	 * @Title : 에세이 글쓰기 처리
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essayboardWrite", method = RequestMethod.POST)
	public ModelAndView essayboardWrite(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("memDTO");
		String email = memberDTO.getMember_email();
		System.out.println("나오라요 이메일 " + email);
		map.put("mentor_email", email);
		essayboardService.essayboardWrite(map);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("display", "/essayboard/essayboardList?pg=1");
		modelAndView.setViewName("/main/index");
		return modelAndView;
	}
	
	 
	/**
	 * 
	 * @Title : 에세이 직무 유형별 검색
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essayjobType", method = RequestMethod.GET)
	public ModelAndView essayjobType(@RequestParam String jobType, Model model) {
		
		List<EssayboardDTO> list = essayboardService.essayjobType(jobType);
//		model.addAttribute("list", list);
//		model.addAttribute("display", "/essayboard/essayboardList.jsp");
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("display" , "/essayboard/essayboardList.jsp");
		modelAndView.setViewName("/main/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title : 에세이 멘토 BODY 뷰
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essaymentorBodyView", method = RequestMethod.GET)
	public ModelAndView essaymentorBodyView(@RequestParam String seq,
									  		@RequestParam String pg,
									  		@RequestParam String name) {
		System.out.println("ㄴeq " + seq);
		EssayboardDTO essayboardDTO = essayboardService.essaymentorBodyView(Integer.parseInt(seq));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("essayboardDTO", essayboardDTO);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("display", "/essayboard/essaymentorBodyView.jsp");
		modelAndView.setViewName("/main/index");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title : 에세이 멘토 HEAD 뷰 
	 * @Author : TR, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essaymentorHeadView", method = RequestMethod.GET)
	public ModelAndView essaymentorHeadView(@RequestParam String pg, 
											@RequestParam String seq,
											@RequestParam String name) {
		
		System.out.println("나오냐? " + name);
		// 해당 멘토가 작성한 에세이 리스트 출력
		List<EssayboardDTO> list = essayboardService.getessayList(name);
		// 해당 멘토가 작성한 에세이 수 
		int essayTotal = essayboardService.getessayMentorTotal(name);
		// 모임 후기 (고맙습니다)
		List<EssayboardDTO> relist = essayboardService.getessayReview();
		System.out.println("고맙습니더 " + relist);
		EssayboardDTO essayboardDTO = essayboardService.essaymentorHeadView(name);
		// 모임 후기 글 수
		int reTotal = essayboardService.getreTotal();
		
		
		System.out.println("헤드" + essayboardDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("relist", relist);
		modelAndView.addObject("reTotal", reTotal);
		modelAndView.addObject("essayTotal", essayTotal);
		modelAndView.addObject("list", list);
		modelAndView.addObject("essayboardDTO", essayboardDTO);
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("display", "/essayboard/essaymentorHeadView.jsp");
		modelAndView.setViewName("/main/index");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title : 에세이 정보 수정
	 * @Author : TR, @Date : 2019. 11. 7.
	 */
	@RequestMapping(value = "essayboardModifyForm", method = RequestMethod.GET)
	public ModelAndView essayboardModifyForm(String seq, String pg) {
		EssayboardDTO essayboardDTO = essayboardService.essayboardModifyForm(Integer.parseInt(seq));
		System.out.println("오 안나온다 나온다 " +  essayboardDTO);
		System.out.println("페이지 " + pg + " 시퀀스 " + seq);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("essayboardDTO", essayboardDTO);
		modelAndView.addObject("display", "/essayboard/essayboardModifyForm.jsp");
		modelAndView.setViewName("/main/index");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title : 에세이 정보 수정 처리
	 * @Author : TR, @Date : 2019. 11. 8.
	 */
	@RequestMapping(value = "essayboardModify", method = RequestMethod.POST)
	public ModelAndView essayboardModify(@RequestParam Map<String, Object> map) {
		map.put("name", "김태형");
		System.out.println("모디파이" + map);
		
		essayboardService.essayboardModify(map);
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("display", "/essayboard/essayboardList?.jsp");
		modelAndView.setViewName("/main/index");
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title : 에세이 보드 삭제
	 * @Author : TR, @Date : 2019. 11. 8.
	 */
	@RequestMapping(value = "essayboardDelete", method = RequestMethod.GET)
	public String essayboardDelete(@RequestParam String seq, Model model) {
		essayboardService.essayboardDelete(Integer.parseInt(seq));
		
		model.addAttribute("display", "/essayboard/essayboardList?pg=1.jsp");
		return "/main/index";
	}
	
//	/**
//	 * 
//	 * @Title : 해당 멘토가 작성한 에세이 리스트 출력
//	 * @Author : TR, @Date : 2019. 11. 8.
//	 */
//	@RequestMapping(value = "getessayList", method = RequestMethod.POST)
//	public ModelAndView getessayList(@RequestParam String seq) {
//		String name = "김태형";
//		List<EssayboardDTO> list = essayboardService.getessayList(name);
//		System.out.println("뤼스트" + list);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("list", list);
//		modelAndView.setViewName("jsonView");
//		return modelAndView;
//	}
}
