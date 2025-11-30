package ui.onliner.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement productTitle = $("h1.catalog-masthead__title");
    private final SelenideElement pricesLink = $("a[href*='/prices']");
    private final SelenideElement buyButton = $(".offers-list__button_cart.button-style_expletive");
    private final SelenideElement goToCartButton = $(byText("Перейти в корзину"));

    @Step("Получить полное название товара")
    public String getProductTitle() {
        return productTitle.getText().trim();
    }

    @Step("Перейти к предложениям продавцов")
    public void goToPrices() {
        pricesLink.scrollIntoView(true).click();
        sleep(3000);
    }

    @Step("Добавить товар в корзину у первого продавца")
    public void addToCartFromFirstSeller() {
        executeJavaScript("document.querySelector('.offers-list__button_cart.button-style_expletive').click();");
        sleep(3000);
    }

    @Step("Перейти в корзину")
    public void goToCart() {
        goToCartButton.click();
        sleep(5000);
    }


}