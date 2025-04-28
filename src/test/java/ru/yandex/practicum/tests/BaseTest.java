package ru.yandex.practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.pages.BasePage;
import ru.yandex.practicum.util.Config;

import java.time.Duration;

public abstract class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        initWebDriver();
        new BasePage(driver).startWebApp();
    }

    private void initWebDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.TIMEOUT));
    }

    @After
    public void tearDown() {
        new BasePage(driver).closeDriverAndQuitBrowser();
    }
}
