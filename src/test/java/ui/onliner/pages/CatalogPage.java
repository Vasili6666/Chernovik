package ui.onliner.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {


    private final SelenideElement firstLaptopLink = $$(".catalog-form__offers-flex").first()
            .$("a[href*='/notebook/']");

    @Step("Выбрать первый ноутбук в списке")
    public void selectFirstLaptop() {
        firstLaptopLink.click();
        sleep(5000);
    }


}