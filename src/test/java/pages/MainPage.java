package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement catalogLink = $("a[href='https://catalog.onliner.by/notebook']");
    private final SelenideElement acceptCookiesButton = $(byText("Принять все cookie"));

    public void openMainPage() {
        open("https://www.onliner.by/");
        sleep(3000);
    }

    public void acceptCookies() {
        if (acceptCookiesButton.exists()) {
            acceptCookiesButton.click();
            sleep(1000);
        }
    }

    public void goToLaptopsSection() {
        catalogLink.click();
        sleep(5000);
    }
}