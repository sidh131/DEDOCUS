package Testscript;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Pagefactory.pf_FOCUSHomepage;
import Pagefactory.pf_Login;
import Pagefactory.pf_SignIn;

public class script_DESignIn extends Base {

	Logger loginlog = Logger.getLogger(script_DESignIn.class);
	
	@Test(dataProvider="validlogin", dataProviderClass=Dataproviders.dp_signIn.class, enabled=true, priority=1, groups={"UAT"})
	
	public void validLogin (Map hm) throws Exception{
		
		String uid=hm.get("username").toString();
		String pwd=hm.get("pwd").toString();
		String exp_msg=hm.get("exp_msg").toString();
		
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		
		
		
		startTest=extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		loginlog.info("Started execution of test case " + tcid +"_" + order);
		
		pf_Login pf_Login = new pf_Login(fd);
		
		pf_Login.Login(uid, pwd);
		
		loginlog.info("Entered Login Credetials");
		startTest.log(LogStatus.PASS, "Enter login details","Entered login details" + startTest.addScreenCapture(getScreenshot()) );
		
		pf_FOCUSHomepage hmePage=new pf_FOCUSHomepage(fd);
		
		WebElement lbl_username = hmePage.lbl_username;
		String Actual= lbl_username.getText();
		
		cv_equals(Actual, exp_msg, "Login Validation");	
		
	
		
		
		
	}
	
}
