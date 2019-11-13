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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date logtime;
	// 에세이  테이블 조인 전용 변수
	private String job_type;
	private String member_name; // 멘토 이름
	private String mentor_company; // 회사
	private String mentor_department; // 부서
	private String mentor_info; // 멘토 소개
	private String mentor_career; // 멘토 경력
	private String mentor_etc; // 멘토 기타사항
	private String mentor_represent; //대표 멘토링 분야
	private String mentoring_type; //멘토링 분야
	private String meeting_review_content; // 모임 후기
	private String meeting_review_name; // 모임 후기쓴 사용자
	private String meeting_review_logtime; // 모임 후기 쓴 날짜
}
