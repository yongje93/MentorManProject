package mentor.socketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import member.bean.MemberDTO;

/**
 * 
 * @Title : 웹소켓 핸들러
 * @author : yangjaewoo
 * @date : 2019. 11. 19.
 */
public class EchoHandler extends TextWebSocketHandler {
	// 전체
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	// 1:1
	private Map<String, WebSocketSession> userSessionsMap = new HashMap<String, WebSocketSession>();

	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	// 서버에 접속이 성공 했을때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		
		String senderEmail = getEmail(session);
		userSessionsMap.put(senderEmail, session);
		
		//logger.info("{} 연결됨", session.getId()); 
		
	}

	// 소켓에 메세지를 보냈을때
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());

//		String senderNickname = getNickname(session);	
		// 모든 유저에게 보낸다 - 브로드 캐스팅
//		for (WebSocketSession sess : sessions) {
//			sess.sendMessage(new TextMessage(senderNickname + ": " +  message.getPayload()));
//		}

		// protocol : cmd , 댓글작성자, 게시글 작성자 , seq (reply , user2 , user1 , 12)
		String msg = message.getPayload();
		if (StringUtils.isNotEmpty(msg)) {
			String[] strs = msg.split(",");

			if (strs != null && strs.length == 4) {
				String cmd = strs[0];
				String replyWriter = strs[1];
				String boardWriter = strs[2];
				String seq = strs[3];

				// 작성자가 로그인 해서 있다면
				WebSocketSession boardWriterSession = userSessionsMap.get(boardWriter);
				if ("reply".equals(cmd) && boardWriterSession != null) {
					TextMessage tmpMsg = new TextMessage(
							replyWriter + "님이 " + "<a type='external' href='/mentor/menteeboard/menteeboardView?seq="
									+ seq + "&pg=1'>" + seq + "</a> 번 게시글에 댓글을 남겼습니다.");
					boardWriterSession.sendMessage(tmpMsg);
				} 
			}
			
			// 모임 신청 했을때
			if(strs != null && strs.length == 5) {
				String cmd = strs[0];
				String mentee_name = strs[1];
				String mentor_email = strs[2];
				String meetingboard_seq = strs[3];
				String participation_seq = strs[4];
				
				// 모임 작성한 멘토가 로그인 해있으면
				WebSocketSession mentorSession = userSessionsMap.get(mentor_email);
				if(cmd.equals("apply") && mentorSession != null) {
					TextMessage tmpMsg = new TextMessage(
							mentee_name + "님이 모임을 신청했습니다. " +"<a type='external' href='/mentor/participation/participationView?mseq="+ meetingboard_seq +"&pseq="+ participation_seq +"'>신청서 보기</a>");
					mentorSession.sendMessage(tmpMsg);
				}
			}

		}
	}

	// 연결 해제될때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		userSessionsMap.remove(session.getId());
		sessions.remove(session);
		
        //logger.info("{} 연결 끊김.", session.getId());
	}

	// 웹소켓 회원email 가져오기
	private String getEmail(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		MemberDTO loginUser = (MemberDTO)httpSession.get("memDTO");
		
		if(loginUser == null) {
			return session.getId();
		}else {
			return loginUser.getMember_email();
		}
	}

}
