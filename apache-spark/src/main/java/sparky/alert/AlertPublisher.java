package sparky.alert;

import org.apache.spark.api.java.JavaRDD;
import org.springframework.stereotype.Component;

import sparky.trade.TradeEvent;

@Component
public class AlertPublisher {

	public void publish(JavaRDD<TradeEvent> naughtyTradeEvents) {
		// TODO Auto-generated method stub
		
	}

}
