package essayboard.bean;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Component
@Data
public class EssayboardScrapDTO {
	
	private int essayboardScrap_es_seq;
	private String essayboardScrap_mem_email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy.MM.dd")
	private Date essatboardScrap_logtime;
}
