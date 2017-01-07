package Genericlibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Base {

	public WebDriver fd;
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	public String tcid;
	public String order;
	public String browser_type;
	public Utility ut;
	
	@BeforeSuite(groups={"Smk","UAT","Reg","ST"})
	public void create_Report(){
		
		extentReports = new ExtentReports("C:\\DecFramework\\Report\\DEFOCUS_"+get_datetimestamp() +".html",false);
		
	}
	
	@Parameters({"browser"})
	@BeforeMethod(groups={"Smk","UAT","Reg","ST"})
	public void launchApp(String btype) throws Exception{
		browser_type=btype;
		if(btype.equals("ff")){
			
			fd=new FirefoxDriver();
		}else if(btype.equals("ch")){
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\siagrawal\\chromedriver.exe");
			fd=new ChromeDriver();
		}else if(btype.equals("ie")){
			System.setProperty("webdriver.ie.driver", "E:\\drivers\\IEDriverServer.exe");
			fd=new InternetExplorerDriver();
			
		}
//		fd= new FirefoxDriver();
		fd.get(Utility.getval(Utility.getval("env")));
		fd.manage().window().maximize();
		fd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		
	}
	
	
	@AfterMethod(groups={"Smk","UAT","Reg", "ST"})
	public void cloaseApp() throws Exception{	
		
		fd.close();
		
		extentReports.endTest(startTest);
		extentReports.flush();
		
		
	}
	
	public String get_datetimestamp(){
		Date date = new Date();
//		format date
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
//		
		 String format = dateFormat.format(date);
		 return format;
	}
	
//	capture snapshot
	public String getScreenshot() throws Exception{
		
		TakesScreenshot sc=(TakesScreenshot)fd;
		File screenshotAs = sc.getScreenshotAs(OutputType.FILE);
		
		String fpath = Utility.getval("Screenshot_path") + tcid + "_" + order + "_" + get_datetimestamp() +".png";
		FileUtils.copyFile(screenshotAs, new File(fpath));
		return fpath;
				
	}
	
	
//	Common Validation
//	equals
	public void cv_equals(String actual,String expected,String stepname) throws Exception{
		
		ut=new Utility();
		
		
		if(actual.equals(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
			//ut.writeExcel(sheetname, testcaseId,Order, "Status", "Pass");
			
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
			//ut.writeExcel(sheetname, testcaseId,Order, "Status", "Fail");
		}
		
				
	}
	
	
//	contains
public void cv_contains(String actual,String expected,String stepname) throws Exception{
		
		if(actual.contains(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
		}
		
				
	}
	
}










