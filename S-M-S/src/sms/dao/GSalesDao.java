package sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sms.entity.GSales;
import sms.db.DbClose;
import sms.db.DbConn;

public class GSalesDao
{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//当天卖出的商品
	public ArrayList<GSales> displayDailyGoods()
	{
		ArrayList<GSales> gsaleslist = new ArrayList<>();
		
		conn = DbConn.getConn();
		
		
		String sql = "select gname, gprice, gnum, allsum "
						+ "from goods,"   //表goods
						+ "(select gid as salesid, sum(snum) as allsum from gsales where trunc(sdate) = trunc(sysdate) group by gid) "//整个括号内是另一张表
				   + "where gid = salesid ";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String gName = rs.getString(1);
				double gPrice = rs.getDouble(2);
				int gNum = rs.getInt(3);
				int allSnum = rs.getInt(4);
				
				GSales gsales = new GSales(gName,gPrice,gNum,allSnum);
				gsaleslist.add(gsales);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.queryClose(pstmt, conn, rs);
		}
		
		return gsaleslist;
	}
	
	//购物结算
	public boolean shoppingSettlement(GSales gsales)
	{
		boolean bool = false;
		conn = DbConn.getConn();
		String sql = "insert into gsales(gid, sid, snum) values(?, ?, ?)";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gsales.getGid());
			pstmt.setInt(2, gsales.getSid());
			pstmt.setInt(3, gsales.getSnum());
			
			int rs = pstmt.executeUpdate();
			if(rs > 0)
			{
				System.out.println("GSales表插入成功");
				bool = true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}
	
}
