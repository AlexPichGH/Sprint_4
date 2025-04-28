package ru.yandex.practicum.pages.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.BasePage;

public class OrderSuccessfullyPlacedPage extends BasePage {

    // текст "Номер заказа" во всплывающем окне об успешном оформлении заказа
    private final By orderNumberText = By.xpath(".//div[@class='Order_Text__2broi' and contains(text(), 'Номер заказа')]");

    public OrderSuccessfullyPlacedPage(WebDriver driver) {
        super(driver);
    }

    // проверка отображения всплывающего окна об успешном оформлении заказа
    public boolean isOrderPlacedSuccessfully() {
        return driver.findElement(orderNumberText).isDisplayed();
    }
}
