package sparky;

import java.time.LocalDate;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sparky.alert.AlertPublisher;
import sparky.marketdata.MarketEvent;
import sparky.marketdata.MarketEventModel;
import sparky.product.Product;
import sparky.trade.TradeEvent;
import sparky.trade.TradeEventModel;

@Component
public class NaughtyTradeModel {

	@Autowired
	private MarketEventModel marketEventModel;

	@Autowired
	private TradeEventModel tradeEventModel;
	
	@Autowired
	private AlertPublisher alertPublisher;
	
	
	public void execute(LocalDate runDate)
	{
		JavaPairRDD<Product,MarketEvent> marketEvents = marketEventModel.findMarketEvents(runDate);
		
		JavaRDD<TradeEvent> naughtyTradeEvents = tradeEventModel.findTradeEvents(marketEvents);
		
		alertPublisher.publish(naughtyTradeEvents);
	}
}
