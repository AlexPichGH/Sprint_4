package ru.yandex.practicum.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
