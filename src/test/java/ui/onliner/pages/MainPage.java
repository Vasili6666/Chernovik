package ui.onliner.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement catalogLink = $("a[href='https://catalog.onliner.by/notebook']");
    private final SelenideElement acceptCookiesButton = $(byText("Принять все cookie"));

    @Step("Открыть главную страницу Onliner.by")
    public void openMainPage() {
        open("https://www.onliner.by/");
        sleep(3000);
    }

    @Step("Принять cookies если отображаются")
    public void acceptCookies() {
        if (acceptCookiesButton.exists()) {
            acceptCookiesButton.click();
            sleep(1000);
        }
    }

    @Step("Перейти в раздел 'Ноутбуки'")
    public void goToLaptopsSection() {
        catalogLink.click();
        sleep(5000);
    }
}