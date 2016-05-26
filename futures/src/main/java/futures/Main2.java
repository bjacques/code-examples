package futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main2 {

	public static void main(String[] args) {
	    ExecutorService es = Executors.newFixedThreadPool(2);

	    List<Future<String>> futures = new ArrayList<Future<String>>();

	    futures.add(es.submit(new MyCallable("A")));
	    futures.add(es.submit(new MyCallable("B")));
	    futures.add(es.submit(new MyCallable("C")));
	    futures.add(es.submit(new MyCallable("D")));
	    futures.add(es.submit(new MyCallable("E")));

	    try {
	        for(Future<String> f  : futures) {
	            try {
	                System.out.println("result " + f.get(1, TimeUnit.SECONDS));
	            }
	            catch (TimeoutException e) {
	                // how do I know which MyCallable() has timed out?
	            	e.printStackTrace();
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
