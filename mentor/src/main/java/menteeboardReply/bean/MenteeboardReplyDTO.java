package menteeboardReply.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Component
public class MenteeboardReplyDTO {
	private int menteeboardReply_seq;  //댓글의 seq
	private int menteeboardReply_mb_seq; //게시물의 seq
    private String menteeboardReply_email;   
    private String menteeboardReply_content; //댓글 내용
    
    //조인해서 사용할 변수
    private String member_profile;
	private String member_nickname; //댓글 주인
    
    private int menteeboardReply_ref; //원글의 seq그룹번호, 답글의 답글도 존재
    private int menteeboardReply_lev; //답글의 단계
    private int menteeboardReply_step; //글순서 원글은 0번으
    private int menteeboardReply_pseq; 
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date menteeboardReply_logtime;

}
