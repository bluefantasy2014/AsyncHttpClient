package AsyncHttpClient.test;


import org.asynchttpclient.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class AsyncTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
		Future<Response> f = asyncHttpClient.prepareGet("http://www.baidu.com/").execute();
		Response r = f.get();
		System.out.println(r.getResponseBody()); 
	}
	
}
