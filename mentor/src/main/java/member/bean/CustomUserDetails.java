package member.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	private String member_email;
	private String member_pwd;
	private String member_name;
	private String member_nickname;
	private String member_profile;
	private int member_flag;
	private int member_seq;
	private Date logtime;
	private String memberAuthKey; // 이메일 인증키
	private int memberAuthStatus; // 이메일 인증상태
	private String memberAuthority; // 멤버 권한
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority(memberAuthority));
		return auth;
	}

	@Override
	public String getPassword() {
		return member_pwd;
	}

	@Override
	public String getUsername() {
		return member_email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
