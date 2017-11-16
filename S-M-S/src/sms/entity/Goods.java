package sms.entity;

public final class Goods
{
	private int gid;
	private String gname;
	private Double gprice;
	private int gnum;
	
	//添加商品信息
	public Goods(String gname, Double gprice, int gnum)
	{
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gnum;
	}
	
	//显示商品信息
	public Goods(int gid, String gname, Double gprice ,int gnum)
	{
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gnum;
	}
	
	//更改商品信息
	public Goods(int gid, String gname)
	{
		this.gid = gid;
		this.gname = gname;
	}
	
	//更改商品信息
	public Goods(int gid, Double gprice)
	{
		this.gid = gid;
		this.gprice = gprice;
	}	
	
	//更改商品信息
	public Goods(int gid, int gnum)
	{
		this.gid = gid;
		this.gnum = gnum;
	}
	
	/*
		公有get和set方法
	*/
	public int getGid()
	{
		return gid;
	}

	public void setGid(int gid)
	{
		this.gid = gid;
	}

	public String getGname()
	{
		return gname;
	}

	public void setGname(String gname)
	{
		this.gname = gname;
	}

	public Double getGprice()
	{
		return gprice;
	}

	public void setGprice(Double gprice)
	{
		this.gprice = gprice;
	}

	public int getGnum()
	{
		return gnum;
	}

	public void setGnum(int gnum)
	{
		this.gnum = gnum;
	}

}
