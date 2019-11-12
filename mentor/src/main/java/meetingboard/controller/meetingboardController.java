package meetingboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import meetingboard.bean.GuideDTO;
import meetingboard.bean.MeetingboardDTO;
import meetingboard.bean.MeetingboardPaging;
import meetingboard.service.MeetingboardService;
import member.bean.MemberDTO;
import participation.service.ParticipationService;

/**
 * 모임 게시판 관련 컨트롤러
 * @author : yong
 * @date : 2019. 11. 2.
 */
@Controller
@RequestMapping(value = "meetingboard")
public class MeetingboardController {
	@Autowired
	private MeetingboardService meetingboardService;
	@Autowired
	private MeetingboardPaging meetingboardPaging;
	@Autowired
	private ParticipationService participationService;

	/**
	 * @Title : 모임 게시판 리스트. head 영역의 모임 버튼 눌렀을때 화면
	 * @Author : yong
	 * @Date : 2019. 11. 2.
	 * @Method Name : meetingboardList 11. 6 페이징처리 추가
	 */
	@RequestMapping(value = "meetingboardList", method = RequestMethod.GET)
	public ModelAndView meetingboardList(@RequestParam(required = false, defaultValue = "1") String pg, HttpSession session) {	
		// 1페이지당 9개
		int endNum = Integer.parseInt(pg) * 9;
		int startNum = endNum - 8;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		List<MeetingboardDTO> meetingboardList = meetingboardService.getMeetingboardList(map);

		// 페이징 처리
		int totalA = meetingboardService.getTotalA();
		meetingboardPaging.setCurrentPage(Integer.parseInt(pg));
		meetingboardPaging.setPageBlock(3);
		meetingboardPaging.setPageSize(9);
		meetingboardPaging.setTotalA(totalA);
		meetingboardPaging.makePagingHTML();

		ModelAndView mav = new ModelAndView();
		mav.addObject("totalA", totalA);
		mav.addObject("pg", pg);
		mav.addObject("meetingboardList", meetingboardList);
		mav.addObject("meetingboardPaging", meetingboardPaging);
		mav.addObject("display", "/meetingboard/meetingboardList.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	/**
	 * @Title : 모임 작성 폼 열기(멘토일때)
	 * @Author : yong
	 * @Date : 2019. 11. 3.
	 * @Method Name : meetingboardWriteForm
	 */
	@RequestMapping(value = "meetingboardWriteForm", method = RequestMethod.GET)
	public String meetingboardWriteForm(Model model) {
		model.addAttribute("display", "/meetingboard/meetingboardWriteForm.jsp");
		return "/main/index";
	}

	/**
	 * @Title : summernote 이미지 업로드
	 * @Author : yong
	 * @Date : 2019. 11. 4.
	 * @Method Name : noticeboardImage
	 */
	@RequestMapping(value = "meetingboardImage", method = RequestMethod.POST)
	@ResponseBody
	public String meetingboardImage(@RequestParam("file") MultipartFile file) {
		String filePath = "C:/Users/yong/Documents/GitHub/MentorMan/mentor/src/main/webapp/storage";
		String fileName = file.getOriginalFilename();
		File files = new File(filePath, fileName);
		System.out.println(fileName);
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(fileName);
		return fileName;
	}

	/**
	 * @Title : 모임 작성하기
	 * @Author : yong
	 * @Date : 2019. 11. 5.
	 * @Method Name : meetingboardWrite
	 */
	@RequestMapping(value = "meetingboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void meetingboardWrite(@ModelAttribute MeetingboardDTO meetingboardDTO, HttpSession session) {
		MemberDTO memDTO = (MemberDTO) session.getAttribute("memDTO");
		meetingboardDTO.setMentor_email(memDTO.getMember_email());
		meetingboardService.meetingboardWrite(meetingboardDTO);
	}

	/**
	 * @Title : 모임 글 보기
	 * @Author : yong
	 * @Date : 2019. 11. 6.
	 * @Method Name : meetingboardView
	 */
	@RequestMapping(value = "meetingboardView", method = RequestMethod.GET)
	public ModelAndView meetingboardView(@RequestParam(required = false, defaultValue = "1") String pg, @RequestParam String seq) {
		int meetingboard_seq = Integer.parseInt(seq);
		MeetingboardDTO meetingboardDTO = meetingboardService.getMeetingboard(meetingboard_seq);
		// 안내사항
		List<GuideDTO> guideList = meetingboardService.getGuideList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("guideList", guideList);
		mav.addObject("meetingboardDTO", meetingboardDTO);
		mav.addObject("pg", pg);
		mav.addObject("display", "/meetingboard/meetingboardView.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	/**
	 * @Title : 모임 수정폼
	 * @Author : yong
	 * @Date : 2019. 11. 6.
	 * @Method Name : meetingboardModifyForm
	 */
	@RequestMapping(value = "meetingboardModifyForm", method = RequestMethod.POST)
	public ModelAndView meetingboardModifyForm(@RequestParam String pg, @RequestParam String seq) {
		int meetingboard_seq = Integer.parseInt(seq);
		MeetingboardDTO meetingboardDTO = meetingboardService.getMeetingboard(meetingboard_seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("meetingboardDTO", meetingboardDTO);
		mav.addObject("seq", seq);
		mav.addObject("pg", pg);
		mav.addObject("display", "/meetingboard/meetingboardModifyForm.jsp");
		mav.setViewName("/main/index");
		return mav;
	}

	/**
	 * @Title : 모임 수정
	 * @Author : yong
	 * @Date : 2019. 11. 7.
	 * @Method Name : meetingboardModify
	 */
	@RequestMapping(value = "meetingboardModify", method = RequestMethod.POST)
	@ResponseBody
	public void meetingboardModify(@ModelAttribute MeetingboardDTO meetingboardDTO, HttpSession session) {
		meetingboardService.meetingboardModify(meetingboardDTO);
	}

	/**
	 * @Title : 모임 삭제
	 * @Author : yong
	 * @Date : 2019. 11. 7.
	 * @Method Name : meetingboardDelete
	 */
	@RequestMapping(value = "meetingboardDelete", method = RequestMethod.POST)
	@ResponseBody
	public void meetingboardDelete(@RequestParam String seq) {
		int meetingboard_seq = Integer.parseInt(seq);
		meetingboardService.meetingboardDelete(meetingboard_seq);
	}
}
