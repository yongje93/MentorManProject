package menteeboardReply.dao;

import java.util.List;
import java.util.Map;

import menteeboardReply.bean.MenteeboardReplyDTO;

public interface MenteeboardReplyDAO {

	public void replyWrite(Map<String, Object> map2);

	public List<MenteeboardReplyDTO> getAllMenteeboardreply(Map<String, Integer> map3);

	public void menteeboardReplyUp(int menteeboard_seq);

	public int getTotalReplyA(int menteeboard_seq);

	public void menteeboardReplyDown(int menteeboard_seq);

	public void replyDelete(int seq_trans);

	public void replyModify(Map<String, Object> map2);

}
