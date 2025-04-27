package ru.yandex.practicum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pages.HomePage;
import ru.yandex.practicum.pages.order_pages.OrderAboutRentPage;
import ru.yandex.practicum.pages.order_pages.OrderConfirmationPage;
import ru.yandex.practicum.pages.order_pages.OrderPersonalDataPage;
import ru.yandex.practicum.pages.order_pages.OrderSuccessfullyPlacedPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int stationIndex;
    private final String phoneNumber;
    private final String deliveryDate;
    private final int rentPeriodIndex;
    private final String scooterColor;
    private final String comment;

    public OrderTest(
            String firstName, String lastName, String address, int stationIndex, String phoneNumber,
            String deliveryDate, int rentPeriodIndex, String scooterColor, String comment
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.stationIndex = stationIndex;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentPeriodIndex = rentPeriodIndex;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    // тестовые данные
    @Parameterized.Parameters
    public static Object[][] getOrderTestData() {
        return new Object[][]{
                {"Антонин", "Долохов", "Москва, Красная площадь, 9", 9, "+74956235527", "05.05.2025", 1, "black", null},
                {"Пи", "Па", "Москва, ул. Маросейка, 2", 89, "+79032854004", "30.05.2025", 4, null, "Оставьте у входа"},
        };
    }

    // проверка позитивного сценария размещения заказа
    public void checkOrderPlacedSuccessfully() {
        OrderPersonalDataPage personalDataPage = new OrderPersonalDataPage(getDriver());
        personalDataPage.enterPersonalData(firstName, lastName, address, stationIndex, phoneNumber);
        personalDataPage.clickOnNextButton();
        OrderAboutRentPage aboutRentPage = new OrderAboutRentPage(getDriver());
        aboutRentPage.enterRentalInformation(deliveryDate, rentPeriodIndex, scooterColor, comment);
        aboutRentPage.clickOnOrderButton();
        OrderConfirmationPage confirmationPage = new OrderConfirmationPage(getDriver());
        confirmationPage.confirmPlacingOrder();
        OrderSuccessfullyPlacedPage orderPlacedPage = new OrderSuccessfullyPlacedPage(getDriver());
        Assert.assertTrue(
                "Не отобразилось всплывающее окно об успешном оформлении заказа",
                orderPlacedPage.isOrderPlacedSuccessfully()
        );
    }

    // проверка позитивного сценария размещения заказа при нажатии на верхнюю кнопку "Заказать"
    @Test
    public void checkOrderPlacedSuccessfullyFromTopOrderButton() {
        new HomePage(getDriver()).clickOnTopOrderButton();
        checkOrderPlacedSuccessfully();
    }

    // проверка позитивного сценария размещения заказа при нажатии на нижнюю кнопку "Заказать"
    @Test
    public void checkOrderPlacedSuccessfullyFromBottomOrderButton() {
        new HomePage(getDriver()).clickOnBottomOrderButton();
        checkOrderPlacedSuccessfully();
    }
}
