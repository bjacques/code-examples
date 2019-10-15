package sparky;

import java.time.LocalDate;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sparky.config.SpringConfig;
import sparky.model.NaughtyTradeModel;

public class Main {

	public static void main(String[] args)
	{
		ConfigurableApplicationContext springCtx = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		try {
			NaughtyTradeModel model = springCtx.getBean(NaughtyTradeModel.class);
			LocalDate runDate = LocalDate.parse(args[0]);
			model.execute(runDate);
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
