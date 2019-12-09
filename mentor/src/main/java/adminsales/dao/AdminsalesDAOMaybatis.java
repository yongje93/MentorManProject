package adminsales.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adminmember.bean.AdminmentorSalesListDTO;

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

	@Override
	public List<Map<String, String>> mentorSalesChart() {
		return sqlSession.selectList("adminsalesSQL.mentorSalesChart");
	}

	@Override
	public List<AdminmentorSalesListDTO> getMentorSales(Map<String, Integer> map) {
		return sqlSession.selectList("adminsalesSQL.getMentorSales",map);
	}

	@Override
	public int getMentorSalesTotalA() {
		return sqlSession.selectOne("adminsalesSQL.getMentorSalesTotalA");
	}

	@Override
	public List<AdminmentorSalesListDTO> getMentorView(String member_name) {
		return sqlSession.selectList("adminsalesSQL.getMentorView",member_name);
	}
	
	
}
