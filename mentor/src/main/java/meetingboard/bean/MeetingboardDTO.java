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
	// 멘토 정보
	private String member_profile; // 멘토 이미지
	private String member_name; // 멘토 이름
	private String mentor_company; // 회사
	private String mentor_department; // 부서
	private String mentor_info; // 멘토 소개
	private String mentor_career; // 멘토 경력
	private String mentor_etc; // 멘토 기타사항
	private String mentor_represent; //대표 멘토링 분야
	
}
