package sparky.marketdata;

import java.time.LocalDate;

import org.apache.spark.api.java.JavaPairRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.product.MarketDataLoader;
import sparky.product.Product;

@Component
public class MarketEventModel {

	@Autowired
	private MarketDataLoader marketDataLoader;
	
	@Autowired
	private HighVolumeComparator highVolumeComparator;
	
	
	public JavaPairRDD<Product, MarketEvent> findMarketEvents(LocalDate runDate)
	{
		JavaPairRDD<Product,MarketData> marketDataByProduct = marketDataLoader.loadByProduct(runDate);
		
		JavaPairRDD<Product,MarketData> marketDataWithHighVolume = highVolumeComparator.compareWithThreshold(marketDataByProduct);
		
		return marketDataWithHighVolume
				.mapToPair(
						pair -> new Tuple2<Product,MarketEvent>(pair._1, new MarketEvent()));
	}
}
