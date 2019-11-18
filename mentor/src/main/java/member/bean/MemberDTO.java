package member.bean;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * @Title : MemberDTO.
 * @author : ginkgo1928
 * @date : 2019. 11. 5.
 */
@Data
@Component
public class MemberDTO {
	private String member_name;
	private String member_nickname;
	private String member_pwd;
	private String member_email;
	private String member_profile;
	private int member_flag;
	private int member_seq;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-DD")
	private Date logtime;

}

