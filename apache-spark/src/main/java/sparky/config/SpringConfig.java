package sparky.config;

import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("sparky")
@Configuration
public class SpringConfig {

	@Bean
	public JavaSparkContext javaSparkContext()
	{
		SparkConf conf = new SparkConf().setAppName("Trade Monitor App").setMaster("local[2]");
		return new JavaSparkContext(conf);
	}
	
	@Bean
	public Partitioner productPartitioner()
	{
		return new HashPartitioner(4);
	}
}
