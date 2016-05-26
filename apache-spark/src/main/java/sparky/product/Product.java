package sparky.product;

import java.io.Serializable;

import com.google.common.base.Preconditions;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public final String symbol;
	
	public Product(String symbol) {
		this.symbol = Preconditions.checkNotNull(symbol, "symbol");
	}

	@Override
	public String toString() {
		return "Ticker [symbol=" + symbol + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Product other = (Product) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
}
