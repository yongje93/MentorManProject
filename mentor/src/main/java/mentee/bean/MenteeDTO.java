package mentee.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MenteeDTO {
	//학생-------------------------------
	private String menteeStudent_school;
	private String menteeStudent_major;
	private String menteeStudent_state;
	private String menteeStudent_grade;
	private String menteeStudent_spec;
	private String menteeStudent_etc;
	private String menteeStudent_email;
	
	//직장인------------------------------
	
	private int menteeEmployee_year;
	private String menteeEmployee_final;
	private String menteeEmployee_school;
	private String menteeEmployee_career;
	private String menteeEmployee_etc;
	private String menteeEmployee_email;
	
}
