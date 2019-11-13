package menteeboardReply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menteeboardReply.bean.MenteeboardReplyDTO;
import menteeboardReply.dao.MenteeboardReplyDAO;

@Service(value="menteeboardReplyService")
public class MenteeboardReplyServiceImpl implements MenteeboardReplyService {

	@Autowired
	private MenteeboardReplyDAO menteeboardReplyDAO;
	
	@Override
	public void replyWrite(Map<String, Object> map2) {
		menteeboardReplyDAO.replyWrite(map2);
	}


	@Override
	public List<MenteeboardReplyDTO> getAllMenteeboardreply(Map<String, Integer> map3) {
		return menteeboardReplyDAO.getAllMenteeboardreply(map3);
	}

	@Override
	public void menteeboardReplyUp(int menteeboard_seq) {
		menteeboardReplyDAO.menteeboardReplyUp(menteeboard_seq);
	}


	@Override
	public int getTotalReplyA(int menteeboard_seq) {
		return menteeboardReplyDAO.getTotalReplyA(menteeboard_seq);
	}


	@Override
	public void menteeboardReplyDown(int menteeboard_seq) {
		menteeboardReplyDAO.menteeboardReplyDown(menteeboard_seq);
	}


	@Override
	public void replyDelete(int seq_trans) {
		menteeboardReplyDAO.replyDelete(seq_trans);
	}


	@Override
	public void replyModify(Map<String, Object> map2) {
		menteeboardReplyDAO.replyModify(map2);
	}


}
