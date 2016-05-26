package sparky.marketdata;

import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.marketdata.threshold.AverageVolumeThresholdFunction;
import sparky.product.Product;

@Component
public class HighVolumeComparator
{
	@Autowired
	private Partitioner productPartitioner;
	
	@Autowired
	private AverageVolumeAggregator averageVolumeAggregator;
	
	@Autowired
	private AverageVolumeThresholdFunction averageVolumeThresholdFunction;
	

	public JavaPairRDD<Product, MarketData> compareWithThreshold(JavaPairRDD<Product, MarketData> marketData)
	{
		JavaPairRDD<Product,Double> averageVolumes = averageVolumeAggregator.getAverageVolumeByProduct(marketData);
		
		return marketData
				.join(
						averageVolumes, productPartitioner)
				.filter(
						averageVolumeThresholdFunction)
				.mapToPair(
						pair -> new Tuple2<Product,MarketData>(pair._1, pair._2._1));
	}
}
