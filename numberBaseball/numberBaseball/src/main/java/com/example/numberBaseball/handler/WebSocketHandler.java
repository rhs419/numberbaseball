package com.example.numberBaseball.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.numberBaseball.command.Test;


@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> users = new ArrayList<WebSocketSession>();
	private Map<String, Object> userMap = new HashMap<String,Object>();
	
	
	@Override
        //소켓 연결 생성 후 실행 메서드
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("TextWebSocketHandler : 연결 생성!");
		users.add(session);
	}

	@Override
        //메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("TextWebSocketHandler : 메시지 수신!");
		System.out.println("메시지 : " + message.getPayload());
		JSONObject object = new JSONObject(message.getPayload());
		String type = object.getString("type");

		if(type != null && type.equals("register") ) {
			//등록 요청 메시지
			String user = object.getString("userid");
			//아이디랑 Session이랑 매핑 >>> Map
			userMap.put(user, session);
		}
        else if(type.equals("lose")){
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target);
			String msg = object.getString("message");
            ws.sendMessage(new TextMessage(msg));
        }else {
			//채팅 메시지 : 상대방 아이디를 포함해서 메시지를 보낼것이기 때문에
			//Map에서 상대방 아이디에 해당하는 WebSocket 꺼내와서 메시지 전송
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target);
			String msg = object.getString("message");
            String from = object.getString("userid");
			if(ws !=null ) {
                if(Test.test(msg).equals("3S"))
                    ws.sendMessage(new TextMessage(from+" Win"));
                else
				    ws.sendMessage(new TextMessage(from+" : "+msg+"\n"+Test.test(msg)));
			}
		}
	}

}