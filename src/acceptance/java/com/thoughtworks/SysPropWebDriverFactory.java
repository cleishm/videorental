package com.thoughtworks;

import java.lang.Class;
import java.lang.ClassLoader;
import org.openqa.selenium.WebDriver;

public class SysPropWebDriverFactory implements WebDriverFactory {
    private static final String CLASS_NAME = System.getProperty(
    		"webdriver.class", "org.openqa.selenium.htmlunit.HtmlUnitDriver");

    @SuppressWarnings("unchecked")
	public WebDriver buildDriver() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class clazz = classLoader.loadClass(CLASS_NAME);
        return (WebDriver)clazz.newInstance();
    }

}
