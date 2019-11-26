package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String member_email) throws UsernameNotFoundException {
		MemberDTO member = memberDAO.getMemberByEmail(member_email);

		if (member == null) {
			throw new UsernameNotFoundException(member_email);
		}

		return member;
	}

}
