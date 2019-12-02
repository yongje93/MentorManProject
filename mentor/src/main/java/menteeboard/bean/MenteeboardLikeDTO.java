package menteeboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MenteeboardLikeDTO {
	private int menteeboardLike_mb_seq;
    private String menteeboardLike_mb_email;
    private Date menteeboardLike_logtime;

}
