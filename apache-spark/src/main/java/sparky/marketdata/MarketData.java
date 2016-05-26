package sparky.marketdata;

import java.io.Serializable;
import java.time.LocalDate;

import sparky.product.Product;

public class MarketData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public LocalDate date;
	public Product product;
	public Double openPrice;
	public Double closePrice;
	public Double highPrice;
	public Double lowPrice;
	public long volume;
	
	
	@Override
	public String toString() {
		return "MarketData [date=" + date + ", product=" + product
				+ ", openPrice=" + openPrice + ", closePrice=" + closePrice
				+ ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", volume=" + volume + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((closePrice == null) ? 0 : closePrice.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((highPrice == null) ? 0 : highPrice.hashCode());
		result = prime * result
				+ ((lowPrice == null) ? 0 : lowPrice.hashCode());
		result = prime * result
				+ ((openPrice == null) ? 0 : openPrice.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + (int) (volume ^ (volume >>> 32));
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
		MarketData other = (MarketData) obj;
		if (closePrice == null) {
			if (other.closePrice != null)
				return false;
		} else if (!closePrice.equals(other.closePrice))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (highPrice == null) {
			if (other.highPrice != null)
				return false;
		} else if (!highPrice.equals(other.highPrice))
			return false;
		if (lowPrice == null) {
			if (other.lowPrice != null)
				return false;
		} else if (!lowPrice.equals(other.lowPrice))
			return false;
		if (openPrice == null) {
			if (other.openPrice != null)
				return false;
		} else if (!openPrice.equals(other.openPrice))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (volume != other.volume)
			return false;
		return true;
	}
}
