package faq.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import faq.bean.FaqDTO;

@Transactional
@Repository
public class FaqDAOMybatis implements FaqDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<FaqDTO> getFaqList(String catalog) {
		return sqlSession.selectList("faqSQL.getFaqList", catalog);
	}
	
	@Override
	public int getCount_1(String faq_catalog_1) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_1);
	}

	@Override
	public int getCount_2(String faq_catalog_2) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_2);
	}

	@Override
	public int getCount_3(String faq_catalog_3) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_3);
	}

	@Override
	public int getCount_4(String faq_catalog_4) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_4);
	}

	@Override
	public int getCount_5(String faq_catalog_5) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_5);
	}

	@Override
	public int getCount_6(String faq_catalog_6) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_6);
	}

	@Override
	public int getCount_7(String faq_catalog_7) {
		return sqlSession.selectOne("faqSQL.getCount_1", faq_catalog_7);
	}
	

}
