package sparky.marketdata;

import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.product.Product;

@Component
public class AverageVolumeAggregator
{
	@Autowired
	private Partitioner productPartitioner;

	
	public JavaPairRDD<Product,Double> getAverageVolumeByProduct(JavaPairRDD<Product, MarketData> marketData)
	{
		JavaPairRDD<Product,SumCount> sumCountOfVolume = marketData
				.combineByKey(
						md -> new SumCount(md.volume, 1),										// createCombiner
						(sc, md) -> new SumCount(sc.sum + md.volume, sc.count + 1), 			// mergeValue
						(sc1, sc2) -> new SumCount(sc1.sum + sc2.sum, sc1.count + sc2.count),	// mergeCombiners
						productPartitioner);
		
		JavaPairRDD<Product,Double> averageVolumeByProduct = sumCountOfVolume
				.mapToPair(
						pair -> new Tuple2<Product,Double>(
								pair._1, 
								(double) pair._2.sum / pair._2.count));
		
		return averageVolumeByProduct;
	}
}
