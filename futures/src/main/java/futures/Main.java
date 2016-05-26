package futures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

	public static void main(String[] args) {
	    ExecutorService es = Executors.newFixedThreadPool(2);

	    Map<Future<String>, Callable<String>> futuresMap = new HashMap<>();

	    MyCallable callableA = new MyCallable("A");
	    MyCallable callableB = new MyCallable("B");
	    MyCallable callableC = new MyCallable("C");
	    MyCallable callableD = new MyCallable("D");
	    MyCallable callableE = new MyCallable("E");
	    
	    futuresMap.put(es.submit(callableA), callableA);
	    futuresMap.put(es.submit(callableB), callableB);
	    futuresMap.put(es.submit(callableC), callableC);
	    futuresMap.put(es.submit(callableD), callableD);
	    futuresMap.put(es.submit(callableE), callableE);

	    try {
	        for(Future<String> f  : futuresMap.keySet()) {
	            try {
	                String result = f.get(1, TimeUnit.SECONDS);
					System.out.println("result " + result);
	            }
	            catch (TimeoutException e) {
	                // how do I know which MyCallable() has timed out?
	            	System.out.println("Timeout for " + futuresMap.get(f));
	            } catch (ExecutionException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	    }
	    catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    finally {
	        es.shutdown();
	    }
	}

}
