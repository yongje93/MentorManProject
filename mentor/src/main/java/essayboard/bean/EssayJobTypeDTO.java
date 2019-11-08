package essayboard.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EssayJobTypeDTO {
	private String job_code;
	private String job_type;
}
