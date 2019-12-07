package adminmember.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminmentorDTO {
	private int mentor_seq;
	private String mentor_company;
	private String mentor_department;
	private int mentor_badge;
	private int mentor_flag;
	private String mentor_logtime;
	private String mentor_career;
	private String mentor_info;
	private String mentor_businesscard;
	private String mentor_email;
	//멤버 조인
	private String member_name;
	private String member_profile;
	private int member_seq;
	//직무 코드 조인
	private String job_type;
}
