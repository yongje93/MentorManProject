package adminboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AdminnoticeboardDTO {
	private int noticeboard_seq; // 시퀀스
	private String noticeboard_adminEmail; // 관리자이메일
	private String noticeboard_title; // 제목
	private String noticeboard_content; // 내용
	private int noticeboard_hit; //조회수
	private String noticeboard_logtime; // 날짜
}
