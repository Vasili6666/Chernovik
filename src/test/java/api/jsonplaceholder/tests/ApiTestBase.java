// Создаем файл ApiTestBase.java
package api.jsonplaceholder.tests;

import helpers.CustomAllureListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestBase {

    @BeforeAll
    static void setup() {
        // Только для API тестов
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.filters(CustomAllureListener.withCustomTemplates());

        System.out.println("=== API TESTS CONFIG ===");
        System.out.println("API Base URL: " + RestAssured.baseURI);
        System.out.println("=========================");
    }
}