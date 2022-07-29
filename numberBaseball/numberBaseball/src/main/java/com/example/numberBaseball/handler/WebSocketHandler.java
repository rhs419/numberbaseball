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

import com.example.numberBaseball.command.NumberBaseball;

import lombok.AllArgsConstructor;
import lombok.Data;


@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	private List<WebSocketSession> users = new ArrayList<WebSocketSession>();
	private Map<String, UserInfo> userMap = new HashMap<String,UserInfo>();

	@Data
	@AllArgsConstructor
	class UserInfo{
		String number;
		Object obj;
	}
	
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
			userMap.put(user, new UserInfo("", session));
		}else if(type != null && type.equals("numberRegister") ) {
			String user = object.getString("userid");
			String number = object.getString("userNumber");
			//아이디랑 Session이랑 매핑 >>> Map
			userMap.get(user).setNumber(number);;
		}else if(type.equals("lose")){
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
			String msg = object.getString("message");
            ws.sendMessage(new TextMessage(msg));
        }else if(type.equals("answer")){
			String target = object.getString("target");
			String user = object.getString("userid");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
			WebSocketSession rws = (WebSocketSession)userMap.get(user).getObj();
			String msg = object.getString("message");
            String from = object.getString("userid");
			if(ws !=null ) {
				rws.sendMessage(new TextMessage("<p style='text-align : right;'>"+NumberBaseball.compare(NumberBaseball.stringToIntList(userMap.get(target).getNumber()), NumberBaseball.stringToIntList(msg))+"</p>"));
				if(NumberBaseball.compare(NumberBaseball.stringToIntList(userMap.get(target).getNumber()), NumberBaseball.stringToIntList(msg)).equals("3S"))
					ws.sendMessage(new TextMessage(from+" Win"));
				else
					ws.sendMessage(new TextMessage(from+" : "+msg+"\n"+NumberBaseball.compare(NumberBaseball.stringToIntList(userMap.get(target).getNumber()), NumberBaseball.stringToIntList(msg))));
			}
		}else if(type.equals("chat")){
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
			String msg = object.getString("message");
            String from = object.getString("userid");
			ws.sendMessage(new TextMessage(from+" : "+msg));
		}else if(type.equals("challenge")){
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target).getObj();
            String from = object.getString("userid");
			System.out.println(from);
			ws.sendMessage(new TextMessage(from));
		}
	}

}