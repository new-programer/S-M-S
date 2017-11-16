package sms.entity;

public final class SalesMan
{
	private int sid;
	private String sname;
	private String spassword;
	
	//新建、保存、修改   售货员   的构造函数
	public SalesMan(String sname, String spassword)
	{
		this.sname = sname;
		this.spassword = spassword;
	}
	
	//查询  售货员列表  的构造函数
	public SalesMan(int sid, String sname, String spassword)
	{
		this.sid = sid;
		this.sname = sname;
		this.spassword = spassword;
	}
	
	
	//公用    get和set函数
	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public String getSpassword()
	{
		return spassword;
	}

	public void setSpassword(String spassword)
	{
		this.spassword = spassword;
	}
	
}
