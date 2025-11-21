package tests;

import pages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlinerLaptopTest extends TestBase {

    @Test
    void buyAndRemoveLaptopFromCart() {
        // Инициализация Page Objects
        MainPage mainPage = new MainPage();
        CatalogPage catalogPage = new CatalogPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        String laptopName = "";

        // 1. Открыть главную страницу и принять куки
        mainPage.openMainPage();
        mainPage.acceptCookies();

        // 2. Перейти в раздел Ноутбуки
        mainPage.goToLaptopsSection();

        // 3. Выбрать первый ноутбук
        catalogPage.selectFirstLaptop();

        // 4. Получить название ноутбука
        laptopName = productPage.getProductTitle();

        // 5. Перейти к предложениям и добавить в корзину
        productPage.goToPrices();
        productPage.addToCartFromFirstSeller();

        // 6. Перейти в корзину
        productPage.goToCart();

        // 7. Проверить добавление и удалить товар
        cartPage.verifyProductAdded();
        cartPage.removeProduct();
        cartPage.verifyProductRemoved();

        System.out.println("🎉 ТЕСТ ПРОЙДЕН! Ноутбук '" + laptopName + "' успешно добавлен и удалён из корзины!");

        // Дополнительная проверка
        assertTrue(cartPage.getRemovalMessage().contains("Вы удалили"),
                "Сообщение об удалении не отображается корректно");
    }
}