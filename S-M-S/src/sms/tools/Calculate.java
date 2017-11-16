package sms.tools;

import java.math.BigDecimal;

public final class Calculate
{
	public static double multi(Double val1, Double val2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(val1));
		BigDecimal b2 = new BigDecimal(Double.toString(val2));
		
		return b1.multiply(b2).doubleValue();
	}

	public static double minus(double userMoney, double allPrice)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(userMoney));
		BigDecimal b2 = new BigDecimal(Double.toString(allPrice));
		return b1.subtract(b2).doubleValue();
	}
}
