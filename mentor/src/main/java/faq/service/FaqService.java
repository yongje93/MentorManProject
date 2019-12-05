package faq.service;

import java.util.List;

import faq.bean.FaqDTO;

public interface FaqService {

	public List<FaqDTO> getFaqList(String catalog);

	public int getCount_1(String faq_catalog_1);
	
	public int getCount_2(String faq_catalog_2);
	
	public int getCount_3(String faq_catalog_3);
	
	public int getCount_4(String faq_catalog_4);
	
	public int getCount_5(String faq_catalog_5);

	public int getCount_6(String faq_catalog_6);
	
	public int getCount_7(String faq_catalog_7);


}
