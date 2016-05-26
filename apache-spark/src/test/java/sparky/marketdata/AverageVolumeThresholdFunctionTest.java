package sparky.marketdata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.spark.api.java.JavaPairRDD;
import org.junit.AfterClass;
import org.junit.Test;

import scala.Tuple2;
import sparky.marketdata.threshold.AverageVolumeThresholdFunction;
import sparky.product.Product;
import sparky.util.RddFactory;

public class AverageVolumeThresholdFunctionTest
{
	private AverageVolumeThresholdFunction function = new AverageVolumeThresholdFunction();

	private static final RddFactory RDD_FACTORY = new RddFactory();
	
	
	@AfterClass
	public static void tearDown()
	{
//		RDD_FACTORY.close();
	}
	
	
	@Test
	public void returnsTrue_givenDailyVolumeIsIncreasing_andGreaterThanThreshold() throws Exception
	{
		function.setThresholdAverageVolumePercent(24.9);
		
		long todayVolume = 10;
		double averageVolume = 8;
		
		boolean isGreater = function.call(createTupleFrom(todayVolume, averageVolume));
		
		assertThat(isGreater, is(true));
	}
	
	
	@Test
	public void returnsFalse_givenDailyVolumeEqualsThreshold() throws Exception
	{
		function.setThresholdAverageVolumePercent(25.0);
		
		long todayVolume = 10;
		double averageVolume = 8;
		
		boolean isGreater = function.call(createTupleFrom(todayVolume, averageVolume));
		
		assertThat(isGreater, is(false));
	}
	
	
	@Test
	public void returnsFalse_givenDailyVolumeLessThanThreshold() throws Exception
	{
		function.setThresholdAverageVolumePercent(25.1);
		
		long todayVolume = 10;
		double averageVolume = 8;
		
		boolean isGreater = function.call(createTupleFrom(todayVolume, averageVolume));
		
		assertThat(isGreater, is(false));
	}
	
	
	@Test
	public void returnsTrue_givenDailyVolumeIsDecreasing_andGreaterThanThreshold() throws Exception
	{
		function.setThresholdAverageVolumePercent(19.9);
		
		long todayVolume = 8;
		double averageVolume = 10;
		
		boolean isGreater = function.call(createTupleFrom(todayVolume, averageVolume));
		
		assertThat(isGreater, is(true));
	}

	
	/**
	 * We're basically recreating the code in HighVolumeComparator.compareWithThreshold() where
	 * this function is called from
	 */
	private Tuple2<Product,Tuple2<MarketData,Double>> createTupleFrom(long todayVolume, double averageVolume)
	{
		Product product = new Product("symbol");
		
		MarketData md = new MarketData();
		md.volume = todayVolume;
		md.product = product;
		
		JavaPairRDD<Product,MarketData> marketData = RDD_FACTORY.createMarketDataByProduct(md);
		
		JavaPairRDD<Product,Double> averageVolumes = RDD_FACTORY.createAverageVolumeByProduct(product, averageVolume);
		
		JavaPairRDD<Product,Tuple2<MarketData,Double>> join = marketData.join(averageVolumes);
		
		return join.collect().get(0);
	}
}
