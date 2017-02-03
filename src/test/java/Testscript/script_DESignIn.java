package Testscript;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Genericlibrary.ExcelRW;
import Genericlibrary.Utility;
import Pagefactory.pf_FOCUSHomepage;
import Pagefactory.pf_Login;
import Pagefactory.pf_SignIn;
import Pagefactory.pf_genericmethods;

public class script_DESignIn extends Base {

	Logger loginlog = Logger.getLogger(script_DESignIn.class);
	
	@Test(dataProvider="validlogin", dataProviderClass=Dataproviders.dp_signIn.class, enabled=true, priority=1, groups={"ST1"})
	
	public void validLogin (Map hm) throws Exception{
		
		String uid=hm.get("username").toString();
		String pwd=hm.get("pwd").toString();
		String exp_msg=hm.get("exp_msg").toString();
		String sheetname=hm.get("SheetName").toString();
		String scriptname=hm.get("Script").toString();
		
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		
		pf_genericmethods genericmethods = new pf_genericmethods();
		
		
		
		startTest=extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		loginlog.info("Started execution of test case " + tcid +"_" + order);
		
		ExcelRW excelRW1 = new ExcelRW(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");
		
		int rowcount = excelRW1.rowcount(sheetname);
		int colcount = excelRW1.Colcount(sheetname);
		
		for (int i=1;i<=rowcount;i++){
			String keyword=excelRW1.readcellval(sheetname, i, 2);
			String xPath= excelRW1.readcellval(sheetname, i, 4);
			String testData= excelRW1.readcellval(sheetname, i, 3);
			String stepName= excelRW1.readcellval(sheetname, i, 5);
			
				
			switch (keyword){
			
			case "EnterText":
				WebElement txt_EnterText = fd.findElement(By.xpath(xPath));
				String testdata = hm.get(testData).toString();
				
				genericmethods.cl_entertext(txt_EnterText, testdata);
				
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;
				
			case "Click":
				WebElement btn = fd.findElement(By.xpath(xPath));
				genericmethods.cl_click(btn);
				break;
				
			case "verifyExpected":
				WebElement lbl = fd.findElement(By.xpath(xPath));
				String actual=lbl.getText();
				String expected = hm.get(testData).toString();
				cv_contains(actual, expected, "Verify");
			
			
			}
			
			
			
			
	/*		if (keyword.equalsIgnoreCase("EnterText")){
				fd.findElement(By.xpath(xPath)).sendKeys(hm.get(testData).toString());;
				startTest.log(LogStatus.PASS, "Enter login details","Entered login details" + startTest.addScreenCapture(getScreenshot()) );
			}
			
			if (keyword.equalsIgnoreCase("Click")){
				fd.findElement(By.xpath(xPath)).click();
		}
			
			if (keyword.equalsIgnoreCase("verifyExpected")){
				String actual=fd.findElement(By.xpath(xPath)).getText();
				
				cv_contains(actual, hm.get(testData).toString(), "Verify");
			}
		
		*/
		
		/*Iterator<Object[]> commonlogic = Utility.dp_commonlogic(sheetname, scriptname);
		
		while(commonlogic.hasNext()){
			Object obj=commonlogic.next();
			Set<String> hm1=new HashSet<String>();
			hm1.add(obj.toString());
			
		}*/
		
		
		/*pf_Login pf_Login = new pf_Login(fd);
		
		pf_Login.Login(uid, pwd);
		
		loginlog.info("Entered Login Credetials");
		startTest.log(LogStatus.PASS, "Enter login details","Entered login details" + startTest.addScreenCapture(getScreenshot()) );
		
		pf_FOCUSHomepage hmePage=new pf_FOCUSHomepage(fd);
		
		WebElement lbl_username = hmePage.lbl_username;
		String Actual= lbl_username.getText();
		
		cv_equals(Actual, exp_msg, "Login Validation");	
		
		*/
		
	
		
		
		
	}
	
}

	
}
