package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import helpers.CustomAllureListener;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = getProperty("url", "https://onliner.by");

        Configuration.browser = getProperty("browser", "chrome");
        Configuration.browserSize = getProperty("windowSize", "1920x1080");
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        // Настройка RestAssured
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.filters(CustomAllureListener.withCustomTemplates());

        System.setProperty("org.aspectj.weaver.loadtime.configuration", "ajcore-disable");
        System.setProperty("aspectj.dump", "none");

        String remoteUrl = getProperty("remoteUrl", null);
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;
            setupSelenoidCapabilities();
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        logConfiguration();
    }

    private static void setupSelenoidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", selenoidOptions);
        Configuration.browserCapabilities = capabilities;
    }

    private static String getProperty(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    private static void logConfiguration() {
        System.out.println("=== КОНФИГУРАЦИЯ ТЕСТОВ ===");
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Browser Size: " + Configuration.browserSize);
        System.out.println("Base URL: " + Configuration.baseUrl);
        System.out.println("Remote: " + Configuration.remote);
        System.out.println("===========================");
    }

    @AfterEach
    void addAttachment() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        if (Configuration.remote != null) {
            Attach.addVideo();
        }

        closeWebDriver();
    }
}
