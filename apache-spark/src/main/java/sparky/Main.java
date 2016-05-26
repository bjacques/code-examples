package sparky;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sparky.config.SpringConfig;

public class Main {

	public static void main(String[] args)
	{
		ConfigurableApplicationContext springCtx = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		try {
			Model model = springCtx.getBean(Model.class);
			model.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(springCtx != null)
			{ 
				springCtx.close();			
				JavaSparkContext sparkCtx = springCtx.getBean(JavaSparkContext.class);
				if(sparkCtx != null) { sparkCtx.close(); }
			}
		}
	}
}
