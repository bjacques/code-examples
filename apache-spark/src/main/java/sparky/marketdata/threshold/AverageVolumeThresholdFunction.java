package sparky.marketdata.threshold;

import org.apache.spark.api.java.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.marketdata.MarketData;
import sparky.product.Product;

@Component
public class AverageVolumeThresholdFunction implements Function<Tuple2<Product,Tuple2<MarketData,Double>>, Boolean>
{
	private static final long serialVersionUID = 1L;
	
	private Double thresholdAverageVolumePercent;
	
	
	@Override
	public Boolean call(Tuple2<Product, Tuple2<MarketData, Double>> pair) throws Exception
	{
		long todayVolume = pair._2._1.volume;
		double averageVolume = pair._2._2;
		return (Math.abs(todayVolume - averageVolume) / averageVolume) * 100 > thresholdAverageVolumePercent;
	}


	@Value("${threshold.average.volume.percent}")
	public void setThresholdAverageVolumePercent(Double thresholdAverageVolumePercent)
	{
		this.thresholdAverageVolumePercent = thresholdAverageVolumePercent;
	}
}
