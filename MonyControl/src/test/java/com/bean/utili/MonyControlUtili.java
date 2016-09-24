package com.bean.utili;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.Augmentable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebElement;

import com.bean.automation_dblayer.InPutExcelDataExtractor;
import com.bean.locater_colaction.LocaterOfGainerNSE;
import com.sun.jmx.snmp.Timestamp;
import io.appium.java_client.AppiumDriver;
public class MonyControlUtili extends MonyControlAbstrat{
InPutExcelDataExtractor handelExcel=new InPutExcelDataExtractor();

	private String ExcelPath=PropertyHandler.getProperty("CHECKLISTOFANDROID");
	private String TestCase=PropertyHandler.getProperty("AUTOMATIONTESTCASE");
	public void getHomePageNgage() throws MalformedURLException
	{	
		MonyControlDriver.driver=new FirefoxDriver();
		//MonyControlDriver.driver.navigate().to("http://www.moneycontrol.com/stocks/marketstats/nsegainer/");
		MonyControlDriver.driver.get("http://economictimes.indiatimes.com/marketstats/sort-intraday,sortby-percentchange,sortorder-desc.cms");
	}

	public void getWaitForElementPresent(String elementXpath,int time)
	{ 	
		WebDriverWait wt = new WebDriverWait(MonyControlDriver.driver,time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
	}
	public void getWaitForNamePresent(String Name,int time)
	{	
		WebDriverWait wt = new WebDriverWait(MonyControlDriver.driver, time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
	}
	public void getWaitForIdPresent(String id,int time)
	{		
		WebDriverWait wt = new WebDriverWait(MonyControlDriver.driver,time);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	public void getDroupDown(WebElement element,String value)
	{
		Select select=new Select(element);
		select.selectByVisibleText(value);
	}

	public void getScroolToText(String Scrooltext)
	{
		HashMap scrollObject = new HashMap();
		RemoteWebElement element = (RemoteWebElement)MonyControlDriver.driver.findElement(By.className("android.widget.ListView"));
		JavascriptExecutor js = (JavascriptExecutor)MonyControlDriver.driver;
		String webElementId = ((RemoteWebElement) element).getId();
		scrollObject.put("text", Scrooltext);
		scrollObject.put("element", webElementId);
		js.executeScript("mobile: scrollTo", scrollObject);
	}

	public void getScroolToText(String Xapth,String Scrooltext)
	{
		HashMap scrollObject = new HashMap();
		RemoteWebElement element = (RemoteWebElement)MonyControlDriver.driver.findElement(By.className(Xapth));
		JavascriptExecutor js = (JavascriptExecutor)MonyControlDriver.driver;
		String webElementId = ((RemoteWebElement) element).getId();
		scrollObject.put("text", Scrooltext);
		scrollObject.put("element", webElementId);
		js.executeScript("mobile: scrollTo", scrollObject);
	}

	public void getTakeScreenShot(String testName) 
	{	
		try {
			Timestamp timestamp = new Timestamp(new Date().getTime());
			String filename = PropertyHandler.getProperty("TAKESCREENHOT")+testName+timestamp+".jpg";
			WebDriver augmentedDriver = new Augmenter().augment(MonyControlDriver.driver);
			if (MonyControlDriver.driver.getClass().isAnnotationPresent(Augmentable.class)) 
			{
				MonyControlDriver.driver = (AppiumDriver) new Augmenter().augment(MonyControlDriver.driver);
			} else
			{
				System.out.println("Non augmentable, so augmentation can be performed but does nothing");
				MonyControlDriver.driver = (AppiumDriver) new Augmenter().augment(MonyControlDriver.driver);
			}
			File scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(filename), true);
		} 
	 catch (Exception e) 
		{
			
		}
	}	
	public void getlistPrintCompanyPrint() throws IOException
	{
		LocaterOfGainerNSE locaterOfGainerNSE=loadObject(new LocaterOfGainerNSE());
		for(int i=1;i<60;i++)
		{
			String companyName=MonyControlDriver.driver.findElement(By.xpath(".//*[@id='mc_content']/descendant::tbody/tr["+i+"]/td[1]/span/a")).getText();
			System.out.println("companyName:"+companyName);
			handelExcel.setErrorMessage(0,i+1,1,companyName);
			//if(i>60)
			//{
			//	locaterOfGainerNSE.getMoreButton().click();
			//	webList=MonyControlDriver.driver.findElements(By.xpath(".//*[@id='mc_content']/descendant::tbody/tr/td[1]/span/a"));
			//}
		}
	}

}
