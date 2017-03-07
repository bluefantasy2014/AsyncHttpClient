package AsyncHttpClient.test;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.util.concurrent.ExecutionException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.ws.WebSocket;
import org.asynchttpclient.ws.WebSocketTextListener;
import org.asynchttpclient.ws.WebSocketUpgradeHandler;
/*
 * 此代码没有测试过
 * */
public class AsyncWebSocketTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AsyncHttpClient c = asyncHttpClient();
		
		WebSocket websocket = c.prepareGet("http://www.baidu.com/")
				.execute(new WebSocketUpgradeHandler.Builder().addWebSocketListener(new WebSocketTextListener() {

					@Override
					public void onMessage(String message) {
					}

					@Override
					public void onOpen(WebSocket websocket) {
						websocket.sendMessage(""); 
					}

					@Override
					public void onClose(WebSocket websocket) {
						 
					}

					@Override
					public void onError(Throwable t) {
					}
				}).build()).get();
	}
}
