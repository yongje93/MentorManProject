package meetingboard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 모임 게시판 관련 컨트롤러
 * @author : yong
 * @date : 2019. 11. 2.
 */
@Controller
@RequestMapping(value = "meetingboard")
public class meetingboardController {
	/**
	 * @Title : 모임 게시판 리스트. head 영역의 모임 버튼 눌렀을때 화면
	 * @Author : yong
	 * @Date : 2019. 11. 2.
	 * @Method Name : meetingboardList
	 */
	@RequestMapping(value = "meetingboardList", method = RequestMethod.GET)
	public ModelAndView meetingboardList(@RequestParam(required = false, defaultValue = "1") String pg,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
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
}
