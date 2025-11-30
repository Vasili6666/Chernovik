package mobile.sql.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import mobile.sql.drivers.EmulationDriver;
import mobile.sql.helpers.Attach;
import mobile.sql.helpers.VideoAttach;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MobileTestBase {

    @BeforeAll
    static void setUp() {
        Configuration.browser = EmulationDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 60000;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        System.out.println("🚀 Настройка тестового окружения...");
    }

    @BeforeEach
    void startDriver() {
        System.out.println("🎬 Инициализация драйвера...");
        open();


        startVideoRecording();
    }

    @AfterEach
    void addAttachments() {
        System.out.println("📸 Сбор вложений для отчета...");


        VideoAttach.attachVideo();

        Attach.screenshotAs("Final screenshot");
        Attach.pageSource();
        closeWebDriver();
    }

    private void startVideoRecording() {
        try {
            AndroidDriver driver = (AndroidDriver) getWebDriver();
            driver.startRecordingScreen();
            System.out.println("🎥 Запись видео запущена");
        } catch (Exception e) {
            System.out.println("⚠️ Не удалось запустить запись видео: " + e.getMessage());
        }
    }
}