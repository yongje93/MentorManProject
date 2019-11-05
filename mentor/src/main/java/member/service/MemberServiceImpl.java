package member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;
/**
 * @Title : 회원가입 Service.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Service(value="memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired private MemberDAO memberDAO;
	//Nickname 중복확인
	@Override
	public MemberDTO writeNicknamecheck(String member_nickname) {
		return memberDAO.writeNicknamecheck(member_nickname);
	}
	//Email중복확인
	@Override
	public MemberDTO writeEmailCheck(String member_email) {
		return memberDAO.writeEmailCheck(member_email);
	}
	//회원가입 완료
	@Override
	public void write(Map<String, String> map) {
		memberDAO.write(map);
	}
	@Override
	public MemberDTO login(Map<String, String> map) {
		return memberDAO.login(map);
	}


}

