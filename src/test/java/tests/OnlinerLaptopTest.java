package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OnlinerLaptopTest {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void buyAndRemoveLaptopFromCart() {
        String[] laptopName = {""};

        // 1. Открыть главную страницу
        open("https://www.onliner.by/");
        sleep(3000);

        // 2. Принять куки если есть
        if ($(byText("Принять все cookie")).exists()) {
            $(byText("Принять все cookie")).click();
            sleep(1000);
        }

        // 3. Перейти в раздел Ноутбуки
        $("a[href='https://catalog.onliner.by/notebook']").click();
        sleep(5000);

        // 4. Кликнуть на первый ноутбук
        $$(".catalog-form__offers-flex").first()
                .$("a[href*='/notebook/']").click();
        sleep(5000);

        // 5. Получить полное название ноутбука
        laptopName[0] = $("h1.catalog-masthead__title").getText().trim();

        // 6. Перейти к предложениям
        $("a[href*='/prices']").scrollIntoView(true).click();
        sleep(3000);

        // 7. Нажать кнопку 'Купить' у первого продавца
        executeJavaScript("document.querySelector('.offers-list__button_cart.button-style_expletive').click();");
        sleep(3000);

        // 8. Перейти в корзину
        $(byText("Перейти в корзину")).click();
        sleep(5000);

        // 9. Проверить добавление в корзину
        $(".cart-form__description_condensed-other").shouldBe(visible);

        // 10. Удалить товар из корзины
        $(".cart-form__button_remove").click();
        sleep(3000);

        // 11. Проверить сообщение об удалении
        $(".cart-form__description_condensed-extra").shouldHave(text("Вы удалили"));

        System.out.println("🎉 ТЕСТ ПРОЙДЕН! Ноутбук '" + laptopName[0] + "' успешно добавлен и удалён из корзины!");
    }
}




/*
package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static io.qameta.allure.Allure.step;

public class OnlinerLaptopTest {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = false;
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void buyAndRemoveLaptopFromCart() {
        String[] laptopName = {""};

        step("1. Открыть главную страницу", () -> {
            open("https://www.onliner.by/");
            sleep(3000);
        });

        step("2. Принять куки если есть", () -> {
            if ($(byText("Принять все cookie")).exists()) {
                $(byText("Принять все cookie")).click();
                sleep(1000);
            }
        });

        step("3. Перейти в раздел Ноутбуки", () -> {
            $("a[href='https://catalog.onliner.by/notebook']").click();
            sleep(5000);
        });

        step("4. Проверить заголовок", () -> {

                webdriver().shouldHave(urlContaining("catalog.onliner.by/notebook"));
                System.out.println("Страница ноутбуков загружена по URL");

        });


        step("5. Кликнуть на первый ноутбук", () -> {
            // Сохраняем название ноутбука из каталога
            laptopName[0] = $$(".catalog-form__offers-flex").first()
                    .$(".catalog-form__link_primary-additional").getText().trim();

            System.out.println("Название ноутбука на странице каталога: " + laptopName[0]);

            $$(".catalog-form__offers-flex").first()
                    .$("a[href*='/notebook/']").click();

            sleep(5000);
        });

        step("6. Получить полное название ноутбука и перейти к предложениям", () -> {
            webdriver().shouldHave(urlContaining("/notebook/"));

            // Получаем полное название товара со страницы товара
            if ($("h1.catalog-masthead__title").exists()) {
                laptopName[0] = $("h1.catalog-masthead__title").getText().trim();
            } else if ($("h1").exists()) {
                laptopName[0] = $("h1").getText().trim();
            }

            System.out.println("Полное название ноутбука: " + laptopName[0]);

            // Прокручиваем к кнопке с предложениями и нажимаем её
            $("a[href*='/prices']").scrollIntoView(true);
            sleep(1500);

            // Нажимаем на кнопку с предложениями (например: "30 предложений")
            $("a[href*='/prices']").click();
            sleep(3000);

            System.out.println("✅ Перешли к списку предложений");
        });



        step("8. Нажать кнопку 'Купить' у первого продавца", () -> {
            // Ждем загрузки списка предложений
            $(".offers-list").shouldBe(visible);
            sleep(2000);

            // Прокручиваем к первому блоку с предложением
            $$(".offers-list__part_action").first().scrollIntoView(true);
            sleep(1000);

            // Используем JavaScript для клика по кнопке (обходит проблему со скрытыми элементами)
            executeJavaScript("document.querySelector('.offers-list__button_cart.button-style_expletive').click();");

            sleep(3000);
            System.out.println("✅ Кнопка 'Купить' у первого продавца нажата через JavaScript");
        });

        step("9. Перейти в корзину", () -> {
            if ($(byText("Перейти в корзину")).exists()) {
                $(byText("Перейти в корзину")).click();
                System.out.println("✅ Переход в корзину через всплывающее окно");
            } else if ($("a[href*='cart.onliner.by']").exists()) {
                $("a[href*='cart.onliner.by']").click();
                System.out.println("✅ Переход в корзину через иконку в хедере");
            } else {
                open("https://cart.onliner.by");
                System.out.println("✅ Переход в корзину по прямому URL");
            }
            sleep(5000);
        });


        step("10. Проверить добавление в корзину по названию товара", () -> {
            webdriver().shouldHave(urlContaining("cart.onliner.by"));

            // Ищем название товара в корзине
            boolean itemFound = false;

            if ($(".cart-form__description_condensed-other").exists()) {
                String cartText = $(".cart-form__description_condensed-other").getText();
                System.out.println("Текст в корзине: " + cartText);

                if (cartText.contains(laptopName[0].split(" ")[0])) {
                    System.out.println("✅ Товар '" + laptopName[0] + "' найден в корзине");
                    itemFound = true;
                }
            }

            if (!itemFound && $x("//*[contains(text(), '" + laptopName[0].split(" ")[0] + "')]").exists()) {
                System.out.println("✅ Товар найден в корзине по частичному названию");
                itemFound = true;
            }

            if (!itemFound) {
                System.out.println("❌ Товар не найден в корзине");
            }
        });

        step("11. Удалить товар из корзины", () -> {
            $(".cart-form__button_remove").click();
            sleep(3000);
            System.out.println("✅ Товар удален из корзины");
        });

        */
/*step("12. Проверить сообщение об удалении", () -> {
            $(".cart-form__description_primary").shouldHave(text("Вы удалили"));
            System.out.println("✅ Сообщение об удалении отображается");
        });*//*

        step("12. Проверить сообщение об удалении", () -> {
            $(".cart-form__description_condensed-extra")
                    .shouldBe(visible)
                    .shouldHave(text("Вы удалили"));
            System.out.println("✅ Сообщение об удалении отображается");
        });

        step("13. Тест завершен успешно!", () -> {
            System.out.println("🎉 ТЕСТ ПРОЙДЕН! Ноутбук '" + laptopName[0] + "' успешно добавлен и удалён из корзины!");
        });
    }
}*/
