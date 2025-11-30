package mobile.sql.drivers;

import com.codeborne.selenide.WebDriverProvider;
import mobile.sql.config.EmulationConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulationDriver implements WebDriverProvider {

    private static final EmulationConfig config = ConfigFactory.create(EmulationConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android")
                .setDeviceName(config.deviceName())
                .setPlatformVersion(config.osVersion())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity());


        try {
            return new AndroidDriver(new URL(config.appiumServerUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(" Ошибка создания URL для Appium сервера", e);
        }
    }
}