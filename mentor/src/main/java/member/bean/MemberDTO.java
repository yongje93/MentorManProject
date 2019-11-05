package member.bean;


import lombok.Data;
/**
 * @Title : MemberDTO.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Data
public class MemberDTO {
	private String member_name;
	private String member_nickname;
	private int member_flag;
	private String member_pwd;
	private String member_email;
	private String member_repwd;
}
