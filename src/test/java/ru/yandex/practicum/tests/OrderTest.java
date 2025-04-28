package ru.yandex.practicum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pages.HomePage;
import ru.yandex.practicum.pages.order.OrderAboutRentPage;
import ru.yandex.practicum.pages.order.OrderConfirmationPage;
import ru.yandex.practicum.pages.order.OrderPersonalDataPage;
import ru.yandex.practicum.pages.order.OrderSuccessfullyPlacedPage;
import ru.yandex.practicum.tests.data.OrderTestData;

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
    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3} {4} {5} {6} {7} {8}")
    public static Object[][] getOrderTestData() {
        return OrderTestData.ORDER_TEST_DATA;
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
    public void checkOrderPlacedSuccessfullyFromTopOrderButtonTest() {
        new HomePage(getDriver()).clickOnTopOrderButton();
        checkOrderPlacedSuccessfully();
    }

    // проверка позитивного сценария размещения заказа при нажатии на нижнюю кнопку "Заказать"
    @Test
    public void checkOrderPlacedSuccessfullyFromBottomOrderButtonTest() {
        new HomePage(getDriver()).clickOnBottomOrderButton();
        checkOrderPlacedSuccessfully();
    }
}
