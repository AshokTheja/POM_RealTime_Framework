package com.NA.Test_Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.NA.TestUtils.TestUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_NA {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;

	public Base_NA() throws IOException {

		FileInputStream fis = new FileInputStream("./src/main/java/com/NA/config/config.Properties");
		prop = new Properties();
		prop.load(fis);

	}

	public void Initialization(String browser, String url) throws InterruptedException {
		boolean flag = false;

		try {

			if ((browser).equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("Disable-popup-blocking");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				driver = new ChromeDriver(options);

			} else if ((browser).equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				// For getting specific Profile.
				ProfilesIni profiles = new ProfilesIni();
				FirefoxProfile fxProfile = profiles.getProfile("Automation New");

				// Linking profile to options.
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(fxProfile);
				driver = new FirefoxDriver(options);
			}

			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 10);
			driver.get(url);
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
