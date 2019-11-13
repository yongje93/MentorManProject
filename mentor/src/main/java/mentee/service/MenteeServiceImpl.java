package mentee.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import mentee.bean.MenteeDTO;
import mentee.dao.MenteeDAO;

@Service
public class MenteeServiceImpl implements MenteeService {
	@Autowired
	private MenteeDAO menteeDAO;

	@Override
	public void mentorUserModify(Map<String, String> map) {
		menteeDAO.mentorUserModify(map);
	}

	@Override
	public MemberDTO getSaveMember(String member_email) {
		return menteeDAO.getSaveMember(member_email);
	}

	@Override
	public MenteeDTO getStudentEmail(String member_email) {
		return menteeDAO.getStudentEmail(member_email);
	}

	@Override
	public void menteeStudentInput(Map<String, String> map) {
		menteeDAO.menteeStudentInput(map);
	}

	@Override
	public MenteeDTO getEmployeeEmail(String member_email) {
		return menteeDAO.getEmployeeEmail(member_email);
	}

	@Override
	public void menteeEmployeeInput(Map<String, String> map) {
		menteeDAO.menteeEmployeeInput(map);
	}
	
	@Override
	public MemberDTO menteePasswordCheck(String member_email) {
		return menteeDAO.menteePasswordCheck(member_email);
	}
	
	@Override
	public void menteePasswordSave(Map<String, String> map) {
		menteeDAO.menteePasswordSave(map);
	}

}
