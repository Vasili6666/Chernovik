package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 20000;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    protected void acceptCookiesIfPresent() {
        if ($(byText("Принять все cookie")).exists()) {
            $(byText("Принять все cookie")).click();
            sleep(1000);
        }
    }
}