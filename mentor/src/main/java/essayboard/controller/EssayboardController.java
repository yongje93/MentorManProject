package essayboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import essayboard.bean.EssayboardDTO;
import essayboard.bean.EssayboardPaging;
import essayboard.bean.EssayboardScrapDTO;
import essayboard.service.EssayboardService;
import member.bean.MemberDTO;

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
   private EssayboardScrapDTO essayboardScrapDTO;
   
   private List<String> jobcodeList = new ArrayList<String>();
   /*
    * 
    * @Title : (추천)에세이 리스트 출력
    * @Author : 김태형, @Date : 2019. 11. 6.
    */
   @RequestMapping(value = "essayboardList", method = RequestMethod.GET)
   public ModelAndView essayboardForm(@RequestParam(required = false, defaultValue = "1") String pg,
                              @RequestParam String flag,
                              HttpSession session,
                              HttpServletResponse response) {
      memberDTO = (MemberDTO)session.getAttribute("memDTO");
      List<EssayboardDTO> list = null;
      
      
      Map<String, Integer> map = new HashMap<String, Integer>();
      // 페이지 당 9개씩
      int endNum = Integer.parseInt(pg) * 9;
      int startNum = endNum - 8;
      
      map.put("endNum", endNum);
      map.put("startNum", startNum);
      
      // 총 글 수
      int totalA = essayboardService.getTotalA(map);
      
      map.put("totalA", totalA);
      
      ModelAndView modelAndView = new ModelAndView();
      
      if(flag.equals("1")) {
         //  추천 에세이 리스트 가져오기
         list = essayboardService.essayboardList(map);         
      } else if (flag.equals("0")) {
         //  신규 에세이 리스트
         list = essayboardService.getNewEssay(map);         
      }
      //로그인 여부에 따라
      
      
      if(memberDTO != null) {
         String nickname = memberDTO.getMember_nickname();
         modelAndView.addObject("memNickname" , nickname);
         
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
         
         
         //조회수(쿠키 생성) 
         if(nickname != null) {
            Cookie cookie = new Cookie("memHit","0");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
         }
      }

      essayboardPaging.setCurrentPage(Integer.parseInt(pg));
      essayboardPaging.setPageBlock(3);
      essayboardPaging.setPageSize(9);
      essayboardPaging.setTotalA(totalA);
      essayboardPaging.makePagingHTML();
      modelAndView.addObject("flag", flag);
      modelAndView.addObject("pg", pg);
      modelAndView.addObject("boardpaging", essayboardPaging);
      modelAndView.addObject("map", map);
      modelAndView.addObject("list", list);
      modelAndView.addObject("display", "/essayboard/essayboardList.jsp");
      modelAndView.setViewName("/main/index");

      return modelAndView;
   }
   
//   /**
//    * 
//    * @Title : (최신순) 에세이 리스트 
//    * @Author : TR, @Date : 2019. 11. 12.
//    */
//   @RequestMapping(value = "essayboardNewEssayList", method = RequestMethod.GET)
//   public ModelAndView essayboardNewEssayList() {
//      
//      // 최신 에세이 리스트
//      List<EssayboardDTO> list = essayboardService.getNewEssay();
//      
//      Map<String, Integer> map = new HashMap<String, Integer>();
//      // 페이지 당 9개씩
//      int endNum = Integer.parseInt(pg) * 9;
//      int startNum = endNum - 8;
//      
//      map.put("endNum", endNum);
//      map.put("startNum", startNum);
//      
//      // 총 글 수
//      int totalA = essayboardService.getTotalA(map);
//      
//      ModelAndView modelAndView = new ModelAndView();
//      
//      modelAndView.addObject("list", list);
//      modelAndView.addObject("display", "/essayboard/essayboardNewEssayList.jsp");
//      modelAndView.setViewName("/main/index");
//      
//      return modelAndView;
//   }
   
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
      map.put("mentor_email", email);
      essayboardService.essayboardWrite(map);
      
      ModelAndView modelAndView = new ModelAndView();
      
      modelAndView.addObject("display", "/essayboard/essayboardList.jsp");
      modelAndView.setViewName("/main/index");
      return modelAndView;
   }
   
    
   /**
    * 
    * @Title : 에세이 직무 유형별 검색
    * @Author : 김태형, @Date : 2019. 11. 6.
    */
   @RequestMapping(value = "essayjobType", method = RequestMethod.GET)
   public ModelAndView essayjobType(@RequestParam String job_code, Model model) {

      jobcodeList.add(job_code);
      
      
      
      Map<String, List<String>> map = new HashMap<String, List<String>>();
      
      
      
      
      for (int i = 0; i < jobcodeList.size(); i++) {
          String   joblist = jobcodeList.get(i);
          if(job_code == joblist) {
             jobcodeList.remove(job_code);
          } else {
             map.put("job_code", jobcodeList);
          }
      }
      
      System.out.println(jobcodeList.toString());
      
      
//      if(jobcodeList == null) {
         List<EssayboardDTO> list = essayboardService.essayjobType(map);
//      }
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
                                   HttpSession session,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
      
      MemberDTO memberDTO = (MemberDTO)session.getAttribute("memDTO");
      
      Cookie[] getCookie = request.getCookies();
      if(getCookie != null) {
         for(int i =0; i<getCookie.length; i++){
            if(getCookie[i].getName().equals("memHit")){
               //hit + 1
               essayboardService.essayboardHit(Integer.parseInt(seq));
               
               getCookie[i].setMaxAge(0);
               response.addCookie(getCookie[i]);
            }
         }
      }
      
      // 에세이 보드 조회수 출력
      int essayHit = essayboardService.getessayboardHit(Integer.parseInt(seq));
      
      System.out.println("ㄴeq " + seq);
      EssayboardDTO essayboardDTO = essayboardService.essaymentorBodyView(Integer.parseInt(seq));

      
      ModelAndView modelAndView = new ModelAndView();
      
      modelAndView.addObject("essayHit", essayHit);
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
                                 @RequestParam String memtors) {
      System.out.println("스컨스 " + seq);
      System.out.println("페지 " + pg);
      System.out.println("나오냐? " + memtors);
      // 해당 멘토가 작성한 에세이 리스트 출력
      List<EssayboardDTO> list = essayboardService.getessayList(Integer.parseInt(memtors));
      // 해당 멘토가 작성한 에세이 수 
      //int essayTotal = essayboardService.getessayMentorTotal(Integer.parseInt(memtors));
      int essayTotal = list.size();
      System.out.println("글수 :" +essayTotal );
      // 모임 후기 (고맙습니다)
      List<EssayboardDTO> relist = essayboardService.getessayReview();
      System.out.println("고맙습니더 " + relist);
      // 에세이 멘토 헤드 뷰
      EssayboardDTO essayboardDTO = essayboardService.essaymentorHeadView(Integer.parseInt(memtors));
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
      
      modelAndView.addObject("display", "/essayboard/essayboardList.jsp");
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
   
//   @RequestMapping(value = "essayboardHit", method = RequestMethod.POST)
//   public void essayboardHit(@RequestParam(required = false, defaultValue = "0")String essay_hit) {
//      System.out.println("조회수 " + essay_hit);
//      if(essay_hit == "1") {
//         essayboardService.essayboardViewHit(Integer.parseInt(seq));
//      }
//   }
   
//   /**
//    * 
//    * @Title : 해당 멘토가 작성한 에세이 리스트 출력
//    * @Author : TR, @Date : 2019. 11. 8.
//    */
//   @RequestMapping(value = "getessayList", method = RequestMethod.POST)
//   public ModelAndView getessayList(@RequestParam String seq) {
//      String name = "김태형";
//      List<EssayboardDTO> list = essayboardService.getessayList(name);
//      System.out.println("뤼스트" + list);
//      ModelAndView modelAndView = new ModelAndView();
//      modelAndView.addObject("list", list);
//      modelAndView.setViewName("jsonView");
//      return modelAndView;
//   }
   
   
   @RequestMapping(value = "essayboardScrap", method = RequestMethod.POST)
   @ResponseBody
   public String essayboardScrap(@RequestParam int essayboardScrap_es_seq ,
		   					   @RequestParam int scrapFlag,
		   					   HttpSession session) {
   
	   System.out.println("essayScrap_es_seq : " + essayboardScrap_es_seq + " , scrapFlag : " + scrapFlag);
	   
	   memberDTO = (MemberDTO)session.getAttribute("memDTO");
	   essayboardScrapDTO.setEssayboardScrap_es_seq(essayboardScrap_es_seq);
	   essayboardScrapDTO.setEssayboardScrap_mem_email(memberDTO.getMember_email());
	   System.out.println("essayboardScrapDTO ::: " + essayboardScrapDTO);
	   //스크랩 선택
	   if(scrapFlag == 1) {
		   essayboardService.essayboardScrapInsert(essayboardScrapDTO);
	   }
	   //스크랩 취소
	   else {
		   essayboardService.essayboardScrapDelete(essayboardScrapDTO);
	   }
	   
	   int totalScrap = essayboardService.getTotalScrap(essayboardScrap_es_seq);
	   System.out.println("totalScrap ^^ : " + totalScrap);
	   
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
	   
	   System.out.println("email : "+memEmail+" , list :::::: " + list);
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