package sparky;

import java.io.Serializable;

public class Trade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String trader;
	private String buySell;
	private String amount;
	private Ticker ticker;

	public void setTrader(String string) {
		this.trader = string;
	}

	public void setBuySell(String string) {
		this.buySell = string;
	}

	public void setAmount(String string) {
		this.amount = string;
	}

	public void setTicker(Ticker ticker) {
		this.ticker = ticker;
	}

	public String getTrader() {
		return trader;
	}

	public String getBuySell() {
		return buySell;
	}

	public String getAmount() {
		return amount;
	}

	public Ticker getTicker() {
		return ticker;
	}

	@Override
	public String toString() {
		return "Trade [trader=" + trader + ", buySell=" + buySell + ", amount="
				+ amount + ", ticker=" + ticker + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((buySell == null) ? 0 : buySell.hashCode());
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
		result = prime * result + ((trader == null) ? 0 : trader.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (buySell == null) {
			if (other.buySell != null)
				return false;
		} else if (!buySell.equals(other.buySell))
			return false;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		if (trader == null) {
			if (other.trader != null)
				return false;
		} else if (!trader.equals(other.trader))
			return false;
		return true;
	}

}
