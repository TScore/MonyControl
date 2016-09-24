package com.bean.locater_colaction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocaterOfGainerNSE 
{
	@FindBy(xpath="//button[@class='pg_num']")
	private WebElement totalNumberCompany;

	@FindBy(xpath="//p[text()='BSE']")
	private WebElement BSEButton;

	@FindBy(xpath="//div[p[text()='BSE']]/descendant::a[text()='All Companies']")
	private WebElement allCompaniesOfBSE;
	
	@FindBy(xpath="//a[@title='more']")
	private WebElement moreButton;

	@FindBy(xpath="//button[@data-tooltip='Next']")
	private WebElement nextButton;
	
	
	
	public WebElement getNextButton() {
		return nextButton;
	}
	public WebElement getAllCompaniesOfBSE() {
		return allCompaniesOfBSE;
	}
	public WebElement getBSEButton() {
		return BSEButton;
	}
	public WebElement getMoreButton() {
		return moreButton;
	}
	public WebElement getTotalNumberCompany() {
		return totalNumberCompany;
	}


}
