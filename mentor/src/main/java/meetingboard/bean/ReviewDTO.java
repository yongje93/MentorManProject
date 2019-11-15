package meetingboard.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReviewDTO {
	private int review_seq;
	private int meetingboard_seq;
	private String mentee_email;
	private String review_content;
	private Date review_date;
}
