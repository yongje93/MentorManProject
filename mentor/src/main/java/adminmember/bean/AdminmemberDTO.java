package adminmember.bean;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class AdminmemberDTO {
	
	private String member_name;
	private String member_nickname;
	private String member_pwd;
	private String member_email;
	private String member_profile;
	private int member_flag;
	private int member_seq;
	private String logtime;
	
	//mentor join
	private int mentor_flag;
	private int mentor_badge;
}

