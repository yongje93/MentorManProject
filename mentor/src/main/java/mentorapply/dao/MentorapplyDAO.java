package mentorapply.dao;

import java.util.Map;

import mentorapply.bean.MentorapplyDTO;

public interface MentorapplyDAO {

	public void mentorapplyWrite(Map<String, String> map);

	public MentorapplyDTO getEmail(String member_email);

}
