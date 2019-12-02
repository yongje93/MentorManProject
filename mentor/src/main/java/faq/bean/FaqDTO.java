package faq.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class FaqDTO {
	private String catalog_type;
	private String catalog_code;
	private String faq_title;
	private String faq_content;
}
