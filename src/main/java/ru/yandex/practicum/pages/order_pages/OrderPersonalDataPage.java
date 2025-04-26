package ru.yandex.practicum.pages.order_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.BasePage;
import ru.yandex.practicum.util.Config;

import java.time.Duration;

public class OrderPersonalDataPage extends BasePage {

    // поле ввода имени
    private final By firstNameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    // поле ввода фамилии
    private final By lastNameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    // поле ввода адреса
    private final By addressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // поле выбора станции метро
    private final By metroStationField = By.cssSelector(".select-search__input");
    // поле ввода номера телефона
    private final By phoneField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // кнопка "Далее"
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");

    public OrderPersonalDataPage(WebDriver driver) {
        super(driver);
    }

    // ввести в поле имя
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    // ввести в поле фамилию
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    // ввести в поле адрес
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // выбрать станцию метро
    public void selectMetroStation(int stationIndex) {
        // нажать на поле станции метро
        driver.findElement(metroStationField).click();
        By station = By.cssSelector(String.format(".select-search__row[data-index='%d']", stationIndex));
        // ожидания открытия списка станций
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(station));
        // выбрать станцию из списка
        driver.findElement(station).click();
    }

    // ввести в поле номер телефона
    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneField).sendKeys(phoneNumber);
    }

    // заполнить персональные данные для заказа
    public void enterPersonalData(String firstName, String lastName, String address, int stationIndex, String phoneNumber) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        selectMetroStation(stationIndex);
        enterPhoneNumber(phoneNumber);
    }

    // нажатие на кнопку "Далее"
    public void clickOnNextButton() {
        driver.findElement(nextButton).click();
    }
}
