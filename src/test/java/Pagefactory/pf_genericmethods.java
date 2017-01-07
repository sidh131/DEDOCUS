package Pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;

public class pf_genericmethods {
	
//	to enter text in textbox
	public void cl_entertext(WebElement ele,String text){
		ele.sendKeys(text);
	}
	
	
	
//	to click on any element
	public void cl_click(WebElement ele){
		ele.click();
	}
	
// select an Element from dropdown
	public void cl_selectfromdropdown(List<WebElement> ele,String value){
		int size = ele.size();
		
		for(WebElement xyz: ele){
			
			
			
			if(xyz.getText().equals(value)){
				
				xyz.click();
				break;
				
			}
	}
	}
	
	
	
	

}
