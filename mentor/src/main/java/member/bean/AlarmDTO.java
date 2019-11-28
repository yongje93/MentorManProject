package member.bean;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class AlarmDTO {
	private int myAlarm_seq;
	private String myAlarm_receiverEmail;
	private String myAlarm_callerNickname;
	private String myAlarm_title;
	private String myAlarm_content;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private String myAlarm_logtime;

}
