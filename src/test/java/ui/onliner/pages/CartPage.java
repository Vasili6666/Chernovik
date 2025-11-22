package ui.onliner.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CartPage {

    private final SelenideElement productDescription = $(".cart-form__description_condensed-other");
    private final SelenideElement removeButton = $(".cart-form__button_remove");
    private final SelenideElement removalMessage = $(".cart-form__description_condensed-extra");
    private final SelenideElement cartTitle = $(".cart-form__title");

    @Step("Проверить что корзина загружена")
    public void verifyCartPageLoaded() {
        cartTitle.shouldBe(visible);
    }

    @Step("Проверить что товар добавлен в корзину")
    public void verifyProductAdded() {
        productDescription.shouldBe(visible);
    }

    @Step("Удалить товар из корзины")
    public void removeProduct() {
        removeButton.click();
        sleep(3000);
    }

    @Step("Проверить сообщение об удалении товара")
    public void verifyProductRemoved() {
        removalMessage.shouldHave(text("Вы удалили"));
    }

    @Step("Получить текст сообщения об удалении")
    public String getRemovalMessage() {
        return removalMessage.getText();
    }

    @Step("Получить название товара в корзине")
    public String getProductNameInCart() {
        return productDescription.getText();
    }
}