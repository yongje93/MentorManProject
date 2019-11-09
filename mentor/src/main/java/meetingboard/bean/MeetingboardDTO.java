package meetingboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MeetingboardDTO {
	private int meetingboard_seq;				// 모임번호(PK)
	private String job_code;					// 직무분야(FK)
	private String meetingboard_title;			// 제목
	private String meetingboard_subtitle;		// 부제목
	private String meetingboard_content;		// 내용
	private String meetingboard_day;			// 일시
	private String meetingboard_starthour;		// 시작시간
	private String meetingboard_endhour;		// 종료시간
	private String meetingboard_address;		// 장소
	private String meetingboard_buildingname;	// 건물이름
	private String meetingboard_address_x;		// 위도
	private String meetingboard_address_y;		// 경도
	private int meetingboard_count;				// 모집인원
	private String meetingboard_host;			// 주최
	private int meetingboard_price;				// 참가비
	private String mentor_email;				// 멘토이메일(FK)
	private int meetingboard_state;				// 상태
	// 직무 유형 값때매 추가로 넣은 부분
	private String job_type;
	
}
