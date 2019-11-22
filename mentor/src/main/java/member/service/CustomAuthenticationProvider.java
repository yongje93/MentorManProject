package member.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import member.bean.MemberDTO;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Inject
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String member_email = (String) authentication.getPrincipal();
		String member_pwd = (String) authentication.getCredentials();
		
		MemberDTO memberDTO = (MemberDTO) myUserDetailsService.loadUserByUsername(member_email);
		
		if(!passwordEncoder.matches(member_pwd, memberDTO.getPassword())) {
			throw new BadCredentialsException(member_email);
		}
		return new UsernamePasswordAuthenticationToken(member_email, member_pwd, memberDTO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
