package essayboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Data
public class EssayboardDTO {
	private int seq;
	private String name;
	private String company;
	private String title;
	private String content;
	private String job_code;
	// 에세이 job 테이블 조인 변수
	private String job_type;
	private int essay_like;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date logtime;
}
