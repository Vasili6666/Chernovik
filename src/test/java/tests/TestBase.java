package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        // ТВОИ СУЩЕСТВУЮЩИЕ НАСТРОЙКИ (не трогай их)
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.onliner.by");
        Configuration.timeout = 15000;

        // ДОБАВЬ ЭТО ДЛЯ SELENOID ↓
        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
            Configuration.browserVersion = "128"; // добавляем версию браузера

            // Простая настройка без сложных Map
            Configuration.browserCapabilities = new DesiredCapabilities();
            Configuration.browserCapabilities.setCapability("enableVNC", true);
            Configuration.browserCapabilities.setCapability("enableVideo", true);
        }
        // КОНЕЦ ДОБАВЛЕНИЯ ↑

        // ТВОЙ СУЩЕСТВУЮЩИЙ КОД
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        // Добавляем видео только для удаленных запусков
        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Attach.addVideo();
        }

        closeWebDriver();
    }
}