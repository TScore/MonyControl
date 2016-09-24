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
import com.bean.utili.MonyControlDriver;
import com.bean.utili.MonyControlUtili;

public class TOPGainerOFNSE_ET extends MonyControlUtili
{
	private List<WebElement>webList =null;
	MonyControlUtili utili=new MonyControlUtili();
	private LocaterOfGainerNSE locaterOfGainerNSE=null;
	InPutExcelDataExtractor handelExcel=new InPutExcelDataExtractor();
	private String nameOfCompnay=null;
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
	{try{
		MonyControlDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//locaterOfGainerNSE.getBSEButton().click();
		//locaterOfGainerNSE.getAllCompaniesOfBSE().click();
		//webList=MonyControlDriver.driver.findElements(By.xpath(".//*[@id='mc_content']/descendant::tbody/tr/td[1]/span/a"));
		//WebElement buttionPresnet=MonyControlDriver.driver.findElement(By.xpath("//a[@title='more']"));
		//utili.getWaitForElementPresent("//a[@title='more']", 90);
		//if(buttionPresnet.isDisplayed())
		//{
		//utili.getlistPrintCompanyPrint();
		//}
		MonyControlDriver.driver.findElement(By.xpath("//button[@data-tooltip='Refresh']")).click();
		MonyControlDriver.driver.findElement(By.xpath("//button[@data-tooltip='Refresh']")).click();
		utili.getWaitForElementPresent("//button[@class='pg_num']", 90);
		String totalNumber=locaterOfGainerNSE.getTotalNumberCompany().getText();
		//1-25 of 560
		System.out.println("totalNumber1:"+totalNumber);
		totalNumber=totalNumber.replace("1-25 of ","");
		System.out.println("totalNumber2:"+totalNumber);
		int companyNumber = Integer.parseInt(totalNumber);
		System.out.println("totalNumber:"+companyNumber);
		int totalCompanyNumber=companyNumber/25;
		System.out.println("totalCompanyNumber:"+totalCompanyNumber);
		int k=0;
		for(int j=1;j<=totalCompanyNumber;j++)
		{
			
			for(int i=1;i<25;i++)
			{
				k++;
				nameOfCompnay=MonyControlDriver.driver.findElement(By.xpath(".//*[@id='dataDisplay']/div[2]/div["+i+"]/ul/li[1]/p/a")).getText();
				System.out.println("nameOfCompnay:"+nameOfCompnay);
				String stockValueOFCompany=MonyControlDriver.driver.findElement(By.xpath(".//*[@id='dataDisplay']/div[2]/div["+i+"]/ul/li[2]/span")).getText();
				System.out.println("stockValueOFCompany:"+stockValueOFCompany);
				String changeOfStock=MonyControlDriver.driver.findElement(By.xpath(".//*[@id='dataDisplay']/div[2]/div["+i+"]/ul/li[3]/span")).getText();
				System.out.println("changeOfStock:"+changeOfStock);
				String volOfStock=MonyControlDriver.driver.findElement(By.xpath(".//*[@id='dataDisplay']/div[2]/div["+i+"]/ul/li[5]/span")).getText();
				System.out.println("volOfStock:"+volOfStock);
				handelExcel.setErrorMessage(0,k+1,1,nameOfCompnay);
				handelExcel.setExcelStringData(0,k+1,2,stockValueOFCompany);
				handelExcel.setExcelStringData(0,k+1,3,changeOfStock);
				handelExcel.setExcelStringData(0,k+1,4,volOfStock);
			}
			
			locaterOfGainerNSE.getNextButton().click();
		}
		}catch(Exception e)
	{
			e.printStackTrace();
	}
	}
	@AfterMethod
	public void validat()
	{

	}
	@AfterClass
	public void closeApplication()
	{
		MonyControlDriver.driver.quit();
	}
	{

	}

}
