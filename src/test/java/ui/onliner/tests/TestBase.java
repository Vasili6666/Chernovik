package ui.onliner.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import ui.onliner.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() {
        // Получаем конфигурацию из системных свойств
        Configuration.baseUrl = System.getProperty("baseUrl", "https://onliner.by");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        // ИСПРАВЛЕНИЕ: правильно получаем remoteUrl
        String remoteUrl = System.getProperty("remoteUrl");

        if (remoteUrl == null || remoteUrl.trim().isEmpty()) {
            // Если параметр не передан или пустой - используем дефолтный Selenoid
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        } else {
            // Если параметр передан - используем его
            Configuration.remote = remoteUrl;
        }

        System.out.println("🌐 Using remote URL: " + Configuration.remote);

        // Всегда настраиваем Selenoid capabilities
        setupSelenoidCapabilities();

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    private static void setupSelenoidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", selenoidOptions);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachment() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}