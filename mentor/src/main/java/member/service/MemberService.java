package member.service;

import java.util.List;
import java.util.Map;
import member.bean.MemberDTO;
import mentor.bean.MentorDTO;

public interface MemberService {
	
	public MemberDTO writeNicknamecheck(String member_nickname);

	public MemberDTO writeEmailCheck(String member_email);

	public void write(Map<String, String> map);

	public MemberDTO login(Map<String, String> map);

	public MemberDTO setmemberpwd(Map<String, String> map);

	public MemberDTO newPwdCommit(Map<String, String> map);

	public List<MentorDTO> getQandA(String member_email);

	
	
	
	
	
	
	
	
	
	
}
