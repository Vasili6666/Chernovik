package mobile.sql.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class VideoAttach {

    @Attachment(value = "Video recording", type = "video/mp4")
    public static byte[] attachVideo() {
        try {
            AndroidDriver driver = (AndroidDriver) getWebDriver();

            String videoBase64 = driver.stopRecordingScreen();

            if (videoBase64 == null || videoBase64.isEmpty()) {

                return new byte[0];
            }

            byte[] videoData = java.util.Base64.getDecoder().decode(videoBase64);

            if (videoData.length == 0) {

                return new byte[0];
            }


            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            saveVideoToFile(videoData, "test-video-" + timestamp);
            return videoData;

        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static void saveVideoToFile(byte[] videoData, String fileName) {
        try {
            Path videoPath = Paths.get("build/videos/" + fileName + ".mp4");
            Files.createDirectories(videoPath.getParent());
            Files.write(videoPath, videoData);
            System.out.println("Видео сохранено в: " + videoPath.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Не удалось сохранить видео в файл: " + e.getMessage());
        }
    }
}