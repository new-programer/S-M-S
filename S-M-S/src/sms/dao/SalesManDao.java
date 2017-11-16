package sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sms.db.DbClose;
import sms.db.DbConn;
import sms.entity.SalesMan;

public final class SalesManDao
{
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//1.添加售货员
	public static boolean addSalesMan(SalesMan salesman)
	{
		boolean booladd = false;
		conn = DbConn.getConn();
		String sqladd = "insert into salesman(sname, spassword) values(?, ?)";
		
		try
		{
			pstmt = conn.prepareStatement(sqladd);
			pstmt.setString(1, salesman.getSname());
			pstmt.setString(2, salesman.getSpassword());
			int rs = pstmt.executeUpdate();
			
			if(rs > 0)  //即添加成功时，executeUpdate函数返回的是大于0的值
			{
				booladd = true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.addClose(pstmt, conn);
		}
		
		return booladd;
	}
	
	//2.更改售货员(更改姓名或者密码)
	public boolean updateSalseMan(int key, SalesMan salesman)
	{
		boolean boolupdate = false;
		conn = DbConn.getConn();
		
		switch(key)
		{
			case 1:   //根据    售货员ID  更改姓名
				String sqlsname = "update salesman set sname = ? where sid = ?";
				try
				{
					pstmt = conn.prepareStatement(sqlsname);
					pstmt.setString(1, salesman.getSname());
					pstmt.setInt(2, salesman.getSid());
					int rs = pstmt.executeUpdate();
					
					if(rs > 0)
					{
						boolupdate = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.addClose(pstmt, conn);
				}
				
				break;
			case 2:    //根据   售货员ID  更改密码
				String sqlspassword = "update salesman set spassword = ? where sid = ?";
				
				try
				{
					pstmt = conn.prepareStatement(sqlspassword);
					pstmt.setString(1, salesman.getSpassword());
					pstmt.setInt(2, salesman.getSid());
					int rs = pstmt.executeUpdate();
					if(rs > 0)
					{
						boolupdate = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.addClose(pstmt, conn);
				}
				break;
			default:
				break;
		}
		return boolupdate;
	}
	
	//3.删除售货员
	public boolean deleteSalesMan(String sname)
	{
		boolean booldelete = false;
		conn = DbConn.getConn();
		String sqldelete = "delete from salesman where sname = ?";
		try
		{
			pstmt = conn.prepareStatement(sqldelete);
			pstmt.setString(1, sname);
			int rs = pstmt.executeUpdate();
			
			if(rs > 0)
			{
				booldelete = true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.addClose(pstmt, conn);
		}
		return booldelete;
	}
	
	//4.查询售货员
	public ArrayList<SalesMan> querySalesMan(String SMname)
	{
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		conn = DbConn.getConn();
		String sqlquery = "select * from salesman where sname like '%' || ? || '%'";
		
		try
		{
			pstmt = conn.prepareStatement(sqlquery);
			pstmt.setString(1, SMname);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int sid = rs.getInt(1);
				String sname = rs.getString(2);
				String spassword = rs.getString(3);
				
				SalesMan salesman = new SalesMan(sid, sname, spassword);
				salesmanlist.add(salesman);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return salesmanlist;
	}
	
	//5.显示所有售货员
	public ArrayList<SalesMan> display()
	{
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		conn = DbConn.getConn();
		String sqlquery = "select * from salesman";
		
		try
		{
			pstmt = conn.prepareStatement(sqlquery);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int sid = rs.getInt(1);
				String sname = rs.getString(2);
				String spassword = rs.getString(3);
				
				SalesMan salesman = new SalesMan(sid, sname, spassword);
				salesmanlist.add(salesman);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return salesmanlist;
	}
	
	
	//6.登陆系统反馈
	public ArrayList<SalesMan> checkLogin(String sName)
	{
		conn = DbConn.getConn();
		String logSQL = "select sid, spassword from salesman where sname = ?";
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		try
		{
			pstmt = conn.prepareStatement(logSQL);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int sID = rs.getInt(1);
				String sPASSWORD = rs.getString(2);
				
				SalesMan salesman = new SalesMan(sID, null, sPASSWORD);
				salesmanlist.add(salesman); //将从数据库获得的用户信息添加到  ArrayList数组
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.queryClose(pstmt, conn, rs);
		}
		
		return salesmanlist;
	}
}
