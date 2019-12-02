package adminsales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("adminsalesDAO")
@Transactional
public class AdminsalesDAOMaybatis implements AdminsalesDAO{

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Map<String, String>> getDaysalesChart() {
		return sqlSession.selectList("adminsalesSQL.getDaysalesChart");
	}

	@Override
	public List<Map<String, String>> getMonthsalesChart() {
		return sqlSession.selectList("adminsalesSQL.getMonthsalesChart");
	}
	
	
}
