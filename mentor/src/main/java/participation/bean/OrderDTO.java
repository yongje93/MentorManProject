package participation.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class OrderDTO {
	private String order_id;	// 주문ID
	private int order_price;	// 주문 가격
	private String mentee_email;// 멘티 이메일
	private String mentee_name;	// 멘티 이름
	private String mentee_tel;	// 멘티 전화번호
	private int meetingboard_seq; // 모임seq
	private int participation_seq;// 신청seq
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date order_date;	// 주문일자
	private int order_flag;		// 주문상태
	
	//조인관련
	private String meetingboard_title;
	private String job_code;
	private int meetingboard_price;
	private String meetingboard_day;
	private String mentor_email;
	private String review_seq;
}
