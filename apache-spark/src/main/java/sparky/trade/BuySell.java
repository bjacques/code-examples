package sparky.trade;

public enum BuySell {

	BUY("Buy"), SELL("Sell");
	
	private final String value;
	
	
	private BuySell(String value) {
		this.value = value;
	}
	
	
	public static BuySell of(String buySell)
	{
		if(buySell.equalsIgnoreCase("BUY")) {
			return BuySell.BUY;
		}
		else if(buySell.equalsIgnoreCase("SELL")) {
			return BuySell.SELL;
		}
		else {
			throw new IllegalArgumentException("Invalid Buy Sell indicator: " + buySell);
		}
	}
	
	
	public String getValue()
	{
		return value;
	}
}
