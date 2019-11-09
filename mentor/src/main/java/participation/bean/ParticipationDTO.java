package participation.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ParticipationDTO {
	private int meetingboard_seq;	// 모임seq
	private String mentee_email;	// 멘티email
	private String mentee_name;		// 멘티이름
	private String mentor_email;	// 멘토email
	private String mentor_name;		// 멘토이름
	private String participation_address; // 거주지
	private String participation_question;		// 사전질문
	
}
