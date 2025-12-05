package ui.onliner.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import ui.onliner.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() throws Exception {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://onliner.by");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "128.0");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        // Исправление: правильно проверяем пустую строку
        String remoteParam = System.getProperty("remoteUrl");
        if (remoteParam != null && !remoteParam.trim().isEmpty()) {
            Configuration.remote = remoteParam;
        } else {
            Configuration.remote = getLocalUrl();
        }

        setupSelenoidCapabilities();

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    private static String getLocalUrl() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("local.properties"));

        return "https://" +
                props.getProperty("SELENOID_USER") + ":" +
                props.getProperty("SELENOID_PASSWORD") + "@" +
                props.getProperty("SELENOID_HOST") + "/wd/hub";
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