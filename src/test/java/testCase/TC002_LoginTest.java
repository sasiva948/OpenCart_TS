package testCase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginTest extends BaseClass{
	
	@Test(dataProvider = "Logindata", dataProviderClass = DataProviders.class, groups= {"C","A"})
	public void logintest(String UserName, String Password, String Validation)
	{
		logger.info("***TC002_STARTED***");
		try
		{
			
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(UserName);
		lp.setPassword(Password);
		lp.clickLogin();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage = map.myaccountPage();
		if (Validation.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
			map.clickLogout();
			Assert.assertTrue(true);
			}
		
		  else
		  {
			Assert.assertTrue(false);
		  }
		}
		if (Validation.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
			map.clickLogout();
			Assert.assertTrue(false);
			}
		
		    else
		    {
			Assert.assertTrue(true);
		    }
		 }
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***TC002 COMPLETED***");
	}

}
