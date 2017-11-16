package sms.page;

import java.util.ArrayList;

import sms.dao.GoodsDao;
import sms.entity.Goods;
import sms.tools.QueryPrint;
import sms.tools.ScannerAndNextStep;

/*
 * 操作商品界面
 * @author insanity2017@126.com
*/
public final class GoodsPage
{
	/*
	 * 1.添加商品界面
	*/
	public static void addGoodsPage()
	{
		System.out.println("\t正在进行  添加商品  操作");
		
		System.out.println("添加商品名称：");
		String good_name = ScannerAndNextStep.StringScannerTool();
		
		System.out.println("添加商品价格：");
		Double good_price = ScannerAndNextStep.DoubleScannerTool();
		
		System.out.println("添加商品数量：");
		int good_num = ScannerAndNextStep.IntScannerTool();
		
		Goods goods = new Goods(good_name, good_price, good_num);
		boolean booladd = GoodsDao.addGoods(goods);  //实现向数据库  添加商品  功能
		
		if(booladd)
		{
			System.out.println("已成功添加商品！");
		}
		else
		{
			System.out.println("添加商品失败！");
		}
		
		//是否进行下一步操作
		ScannerAndNextStep.judgeGoodsNext("addGoods");
	}
	
	/*
	 * 2.更改商品界面
	*/
	public static void updateGoodsPage()
	{
		System.out.println("\t正在进行  更改商品  操作");
		System.out.println("请输入需要更改商品的名字");
		
		String gname = ScannerAndNextStep.StringScannerTool();
		//调用查找商品函数，显示将要更改的商品信息
		ArrayList<Goods> goodslist = new ArrayList<>();
		goodslist = QueryPrint.query(gname);    //从数据库里查商品名称并返回对应得商品ID-gid  
		
		if(goodslist.size() <= 0 || goodslist == null)
		{
			System.err.println("该商品不存在");
			ScannerAndNextStep.judgeGoodsNext("updateGoods");  //选择下一步操作
		}else
		{
			//显示所要更改的商品信息
			System.out.println("\t\t*******需要  更改  的商品的具体信息*******\n");
			System.out.println("\t商品ID\t\t商品名称\t\t商品价格\t\t商品数量");
			System.out.println("\t------------------------------------------------------");
			Goods goods = goodslist.get(0);
			System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t"
					+ goods.getGprice() + "\t\t" + goods.getGnum());
			System.out.println("\t------------------------------------------------------\n");
			
			//更改内容选择
			System.out.println("\n--------------请选择你要更改的商品内容---\n");
			System.out.println("\t 1.更改商品-名称");
			System.out.println("\t 2.更改商品-价格");
			System.out.println("\t 3.更改商品-数量");
			System.out.println("\n请输入选项,或者按0返回上一级菜单.");
			
			do
			{
				System.out.println("请输入：");
				String input = ScannerAndNextStep.StringScannerTool();
				String regex = "[0-3]";
				if(input.matches(regex))
				{
					int num = Integer.parseInt(input);
					switch(num)
					{
						case 0:
							MainPage.goodsMaitenanceMage();
							break;
							
						case 1:
							System.out.println("请输入商品-新名称");
							String newname = ScannerAndNextStep.StringScannerTool();
							Goods goodsname = new Goods(goods.getGid(), newname);
							boolean boolname = new GoodsDao().updateGoods(1, goodsname);
							
							 if (boolname)
							 {
								 System.out.println("\n\t！！成功更新商品名至数据库！！\n");
							 }
							 else 
							 {
								 System.err.println("\n\t！！更新商品名失敗！！");
							 }
							 
							//是否进行下一步操作         代码重复使用了
							ScannerAndNextStep.judgeGoodsNext("updateGoods");
							break;	
						case 2:
							System.out.println("请输入商品-新价格");
							String newprice = ScannerAndNextStep.StringScannerTool();
							Goods goodsprice = new Goods(goods.getGid(), newprice);
							boolean boolprice = new GoodsDao().updateGoods(2, goodsprice);
							
							 if (boolprice)
							 {
								 System.out.println("\n\t！！成功更新商品名至数据库！！\n");
							 }
							 else 
							 {
								 System.err.println("\n\t！！更新商品名失敗！！");
							 }
							 
							//是否进行下一步操作         代码重复使用了
							 ScannerAndNextStep.judgeGoodsNext("updateGoods");
							break;
						case 3:
							System.out.println("请输入商品-新数量");
							int newnum = ScannerAndNextStep.IntScannerTool();
							Goods goodsnum = new Goods(goods.getGid(), newnum);
							boolean boolnum = new GoodsDao().updateGoods(3, goodsnum);
							
							 if (boolnum)
							 {
								 System.out.println("\n\t！！成功更新商品名至数据库！！\n");
							 }
							 else 
							 {
								 System.err.println("\n\t！！更新商品名失敗！！");
							 }
							 
							//是否进行下一步操作         代码重复使用了
							 ScannerAndNextStep.judgeGoodsNext("updateGoods");
							 break;
					}
				}
				System.err.println("！输入有误！");
				System.out.println("请重新选择,或者按0返回上一级菜单.");
			}while(true);
		}
		
	}
	
	/*
	 * 3.删除商品界面
	*/
	public static void deleteGoodsPage()
	{
		System.out.println("\t正在执行  删除商品  操作");
		System.out.println("请输入需要删除的商品名称");
		
		String gname = ScannerAndNextStep.StringScannerTool();
		
		ArrayList<Goods> goodslist = new ArrayList<>();
		goodslist = QueryPrint.query(gname);    //从数据库里查商品名称并返回对应得商品ID-gid  
		if(goodslist.size() <= 0 || goodslist == null)
		{
			System.err.println("该商品不存在!!");
			ScannerAndNextStep.judgeGoodsNext("deleteGoods");  //选择下一步操作
		}else
		{
			//显示所要更改的商品信息
			System.out.println("\t\t*******需要  删除  的商品的具体信息*******\n");
			System.out.println("\t商品ID\t\t商品名称\t\t商品价格\t\t商品数量");
			System.out.println("\t------------------------------------------------------");
			Goods goods = goodslist.get(0);
			System.out.println("\t" + goods.getGid() + "\t\t" + goods.getGname() + "\t\t"
					+ goods.getGprice() + "\t\t" + goods.getGnum());
			System.out.println("\t------------------------------------------------------\n");
			//确认删除
			do
			{
				System.out.println("\n确认是否删除商品：Y or N");
				String choice = ScannerAndNextStep.StringScannerTool();
				if(choice.equals("Y") || choice.equals("y"))
				{
					boolean boolDeleteGoods = new GoodsDao().deleteGoods(goods.getGid());
					if (boolDeleteGoods)
					{
						System.out.println("\t！！已成功刪除该商品！！\n");
					}
					else 
					{
						System.err.println("\t！！刪除该商品失敗！！\n");
					}
					
					//是否进行下一步操作         代码重复使用了
					ScannerAndNextStep.judgeGoodsNext("deleteGoods");
				}else if(choice.equals("N") || choice.equals("n"))
						{
							MainPage.goodsMaitenanceMage();
						}
			System.out.println("\t!!输入有误,请重新输入!!\n");
			}while(true);
		}
	}
	
	/*
	 * 4.查询商品界面
	*/
	public static void queryGoodsPage()
	{
		System.out.println("\n正在执行   查询商品   操作");
		System.out.println("\t1.根据商品数量升序查询");
		System.out.println("\t2.根据商品价格升序查询");
		System.out.println("\t3.根据关键字来查询商品");
		System.out.println("\n请输入选项，或者按0返回上一级菜单.");
		
		do
		{
			String input = ScannerAndNextStep.StringScannerTool();
			String regex = "[0-3]";
			
			if(input.matches(regex))
			{
				int num = Integer.parseInt(input);
				switch(num)
				{
					case 0:
						MainPage.goodsMaitenanceMage();
						break;
					case 1:
					case 2:
					case 3:
						if(num == 3)
						{
							System.out.println("\t正在执行商品    关键字   查询操作！");
							System.out.println("请输入商品关键字");
						}
						
						//调用  查询商品  函数
						ArrayList<?> goodslist = new GoodsDao().queryGoods(num);
						
						if(num == 1)
						{
							System.out.println("\t商品按    数量排序     列表：");
						}else if(num == 2)
							{
								System.out.println("\t商品按    价格排序    列表：");
							}else if(num == 3)
								{
									System.out.println("\t\t\t*******所查的商品信息如下********");
								}
						System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注");
						System.out.println("\t----------------------------------------------------------------------------");
						if(goodslist==null || goodslist.size()<=0)
						{
							System.out.println("\t\t!!!你查询的商品不存在！！！");
							
							ScannerAndNextStep.judgeGoodsNext("queryGoods");//选择是否继续本操作
						}else
							{
								for(int i=0,length=goodslist.size(); i < length; i++)
								{
									Goods goods = (Goods) goodslist.get(i);   //为何要将get到的类型强制转换
									System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname()
											+ "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
									if(goods.getGnum() == 10)
									{
										System.out.println("\t\t该商品已售空");
									}
									else if(goods.getGnum() < 10)
									{
										System.out.println("\t\t该商品已不足10件");
									}
									else
										System.out.println();
								}
							}
						System.out.println("\t----------------------------------------------------------------------------");
						
						System.out.println("输入“0”返回    查询商品    界面");
						boolean judge = true;
						while(judge)
						{
							String str = ScannerAndNextStep.StringScannerTool();
							if(str.equals("0"))       // 返回上级菜单并结束查询    字符串比较一定要用equals()函数，“==”比的是引用地址
							{
								judge = false;
								queryGoodsPage();
							}else
							{
								System.out.println("输入有误");
								System.out.println("输入“0”返回    查询商品    界面");
							}
						}
						break;
						
					default:
						break;
				}
				break;    //目的是如果查询合法，就结束循环，进入下一步操作
				          //如果这行代码“break;”没有，那么  360行的代码“System.out.println("输入“0”返回上级菜单");”以下会出错
			}
			
			System.err.println("！输入有误！");
			System.out.println("请重新选择,或者按0返回上一级菜单.");
		}while(true);
		
		//查询后的下一步操作	
		System.out.println("输入“0”返回上级菜单");
		boolean boolNext = true;
		while(boolNext)
		{
			String str = ScannerAndNextStep.StringScannerTool();
			if(str.equals("0"))       // 返回上级菜单并结束查询
			{
				boolNext = false;
				queryGoodsPage();
			}else
			{
				System.out.println("输入有误");
				System.out.println("输入“0”返回上一级菜单");
			}
		}
	}
	
	/*
	 * 5.显示所有商品
	*/
	public static void showAllGoods()
	{
		System.out.println("\t\t\t**********所有商品信息**********\n");
		ArrayList<Goods> goodslist = new GoodsDao().display();
		
		if(goodslist.size()<=0)
		{
			System.out.println("\t!!!库存为空！！！");
			MainPage.goodsMaitenanceMage();
		}else
			{
				System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注");
				System.out.println("\t----------------------------------------------------------------------------");
				
				for(int i=0,length=goodslist.size(); i < length; i++)
				{
					Goods goods = (Goods) goodslist.get(i);   //为何要将get到的类型强制转换
					System.out.print("\t" + goods.getGid() + "\t\t" + goods.getGname()
							+ "\t\t" + goods.getGprice() + "\t\t" + goods.getGnum());
					if(goods.getGnum() == 10)
					{
						System.out.println("\t\t该商品已售空");
					}
					else if(goods.getGnum() < 10)
					{
						System.out.println("\t\t该商品已不足10件");
					}
					else
						System.out.println();
				}
				System.out.println("\t----------------------------------------------------------------------------");
			}
		
		System.out.println("输入“0”返回商品维护界面");
		boolean judge = true;
		while(judge)
		{
			String str = ScannerAndNextStep.StringScannerTool();
			if(str.equals("0"))       // 返回上级菜单并结束查询    字符串比较一定要用equals()函数，“==”比的是引用地址
			{
				judge = false;
				MainPage.goodsMaitenanceMage();
			}else
			{
				System.out.println("输入有误");
				System.out.println("输入“0”返回商品维护界面");
			}
		}
	}
}
