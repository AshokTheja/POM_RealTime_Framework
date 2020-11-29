package com.NA.Test;

import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.util.ArrayList;

import com.NA.Page.EditProfilePage;
import com.NA.Page.HomePage;
import com.NA.Page.LoginPage;
import com.NA.Test_Base.Base_NA;
import com.NA.testutils.TestUtilities;

public class TC_NA_UpdateResume_1_1 extends Base_NA {

	public TC_NA_UpdateResume_1_1() throws IOException {
		super();
	}

	static ArrayList<String> reqDataList;

	LoginPage loginpage;
	HomePage homepage;
	EditProfilePage editprofilepage;

	@BeforeMethod
	public void setUp() throws InterruptedException, IOException {
		TestUtilities data = new TestUtilities();
		reqDataList = data.extractExcelcontentByColumnIndex("TC_NA_UpdateResume_1_1");
	}

	@Test
	public void Delete_Upload_Resume_From_xls() throws InterruptedException, IOException {

		String browser = reqDataList.get(1).trim();
		String url = reqDataList.get(2).trim();
		String email = reqDataList.get(3).trim();
		String password = reqDataList.get(4).trim();
		String link1 = reqDataList.get(5).trim();
		String link2 = reqDataList.get(6).trim();
		String path = reqDataList.get(7).trim();

		// Step 1
		Initialization(browser, url);
		loginpage = new LoginPage();
		System.out.println("Step 1: Success");
		// Step 2(Close all extra windows)
		loginpage.CloseExtraWidnows();
		System.out.println("Step 2: Success");
		// Step 3(login)
		homepage = loginpage.Login(email, password);
		System.out.println("Step 3: Success");
		// Step 4(Navigate to Edit profile)
		editprofilepage = homepage.navigate_To(link1);
		System.out.println("Step 4: Success");
		// Step 5(Delte current resume)
		editprofilepage.deleteResume();
		System.out.println("Step 5: Success");
		// Step 6(Upload new resume)
		editprofilepage.uploadResume(path);
		System.out.println("Step 6: Success");
		// Step 7(Navigate back to home)
		editprofilepage.navigateBacktoHome();
		System.out.println("Step 7: Success");
		// Step 8(Logout)
		homepage.navigate_To(link2);
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
