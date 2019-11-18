package mentor.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MentorDTO {
	private int mentor_seq;
	private String mentor_company;
	private String mentor_department;
	private String mentor_position;
	private String job_code;
	private String mentor_school;
	private String mentor_career;
	private String mentoring_code;
	private String mentor_tel;
	private String mentor_represent;
	private String mentor_info;
	private String mentor_etc;
	private String mentor_email;
	private String mentor_selectNaming;
	private String mentor_businesscard;
	private int mentor_badge;
	private int mentor_flag;
	
	
	//---join 멘토 찾기-------------------------
	private String mentoring_type;
	private String member_name;
	private String member_profile;
	private String job_type;
	
	//---join 질문 답변-------------------------
	private String question_title;
	private String question_content;
	private int question_flag;
	
	//--join 에세이
	private String essayboard_title; // 에세이 보드 제목
	private String essayboard_content; // 에세이 보드 내용
	private int essayboard_scrap;
	private Date essayboard_logtime; //
	
	//--join 고맙습니다
	private String review_content;	// 고맙습니다 내용
	private Date review_date;	// 고맙습니다 쓴날
	private String mentee_email; // 고맙습니다 쓴 사람 email

}
