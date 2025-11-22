package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {
        // Базовые настройки
        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.onliner.by");
        Configuration.browser = "chrome";
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.timeout = 15000;

        // НАСТРОЙКА SELENOID (только если указан remoteUrl)
        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
            Configuration.browserVersion = System.getProperty("browserVersion", "128");

            // Capabilities только для Selenoid
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        // Видео только для Selenoid
        if (Configuration.remote != null) {
            Attach.addVideo();
        }

        closeWebDriver();
    }
}