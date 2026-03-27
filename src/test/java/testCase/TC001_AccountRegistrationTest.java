package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"A","B"})
	public void verifyRegistration()
	{
		logger.info("*** Starting of TC001 Account Registration Testcase ***");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Clicked the My Account and register");
		
		AccountRegistration ar = new AccountRegistration(driver);
		ar.setFirstName(randomAlphabets());
		ar.setLastName(randomAlphabets());
		ar.setEmail(randomAlphabets()+"@gmail.com");
		//ar.setEmail(p.getProperty("email"));
		ar.setTelephone(randomNumeric());
		
		
		String password = randomAlphaNumeric();
		
		ar.setPassword(password);
		ar.setConfirmPassword(password);
		ar.clickPrivacychkb();
		
		logger.info("Added the required details");
		
		ar.clickSubmitbtn();
		logger.info("clicked on submit button");
		
		String msg = ar.getmsgConfirmation();
		Assert.assertEquals(msg, "Your Account Has Been Created!");
		}
		catch (Throwable  e)
		{
			logger.error("Testcase failed");
			logger.debug("debug logs");
			logger.info("test failed");
			Assert.fail();
		}
		logger.info("*** Completion of TC001 Account Registration Testcase ***");
		
	}
}
