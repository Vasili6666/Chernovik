package ui.onliner.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.onliner.pages.*;

import static io.qameta.allure.Allure.step;

@Epic("Onliner.by E-commerce")
@Feature("Корзина покупок")
@Story("Добавление и удаление товаров из корзины")
@Tag("ui")

public class OnlinerLaptopTest extends TestBase {

    private String laptopName = "";

    @Test
    @DisplayName("Добавление и удаление ноутбука из корзины")
    @Description("Тест проверяет полный цикл: выбор ноутбука, добавление в корзину и удаление")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    @Link(name = "Onliner.by", url = "https://www.onliner.by")
    void buyAndRemoveLaptopFromCart() {
        MainPage mainPage = new MainPage();
        CatalogPage catalogPage = new CatalogPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        step("1. Открыть главную страницу Onliner.by", () -> {
            mainPage.openMainPage();
        });

        step("2. Принять cookies если отображаются", () -> {
            mainPage.acceptCookies();
        });

        step("3. Перейти в раздел 'Ноутбуки'", () -> {
            mainPage.goToLaptopsSection();
        });

        step("4. Выбрать первый ноутбук в списке", () -> {
            catalogPage.selectFirstLaptop();
        });

        step("5. Получить полное название ноутбука", () -> {
            laptopName = productPage.getProductTitle();
            Allure.addAttachment("Название ноутбука", "text/plain", laptopName);
        });

        step("6. Перейти к предложениям продавцов", () -> {
            productPage.goToPrices();
        });

        step("7. Добавить ноутбук в корзину у первого продавца", () -> {
            productPage.addToCartFromFirstSeller();
        });

        step("8. Перейти в корзину для проверки", () -> {
            productPage.goToCart();
        });

        step("9. Проверить что ноутбук добавлен в корзину", () -> {
            cartPage.verifyProductAdded();
        });

        step("10. Удалить ноутбук из корзины", () -> {
            cartPage.removeProduct();
        });

        step("11. Проверить сообщение об успешном удалении", () -> {
            cartPage.verifyProductRemoved();
        });

        step("12. Завершение теста", () -> {
            Allure.addAttachment("Результат теста", "text/plain",
                    "✅ ТЕСТ ПРОЙДЕН! Ноутбук '" + laptopName + "' успешно добавлен и удалён из корзины!");
        });
    }
}