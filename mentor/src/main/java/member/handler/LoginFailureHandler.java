package member.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import lombok.Data;

@Data
public class LoginFailureHandler implements AuthenticationFailureHandler {
	private String loginemailname;
	private String loginpwdname;
	private String errormsgname;
	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String member_email = request.getParameter(loginemailname);
		String member_pwd = request.getParameter(loginpwdname);
		String errormsg = null;

		if (exception instanceof BadCredentialsException) {
			errormsg = "1"; // 비밀번호를 잘못 입력했습니다.
		} else if (exception instanceof UsernameNotFoundException) {
			errormsg = "2"; // 계정이 존재하지 않습니다.
		} else if (exception instanceof DisabledException) {
			errormsg = "3"; // 이메일 인증을 해주세요.
		} else if (exception instanceof SessionAuthenticationException) {
			errormsg = "4"; // 중복 로그인
		}

		request.setAttribute(loginemailname, member_email);
		request.setAttribute(loginpwdname, member_pwd);
		request.setAttribute(errormsgname, errormsg);

		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

}
