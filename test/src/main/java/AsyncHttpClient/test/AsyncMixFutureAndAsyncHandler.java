package AsyncHttpClient.test;
import org.asynchttpclient.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncMixFutureAndAsyncHandler {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
	Future<Integer> f = asyncHttpClient.prepareGet("http://www.baidu.com/").execute(
	   new AsyncCompletionHandler<Integer>(){

	    @Override
	    public Integer onCompleted(Response response) throws Exception{
	        // Do something with the Response
	    	System.out.println("Response body:" +response.getResponseBody());
	        return response.getStatusCode();
	    }

	    @Override
	    public void onThrowable(Throwable t){
	        // Something wrong happened.
	    }
	});

	int statusCode = f.get();
}
}
