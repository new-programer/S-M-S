package sms.entity;


public final class GSales
{
	private int gid;
	private int sid;
	private int snum;
	
	private String gName; 
	private double gPrice;
	private int gNum;
	private int allSnum; //单种商品销量总和
	//购物结算
	/**
	 * @param gid
	 * @param sid
	 * @param snum
	 */
	public GSales(int gid, int sid, int snum)
	{
		this.gid = gid;
		this.sid = sid;
		this.snum = snum;
	}

	
	//展示商品列表
	public GSales(String gName, double gPrice, int gNum, int allSnum)
	{
		this.gName = gName;
		this.gPrice = gPrice;
		this.gNum = gNum;
		this.allSnum = allSnum;
	}

	//公用get 和    set 函数
	public int getGid()
	{
		return gid;
	}


	public void setGid(int gid)
	{
		this.gid = gid;
	}


	public int getSid()
	{
		return sid;
	}


	public void setSid(int sid)
	{
		this.sid = sid;
	}


	public int getSnum()
	{
		return snum;
	}


	public void setSnum(int snum)
	{
		this.snum = snum;
	}


	public String getgName()
	{
		return gName;
	}


	public void setgName(String gName)
	{
		this.gName = gName;
	}


	public double getgPrice()
	{
		return gPrice;
	}


	public void setgPrice(double gPrice)
	{
		this.gPrice = gPrice;
	}


	public int getgNum()
	{
		return gNum;
	}


	public void setgNum(int gNum)
	{
		this.gNum = gNum;
	}


	public int getAllSnum()
	{
		return allSnum;
	}


	public void setAllSnum(int allSnum)
	{
		this.allSnum = allSnum;
	}

}
