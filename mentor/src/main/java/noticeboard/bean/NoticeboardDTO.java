package noticeboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class NoticeboardDTO {
	private int noticeboard_seq;
	private String noticeboard_adminEmail;
	private String noticeboard_title;
	private String noticeboard_content;
	private int noticeboard_hit;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
	private Date noticeboard_logtime;
}
