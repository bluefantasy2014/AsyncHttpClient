package AsyncHttpClient.test;
import org.asynchttpclient.*;

public class AsyncHandlerTest {
	public static void main(String[] args) {
		AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
		asyncHttpClient.prepareGet("http://www.baidu.com/").execute(new AsyncCompletionHandler<Response>(){

		    @Override
		    public Response onCompleted(Response response) throws Exception{
		        // Do something with the Response
		        // ...
		    	System.out.println(response.getResponseBody());
		        return response;
		    }

		    @Override
		    public void onThrowable(Throwable t){
		        // Something wrong happened.
		    }
		});
	}
}
