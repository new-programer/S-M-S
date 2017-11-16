package sms.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import sms.db.DbClose;
import sms.db.DbConn;
import sms.entity.Goods;
/*
 *DAO就是Database Access Objects，数据访问对象的英文缩写。
 *Data控件只给出有限的不需编程而能访问现存数据库的功能，而DAO模型则是全面控制数据库的完整编程接口。
 *DAO是集合，对象，方法和属性；它用对象集合来处理数据库，表，视图和索引等。
 *使用DAO编程，可以访问并操作数据库，管理数据库的对象和定义数据库的结构等。
*/
public final class GoodsDao
{
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	/*
	 * 1.实现   添加商品   的功能
	*/
	public static boolean addGoods(Goods goods)
	{
		boolean bool = false;
		conn = DbConn.getConn();
		String sql = "insert into goods(gname, gprice, gnum) values(?, ?, ?)";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods.getGname());
			pstmt.setDouble(2, goods.getGprice());
			pstmt.setInt(3, goods.getGnum());
			
			int rs = pstmt.executeUpdate();
			
			if(rs > 0)
			{
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
	
	/*
	 * 2.实现  更改商品   的功能
	*/
	public boolean updateGoods(int key, Goods goods)
	{
		boolean bool = false;
		conn = DbConn.getConn();
		
		switch(key)
		{
			case 1:      //2.1更改商品名称
				String sql1 = "update goods set gname=? where gid=?";
				try
				{
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1,goods.getGname());
					pstmt.setInt(2, goods.getGid());
					int rs = pstmt.executeUpdate();
					if(rs > 0)
					{
						bool = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.addClose(pstmt, conn);
				}
				break;
				
			case 2:          //2.2更改商品价格
				String sql2 = "update goods set gprice = ? where gid = ?";
				try
				{
					pstmt = conn.prepareStatement(sql2);
					pstmt.setDouble(1,goods.getGprice());
					pstmt.setInt(2, goods.getGid());
					int rs = pstmt.executeUpdate();
					if(rs > 0)
					{
						bool = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.addClose(pstmt, conn);
				}
				break;
				
			case 3:         // 2.3更改商品数量
				String sql3 = "update goods set gnum = ? where gid = ?";
				try
				{
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1,goods.getGnum());
					pstmt.setInt(2, goods.getGid());
					int rs = pstmt.executeUpdate();
					if(rs > 0)
					{
						bool = true;
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
		return bool;
	}
	
	/*
	 * 3.实现   删除商品   的功能
	*/
	public boolean deleteGoods(int gid)
	{
		boolean bool = false;
		conn = DbConn.getConn();
		String sql = "delete from goods where gid = ?";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			int rs = pstmt.executeUpdate();
			if(rs > 0)
			{
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
	
	/*
	 * 4.实现    查询商品    的功能
	*/
	public ArrayList<Goods> queryGoods(int num)
	{
		ArrayList<Goods> goodslist = new ArrayList<>();
		conn = DbConn.getConn();
		
		switch(num)
		{
			case 1:  //4.1   按   商品数量    升序查询
				String sqlGnum = "select gid,gname, trim(to_char(gprice,'99999999999999.99')), gnum from goods order by gnum asc";   //ASC表示升序查找  DESC表示降序查询
				try
				{
					pstmt = conn.prepareStatement(sqlGnum);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						int goodid= rs.getInt(1);
						String goodname = rs.getString(2);
						Double goodprice = rs.getDouble(3);
						int goodnum = rs.getShort(4);
						
						Goods goods = new Goods(goodid, goodname, goodprice, goodnum);
						goodslist.add(goods);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.queryClose(pstmt, conn, rs);    //关闭数据库连接
				}
				break;
				
			case 2:  //4.2   按  商品价格   升序查询
				String sqlGprice = "select gid,gname, trim(to_char(gprice,'99999999999999.99')), gnum from goods order by gprice asc";
				try
				{
					pstmt = conn.prepareStatement(sqlGprice);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						int goodid= rs.getInt(1);
						String goodname = rs.getString(2);
						Double goodprice = rs.getDouble(3);
						int goodnum = rs.getShort(4);
						
						Goods goods = new Goods(goodid, goodname, goodprice, goodnum);
						goodslist.add(goods);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.queryClose(pstmt, conn, rs);
				}
				break;
				
			case 3:  //4.3   按   商品关键字   查询
				Scanner input = new Scanner(System.in);
				String gname = input.next();
				String sqlGname = "select gid,gname, trim(to_char(gprice,'99999999999999.99')), gnum from goods where gname like '%' || ? || '%'";
				
				conn = DbConn.getConn();
				try
				{
					pstmt = conn.prepareStatement(sqlGname);
					pstmt.setString(1, gname);
					rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						int goodid = rs.getInt(1);
						String goodname = rs.getString(2);
						Double goodprice = rs.getDouble(3);
						int goodnum = rs.getInt(4);
						
						Goods goods = new Goods(goodid, goodname, goodprice, goodnum);
						goodslist.add(goods);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
				{
					DbClose.queryClose(pstmt, conn, rs);;
				}
				//input.close();      //此处的不该关闭输入流，要关闭此输入流应该在调用此函数这那里关掉    因为如果此处关闭输入流的话会导致在进行     关键字    查询操作时
				//抛出异常的原因是  此处关闭了输入流   无法获取输入信息 具体解析如下：
				//    见链接：http://www.cnblogs.com/qingyibusi/p/5812725.html 【Java学习笔记之Scanner报错java.util.NoSuchElementException】
	/*			抛出以下异常：
	 * 
				正在执行商品    关键字   查询操作！
				请输入商品关键字
				bigegg
					所查的商品信息如下：
					商品编号		商品名称		商品价格		商品数量		备注
					-------------------------------------------------------------------
				Exception in thread "main" 	2		bigegg		1.5		100
					-------------------------------------------------------------------
				输入“0”返回商品维护界面
				java.util.NoSuchElementException
					at java.util.Scanner.throwFor(Scanner.java:862)
					at java.util.Scanner.next(Scanner.java:1371)
					at sms.page.GoodsPage.queryGoodsPage(GoodsPage.java:339)      //
					at sms.page.MainPage.goodsMaitenanceMage(MainPage.java:99)
					at sms.page.MainPage.mainPage(MainPage.java:39)
					at sms.page.MainPage.main(MainPage.java:9)
				*/
				break;
				
			default:
				break;
		}
		return goodslist;
	}
	
	public ArrayList<Goods> display()
	{
		ArrayList<Goods> goodslist = new ArrayList<>();
		conn = DbConn.getConn();
		String sql = "select gid,gname, trim(to_char(gprice,'99999999999999.99')) as gprice, gnum from goods";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int goodid = rs.getInt(1);
				String goodname = rs.getString(2);
				Double goodprice = rs.getDouble(3);
				int goodnum = rs.getInt(4);
				
				Goods goods = new Goods(goodid, goodname, goodprice, goodnum);
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
