package AsyncHttpClient.test;
import static org.asynchttpclient.Dsl.*;

import org.asynchttpclient.*;

import io.netty.handler.codec.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncFullLifeCycleControlTest {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	AsyncHttpClient c = asyncHttpClient();
	Future<String> f = c.prepareGet("http://www.baidu.com/").execute(new AsyncHandler<String>() {
	    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

	    @Override
	    public AsyncHandler.State onStatusReceived(HttpResponseStatus status) throws Exception {
	        int statusCode = status.getStatusCode();
	        // The Status have been read
	        // If you don't want to read the headers,body or stop processing the response
	        if (statusCode >= 500) {
	            return AsyncHandler.State.ABORT;
	        }else {
	        	return AsyncHandler.State.CONTINUE;
	        }
	    }

	    @Override
	    public AsyncHandler.State onHeadersReceived(HttpResponseHeaders h) throws Exception {
	        HttpHeaders headers = h.getHeaders();
	         // The headers have been read
	         // If you don't want to read the body, or stop processing the response
	         return AsyncHandler.State.ABORT;
	    }

	    @Override
	    public AsyncHandler.State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
	         bytes.write(bodyPart.getBodyPartBytes());
	         return AsyncHandler.State.CONTINUE;
	    }

	    @Override
	    public String onCompleted() throws Exception {
	         // Will be invoked once the response has been fully read or a ResponseComplete exception
	         // has been thrown.
	         // NOTE: should probably use Content-Encoding from headers
	         return bytes.toString("UTF-8");
	    }

	    @Override
	    public void onThrowable(Throwable t) {
	    }
	});

	String bodyResponse = f.get();
}
}
