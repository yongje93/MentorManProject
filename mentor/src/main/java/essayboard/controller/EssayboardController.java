package essayboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardPaging;
import essayboard.bean.EssayboardScrapDTO;
import essayboard.service.EssayboardService;
import member.bean.MemberDTO;
import mentor.bean.MentorDTO;
import mentor.service.MentorService;

@Controller
@RequestMapping("/essayboard")
public class EssayboardController {
	@Autowired
	private EssayboardService essayboardService;
	@Autowired
	private EssayboardPaging essayboardPaging;
	@Autowired
	private MemberDTO memberDTO;
	@Autowired
	private EssayboardDTO essayboardDTO;
	@Autowired
	private EssayboardScrapDTO essayboardScrapDTO;
	@Autowired
	private MentorService mentorService;

	/*
	 *
	 * @Title : (신규)에세이 리스트 출력
	 * @Author : 김태형, @Date : 2019. 11. 15.
	 */
	@RequestMapping(value = "essayboardList", method = RequestMethod.GET)
	public ModelAndView essayboardForm(@RequestParam(required = false, defaultValue = "1") String pg,
									   @RequestParam(required = false, defaultValue = "0") String flag,
									   @RequestParam(required = false) String essayFlag,
									   HttpSession session,
									   HttpServletResponse response) {
		memberDTO = (MemberDTO)session.getAttribute("memDTO");

		Map<String, Object> map = new HashMap<String, Object>();
		// 페이지 당 9개씩
		int endNum = Integer.parseInt(pg) * 9;
		int startNum = endNum - 8;

		map.put("endNum", endNum);
		map.put("startNum", startNum);

		// 총 글 수
		int totalA = essayboardService.getTotalA();

		map.put("totalA", totalA);

		ModelAndView modelAndView = new ModelAndView();
		//  신규 에세이 리스트
		List<EssayboardDTO>list = essayboardService.getNewEssay(map);
		//로그인 여부에 따라
		if(memberDTO != null) {
			String nickname = memberDTO.getMember_nickname();
			modelAndView.addObject("memNickname" , nickname);
			modelAndView.addObject("memFlag", memberDTO.getMember_flag());

			//양재우 scrap 기능 구현
	         for (EssayboardDTO essayboardDTO : list) {
	        	 int seq = essayboardDTO.getEssayboard_seq();
	        	 Map<String, Object> scrapMap = new HashMap<String, Object>();
	        	 scrapMap.put("seq", seq);
	        	 scrapMap.put("memEmail" , memberDTO.getMember_email());

	        	 int cnt = essayboardService.getEssayboardScrap(scrapMap);
	        	 //스크랩을 눌렀다면
	        	 if(cnt == 1) {
	        		 //flag 1 저장
	        		 essayboardDTO.setEssayboard_scrapFlag(cnt);
	        	 }

	         }
		}

		essayboardPaging.setCurrentPage(Integer.parseInt(pg));
		essayboardPaging.setPageBlock(3);
		essayboardPaging.setPageSize(9);
		essayboardPaging.setTotalA(totalA);
		essayboardPaging.makePagingHTML();
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("boardpaging", essayboardPaging);
		if(essayFlag != null) {
			modelAndView.addObject("essayFlag", essayFlag);
		}
		modelAndView.addObject("flag", flag);
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
	@ResponseBody
	public void essayboardWrite(@RequestParam Map<String, Object> map, HttpSession session) {
		memberDTO = (MemberDTO)session.getAttribute("memDTO");
		String email = memberDTO.getMember_email();
		map.put("mentor_email", email);
		essayboardService.essayboardWrite(map);

	}

	/**
	 *
	 * @Title : 에세이 글 쓰기 이미지 처리
	 * @Author : 김태형, @Date : 2019. 11. 26.
	 */
	@RequestMapping(value = "essayboardImage", method = RequestMethod.POST)
	@ResponseBody
	public String noticeboardImage(@RequestParam("file") MultipartFile file) {
		String filePath = "C:\\Users\\TR\\Documents\\GitHub\\MentorMan\\mentor\\src\\main\\webapp\\storage";
		String fileName = file.getOriginalFilename();
		File files = new File(filePath, fileName);
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 *
	 * @Title : 에세이 글 가져오기 (수정)
	 * @Author : TR, @Date : 2019. 11. 7.
	 */
	@RequestMapping(value = "essayboardModifyForm", method = RequestMethod.GET)
	public ModelAndView essayboardModifyForm(String seq, String pg) {
		EssayboardDTO essayboardDTO = essayboardService.getEssayboard(Integer.parseInt(seq));
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
	@ResponseBody
	public void essayboardModify(@RequestParam Map<String, Object> map) {
		essayboardService.essayboardModify(map);
	}



	/**
	 *
	 * @Title : 에세이 직무 유형별 검색
	 * @Author : 김태형, @Date : 2019. 11. 21.
	 */
	@RequestMapping(value = "essayjobType", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView essayjobType(@RequestBody Map<String, Object> jsonData ,
										  HttpServletResponse response ,
										  HttpSession session) {

		List<EssayboardDTO> list = null;

		// job_code
		ArrayList<String> joblist = (ArrayList<String>) jsonData.get("job_code");
		// 현재 페이지
		int pg = (Integer) jsonData.get("pg");
		// 신규에세이, 추천에세이 플래그
		int flag = (Integer) jsonData.get("flag");

		// job_code 유무 체크
		String check = null;
		for (String jobs : joblist) {
			if(!jobs.equals(null)) {
				check = "success";
			}
		}

		memberDTO = (MemberDTO)session.getAttribute("memDTO");
		Map<String, Object> map = new HashMap<String, Object>();

		// 페이지 당 9개씩
		int endNum = pg * 9;
		int startNum = endNum - 8;

		map.put("startNum", startNum);
		map.put("endNum", endNum);
		map.put("job_code", joblist);
		map.put("flag", flag);

		// 직무유형 총 글 수
		int essayDutyTotal = 0;

		// 직무 유형 찾기
		if(check == "success") {
			// 직무유형에 대한  에세이 리스트
			list = essayboardService.essayjobType(map);
			// 직무유형에 대한 에세이 개수
			essayDutyTotal = essayboardService.getEssayDuty(map);
		}

		// job_code가 없을 경우 아래 코드를 실행한다.
		if(check == null && flag == 0){
			list = essayboardService.getNewEssay(map);
			essayDutyTotal = essayboardService.getTotalA();
		} else if(check == null && flag == 1) {
			list = essayboardService.getRecommendEssay(map);
			essayDutyTotal = essayboardService.getRecommendTotal();
		}

		ModelAndView modelAndView = new ModelAndView();

		essayboardPaging.setCurrentPage(pg);
		essayboardPaging.setPageBlock(3);
		essayboardPaging.setPageSize(9);
		essayboardPaging.setTotalA(essayDutyTotal);
		essayboardPaging.makePagingHTML();

		//로그인 여부에 따라
		if(memberDTO != null) {
			String nickname = memberDTO.getMember_nickname();
			modelAndView.addObject("memNickname" , nickname);
			modelAndView.addObject("memFlag", memberDTO.getMember_flag());

			//양재우 scrap 기능 구현
	         for (EssayboardDTO essayboardDTO : list) {
	        	 int seq = essayboardDTO.getEssayboard_seq();
	        	 Map<String, Object> scrapMap = new HashMap<String, Object>();
	        	 scrapMap.put("seq", seq);
	        	 scrapMap.put("memEmail" , memberDTO.getMember_email());

	        	 int cnt = essayboardService.getEssayboardScrap(scrapMap);
	        	 //스크랩을 눌렀다면
	        	 if(cnt == 1) {
	        		 //flag 1 저장
	        		 essayboardDTO.setEssayboard_scrapFlag(cnt);
	        	 }
	         }
		}
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("flag", flag);
		modelAndView.addObject("pageSize", essayboardPaging.getPageSize());
		modelAndView.addObject("boardpaging", essayboardPaging.getCurrentPage());
		modelAndView.addObject("essayDutyTotal", essayDutyTotal);
		modelAndView.addObject("list", list);
		modelAndView.setViewName("jsonView");

		return modelAndView;
	}

	/**
	 *
	 * @Title : 에세이 boardView
	 * @Author : 김태형, @Date : 2019. 11. 6.
	 */
	@RequestMapping(value = "essayboardView", method = RequestMethod.GET)
	public ModelAndView essaymentorBodyView(@RequestParam String seq,
											@RequestParam(required = false, defaultValue = "1") String pg,
									  		@RequestParam String mentors,
									  		HttpSession session,
									  		HttpServletRequest request,
									  		HttpServletResponse response) {

		memberDTO = (MemberDTO)session.getAttribute("memDTO");

		// 해당 멘토 명함 출력
		int mentor_seq = Integer.parseInt(mentors);
		MentorDTO mentorDTO = mentorService.getMentorInfomation(mentor_seq);
		String[] mentoringArray = mentorDTO.getMentoring_code().split(",");
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("mentoring_code", mentoringArray);
		List<MentorDTO> mentoringList = mentorService.getMentoring_code(map);
		
		// 에세이 보드
		essayboardDTO = essayboardService.getEssayboardView(Integer.parseInt(seq));

		//scrap flag 업데이트
		Map<String, Object> scrapMap = new HashMap<String, Object>();
	   	scrapMap.put("seq", seq);
	   	scrapMap.put("memEmail" , memberDTO.getMember_email());
	   	int cnt = essayboardService.getEssayboardScrap(scrapMap);
	   	 //스크랩을 눌렀다면
	   	if(cnt == 1) {
	   		 //flag 1 저장
	   		essayboardDTO.setEssayboard_scrapFlag(cnt);
	   	}

	   	Map<String, String> followMap = new HashMap<String, String>();
		followMap.put("memEmail" , memberDTO.getMember_email());
		followMap.put("mentorEmail" , mentorDTO.getMentor_email());
	   	//팔로우 찾기
	  	int follow = mentorService.getFollowCheck(followMap);

	   	ModelAndView modelAndView = new ModelAndView();
		// 로그인 후 멘토 SEQ
		//modelAndView.addObject("memSeq", memberDTO.getMember_seq());
		// 게시물을 쓴 멘토의 SEQ
		modelAndView.addObject("follow", follow);
		modelAndView.addObject("mentorDTO", mentorDTO);
		modelAndView.addObject("member_seq", mentors);
		modelAndView.addObject("mentoringList", mentoringList);
		modelAndView.addObject("essayboardDTO", essayboardDTO);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("display", "/essayboard/essayboardView.jsp");
		modelAndView.setViewName("/main/index");

		return modelAndView;
	}

	/**
	 *
	 * @Title : 에세이 보드 삭제
	 * @Author : TR, @Date : 2019. 11. 8.
	 */
	@RequestMapping(value = "essayboardDelete", method = RequestMethod.GET)
	@ResponseBody
	public void essayboardDelete(@RequestParam String seq) {
		essayboardService.essayboardDelete(Integer.parseInt(seq));
	}

	   @RequestMapping(value = "essayboardScrap", method = RequestMethod.POST)
	   @ResponseBody
	   public String essayboardScrap(@RequestParam int essayboardScrap_es_seq ,
			   					   @RequestParam int scrapFlag,
			   					   HttpSession session) {


		   memberDTO = (MemberDTO)session.getAttribute("memDTO");
		   essayboardScrapDTO.setEssayboardScrap_es_seq(essayboardScrap_es_seq);
		   essayboardScrapDTO.setEssayboardScrap_mem_email(memberDTO.getMember_email());
		   //스크랩 선택
		   if(scrapFlag == 1) {
			   essayboardService.essayboardScrapInsert(essayboardScrapDTO);
		   }
		   //스크랩 취소
		   else {
			   essayboardService.essayboardScrapDelete(essayboardScrapDTO);
		   }
		   //에세이의 총 스크랩수
		   int totalScrap = essayboardService.getTotalScrap(essayboardScrap_es_seq);

		   return totalScrap+"";
	   }



	   /**
	    *
	    * @Title :  스크랩트한 관심 에세이
	    * @Author : yangjaewoo, @Date : 2019. 11. 15.
	    */

	   @RequestMapping(value = "essayboardAttention", method = RequestMethod.GET)
	   public ModelAndView essayboardAttention(HttpSession session) {
		   MemberDTO memberDTO = (MemberDTO)session.getAttribute("memDTO");

		   String memEmail = memberDTO.getMember_email();

		   List<EssayboardDTO> list = essayboardService.getEssayboardAttention(memEmail);

		   for (EssayboardDTO essayboardDTO : list) {
	      	 int seq = essayboardDTO.getEssayboard_seq();
	      	 Map<String, Object> scrapMap = new HashMap<String, Object>();
	      	 scrapMap.put("seq", seq);
	      	 scrapMap.put("memEmail" , memberDTO.getMember_email());

	      	 int cnt = essayboardService.getEssayboardScrap(scrapMap);
	      	 //스크랩을 눌렀다면
	      	 if(cnt == 1) {
	      		 //flag 1 저장
	      		 essayboardDTO.setEssayboard_scrapFlag(cnt);
	     	  	 }
	       }

		   ModelAndView mav = new ModelAndView();

		   mav.addObject("list", list);
		   mav.addObject("display", "/essayboard/essayboardAttention.jsp");
		   mav.setViewName("/main/index");

		   return mav;

	   }
}
