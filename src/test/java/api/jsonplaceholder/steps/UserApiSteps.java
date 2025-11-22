package api.jsonplaceholder.steps;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.jsonplaceholder.specs.GetUsersSpecs.getUsersRequestSpec;
import static api.jsonplaceholder.specs.GetUsersSpecs.getUsersResponseSpec;
import static api.jsonplaceholder.specs.UserByIdSpec.getUserByIdRequestSpec;
import static api.jsonplaceholder.specs.UserByIdSpec.getUserByIdResponseSpec;
import static api.jsonplaceholder.specs.UserWithWrongIdSpec.getUserWithWrongIdRequestSpec;
import static api.jsonplaceholder.specs.UserWithWrongIdSpec.getUserWithWrongIdResponseSpec;
import static org.hamcrest.Matchers.*;

public class UserApiSteps {

    public void getAllUsers() {
        step("API: Получить всех пользователей (GET /users)", () ->
                given(getUsersRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUsersResponseSpec)
                        .body("$", not(empty()))
                        .body("id", everyItem(greaterThan(0)))
        );
    }

    public void getUserById() {
        step("API: Получить пользователя по ID (GET /users/4)", () ->
                given(getUserByIdRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUserByIdResponseSpec)
                        .body("id", equalTo(4))
                        .body("$", not(empty()))
        );
    }

    public void getUserWithWrongId() {
        step("API: Получить пользователя по неверному ID (GET /users/24)", () ->
                given(getUserWithWrongIdRequestSpec)
                        .when()
                        .get()
                        .then()
                        .spec(getUserWithWrongIdResponseSpec)
                        .statusCode(404)
        );
    }
}