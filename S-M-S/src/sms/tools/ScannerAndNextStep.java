package sms.tools;

import java.util.Scanner;

import sms.page.SalesManPage;
import sms.page.GoodsPage;
import sms.page.MainPage;

/**
 * @author gaobaihong 2017年11月13日
 *
 */
public final class ScannerAndNextStep
{
	//输入工具1--StringInfo
	/**
	 * @return 从键盘获取String数据
	 */
	public static String StringScannerTool()  
	{
		Scanner sc = new Scanner(System.in);
		String StringInfo = sc.next();
		return StringInfo;
	}
	
	//输入工具2--IntInfo
	/**
	 * @return  从键盘获取int数据
	 */
	public static int IntScannerTool()  
	{
		Scanner sc = new Scanner(System.in);
		int IntInfo = sc.nextInt();
		return IntInfo;
	}
	
	//输入工具3--DoubleInfo
	/**
	 * @return  从键盘获取Double数据
	 */
	public static Double DoubleScannerTool()  
	{
		Scanner sc = new Scanner(System.in);
		double DoubleInfo = sc.nextDouble();
		return DoubleInfo;
	}
	
	//判断Goods的下一步操作
	/**
	 * @param operation 对goods更新 选择下一步操作
	 */
	public static void judgeGoodsNext(String operation)  
	{
		do
		{
			System.out.println("是否继续进行-当前操作：Y or N");
			String choice = StringScannerTool();
			
			if(choice.equals("Y") || choice.equals("y"))
			{
				switch(operation)
				{
					case "addGoods":
						GoodsPage.addGoodsPage();    //继续进行添加商品操作
						break;
					case "updateGoods":
						GoodsPage.updateGoodsPage();    //继续进行添加商品操作
						break;
					case "deleteGoods":
						GoodsPage.deleteGoodsPage();  //继续进行  删除商品   操作
						break;
					case "queryGoods":
						GoodsPage.queryGoodsPage();  //继续进行  查询商品   操作
						break;
					default:
						break;
				}
				
			}
			
			if(choice.equals("N") || choice.equals("n"))
				{
					System.out.println("取消当前操作");
					MainPage.goodsMaitenanceMage();
				}
			
			System.out.println("\n输入有误！请重新输入！");
		}while(true);
	}
	
	//
	/**
	 * @param 对SalesMan更新 选择下一步操作，  "operation" 为调用者
	 */
	public static void judgeSalesManNext(String operation)
	{
		do
		{
			System.out.println("是否继续进行-当前操作（" + operation +"）：Y or N");
			String choice = StringScannerTool();
			
			if(choice.equals("Y") || choice.equals("y"));
			{
				switch(operation)
				{
					case "addSalesMan":
						SalesManPage.addSalesManPage();   //继续进行添加商品操作
						break;
					case "updateSalesMan":
						SalesManPage.updateSalesManPage();   //继续进行添加商品操作
						break;
					case "deleteSalesMan":
						SalesManPage.deleteSalesManPage();  //继续进行  删除商品   操作
						break;
					case "querySalesMan":
						SalesManPage.querySalesManPage();  //继续进行  查询商品   操作
						break;
					default:
						break;
				}
				
			}
			
			if(choice.equals("N") || choice.equals("n"))
				{
					System.out.println("取消当前操作");
					MainPage.salesManManagementPage();
				}
			
			System.out.println("\n输入有误！请重新输入！");
		}while(true);
	}

	public static void jugeGSalesNext(String operation)
	{
		do
		{
			System.out.println("是否继续进行-当前操作（" + operation +"）：Y or N");
			String choice = StringScannerTool();
			
			if(choice.equals("Y") || choice.equals("y"));
			{
				
			}
			
			if(choice.equals("N") || choice.equals("n"))
				{
					System.out.println("取消当前操作");
					MainPage.salesManManagementPage();
				}
			
			System.out.println("\n输入有误！请重新输入！");
		}while(true);
	}

}
