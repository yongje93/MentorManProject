package adminmember.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminmentorSalesListDTO {
	
	//멘토조인
	private String mentor_seq;
	private String mentor_badge;
	private String mentor_email;
	//멤버조인
	private String member_profile;
	private String member_name;
	
	private int sales;
}
