package essayboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Data
public class EssayboardDTO {
	private int essayboard_seq;
	private String mentor_email;
	private String job_code;
	private String essayboard_title;
	private String essayboard_content;	
	private int essayboard_hit;
	private int essayboard_scrap;
	private int essayboard_scrapFlag;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date essayboard_logtime;
	
	// 에세이  테이블 조인 전용 변수
	private String member_profile;
	private String member_seq;
	private String member_name; // 멘토 이름
	private String mentor_company; // 회사
	private String mentor_department; // 부서
	private String mentor_flag;
	private String job_type;

}
