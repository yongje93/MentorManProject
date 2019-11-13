package menteeboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MenteeboardLikeDTO {
	private int menteeboard_seq;
    private String email;

}
