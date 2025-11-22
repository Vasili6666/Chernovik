package ui.onliner.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    private final ElementsCollection laptopItems = $$(".catalog-form__offers-flex");
    private final SelenideElement firstLaptopLink = $$(".catalog-form__offers-flex").first()
            .$("a[href*='/notebook/']");

    @Step("Выбрать первый ноутбук в списке")
    public void selectFirstLaptop() {
        firstLaptopLink.click();
        sleep(5000);
    }

    @Step("Выбрать ноутбук по индексу {index}")
    public void selectLaptopByIndex(int index) {
        laptopItems.get(index).$("a[href*='/notebook/']").click();
        sleep(5000);
    }

    @Step("Получить количество ноутбуков на странице")
    public int getLaptopsCount() {
        return laptopItems.size();
    }
}