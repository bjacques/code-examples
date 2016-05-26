package sparky.trade;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.stereotype.Component;

import sparky.marketdata.MarketEvent;
import sparky.product.Product;

@Component
public class TradeEventModel {

	public JavaRDD<TradeEvent> findTradeEvents(JavaPairRDD<Product, MarketEvent> marketEvents) {
		return null;
	}

}
