package member.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Data;
import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Data
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private MemberDAO memberDAO;
	
	private static int TIME = 60 * 60 * 24; // 하루
	private String defaultUrl;
	
	private RequestCache reqCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MemberDTO memberDTO = memberDAO.getMemberByEmail(authentication.getName());
		
		//System.out.println("로그인 된 유저DTO : " + memberDTO);
		HttpSession session = request.getSession();
		session.setAttribute("memDTO", memberDTO);
		session.setMaxInactiveInterval(TIME);
		
		//에러 세션 지우기
		clearAuthenticationAttributes(request);

		if(memberDTO.getMember_nickname().equals("admin")) {
			redirectStratgy.sendRedirect(request, response, "/admin/adminMain");
		} else {
			resultRedirectStrategy(request, response, authentication);
		}
		
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = reqCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStratgy.sendRedirect(request, response, targetUrl);
		} else {
			redirectStratgy.sendRedirect(request, response, defaultUrl);
		}
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session==null) return;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
}
