package api.jsonplaceholder.steps;

import api.jsonplaceholder.models.lombok.Post;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static api.jsonplaceholder.specs.ApiSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostApiSteps {

    public void updatePost(int postId, String title, String body, int userId) {
        step("API: Обновить пост с ID " + postId, () -> {
            Post requestData = new Post(postId, title, body, userId);

            Post response = given(forPostById(postId))
                    .body(requestData)
                    .when()
                    .put()
                    .then()
                    .spec(baseResponse())
                    .extract()
                    .as(Post.class);

            assertEquals(title, response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(userId, response.getUserId(), "userId должен быть равен " + userId);
            assertEquals(body, response.getBody(), "Body не совпадает");
            assertEquals(postId, response.getId(), "ID поста не совпадает!");
        });
    }

    public void getPostById(int postId) {
        step("API: Получить пост по ID " + postId, () -> {
            Post response = given(forPostById(postId))
                    .when()
                    .get()
                    .then()
                    .spec(baseResponse())
                    .extract()
                    .as(Post.class);

            assertNotNull(response.getId(), "ID поста пустой!");
            assertNotNull(response.getTitle(), "Заголовок поста пустой!");
            assertNotNull(response.getBody(), "Body поста пустой!");
            assertNotNull(response.getUserId(), "userId поста пустой!");
        });
    }

    public void createPost(String title, String body, int userId) {
        step("API: Создать новый пост", () -> {

            Post requestData = new Post(0, title, body, userId);

            Post response = given(forPosts())
                    .body(requestData)
                    .when()
                    .post()
                    .then()
                    .spec(baseResponse())
                    .extract()
                    .as(Post.class);

            assertEquals(title, response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(body, response.getBody(), "Body поста не совпадает!");
            assertEquals(userId, response.getUserId(), "userId должен быть равен " + userId);
            assertTrue(response.getId() > 0, "ID поста должен быть больше 0!");
        });
    }

    public void deletePost(int postId) {
        step("API: Удалить пост с ID " + postId, () ->
                given(forPostById(postId))
                        .when()
                        .delete()
                        .then()
                        .spec(baseResponse())
        );
    }
}