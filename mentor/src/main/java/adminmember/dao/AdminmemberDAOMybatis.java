package adminmember.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import adminmember.bean.AdminmemberDTO;

@Repository("adminmameberDAO")
@Transactional
public class AdminmemberDAOMybatis implements AdminmemberDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<AdminmemberDTO> getAdminmemberList(Map<String, Integer> map) {
		return sqlSession.selectList("adminmemberSQL.getAdminmemberList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("adminmemberSQL.getTotalA");
	}
	
}
