package faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faq.bean.FaqDTO;
import faq.dao.FaqDAO;

@Service
public class FaqServiceImpl implements FaqService {
	@Autowired
	private FaqDAO faqDAO;
	
	@Override
	public List<FaqDTO> getFaqList(String catalog) {
		return faqDAO.getFaqList(catalog);
	}
	
	@Override
	public int getCount_1(String faq_catalog_1) {
		return faqDAO.getCount_1(faq_catalog_1);
	}

	@Override
	public int getCount_2(String faq_catalog_2) {
		return faqDAO.getCount_1(faq_catalog_2);
	}

	@Override
	public int getCount_3(String faq_catalog_3) {
		return faqDAO.getCount_1(faq_catalog_3);
	}

	@Override
	public int getCount_4(String faq_catalog_4) {
		return faqDAO.getCount_1(faq_catalog_4);
	}

	@Override
	public int getCount_5(String faq_catalog_5) {
		return faqDAO.getCount_1(faq_catalog_5);
	}

	@Override
	public int getCount_6(String faq_catalog_6) {
		return faqDAO.getCount_1(faq_catalog_6);
	}

	@Override
	public int getCount_7(String faq_catalog_7) {
		return faqDAO.getCount_1(faq_catalog_7);
	}
	

}
