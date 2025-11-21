package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CartPage {

    private final SelenideElement productDescription = $(".cart-form__description_condensed-other");
    private final SelenideElement removeButton = $(".cart-form__button_remove");
    private final SelenideElement removalMessage = $(".cart-form__description_condensed-extra");

    public void verifyProductAdded() {
        productDescription.shouldBe(visible);
    }

    public void removeProduct() {
        removeButton.click();
        sleep(3000);
    }

    public void verifyProductRemoved() {
        removalMessage.shouldHave(text("Вы удалили"));
    }

    public String getRemovalMessage() {
        return removalMessage.getText();
    }
}