package member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired 
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String member_email) throws UsernameNotFoundException {
		MemberDTO memberDTO = memberDAO.getMemberByEmail(member_email);
		if(memberDTO == null) {
			throw new UsernameNotFoundException(member_email);
		}
		return memberDTO;
	}

}
