package member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import member.bean.MemberDTO;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String member_email = (String) authentication.getPrincipal();
		String member_pwd = (String) authentication.getCredentials();

		MemberDTO member = (MemberDTO) customUserDetailsService.loadUserByUsername(member_email);

		if (!passwordEncoder.matches(member_pwd, member.getMember_pwd())) {
			throw new BadCredentialsException(member_email);
		}

		if (member.getMemberAuthStatus() == 0) {
			throw new DisabledException(member_email);
		}

		List<GrantedAuthority> roles = (List<GrantedAuthority>) member.getAuthorities();
		// 스프링 시큐리티 내부 클래스로 인증 토큰 생성
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(member_email, member_pwd, roles);

		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
