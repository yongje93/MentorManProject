package menteeboardReply.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class MenteeboardReplyDTO {
	private int seq;  //댓글의 seq
	private int menteeboard_seq; //게시물의 seq
	private String nickname; //댓글 주인
    private String email;   
    private String content; //댓글 내용
    
    private int ref; //원글의 seq그룹번호, 답글의 답글도 존재
    private int lev; //답글의 단계
    private int step; //글순서 원글은 0번으
    private int pseq; 
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date logtime;

}
