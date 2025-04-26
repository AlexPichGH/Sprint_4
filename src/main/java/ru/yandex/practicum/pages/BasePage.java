package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.util.Config;

public class BasePage {
    // кнопка "принять куки"
    private final By acceptCookiesButton = By.cssSelector("#rcc-confirm-button");

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // принять куки и скрыть нижнюю панель про куки
    public void acceptCookiesIfNotAlready() {
        try {
            driver.findElement(acceptCookiesButton).click();
        } catch (Exception e) {
            // куки были приняты ранее, панель с кнопкой не отобразилась
        }
    }

    // запустить веб-приложение и принять куки
    public void startWebApp() {
        driver.get(Config.SERVICE_URL);
        acceptCookiesIfNotAlready();
    }

    // закрыть драйвер и браузер
    public void closeDriverAndQuitBrowser() {
        driver.quit();
    }
}
