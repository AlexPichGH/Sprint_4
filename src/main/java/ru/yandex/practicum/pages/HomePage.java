package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.util.Config;

import java.time.Duration;

public class HomePage extends BasePage {

    // все элементы выпадающего списка FAQ
    private final By faqDropdownItems = By.cssSelector(".accordion__button");
    // ответ из выпадающего списка FAQ
    private final By faqAnswerItems = By.cssSelector(".accordion__panel > p");
    // кнопка «Заказать» вверху страницы
    private final By topOrderButton = By.cssSelector(".Button_Button__ra12g");
    // панель с кнопкой «Заказать» внизу страницы
    private final By bottomOrderPanel = By.cssSelector(".Home_FinishButton__1_cWm");
    // кнопка «Заказать» внизу страницы
    private final By bottomOrderButton = By.cssSelector(".Home_FinishButton__1_cWm > button.Button_Button__ra12g");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // прокрутить страницу, чтобы нужный элемент оказался в зоне видимости
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // раскрыть элемент выпадающего списка FAQ
    public void expandFaqDropdownItem(int index) {
        WebElement dropdown = driver.findElements(faqDropdownItems).get(index);
        // ожидание появления элемента с вопросом
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT)).until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();
    }

    // получить текст из элемента ответа выпадающего списка FAQ
    public String getFaqAnswerText(int index) {
        WebElement answer = driver.findElements(faqAnswerItems).get(index);
        scrollToElement(answer);
        // ожидание появления элемента с ответом
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT)).until(ExpectedConditions.visibilityOf(answer));
        return answer.getText();
    }

    // нажать на верхнюю кнопку "Заказать"
    public void clickOnTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    // нажать на нижнюю кнопку "Заказать"
    public void clickOnBottomOrderButton() {
        WebElement bottomButtonParent = driver.findElement(bottomOrderPanel);
        scrollToElement(bottomButtonParent);
        new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(bottomOrderPanel));
        driver.findElement(bottomOrderButton).click();
    }
}
