package api.jsonplaceholder.steps;

import api.jsonplaceholder.models.lombok.BodyLombokModelsUpdatePostTest;
import api.jsonplaceholder.models.lombok.ResponseLomboktUpdatePostTest;
import api.jsonplaceholder.models.lombok.ResponseLombokPostByIdTest;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.jsonplaceholder.specs.CreatePostSpec.createPostRequestSpec;
import static api.jsonplaceholder.specs.CreatePostSpec.createPostResponseSpec;
import static api.jsonplaceholder.specs.PostByIdSpec.postByIdRequestSpec;
import static api.jsonplaceholder.specs.PostByIdSpec.postByIdResponseSpec;
import static api.jsonplaceholder.specs.UpdatePostSpecs.updatePostRequestSpec;
import static api.jsonplaceholder.specs.UpdatePostSpecs.updatePostResponseSpec;
import static org.junit.jupiter.api.Assertions.*;

public class PostApiSteps {

    public void updatePost(int postId, String title, String body, int userId) {
        step("API: Обновить пост с Lombok (PUT /posts/" + postId + ")", () -> {
            BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
            data.setId(postId);
            data.setTitle(title);
            data.setBody(body);
            data.setUserId(userId);

            ResponseLomboktUpdatePostTest response = given(updatePostRequestSpec)
                    .body(data)
                    .when()
                    .put()
                    .then()
                    .spec(updatePostResponseSpec)
                    .extract()
                    .as(ResponseLomboktUpdatePostTest.class);

            assertEquals(title, response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(userId, response.getUserId(), "userId должен быть равен " + userId);
            assertEquals(body, response.getBody(), "Body не совпадает");
        });
    }

    public void getPostById(int postId) {
        step("API: Получить пост по ID (GET /posts/" + postId + ")", () -> {
            ResponseLombokPostByIdTest response = given(postByIdRequestSpec)
                    .when()
                    .get()
                    .then()
                    .spec(postByIdResponseSpec)
                    .extract()
                    .as(ResponseLombokPostByIdTest.class);

            assertNotNull(response.getId(), "ID поста пустой!");
            assertNotNull(response.getTitle(), "Заголовок поста пустой!");
            assertNotNull(response.getBody(), "Body поста пустой!");
            assertNotNull(response.getUserId(), "userId поста пустой!");
        });
    }

    public void createPost(String title, String body, int userId) {
        step("API: Создать новый пост (POST /posts)", () -> {
            BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
            data.setTitle(title);
            data.setBody(body);
            data.setUserId(userId);

            ResponseLomboktUpdatePostTest response = given(createPostRequestSpec)
                    .body(data)
                    .when()
                    .post()
                    .then()
                    .spec(createPostResponseSpec)
                    .extract()
                    .as(ResponseLomboktUpdatePostTest.class);

            assertEquals(title, response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(body, response.getBody(), "Body поста не совпадает!");
            assertEquals(userId, response.getUserId(), "userId должен быть равен " + userId);
        });
    }

    public void deletePost(int postId) {
        step("API: Удалить пост (DELETE /posts/" + postId + ")", () ->
                given(updatePostRequestSpec)
                        .when()
                        .delete()
                        .then()
                        .spec(updatePostResponseSpec)
        );
    }
}