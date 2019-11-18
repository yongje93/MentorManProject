package menteeboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MenteeboardLikeDTO {
	private int menteeboardLike_mb_seq;
    private String menteeboardLike_mb_email;

}
