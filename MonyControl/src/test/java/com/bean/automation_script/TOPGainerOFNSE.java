package com.bean.automation_script;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bean.automation_dblayer.InPutExcelDataExtractor;
import com.bean.locater_colaction.LocaterOfGainerNSE;
import com.bean.utili.MonyControlAbstrat;
import com.bean.utili.MonyControlDriver;
import com.bean.utili.MonyControlUtili;

public class TOPGainerOFNSE extends MonyControlAbstrat {
	private List<WebElement>webList =null;
	MonyControlUtili utili=new MonyControlUtili();
	private LocaterOfGainerNSE locaterOfGainerNSE=null;
	InPutExcelDataExtractor handelExcel=new InPutExcelDataExtractor();
	@BeforeClass
	public void openUrl() throws MalformedURLException
	{
		utili.getHomePageNgage();
		MonyControlDriver.driver.manage().window().maximize();
		System.out.println("full screen start!");
	}
	@BeforeMethod
	public void loadLocater()
	{
		locaterOfGainerNSE=loadObject(new LocaterOfGainerNSE());
	}
	@Test
	public void findAllCompany() throws IOException
	{
		MonyControlDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		locaterOfGainerNSE.getBSEButton().click();
		locaterOfGainerNSE.getAllCompaniesOfBSE().click();
		webList=MonyControlDriver.driver.findElements(By.xpath(".//*[@id='mc_content']/descendant::tbody/tr/td[1]/span/a"));
		WebElement buttionPresnet=MonyControlDriver.driver.findElement(By.xpath("//a[@title='more']"));
		utili.getWaitForElementPresent("//a[@title='more']", 90);
		if(buttionPresnet.isDisplayed())
		{
		utili.getlistPrintCompanyPrint();
		}
		
	}
	@AfterMethod
	public void validat()
	{

	}
	@AfterClass
	public void closeApplication()
	{

	}
	{

	}
}
