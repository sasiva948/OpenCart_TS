package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);	
	}
	
	@FindBy(xpath="//h2[text()='My Account']") WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][text()='Logout']") WebElement lnkLogout;
	
	
	public Boolean myaccountPage()
	{
		try
		{
		Boolean myaccountPageExist = msgHeading.isDisplayed();
		return myaccountPageExist;
		}
		catch(Exception e)
		{
			return false;
		}	
	}
	public void clickLogout()
	{
		lnkLogout.click();
	}

}
