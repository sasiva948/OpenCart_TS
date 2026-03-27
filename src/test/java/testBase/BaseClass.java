package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(alwaysRun = true)
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException
	{
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
		switch (br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		case "firefox" : driver = new FirefoxDriver();break;
		default: System.out.println("Invalid browser name");
		}
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		DesiredCapabilities capability = new DesiredCapabilities();
		if(os.equalsIgnoreCase("windows"))
		{
			capability.setPlatform(Platform.WIN11);
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			capability.setPlatform(Platform.MAC);
		}
		else if (os.equalsIgnoreCase("linux"))
		{
			capability.setPlatform(Platform.LINUX);
		}
		else
		{
			System.out.println("No matching OS");
			return;
		}
		switch (br.toLowerCase())
		{
		case "chrome" : capability.setBrowserName("chrome");break;
		case "edge"   : capability.setBrowserName("MicrosoftEdge");break;
		default : System.out.println("No matching browser");return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444"),capability );
		}
		
		driver.manage().deleteAllCookies();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomAlphabets()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	public String randomNumeric()
	{
		String generatedNumber = RandomStringUtils.randomAlphanumeric(5);
		return (generatedNumber);
	}
	
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomAlphanumeric(3);
		return (generatedString+"@"+generatedNumber);
	}

	
	public String captureScreen(String tname)
	{
		String filePath="";
		try
		{
		  if(driver == null)
		    {
		        System.out.println("Driver is NULL → Screenshot skipped");
		        return "";
		    }
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Date dt = new Date();
		String timestamp = df.format(dt);
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		filePath = System.getProperty("user.dir")+"/screenshots/"+tname+timestamp+".png";
		File targetFile = new File(filePath);
		sourceFile.renameTo(targetFile);
		
		}
		catch(Exception e)
		{
			System.out.println("Screenshot not taken");
		}
		return filePath;
		
	}
}
