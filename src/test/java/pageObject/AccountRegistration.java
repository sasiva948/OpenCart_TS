package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public class AccountRegistration extends BasePage {
	
	WebDriver driver;
	
	public AccountRegistration(WebDriver driver)
	{
		super(driver);
	}
	
	
@FindBy (xpath ="//input[@name='firstname']") WebElement txtFirstName;
@FindBy (xpath ="//input[@name='lastname']") WebElement txtLastName;
@FindBy (xpath ="//input[@name='email']") WebElement txtEmail;
@FindBy (xpath ="//input[@name='telephone']") WebElement txtTelephone;
@FindBy (xpath ="//input[@name='password']") WebElement txtPassword;
@FindBy (xpath ="//input[@name='confirm']") WebElement txtConfirmPassword;
@FindBy (xpath ="//input[@name='agree']") WebElement chkbPrivacy;
@FindBy (xpath ="//input[@value='Continue']") WebElement btnContinue;
@FindBy (xpath ="//div[@id='content']/h1") WebElement msgConfirmation;

public void setFirstName(String fname) 
{
	txtFirstName.sendKeys(fname);
}

public void setLastName(String lname) 
{
	txtLastName.sendKeys(lname);
}
public void setEmail(String email) 
{
	txtEmail.sendKeys(email);
}
public void setTelephone(String telephone) 
{
	txtTelephone.sendKeys(telephone);
}
public void setPassword(String password) 
{
	txtPassword.sendKeys(password);
}
public void setConfirmPassword(String cnfpassword) 
{
	txtConfirmPassword.sendKeys(cnfpassword);
}
public void clickPrivacychkb() 
{
	chkbPrivacy.click();;
}
public void clickSubmitbtn() 
{
	btnContinue.click();;
}
public String getmsgConfirmation () 
{
	try
	{
		return (msgConfirmation.getText());
	}
	catch (Exception e)
	{
		return (e.getMessage());
	}
	
}




}
