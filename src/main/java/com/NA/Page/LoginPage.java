package com.NA.Page;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.NA.Test_Base.Base_NA;
import com.NA.testutils.TestUtilities;

public class LoginPage extends Base_NA {

	@FindBy(xpath = "//a[@title='Jobseeker Login']")
	private static WebElement login_Btn;

	@FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
	private static WebElement username;

	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	private static WebElement password;

	@FindBy(xpath = "//button[text()='Login']")
	private static WebElement login_Btn1;

	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void CloseExtraWidnows() throws InterruptedException {
		boolean flag = false;

		try {
			System.out.println(driver.getTitle());
			String base = driver.getWindowHandle();
			TestUtilities.sync(5000);

			Set<String> instant = driver.getWindowHandles();
			for (String s : instant) {
				if (!s.equals(base)) {
					driver.switchTo().window(s).close();
				}
			}
			driver.switchTo().window(base);
			String back2Base = driver.getWindowHandle();
			Assert.assertEquals(back2Base, base);
			flag = true;

		} finally {

			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}
	}

	public HomePage Login(String userName, String passWord) throws InterruptedException, IOException {
		boolean flag = false;
		try {
			login_Btn.click();
			TestUtilities.sync(3000);
			wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(userName);
			wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(passWord);
			login_Btn1.click();
			TestUtilities.sync(5000);
			flag = true;
		} finally {

			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}

		return new HomePage();

	}

}
