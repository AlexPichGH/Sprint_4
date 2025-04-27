package ru.yandex.practicum.pages.order_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.BasePage;

public class OrderConfirmationPage extends BasePage {

    // кнопка "Да" для подтверждения заказа
    private final By confirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(@class, 'Button_Middle__1CSJM') and text()='Да']");

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    // подтвердить заказ нажав на кнопку "Да"
    public void confirmPlacingOrder() {
        driver.findElement(confirmOrderButton).click();
    }
}
