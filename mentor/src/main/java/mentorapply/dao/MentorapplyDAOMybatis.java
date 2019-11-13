package mentorapply.dao;

import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mentorapply.bean.MentorapplyDTO;

@Transactional
@Repository
public class MentorapplyDAOMybatis implements MentorapplyDAO {
	@Autowired 
	private SqlSession sqlSession;

	@Override
	public void mentorapplyWrite(Map<String, String> map) {
		sqlSession.insert("applySQL.mentorapplyWrite", map);
	}

	@Override
	public MentorapplyDTO getEmail(String member_email) {
		return sqlSession.selectOne("applySQL.getEmail",member_email);
	}
}
