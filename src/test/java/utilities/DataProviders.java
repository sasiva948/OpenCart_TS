package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Logindata")
	public String[][] getData() throws IOException
	{
		String path = ".//testData//TestData1.xlsx";
		//String path = "C:\\Users\\C3in\\eclipse-workspace\\OpenCart_TS\\testData\\TestData1.xlsx";
		ExcelUtility ex = new ExcelUtility(path);
		
		int rowcount = ex.getRowCount("sheet1");
		int cellcount = ex.getCellCount("sheet1", 1);
		System.out.println("RowCount: " + rowcount);
		System.out.println("CellCount: " + cellcount);
		
		String logindata[][] = new String[rowcount][cellcount];
		
		for (int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				logindata[i-1][j]= ex.getCellData("sheet1", i, j);
			}
		}
		
		return logindata;
		
	}

}
