package meetingboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MeetingboardDTO {
	private int meeting_seq;		// 모임번호(PK)
	private String job_code;		// 직무분야(FK)
	private String title;			// 제목
	private String subtitle;		// 부제목
	private String content;			// 내용
	private String day;				// 일시
	private String starthour;		// 시작시간
	private String endhour;			// 종료시간
	private String address;			// 장소
	private String buildingname;	// 건물이름
	private String address_x;		// 위도
	private String address_y;		// 경도
	private int count;				// 모집인원
	private String host;			// 주최
	private int price;				// 참가비
	private String email;			// 멘토이메일(FK)
	private int state;				// 상태
	// 직무 유형 값때매 추가로 넣은 부분
	private String job_type;
	
}
