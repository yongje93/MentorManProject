package menteeboardReply.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import menteeboardReply.bean.MenteeboardReplyDTO;

@Transactional
@Repository(value="menteeboardReplyDAO")
public class MenteeboardReplyDAOMybatis implements MenteeboardReplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void replyWrite(Map<String, Object> map2) {
		sqlSession.insert("menteeboardReplySQL.replyWrite", map2);
	}

	@Override
	public List<MenteeboardReplyDTO> getAllMenteeboardreply(Map<String, Integer> map3) {
		return sqlSession.selectList("menteeboardReplySQL.getAllMenteeboardreply", map3);
	}

	@Override
	public void menteeboardReplyUp(int menteeboard_seq) {
		sqlSession.update("menteeboardReplySQL.menteeboardReplyUp" , menteeboard_seq);
	}

	@Override
	public int getTotalReplyA(int menteeboard_seq) {
		return sqlSession.selectOne("menteeboardReplySQL.getTotalReplyA" ,menteeboard_seq );
	}

	@Override
	public void menteeboardReplyDown(int menteeboard_seq) {
		sqlSession.update("menteeboardReplySQL.menteeboardReplyDown" ,menteeboard_seq);
	}

	@Override
	public void replyDelete(int seq_trans) {
		sqlSession.delete("menteeboardReplySQL.replyDelete" , seq_trans);
	}

	@Override
	public void replyModify(Map<String, Object> map2) {
		sqlSession.update("menteeboardReplySQL.replyModify" , map2);
	}

	


}
