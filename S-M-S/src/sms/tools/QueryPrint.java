package sms.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sms.dao.GoodsDao;
import sms.db.DbClose;
import sms.db.DbConn;
import sms.entity.Goods;
import sms.entity.SalesMan;

public final class QueryPrint
{
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	public static ArrayList<Goods> query(String gname)
	{
		ArrayList<Goods> goodslist = new ArrayList<>();
		conn = DbConn.getConn();
		
		String sql = "select * from goods where gname = ?";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gname);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int gId = rs.getInt(1);
				String gName = rs.getString(2);
				Double gPrice = rs.getDouble(3);
				int gNum = rs.getInt(4);
				
				Goods goods = new Goods(gId, gName, gPrice, gNum);
				goodslist.add(goods);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.addClose(pstmt, conn);
		}
//		sc.close();
		return goodslist;
	}
	
	public static ArrayList<SalesMan> querySalesMan(String sname)
	{
		conn = DbConn.getConn();
		String sqlquery = "select * from salesman where sname = ?";
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		try
		{
			pstmt = conn.prepareStatement(sqlquery);
			pstmt.setString(1, sname);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				int sID = rs.getInt(1);
				String sName = rs.getString(2);
				String sPassword = rs.getString(3);
				
				SalesMan salesman = new SalesMan(sID, sName, sPassword);
				salesmanlist.add(salesman);
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
	
	public static int querySettlement()
	{
		int gid = -1;  //初始化-1  默认返回  “该商品不存在”
		
		ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);
		if(goodsSettlement.size()<=0 || goodsSettlement == null)
		{
			System.out.println("\t此商品不存在！");
		}else
			{
				System.out.println("\t\t\t**********商品列表***********\n");
				System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注");
				System.out.println("\t--------------------------------------------------------------------");
				for(int i=0, length=goodsSettlement.size(); i<length; i++)
				{
					Goods goods = goodsSettlement.get(i);
					
					System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname()
					+ "\t\t" + goods.getGprice() + "\t\t");
					
					int gnum = goods.getGnum();
					if(gnum < 10)
					{
						System.out.println("数量不足10个");
						gid = goods.getGid();
					}else if(gnum == 0)
						{
							System.out.println("该商品已售空");
							return gid = -2;
						}else
							{
								System.out.println("-");
								gid = goods.getGid();
							}
				}
				System.out.println("\t--------------------------------------------------------------------");
			}
		
		return gid;
	}
	
	/**
	 * @param goodsID
	 * @param name
	 * @return  根据  关键字  查询商品信息
	 */
	public ArrayList<Goods> queryGoodsByKey(int goodsID, String name)
	{
		ArrayList<Goods> goodslist = new ArrayList<>();
		
		conn = DbConn.getConn();
		String sql = "select * from goods where gid = ? or gname = ?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goodsID);
			pstmt.setString(2, name);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int gid = rs.getInt(1);
				String gname = rs.getString(2);
				Double gprice = rs.getDouble(3);
				int gnum = rs.getInt(4);
				
				Goods goods = new Goods(gid, gname, gprice, gnum);
				goodslist.add(goods);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally 
			{
				DbClose.queryClose(pstmt, conn, rs);
			}
		return goodslist;
	}
}
