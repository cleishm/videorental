package com.thoughtworks;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver buildDriver() throws Exception;
}
