package ru.yandex.practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.util.Config;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        initWebDriver();
        driver.get(Config.SERVICE_URL);
    }

    private void initWebDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
