package futures;

import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

	public final String name;
	
	
	@Override
	public String toString() {
		return "MyCallable [name=" + name + "]";
	}

	public MyCallable(String name) {
		this.name = name;
	}

	public String call() throws Exception {
		try {
			long nextLong = 0L; new Random().nextInt(100);
			if (this.name == "A") nextLong = 2000;
			Thread.sleep(nextLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

}
