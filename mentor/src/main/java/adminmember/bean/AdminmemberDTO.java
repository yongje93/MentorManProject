package adminmember.bean;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class AdminmemberDTO {

	private String member_name;
	private String member_nickName;
	private String member_email;
	private String member_pwd;
	private int member_flag;
	private String member_profile;
	private String member_seq;
	private String member_logtime;
}

