package kakao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

import member.bean.MemberDTO;

@Controller
public class KakaoController {
	// 카카오 로그인
	@RequestMapping(value="/kakaoLogin", produces="application/json", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse respose, HttpSession session) throws Exception {
		// 결과값을 node에 담아줌
		JsonNode node = KakaoApi.getAccessToken(code);
		// accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token");
		// 사용자 정보
		JsonNode userInfo = KakaoApi.getKakaoUserInfo(accessToken);
		
		String id = null;
		String email = null;
		String nickName = null;
		String image = null;
		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		
		id = userInfo.path("id").asText();
		email = kakao_account.path("email").asText();
		nickName = properties.path("nickname").asText();
		image = properties.path("profile_image").asText();
		
		//System.out.println("id : " + id);
		//System.out.println("email : " + email);
        //System.out.println("name : " + nickName);
        //System.out.println("image : " + image);
        
        MemberDTO memDTO = new MemberDTO();
        memDTO.setMember_email(email);
        memDTO.setMember_nickname(nickName);
        memDTO.setMember_flag(0);
        if(image == null) {
        	memDTO.setMember_profile("profile.jpg");
        } else {
        	memDTO.setMember_profile(image);
        }
        session.setAttribute("memDTO", memDTO);
        session.setAttribute("access_token", accessToken);
		session.setMaxInactiveInterval(60*60*24); // 세션 1일 유지

        return new ModelAndView("redirect:/main/index");
	}
	
	//카카오 로그아웃
	@RequestMapping(value="/logout", produces="application/json")
	public String logout(HttpSession session) {
	    JsonNode node =  KakaoApi.kakaoLogout((JsonNode) session.getAttribute("access_token"));
	    //System.out.println("로그아웃 후 반환되는 아이디 : " + node.get("id"));
	    session.invalidate();
	    return "redirect:/main/index";
	}
}
