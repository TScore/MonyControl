package com.bean.utili;

import org.openqa.selenium.support.PageFactory;

public class MonyControlAbstrat {
	@SuppressWarnings("unchecked")
	public <T> T loadObject(T t)
	{
		return (T) PageFactory.initElements(MonyControlDriver.driver, t.getClass());
	}
}
