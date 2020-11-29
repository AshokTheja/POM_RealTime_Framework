package com.NA.Test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.NA.Page.EditProfilePage;
import com.NA.Page.HomePage;
import com.NA.Page.LoginPage;
import com.NA.Test_Base.Base_NA;
import com.NA.testutils.TestUtilities;

public class TC_NA_UpdateResume extends Base_NA {

	public TC_NA_UpdateResume() throws IOException {
		super();
	}

	LoginPage loginPage;
	HomePage homePage;
	EditProfilePage editProfilePage;

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		Initialization(prop.getProperty("browser"), prop.getProperty("url"));
		loginPage = new LoginPage();
	}

	@Test
	public void Delete_Upload_Resume_Fromprops() throws InterruptedException, IOException {

		// Step 1(Close all extra windows)
		loginPage.CloseExtraWidnows();
		System.out.println("Step 1: Success");
		// Step 2(login)
		homePage = loginPage.Login(prop.getProperty("email"), prop.getProperty("password"));
		System.out.println("Step 2: Success");
		editProfilePage = homePage.navigate_To(prop.getProperty("editProfileLink"));
		System.out.println("Step 3: Success");
		// Step 4(Delte current resume)
		editProfilePage.deleteResume();
		System.out.println("Step 4: Success");
		// Step 5(Upload new resume)
		editProfilePage.uploadResume(prop.getProperty("resumePath"));
		System.out.println("Step 5: Success");
		// Step 6(Navigate back to home)
		editProfilePage.navigateBacktoHome();
		System.out.println("Step 6: Success");
		// Step 7(Logout)
		homePage.navigate_To(prop.getProperty("logoutLink"));
		System.out.println("Step 7: Success");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {
			TestUtilities.takeScreenshot(driver, result.getName());
		}
		driver.quit();
	}
}
