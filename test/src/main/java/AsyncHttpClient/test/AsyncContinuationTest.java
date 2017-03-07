package AsyncHttpClient.test;

import static org.asynchttpclient.Dsl.*;

import org.asynchttpclient.*;

import java.io.IOException;

public class AsyncContinuationTest {
	 public static void main(String[] args) throws IOException {
	        try(AsyncHttpClient asyncHttpClient = asyncHttpClient()) {
	            asyncHttpClient
	                    .prepareGet("http://www.baidu.com/")
	                    .execute()
	                    .toCompletableFuture()
	                    .thenApply(Response::getResponseBody)
	                    .thenAccept(System.out::println)
	                    .join();
	        }
	    }
}
