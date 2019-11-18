package menteeboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Data
public class MenteeboardDTO {
	private int menteeboard_seq;
	private String menteeboard_profile;
    private String menteeboard_nickname;
    private String menteeboard_email;
    private String menteeboard_title;
    private String menteeboard_content;
    private String job_code; //직무유형
    
    private String job_type; //조인할때만 사용
    
    private int menteeboard_good;
    private int menteeboard_ref; 
    private int menteeboard_lev;
    private int menteeboard_step; 
    private int menteeboard_pseq; 
    private int menteeboard_reply; 
    private int menteeboard_hit; 
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date menteeboard_logtime;
}

