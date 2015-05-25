package sparky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sparky.trade.TradeLoader;

@Component
public class Model {

	@Autowired
	private TradeLoader tradeLoader;
	
	
	public void execute()
	{
		tradeLoader.loadTradesByProduct();
		
	}

}
