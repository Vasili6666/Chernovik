package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final SelenideElement productTitle = $("h1.catalog-masthead__title");
    private final SelenideElement pricesLink = $("a[href*='/prices']");
    private final SelenideElement buyButton = $(".offers-list__button_cart.button-style_expletive");

    public String getProductTitle() {
        return productTitle.getText().trim();
    }

    public void goToPrices() {
        pricesLink.scrollIntoView(true).click();
        sleep(3000);
    }

    public void addToCartFromFirstSeller() {
        executeJavaScript("document.querySelector('.offers-list__button_cart.button-style_expletive').click();");
        sleep(3000);
    }

    public void goToCart() {
        $(byText("Перейти в корзину")).click();
        sleep(5000);
    }
}