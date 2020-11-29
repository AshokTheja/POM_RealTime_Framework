package com.NA.Page;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.NA.Test_Base.Base_NA;
import com.NA.testutils.TestUtilities;

public class EditProfilePage extends Base_NA {

	@FindBy(xpath = "//a[text()='DELETE RESUME']")
	private static WebElement del_Resume_Btn;

	@FindBy(xpath = "(//div[@class='action right-align']//button[text()='DELETE' and @class='waves-effect waves-light btn-large blue-btn'])[2]")
	private static WebElement proceed_Delete;

	@FindBy(xpath = "//span[text()='90%']")
	private static WebElement expected_90;

	@FindBy(xpath = "//span[text()='100%']")
	private static WebElement expected_100;

	@FindBy(xpath = "//input[@class='fileUpload waves-effect waves-light btn-large' and @id='attachCV']")
	private static WebElement upload_Resume;

	@FindBy(xpath = "//img[@src='https://static.naukimg.com/s/4/100/i/naukri_Logo.png']")
	private static WebElement home_Image;

	@FindBy(xpath = "//span[text()='Resume Headline']/following-sibling::span[@class='edit icon']")
	private static WebElement resume_HeadLine_EditBtn;

	@FindBy(xpath = "//span[text()='Profile Summary']/following-sibling::span[@class='edit icon']")
	private static WebElement profile_Summary_EditBtn;

	@FindBy(css = "#resumeHeadlineTxt")
	private static WebElement resume_Headline_Txt;

	@FindBy(css = "#profileSummaryTxt")
	private static WebElement profile_Summary_Txt;

	@FindBy(xpath = "//div[@class='action s12']//button[text()='Save']")
	private static WebElement text_SaveBtn;

	public EditProfilePage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void deleteResume() throws InterruptedException {
		boolean flag = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(del_Resume_Btn));
			del_Resume_Btn.click();
			TestUtilities.sync(2000);
			proceed_Delete.click();
			driver.navigate().refresh();
			TestUtilities.sync(5000);
			try {
				Assert.assertSame(expected_90.isDisplayed(), true);
				flag = true;
			} catch (AssertionError e) {
				flag = false;
			}
		} finally {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}
	}

	public void uploadResume(String resumePath) throws InterruptedException {
		boolean flag = false;
		try {
			upload_Resume.sendKeys(resumePath);
			TestUtilities.sync(5000);
			driver.navigate().refresh();
			TestUtilities.sync(5000);
			try {
				Assert.assertSame(expected_100.isDisplayed(), true);
				flag = true;
			} catch (AssertionError e) {
				flag = false;
			}
		} finally {

			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}

	}

	public HomePage navigateBacktoHome() throws IOException, InterruptedException {
		boolean flag = false;
		try {
			home_Image.click();
			flag = true;
		} finally {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}
		TestUtilities.sync(8000);
		return new HomePage();
	}

	public void editResume_HeadLine(String new_resumeHeadLine) throws InterruptedException {
		boolean flag = false;
		try {
			TestUtilities.jsExecutor_ScrollIntoView(driver, resume_HeadLine_EditBtn);
			TestUtilities.sync(2000);
			resume_HeadLine_EditBtn.click();
			wait.until(ExpectedConditions.elementToBeClickable(resume_Headline_Txt)).clear();
			resume_Headline_Txt.sendKeys(new_resumeHeadLine);
			text_SaveBtn.click();

			flag = true;
		} finally {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}

	}

	public void edit_ProfileSummary(String new_profileSummary) throws InterruptedException {
		boolean flag = false;
		try {
			TestUtilities.jsExecutor_ScrollIntoView(driver, profile_Summary_EditBtn);
			TestUtilities.sync(2000);
			profile_Summary_EditBtn.click();
			wait.until(ExpectedConditions.elementToBeClickable(profile_Summary_Txt)).clear();
			profile_Summary_Txt.sendKeys(new_profileSummary);
			text_SaveBtn.click();

			flag = true;
		} finally {
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			String className = new Object() {
			}.getClass().getName();
			TestUtilities.printPassorFail(methodName, className, flag);
		}

	}
}
