package sms.page;

import java.util.ArrayList;

import sms.dao.SalesManDao;
import sms.entity.SalesMan;
import sms.tools.QueryPrint;
import sms.tools.ScannerAndNextStep;

/*
 * 操作售货员界面
 * @author insanity2017@126.com
*/
public final class SalesManPage
{
	/*
	 * 1.添加售货员界面
	*/
	public static void addSalesManPage()
	{
		System.out.println("正在执行    添加售货员     操作");
		do
		{
			System.out.println("请输入售货员--【姓名】 ");
			String sname = ScannerAndNextStep.StringScannerTool();
			System.out.println("请输入售货员--【密码】 ");
			String spassword = ScannerAndNextStep.StringScannerTool();
			
			SalesMan salesman = new SalesMan(sname, spassword);
			boolean booladd = SalesManDao.addSalesMan(salesman);
			
			if(booladd)
			{
				System.out.printf("售货员--%s  添加成功！", sname);
			}
			else
			{
				System.err.printf("售货员--%s  添加失败！", sname);
			}
			
			ScannerAndNextStep.judgeSalesManNext("addSalesMan");  
		}while(true);
	}
	
	/*
	 * 2.更改售货员界面
	*/
	public static void updateSalesManPage()
	{
		System.out.println("正在执行   更改售货员   操作\n");
		System.out.println("请输入需要变更的售货员姓名：");
		String sname = ScannerAndNextStep.StringScannerTool();
		
		ArrayList<SalesMan> salesmanList = new ArrayList<>();
		salesmanList = QueryPrint.querySalesMan(sname);   //根据 售货员  的姓名查询该售货员的ID
		
		if(salesmanList.size() <= 0 || salesmanList == null)
		{
			System.err.println("\t查无此人！\n");
			ScannerAndNextStep.judgeSalesManNext("updateSalesMan");    //选择是否继续进行该操作
		}
		else
		{
			System.out.println("\t\t需要变更的售货员信息如下：\n");
			System.out.println("\t售货员ID\t\t售货员姓名\t\t售货员密码");
			System.out.println("\t-----------------------------------------");
				SalesMan salesman = salesmanList.get(0);
				System.out.println("\t" + salesman.getSid() + "\t\t" + salesman.getSname() + "\t\t" + salesman.getSpassword());
			System.out.println("\t-----------------------------------------");
			
			System.out.println("\n输入序号选择相应操作, 或者输入0返回上级菜单");
			System.out.println("\t1.更改售货员--姓名");
			System.out.println("\t2.更改售货员--密码");
			System.out.println("请输入：");
			
			do
			{
				String inputupdate = ScannerAndNextStep.StringScannerTool();
				String regex = "[0-2]";
				if(inputupdate.matches(regex))
				{
					int key = Integer.parseInt(inputupdate);
					switch(key)
					{
						case 0:
							MainPage.salesManManagementPage();
							break;
							
						case 1:
							System.out.println("请输入售货员--新姓名");
							String newName = ScannerAndNextStep.StringScannerTool();
							SalesMan salesmanName = new SalesMan(salesman.getSid(), newName,null);
							boolean boolname = new SalesManDao().updateSalseMan(key, salesmanName);
							if(boolname)
							{
								System.out.println("t！！成功更新售货员名字至数据库！！");
								ScannerAndNextStep.judgeSalesManNext("updateSalesMan");
							}
							else
							{
								System.err.println("\t更新售货员名字失败 !!");
								ScannerAndNextStep.judgeSalesManNext("updateSalesMan");
							}
							break;
							
						case 2:
							System.out.println("请输入售货员--新密码");
							String newPassword = ScannerAndNextStep.StringScannerTool();
							SalesMan salesmanPassword = new SalesMan(salesman.getSid(), null, newPassword);
							boolean boolpassword = new SalesManDao().updateSalseMan(key, salesmanPassword);
							if(boolpassword)
							{
								System.out.println("\t！！成功更新售货员密码至数据库！！");
								System.out.println("新密码是：" + newPassword);
								ScannerAndNextStep.judgeSalesManNext("updateSalesMan");  
							}else
								{
									System.err.println("\t更新售货员名字失败 !!\n");
									ScannerAndNextStep.judgeSalesManNext("updateSalesMan");  
								}
						default:
							break;
					}
				}
				System.err.println("输入有误！");
				System.out.println("请选择选项，或者按0返回上一级菜单");
			}while(true);
			
		}
	}
	
	/*
	 * 3.删除售货员界面
	*/
	public static void deleteSalesManPage()
	{
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		System.out.println("请输入要删除的售货员姓名：");
		String sname = ScannerAndNextStep.StringScannerTool();
		salesmanlist = new SalesManDao().querySalesMan(sname); 
		
		if(salesmanlist.size() <= 0 || salesmanlist == null)
		{
			System.err.println("\t查无售货员！");
			ScannerAndNextStep.judgeSalesManNext("deleteSalesMan");
		}else
			{
				System.out.println("\t\t删除售货员信息");
				System.out.println("\t售货员ID\t\t售货员姓名\t\t售货员密码");
				
					SalesMan salesman = salesmanlist.get(0);
					System.out.println("\t" + salesman.getSid() + "\t\t" + salesman.getSname() 
						+ "\t\t" + salesman.getSpassword());
				
					
					do
					{
						System.out.println("确认是否删除  售货员--" + salesman.getSname() + "     Y or N");
						String choice = ScannerAndNextStep.StringScannerTool();
						if(choice.equals("Y") || choice.equals("y"))
						{
							boolean booldelete = new SalesManDao().deleteSalesMan(sname);
							if(booldelete)
							{
								System.out.println("售货员" + sname + "已经从数据库中删除");
								ScannerAndNextStep.judgeSalesManNext("deleteSalesMan");
							}else
								{
									System.err.println("删除失败！");
									ScannerAndNextStep.judgeSalesManNext("deleteSalesMan");
								}
						}else if(choice.equals("N") || choice.equals("n"))
							{
								MainPage.salesManManagementPage();
							}else
								{
									System.err.println("\t!!!输入有误！！！");
								}
						
					}while(true);
				
			}
		
		
	}
	
	/*
	 * 4.查询售货员界面
	*/
	public static void querySalesManPage()
	{
		System.out.println("正在执行    查询售货员    操作！");
		
		do
		{
			System.out.println("请输入关键字：");
			String sname = ScannerAndNextStep.StringScannerTool();
			
			ArrayList<SalesMan> salesmanlist = new SalesManDao().querySalesMan(sname);
			
			if(salesmanlist == null || salesmanlist.size() <= 0)
			{
				System.out.println("\t\t你查询的  售货员  不存在,请更换  关键字  搜索\n");
				ScannerAndNextStep.judgeSalesManNext("querySalesMan");
			}else
				{
					System.out.println("\t\t售货员ID\t\t售货员姓名\t\t售货员密码");
					System.out.println("\t\t----------------------------------------");
					for(int i=0,length=salesmanlist.size(); i<length; i++)
					{
						SalesMan salesman = (SalesMan) salesmanlist.get(i);
						System.out.println("\t\t" + salesman.getSid() + "\t\t" + salesman.getSname()
						+ "\t\t" + salesman.getSpassword());
					}
					
					System.out.println("\t\t-----------------------------------------");
					
				}
			
			ScannerAndNextStep.judgeSalesManNext("querySalesMan");
		}while(true);
		
		
	}
	
	/*
	 * 5.显示所有售货员
	*/
	public static void showAllSalesMan()
	{
		ArrayList<SalesMan> salesmanlist = new ArrayList<>();
		salesmanlist = new SalesManDao().display();
		
		if(salesmanlist.size() <= 0 || salesmanlist == null)
		{
			System.out.println("!无售货员信息！");
			MainPage.salesManManagementPage();
		}
		else
		{
			System.out.println("\t售货员ID\t\t售货员姓名\t\t售货员密码");
			System.out.println("\t----------------------------------------");
			for(int i=0,length=salesmanlist.size(); i<length; i++)
			{
				SalesMan salesman = (SalesMan) salesmanlist.get(i);
				System.out.println("\t" + salesman.getSid() + "\t\t" + salesman.getSname()
				+ "\t\t" + salesman.getSpassword());
			}
			
			System.out.println("\t----------------------------------------");
		}
		
		do
		{
			System.out.println("输入0返回上一级菜单");
			String str0 = ScannerAndNextStep.StringScannerTool();
			
			if(str0.equals("0"))
			{
				MainPage.salesManManagementPage();
			}
			else
			{
				System.err.println("输入有误");
			}
		}while(true);
	}
}
