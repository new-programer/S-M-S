package sms.page;

import java.util.ArrayList;

import sms.dao.GSalesDao;
import sms.dao.GoodsDao;
import sms.dao.SalesManDao;
import sms.entity.GSales;
import sms.entity.Goods;
import sms.entity.SalesMan;
import sms.tools.Calculate;
import sms.tools.QueryPrint;
import sms.tools.ScannerAndNextStep;

public class MainPage
{
	public static void main(String args[])
	{
		MainPage.mainPage();
	}

	//0.主界面
	public static void mainPage()
	{
		System.out.println("------------------------------------------");
		System.out.println("\t\t1.商品维护\n");
		System.out.println("\t\t2.前台收银\n");
		System.out.println("\t\t3.商品管理");
		System.out.println("------------------------------------------");
		System.out.println(" 备注：输入以上数字进入相应功能界面，退出请输入数字0！");
		
		do
		{
			System.out.println("请输入");
			String user_input = ScannerAndNextStep.StringScannerTool();   //从键盘获取信息
			
			String regex = "[0-3]";
			if(user_input.matches(regex))
			{
				int num = Integer.parseInt(user_input);
				switch(num)
				{
					case 0: 
						System.out.println("-----------------");
						System.out.println("已退出系统！");
						System.exit(1);
					case 1:
						goodsMaitenanceMage();
						break;
					case 2:
						cashRegisterPage();
						break;
					case 3:
						goodsManagementPage();
					default:
						break;
				}
			}
			System.err.println("输入有误！");
			System.out.println("重新选择或者按0退出");
		}
		while(true);
		
	}

	//1.商品维护界面
	public static void goodsMaitenanceMage()
	{
		/*
		 * 商品维护界面显示信息
		*/
		System.out.println("------------------------------------------");
		System.out.println("\t\t1.添加商品");
		System.out.println("\t\t2.更改商品");
		System.out.println("\t\t3.删除商品");
		System.out.println("\t\t4.查询商品");
		System.out.println("\t\t5.显示所有商品");
		System.out.println("------------------------------------------");
		System.out.println(" 备注：输入以上数字进入相应功能界面，退出请输入数字0 ！");
		
		/*
		 * 商品维护功能实现
		*/
		do
		{
			System.out.println("请输入");
			String user_input = ScannerAndNextStep.StringScannerTool();   //这三行待具体工具函数设置
			
			String regex = "[0-5]";  //正则表达式
			if(user_input.matches(regex))
			{
				int num = Integer.parseInt(user_input);
				switch(num)
				{
					case 0: 
						mainPage();
						break;
					case 1:
						GoodsPage.addGoodsPage();
						break;
					case 2:
						GoodsPage.updateGoodsPage();
						break;
					case 3:
						GoodsPage.deleteGoodsPage();
					case 4:
						GoodsPage.queryGoodsPage();
					case 5:
						GoodsPage.showAllGoods();
					default:
						break;
				}
			}
			System.err.println("输入有误！");
			System.out.println("重新选择或者按0退出");
		}
		while(true);
	}
	
	//2.收银系统登陆界面
	public static void cashRegisterPage()
	{
		System.out.println("\t     !!欢迎使用购物管理系统!!");
		System.out.println("------------------------------------------\n");
		System.out.println("\t\t1.登陆系统\n");
		System.out.println("\t\t2.退出\n");
		System.out.println("------------------------------------------");
		System.out.println(" 备注：根据提示操作");
		
		do
		{
			System.out.println("请输入：");
			String user_input = ScannerAndNextStep.StringScannerTool();
			
			String regex = "[0-2]";
			if(user_input.matches(regex))
			{
				int num = Integer.parseInt(user_input);
				switch(num)
				{
					case 0:
					case 2:
						mainPage();
						break;
					case 1:
						int loginTimes = 3;
						while(loginTimes != 0)
						{
							--loginTimes;
							
							System.out.println("----姓名-----");
							String logName = ScannerAndNextStep.StringScannerTool();
							System.out.println("----密码-----");
							String logPassword = ScannerAndNextStep.StringScannerTool();
							
							ArrayList<SalesMan> salesmanlist = new SalesManDao().checkLogin(logName);
							if(salesmanlist.size() <=0 || salesmanlist == null)   //无该售货员
							{
								System.err.println("\t用户名输入错误！！");
								System.out.println("\t剩余登陆次数：" + loginTimes);
							}else
								{
									SalesMan salesman = salesmanlist.get(0);
									if(logPassword.equals(salesman.getSpassword()))
										{
												System.out.println("\t\t-----登陆成功-----");
												shoppingSettlementPage(salesman.getSid());   //登陆成功后，进入  购物结算界面
										}else
											{
												System.out.println("\t密码输入错误！！请重新登陆");
												System.out.println("\t剩余登陆次数：" + loginTimes);
											}
								}
							
							//验证用户名和密码，此处代码待完善
						}
						
						System.out.println("\t###################");
						System.out.println("\t#  你已被强制退出系统    #");
						System.out.println("\t###################");
						System.exit(1);
				}
			}
		System.err.println("\n!输入有误!");
		System.out.println("\n重新输入或按 0 返回上一级菜单");			
		}
		while(true);
	}
	
	//2.1购物结算界面
	
	/**
	 * @param salesmanID
	 */
	/**
	 * @param salesmanID
	 */
	/**
	 * @param salesmanID
	 */
	public static void shoppingSettlementPage(int salesmanID)  //待完善
	{
		System.out.println("\n\t\t*******购物结算*******\n");
		
		do
		{
			System.out.println("\t输入  S 开始进行购物结算， 输入   0  返回上一级");
			String input = ScannerAndNextStep.StringScannerTool();
			
			if(input.equals("0"))
			{
				cashRegisterPage();
			}else if(input.equals("S") || input.equals("s"))     //开始进行结算
					{
						System.out.println("请输入商品---关键字");
						int gid = QueryPrint.querySettlement();
						
						switch(gid)
						{
							case -1:
								System.out.println("该商品不存在");
								break;
							case -2:
								System.out.println("该商品已售空");
								break;
							default:
								System.out.println("*****请选择商品(输入商品ID)*****");
								//根据商品编号 gid 精确查询
								int goodsid = ScannerAndNextStep.IntScannerTool();
								ArrayList<Goods> goodslist = new QueryPrint().queryGoodsByKey(goodsid, null);
								
								if(goodslist.size() <= 0 || goodslist == null)
								{
									System.out.println("查无此商品");
								}else
									{
										Goods goods = goodslist.get(0);
										int gNum = goods.getGnum();
										Double gPrice = goods.getGprice();
										
										System.out.println("-----请输入购买商品数量-----\n");
										do
										{
											//inputGNUM 是商品数量
											int inputGNUM = ScannerAndNextStep.IntScannerTool();
											if(inputGNUM > gNum)
											{
												System.err.println("\t仓库储备不足！");
												System.out.println("请重新输入商品数量");
											}else
												{
													double allPrice = Calculate.multi((double) inputGNUM, gPrice);
													System.out.println("\t\t***********购物车结算**********");
													System.out.println("\t商品名称\t\t单价\t\t购买数量\t\t总价");
													System.out.println("\t----------------------------------------------------");
													System.out.println("\t" + goods.getGname() + "\t\t" + goods.getGprice() + 
															"$\t\t" + inputGNUM + "\t\t" + allPrice + "$\n");
													System.out.println("\t----------------------------------------------------");
													do
													{
														System.out.println("确认购买    Y or N");
														String choice = ScannerAndNextStep.StringScannerTool();
														if(choice.equals("Y") || choice.equals("y"))
														{
															System.out.println("总价：" + allPrice + "$");
															System.out.println("\n实际缴费金额");
															
															do
															{
																double userMoney = ScannerAndNextStep.DoubleScannerTool();
																double balance = Calculate.minus(userMoney, allPrice);
																
																if(balance < 0)
																{
																	System.out.println("\t支付金额不足!!\n");
																	System.out.println("\t请重新输入支付金额！！");
																}else
	      /*	这里是购物结算操作数据库！！！！！！----------------------	  1.更改goods表数量 
			  														  2.增加sales表数量
		 */
																	{
																		//对gsales表进行操作
																		GSales gsales = new GSales(goods.getGid(), salesmanID, inputGNUM);
																		boolean boolInsert = new GSalesDao().shoppingSettlement(gsales);
																		
																		//对goods表进行操作
																		int goodsNewNum = gNum - inputGNUM;
																		Goods goodsUpdate = new Goods(goods.getGid(),goodsNewNum);
																		boolean boolUpdate = new GoodsDao().updateGoods(3, goodsUpdate);
																		if(boolInsert && boolUpdate)
																		{
																			System.out.println("找零：" + balance + "$");
																			//ScannerAndNextStep.jugeGSalesNext("goodsSettlement");
																			System.out.println("\n谢谢光临， 欢迎再次惠顾");
																		}else
																			{
																				System.err.println("支付失败！");
																			}
																		shoppingSettlementPage(salesmanID);//最后跳转到到购物结算页面
			//	结束购物结算操作数据库！！！！！！-----------------------------------		
																	}
															}while(true);
														}else if(choice.equals("N") || choice.equals("n"))
															{
																shoppingSettlementPage(salesmanID);
															}else
																{
																	System.out.println("输入有误，请重新输入合法字符");
																}
													}while(true);
												}
										}while(true);
									}
						}
					}else
						{
							System.err.println("\t请输入合法字符!！");
						}
		}while(true);
	}
	
	//3.综合管理界面
	public static void goodsManagementPage()
	{
		System.out.println("------------------------------------------");
		System.out.println("\t\t1.列出当日卖出商品列表");
		System.out.println("\t\t2.售货员管理");
		System.out.println("------------------------------------------");
		System.out.println(" 备注：输入以上数字进入相应功能界面，退出请输入数字0 ！");
		
		do
		{
			System.out.println("请输入：");
			String user_input = ScannerAndNextStep.StringScannerTool();
			String regex = "[0-2]";
			if(user_input.matches(regex))
			{
				int num = Integer.parseInt(user_input);
				switch(num)
				{
					case 0:
						mainPage();  //返回主页面
						break;
					case 1:
						GSalesPage.dailySalesGoodsPage();   //列出当日所有商品出售信息
						break;
					case 2:
						salesManManagementPage();
						break;
					default:
						break;
				}
			}
			System.err.println("输入有误！\n");
			System.out.println("重新输入或按 0 返回上一级菜单");			
		}
		while(true);
	}

	//3.1售货员管理界面
	public static void salesManManagementPage()
	{
		/*
		 * 售货员管理界面显示信息
		*/
		System.out.println("------------------------------------------");
		System.out.println("\t\t1.添加售货员");
		System.out.println("\t\t2.更改售货员");
		System.out.println("\t\t3.删除售货员");
		System.out.println("\t\t4.查询售货员");
		System.out.println("\t\t5.显示所有售货员");
		System.out.println("------------------------------------------");
		System.out.println(" 备注：输入以上数字进入相应功能界面，退出请输入数字0 ！");
		
		/*
		 * 售货员管理功能实现
		*/
		do
		{
			System.out.println("请输入");
			String user_input = ScannerAndNextStep.StringScannerTool();   //这三行待具体工具函数设置
			
			String regex = "[0-5]";  //正则表达式
			if(user_input.matches(regex))
			{
				int num = Integer.parseInt(user_input);
				
				switch(num)      //对应的SalesManPage的代码未完善
				{
					case 0: 
						goodsManagementPage();
						break;
					case 1:
						SalesManPage.addSalesManPage();
						break;
					case 2:
						SalesManPage.updateSalesManPage();
						break;
					case 3:
						SalesManPage.deleteSalesManPage();
					case 4:
						SalesManPage.querySalesManPage();
					case 5:
						SalesManPage.showAllSalesMan();
					default:
						break;
				}
			}
			System.err.println("输入有误！");
			System.out.println("重新选择或者按0退出");
		}
		while(true);
	}

}
