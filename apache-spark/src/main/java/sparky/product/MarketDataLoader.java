package sparky.product;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import scala.Tuple2;
import sparky.config.SpringConfig;
import sparky.marketdata.MarketData;
import au.com.bytecode.opencsv.CSVParser;

@Component
public class MarketDataLoader {

	@Value("${input.directory}")
	private String directory;
	
	private final JavaSparkContext sparkCtx;
	private final Partitioner productPartitioner;
	
	
	@Autowired
	public MarketDataLoader(JavaSparkContext sparkCtx, Partitioner productPartitioner)
	{
		this.sparkCtx = sparkCtx;
		this.productPartitioner = productPartitioner;
	}

	
	public JavaPairRDD<Product, MarketData> loadByProduct(LocalDate runDate)
	{
		// TODO read from multiple filenames with a date in the names e.g. marketdata-yyyymmdd
		
		Path csvFilePath = Paths.get(directory + "marketdata.txt");
		
//		if(!Files.exists(csvFilePath)) {
//			 sparkCtx.parallelizePairs(Arrays.<Tuple2<Product,MarketData>>asList());
//		}
		
		return MarketDataLoader.loadByProduct(sparkCtx, csvFilePath, productPartitioner);
	}
	
	
	public static JavaPairRDD<Product, MarketData> loadByProduct(JavaSparkContext sparkCtx, Path csvFilePath, Partitioner productPartitioner)
	{
		JavaRDD<String> csvData = sparkCtx.textFile(csvFilePath.toString());
		
		return csvData
				.filter(notCsvHeader)
				.mapToPair(convertCsvLineToMarketData)
				.partitionBy(productPartitioner);
	}
	
	
	private static Function<String, Boolean> notCsvHeader = line ->
	{
		return !line.toLowerCase().startsWith("date,");
	};
	
	
	private static PairFunction<String, Product, MarketData> convertCsvLineToMarketData = line ->
	{
		final CSVParser parser = new CSVParser(',');
		
		String[] values = parser.parseLine(line);
			
		MarketData marketData = createMarketDataFromArray(values);
			
		return new Tuple2<Product, MarketData>(marketData.product, marketData);
	};

	
	/**
	 * Requires knowledge of the CSV file format (header line)
	 */
	private static MarketData createMarketDataFromArray(String[] values)
	{
		MarketData md = new MarketData();
		md.date = LocalDate.parse(values[0]);
		md.product = new Product(values[1]);
		md.closePrice = Double.valueOf(values[2]);
		md.volume = Long.valueOf(values[3]);
		return md;
	}
}
