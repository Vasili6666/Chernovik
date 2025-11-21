package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {

    private final ElementsCollection laptopItems = $$(".catalog-form__offers-flex");
    private final SelenideElement firstLaptopLink = $$(".catalog-form__offers-flex").first()
            .$("a[href*='/notebook/']");

    public void selectFirstLaptop() {
        firstLaptopLink.click();
        sleep(5000);
    }

    public void selectLaptopByIndex(int index) {
        laptopItems.get(index).$("a[href*='/notebook/']").click();
        sleep(5000);
    }
}