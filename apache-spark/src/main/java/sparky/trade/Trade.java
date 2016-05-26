package sparky.trade;

import java.io.Serializable;

import sparky.product.Product;

public final class Trade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String trader;
	public String buySell;
	public String amount;
	public Product product;
	
	public void setTrader(String string) {
		this.trader = string;
	}

	public void setBuySell(String string) {
		this.buySell = string;
	}

	public void setAmount(String string) {
		this.amount = string;
	}

	public void setProduct(Product ticker) {
		this.product = ticker;
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

	public Product getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return "Trade [trader=" + trader + ", buySell=" + buySell + ", amount="
				+ amount + ", ticker=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((buySell == null) ? 0 : buySell.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (trader == null) {
			if (other.trader != null)
				return false;
		} else if (!trader.equals(other.trader))
			return false;
		return true;
	}

}
