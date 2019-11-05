package member.service;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberService {

	public MemberDTO writeNicknamecheck(String member_nickname);

	public MemberDTO writeEmailCheck(String member_email);

	public void write(Map<String, String> map);

	public MemberDTO login(Map<String, String> map);

	
	
	

	

}
