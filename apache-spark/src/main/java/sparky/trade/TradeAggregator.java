package sparky.trade;

import java.time.LocalDate;

import org.apache.spark.api.java.JavaPairRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sparky.marketdata.MarketEvent;
import sparky.product.Product;

@Component
public class TradeAggregator {

	@Autowired
	private TradeLoader tradeLoader;
	
	
	public TradeEvent generateTradeEvents(JavaPairRDD<Product,MarketEvent> x, LocalDate runDate)
	{
		JavaPairRDD<Product,Trade> tradesByProduct = tradeLoader.loadByProduct(runDate);
		return null;
	}
}
