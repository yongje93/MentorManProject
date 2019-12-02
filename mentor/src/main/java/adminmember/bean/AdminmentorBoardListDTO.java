package adminmember.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminmentorBoardListDTO {

	private String member_name;
	private int cnt;
}
