package sparky;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;
import au.com.bytecode.opencsv.CSVParser;


public class TradeApp {
	
	private static Clock clock = Clock.systemUTC();

	public static void main(String[] args) {
		
		System.out.println("Starting job...");
		
		Instant startInstant = clock.instant();
				
		SparkConf conf = new SparkConf().setAppName("Trade Monitor App").setMaster("local[2]");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		String csvFilePath = "G:\\git\\kata\\spark\\trades.txt";
		
		JavaRDD<String> csvData = sc.textFile(csvFilePath);
		
		JavaRDD<Trade> tradeData = csvData.mapPartitions(convertCsvLinesToTrades);	// stage
		
		JavaPairRDD<Ticker, Trade> tradeDataByTicker = csvData
				.mapToPair(convertCsvLineToTradePair)
				.partitionBy(new HashPartitioner(100));	// stage

		System.out.println("Number of CSV entries: " + tradeData.count());							// job
		System.out.println("Number of distinct Ticker symbols: " + tradeDataByTicker.countByKey());	// job
		
		JavaPairRDD<Ticker, Integer> countOneForEachTrade = tradeData
				.mapToPair(
						trade -> new Tuple2<Ticker, Integer>(trade.getTicker(), 1));	// stage
		
		// if we need a Map representation use countByKey()
		Map<Ticker, Object> countByTickerAsMap = countOneForEachTrade.countByKey();
		
		System.out.println("Distinct tickers are: " + countByTickerAsMap);
		
		// use reduceByKey() if need another RDD
		JavaPairRDD<Ticker, Integer> countByTickersAsRdd = countOneForEachTrade
				.reduceByKey(
						(a, b) -> a + b);
		
		System.out.println(countByTickersAsRdd.first());
		System.out.println(countByTickersAsRdd.keys().collect());	// job
		
		Instant endInstant = clock.instant();
		
		System.out.println("Time taken: " + Duration.between(startInstant, endInstant).getSeconds() + " seconds");
		
		sc.close();
		
		System.out.println("Done");
	}
	
	
	/**
	 * Creates only one CSVParser per Partition
	 */
	private static FlatMapFunction<Iterator<String>, Trade> convertCsvLinesToTrades = lines ->
	{
		final CSVParser parser = new CSVParser(',');
		
		List<Trade> trades = new ArrayList<Trade>();
		
		while (lines.hasNext())
		{
			String[] values = parser.parseLine(lines.next());
			
			Trade trade = createTradeFromArray(values);
			
			trades.add(trade);
		}
		
		return trades;
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
		trade.setTicker(new Ticker(values[3]));
		return trade;
	}
	
	
	/**
	 * Creates a CSVParser for every line
	 */
	private static PairFunction<String, Ticker, Trade> convertCsvLineToTradePair = line ->
	{
		final CSVParser parser = new CSVParser(',');
		
		String[] values = parser.parseLine(line);
		
		Ticker key = new Ticker(values[3]);
		Trade value = createTradeFromArray(values);
		
		return new Tuple2<Ticker, Trade>(key, value);
	};

}
