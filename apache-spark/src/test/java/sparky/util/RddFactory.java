package sparky.util;

import static sparky.util.FileHelper.getClassPathResource;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;
import sparky.marketdata.MarketData;
import sparky.product.MarketDataLoader;
import sparky.product.Product;

public class RddFactory
{
	private final JavaSparkContext sparkCtx;
	private final Partitioner productPartitioner = new HashPartitioner(1);
	
	
	public RddFactory()
	{
//		conf.set("spark.driver.allowMultipleContexts", "true")
		sparkCtx = new JavaSparkContext("local", "RddFactory");
	}
	
	
	public RddFactory(JavaSparkContext sparkCtx)
	{
		this.sparkCtx = sparkCtx;
	}
	
	
	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		sparkCtx.close();
	}
	
	
	public void close()
	{
		this.sparkCtx.close();
	}
	
	
	public JavaPairRDD<Product,MarketData> createMarketDataFromFile(String filename)
	{
		return MarketDataLoader.loadByProduct(sparkCtx, getClassPathResource(filename), productPartitioner);
	}
	
	
	public JavaPairRDD<Product,MarketData> createMarketDataByProduct(MarketData... marketDatas)
	{
		List<Tuple2<Product,MarketData>> marketDataList = new ArrayList<>();
		
		for(MarketData md : marketDatas) {
			marketDataList.add(new Tuple2<Product,MarketData>(md.product, md));
		}
		
		return sparkCtx.parallelizePairs(marketDataList);
	}


	public JavaPairRDD<Product, Double> createAverageVolumeByProduct(Product product, double... averageVolumes)
	{
		List<Tuple2<Product,Double>> list = new ArrayList<>();
		
		for(Double vol : averageVolumes) {
			list.add(new Tuple2<Product,Double>(product, vol));
		}
		
		return sparkCtx.parallelizePairs(list);
	}
}
