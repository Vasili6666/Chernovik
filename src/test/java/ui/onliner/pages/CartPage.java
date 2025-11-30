package ui.onliner.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CartPage {

    private final SelenideElement productDescription = $(".cart-form__description_condensed-other");
    private final SelenideElement removeButton = $(".cart-form__button_remove");
    private final SelenideElement removalMessage = $(".cart-form__description_condensed-extra");

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


}