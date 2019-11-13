package member.bean;


import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * @Title : MemberDTO.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Data
@Component
public class MemberDTO {
	private int member_seq;
	private String member_name;
	private String member_nickname;
	private String member_pwd;
	private String member_email;
	private String member_profile;
	private int member_flag;
}
