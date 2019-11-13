package mentorapply.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MentorapplyDTO {
	private int mentor_seq;
	private String mentor_name;
	private String mentor_company;
	private String mentor_department;
	private String mentor_position;
	private String job_code;
	private String mentor_school;
	private String mentor_career;
	private String mentoring_code;
	private String mentor_tel;
	private String mentor_represent;
	private String mentor_infro;
	private String mentor_etc;
	private String mentor_email;
	private String mentor_selectNaming;
	private String mentor_businesscard;
	private int mentor_badge;
	private int mentor_flag;
}
