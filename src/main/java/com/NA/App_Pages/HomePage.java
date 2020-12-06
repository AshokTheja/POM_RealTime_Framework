package com.NA.App_Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.NA.TestUtils.TestUtilities;
import com.NA.Test_Base.Base_NA;

public class HomePage extends Base_NA {

	@FindBy(xpath = "//a[@href='https://my.naukri.com/HomePage/view']")
	private static WebElement myNaukri_Main;

	@FindBy(xpath = "(//div[@class='subMenu c2'])[2]")
	private static WebElement myNaukri_SubMenu;

	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	// Functions
	public EditProfilePage navigate_To(String requiredPage) throws InterruptedException, IOException {
		boolean flag = false;
		try {
			Actions action = new Actions(driver);
			action.moveToElement(myNaukri_Main).perform();
			TestUtilities.sync(2000);
			List<WebElement> test = myNaukri_SubMenu.findElements(By.xpath("(//div[@class='subMenu c2'])[2]//ul//li"));
			for (WebElement we : test) {
				if (we.getText().equals((requiredPage))) {
					we.click();
					break;
				}
			}
			flag = true;
		} finally {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className,  flag);
		}

		TestUtilities.sync(5000);
		return new EditProfilePage();
	}

}
