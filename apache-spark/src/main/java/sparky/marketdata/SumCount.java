package sparky.marketdata;

import java.io.Serializable;

public class SumCount implements Serializable
{
	private static final long serialVersionUID = 1L;

	public final Long sum;
	public final Integer count;
	
	public SumCount(Long sum, Integer count)
	{
		this.sum = sum;
		this.count = count;
	}
}
