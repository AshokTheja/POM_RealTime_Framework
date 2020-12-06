package com.NA.Test;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.NA.App_Pages.EditProfilePage;
import com.NA.App_Pages.HomePage;
import com.NA.App_Pages.LoginPage;
import com.NA.TestUtils.TestUtilities;
import com.NA.Test_Base.Base_NA;

public class TC_NA_EditProfile extends Base_NA {

	public TC_NA_EditProfile() throws IOException {
		super();
	}

	static ArrayList<String> reqDataList;

	LoginPage loginPage;
	HomePage homePage;
	EditProfilePage editProfilePage;

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		TestUtilities data = new TestUtilities();
		reqDataList = data.extractExcelcontentByColumnIndex("TC_NA_EditProfile");
	}

	@Test
	public void Delete_Upload_ResumeHeadLine_ProfileSummary() throws InterruptedException, IOException {

		String browser = reqDataList.get(1).trim();
		String url = reqDataList.get(2).trim();
		String email = reqDataList.get(3).trim();
		String password = reqDataList.get(4).trim();
		String link1 = reqDataList.get(5).trim();
		String link2 = reqDataList.get(6).trim();
		String resumeHeadLine = reqDataList.get(8).trim();
		String profileSummary = reqDataList.get(9).trim();

		// Step 1
		Initialization(browser, url);
		loginPage = new LoginPage();
		System.out.println("Step 1: Success");
		// Step 2(Close all extra windows)
		loginPage.CloseExtraWidnows();
		System.out.println("Step 2: Success");
		// Step 3(login)
		homePage = loginPage.Login(email, password);
		System.out.println("Step 3: Success");
		// Step 4(Navigate to Edit profile)
		editProfilePage = homePage.navigate_To(link1);
		System.out.println("Step 4: Success");
		// Step 5(Edit Resume Headline)
		editProfilePage.editResume_HeadLine(resumeHeadLine);
		System.out.println("Step 5: Success");
		Thread.sleep(3000);
		// Step 6(Edit Profile summary)
		editProfilePage.edit_ProfileSummary(profileSummary);
		Thread.sleep(3000);
		System.out.println("Step 6: Success");
		// Step 7(Navigate back to home)
		editProfilePage.navigateBacktoHome();
		System.out.println("Step 7: Success");
		// Step 8(Logout)
		homePage.navigate_To(link2);
		System.out.println("Step 8: Success");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {
			TestUtilities.takeScreenshot(driver, result.getName());
		}
		driver.quit();

	}

}
