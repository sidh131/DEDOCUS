package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_signIn {
	
	
	@DataProvider(name="validlogin")
	public static Iterator<Object[]> dp_invalidLogin() throws Exception{

		return Utility.dp_commonlogic("SignIn","ValidLogin");
		
	}
	

}
