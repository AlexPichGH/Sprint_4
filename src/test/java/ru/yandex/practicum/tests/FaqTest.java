package ru.yandex.practicum.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pages.HomePage;
import ru.yandex.practicum.tests.data.FaqTestData;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    private final int questionIndex;
    private final String expectedAnswer;

    public FaqTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    // тестовые данные
    @Parameterized.Parameters(name = "Тестовые данные: {0} -> {1}")
    public static Object[][] getFaqTestData() {
        return FaqTestData.FAQ_TEST_DATA;
    }

    // проверка соответствует ли текст ответа вопросу из выпадающего списка FAQ
    @Test
    public void checkAnswerMatchesExpectedTextTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.expandFaqDropdownItem(questionIndex);
        Assert.assertEquals(
                "Текст ответа не соответствует ожидаемому.",
                expectedAnswer,
                homePage.getFaqAnswerText(questionIndex)
        );
    }
}
