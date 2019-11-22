package mentee.service;

import java.util.Map;

import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;

public interface MenteeService {

	public void mentorUserModify(Map<String, String> map);

	public MemberDTO getSaveMember(String member_email);

	public MenteeDTO getStudentEmail(String member_email);

	public void menteeStudentInput(Map<String, String> map);

	public MenteeDTO getEmployeeEmail(String member_email);

	public void menteeEmployeeInput(Map<String, String> map);

	public MemberDTO menteePasswordCheck(String member_email);

	public void menteePasswordSave(Map<String, String> map);

	public MemberDTO getNickname(String member_nickname);


}
