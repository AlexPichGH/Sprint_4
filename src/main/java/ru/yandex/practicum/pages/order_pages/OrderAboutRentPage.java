package ru.yandex.practicum.pages.order_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.BasePage;

public class OrderAboutRentPage extends BasePage {

    // поле ввода даты доставки самоката
    private final By deliveryDateSelectionField = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");
    // выпадающий список для выбора срока аренды
    private final By rentalPeriodDropdown = By.cssSelector(".Dropdown-placeholder");
    // список сроков аренды
    private final By rentalPeriods = By.cssSelector(".Dropdown-menu > div");
    // чекбокс выбора черного цвета самоката
    private final By blackScooterColor = By.cssSelector(".Checkbox_Label__3wxSf[for='black']");
    // чекбокс выбора серого цвета самоката
    private final By grayScooterColor = By.cssSelector(".Checkbox_Label__3wxSf[for='gray']");
    // поле ввода комментария
    private final By commentField = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    // кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    public OrderAboutRentPage(WebDriver driver) {
        super(driver);
    }

    // выбрать дату доставки самоката
    public void enterDeliveryDate(String date) {
        driver.findElement(deliveryDateSelectionField).click();
        driver.findElement(deliveryDateSelectionField).sendKeys(date);
        driver.findElement(deliveryDateSelectionField).sendKeys(Keys.ENTER);
    }

    // выбрать срок аренды
    public void selectRentalPeriod(int periodIndex) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElements(rentalPeriods).get(periodIndex).click();
    }

    // выбрать цвет самоката
    public void selectScooterColor(String color) {
        if (color == null) return;
        switch (color) {
            case "black":
                driver.findElement(blackScooterColor).click();
                break;
            case "gray":
                driver.findElement(grayScooterColor).click();
                break;
            default:
                // ни один чекбокс не отмечен
        }
    }

    // ввести в поле комментарий
    public void enterComment(String comment) {
        if (comment == null) return;
        driver.findElement(commentField).sendKeys(comment);
    }

    // заполнить информацию об аренде
    public void enterRentalInformation(String date, int periodIndex, String color, String comment) {
        enterDeliveryDate(date);
        selectRentalPeriod(periodIndex);
        selectScooterColor(color);
        enterComment(comment);
    }

    // нажать на кнопку "Заказать"
    public void clickOnOrderButton() {
        driver.findElement(orderButton).click();
    }
}
