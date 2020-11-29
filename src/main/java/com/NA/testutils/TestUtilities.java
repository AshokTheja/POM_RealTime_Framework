package com.NA.testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestUtilities {

	@SuppressWarnings({ "resource", "incomplete-switch", "rawtypes", "unused" })
	public ArrayList<String> extractExcelcontentByColumnIndex(String status) {
		ArrayList<String> columnData = null;
		int rowNum = 0;
		int columnIndex = 0;
		String cell_Value_Text = null;

		try {

			File f = new File("./src/main/java/com/NA/TestData/TestData_NA.xlsx");
			FileInputStream fis = new FileInputStream(f);
			XSSFWorkbook wbk = new XSSFWorkbook(fis);
			XSSFSheet sht = wbk.getSheetAt(0);
			Iterator<Row> rowIterator = sht.iterator();
			columnData = new ArrayList<String>();
			DataFormatter df = new DataFormatter();
			while (rowIterator.hasNext()) {
				Row row1 = rowIterator.next();
				Iterator<Cell> cells = row1.cellIterator();
				while (cells.hasNext()) {
					Cell cell1 = cells.next();
					if (cell1.getColumnIndex() == columnIndex) {
						switch (cell1.getCellType()) {

						case STRING:
							String cell_Value = cell1.getStringCellValue();

							if (cell_Value.equals(status)) {
								rowNum = row1.getRowNum();
								columnIndex = cell1.getColumnIndex();
								cell_Value_Text = cell_Value;
								System.out.println(cell_Value);
								break;
							}

						}
					}
				}
			}
			Row rowNumberforStatus = sht.getRow(rowNum);
			Cell columnValue = rowNumberforStatus.getCell(columnIndex);
			// System.out.println(rowNum + "==========" + columnIndex + "=========" +
			// cell_Value_Text);

			String statusText = df.formatCellValue(columnValue);
			if (statusText.toLowerCase().replaceAll("\\s", "")
					.equals(status.toLowerCase().replaceAll("\\s", "")) == true) {
				Iterator rows = sht.rowIterator();
				while (rows.hasNext()) {
					Row row = (Row) rows.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (row.getRowNum() == rowNum) {
							if (cell.getColumnIndex() >= columnIndex) {
								switch (cell.getCellType()) {
								case NUMERIC:
									columnData.add(cell.getNumericCellValue() + "");
									break;

								case STRING:
									String cell_Value = cell.getStringCellValue();
									if (cell_Value.contains("CR:")) {
										boolean stat = true;
									} else
										columnData.add(cell_Value);
									break;

								}
							}
						}
					}
				}
			} else {
				System.out.println(
						"Actual status: " + status + " and Expected Status: " + statusText + " are not matched.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return columnData;

	}

	public static void takeScreenshot(WebDriver driver, String screenshotname) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./Screenshots/ " + screenshotname + ".png"));
		System.out.println("Screenshot taken");
	}

	public static void printPassorFail(String methodName, String className, boolean flag) {

		if (className.contains("$")) {
			className = className.substring(0, className.length() - 2);
		}

		if (flag) {
			System.out.println(methodName + ": step from class: " + className + ": was success.");
		} else {
			System.out.println(methodName + ": step from class: " + className + ": failed.");
		}
	}

	public static void jsExecutor_ScrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);

	}

	public static void sync(int millis) throws InterruptedException {
		Thread.sleep(millis);
	}

}
