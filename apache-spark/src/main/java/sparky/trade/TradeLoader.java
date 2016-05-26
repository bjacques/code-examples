package sparky.trade;

import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.product.Product;
import au.com.bytecode.opencsv.CSVParser;

@Component
public class TradeLoader {
	
	private final JavaSparkContext sparkCtx;
	private final Partitioner productPartitioner;
	
	
	@Autowired
	public TradeLoader(JavaSparkContext sparkCtx, Partitioner productPartitioner)
	{
		this.sparkCtx = sparkCtx;
		this.productPartitioner = productPartitioner;
	}

	
	public JavaPairRDD<Product, Trade> loadTradesByProduct()
	{
		String csvFilePath = "G:\\git\\kata\\spark\\trades.txt";
		
		JavaRDD<String> csvData = sparkCtx.textFile(csvFilePath);
		
		return csvData
				.mapToPair(convertCsvLinesToTrades)
				.partitionBy(productPartitioner);
	}
	
	
	private static PairFunction<String, Product, Trade> convertCsvLinesToTrades = line ->
	{
		final CSVParser parser = new CSVParser(',');
		
		String[] values = parser.parseLine(line);
			
		Trade trade = createTradeFromArray(values);
			
		return new Tuple2<Product, Trade>(trade.getProduct(), trade);
	};
	
	
	/**
	 * Requires knowledge of the CSV file format (header line)
	 */
	private static Trade createTradeFromArray(String[] values)
	{
		Trade trade = new Trade();
		trade.setTrader(values[0]);
		trade.setBuySell(values[1]);
		trade.setAmount(values[2]);
		trade.setProduct(new Product(values[3]));
		return trade;
	}
}
