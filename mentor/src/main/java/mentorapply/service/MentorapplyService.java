package mentorapply.service;

import java.util.Map;

import mentee.bean.MenteeDTO;
import mentorapply.bean.MentorapplyDTO;

public interface MentorapplyService {

	public void mentorapplyWrite(Map<String, String> map);

	public MentorapplyDTO getEmail(String member_email);

}
