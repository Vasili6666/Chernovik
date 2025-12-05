// Создаем файл ApiTestBase.java
package api.jsonplaceholder.tests;

import ui.onliner.helpers.CustomAllureListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {

    @BeforeAll
    static void setup() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.filters(CustomAllureListener.withCustomTemplates());

    }
}