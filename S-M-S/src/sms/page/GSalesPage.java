package sms.page;

import java.util.ArrayList;

import sms.dao.GSalesDao;
import sms.entity.GSales;
import sms.tools.ScannerAndNextStep;

public final class GSalesPage
{
	public static void dailySalesGoodsPage()
	{
		System.out.println("列出当日出售商品       操作");
		ArrayList<GSales> gsaleslist = new GSalesDao().displayDailyGoods();
		if(gsaleslist == null || gsaleslist.size() <= 0)
		{
			System.out.println("\n今日未出售商品");
			MainPage.goodsManagementPage();
		}else
			{
				System.out.println("\t\t\t***********当日出售商品列表*********\n");
				
				System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t今日销量\t\t备注");
				System.out.println("\t------------------------------------------------------------------");
				for(int i=0, length=gsaleslist.size(); i < length; i++)
				{
					GSales gsales = gsaleslist.get(i);
					int gNum = gsales.getgNum();
					System.out.print("\t" + gsales.getgName() + "\t\t" + gsales.getgPrice() + "\t\t"
							 + gsales.getgNum() + "\t\t" + gsales.getAllSnum());
					if(gNum == 0)
					{
						System.out.print("\t\t该商品已售罄\n");
					}else if(gNum < 10)
						{
							System.out.print("\t\t该商品库存不足\n");
						}else
							{
								System.out.print("\t\t-\n");
							}
				}
				System.out.println("\t------------------------------------------------------------------");
				
				do
				{
					System.out.println("输入0返回上一级");
					String choice = ScannerAndNextStep.StringScannerTool();
					
					if(choice.equals("0"))
					{
						MainPage.salesManManagementPage();
					}
					
					MainPage.goodsManagementPage();
				}while(true);
			}//if else
	}//dailySalesGoodsPage()
}
