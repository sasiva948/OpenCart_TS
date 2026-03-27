package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import testBase.BaseClass;

public class ExtendReportManager extends BaseClass implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public String reportPath;

    

    public void onStart(ITestContext context) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
    	Date dt = new Date();
    	String timstamp=df.format(dt);
    	String reportName = "ExtentReport"+timstamp+".html";
    	reportPath = System.getProperty("user.dir") + "/reports/"+reportName;

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester", "Siva");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        
        String os = context.getCurrentXmlTest().getParameter("os");
        String browser = context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating System", os);
        extent.setSystemInfo("Browser", browser);
        
        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if(! includedGroups.isEmpty())
        {
        	extent.setSystemInfo("Groups", includedGroups.toString());
        }
        
        
        
    }

    public void onTestStart(ITestResult result) {
    	

        test = extent.createTest(result.getTestClass().getName());

        test.assignCategory(result.getMethod().getGroups());
    }

    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS, result.getName() + " Passed");
    }

    public void onTestFailure(ITestResult result) {

        test.log(Status.FAIL, result.getName() + " Failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            BaseClass bc = (BaseClass) result.getInstance();
            String imgPath = bc.captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        test.log(Status.SKIP, result.getName() + " Skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext context) {

        extent.flush();
        //String reportName = "ExtentReport.html";
        //String reportPath = System.getProperty("user.dir") + "/reports/"+reportName;

        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
