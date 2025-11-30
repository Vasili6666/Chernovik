package api.jsonplaceholder.steps;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.jsonplaceholder.specs.ApiSpecs.*;
import static org.hamcrest.Matchers.*;

public class UserApiSteps {

    public void getAllUsers() {
        step("API: Получить всех пользователей", () ->
                given(forUsers())
                        .when()
                        .get()
                        .then()
                        .spec(baseResponse())
                        .body("$", not(empty()))
                        .body("id", everyItem(greaterThan(0)))
        );
    }

    public void getUserById(int userId) {
        step("API: Получить пользователя по ID " + userId, () ->
                given(forUserById(userId))
                        .when()
                        .get()
                        .then()
                        .spec(baseResponse())
                        .body("id", equalTo(userId))
        );
    }

    public void getUserWithWrongId(int wrongUserId) {
        step("API: Получить пользователя по неверному ID " + wrongUserId, () ->
                given(forUserById(wrongUserId))
                        .when()
                        .get()
                        .then()
                        .statusCode(404)
        );
    }
}