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

    public void updatePost() {
        step("API: Обновить пост с Lombok (PUT /posts/1)", () -> {
            BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
            data.setId(1);
            data.setTitle("Basil post updated");
            data.setBody("Fata viam invenient. (пер. «Судьба найдёт путь.»)");
            data.setUserId(1);

            ResponseLomboktUpdatePostTest response = given(updatePostRequestSpec)
                    .body(data)
                    .when()
                    .put()
                    .then()
                    .spec(updatePostResponseSpec)
                    .extract()
                    .as(ResponseLomboktUpdatePostTest.class);

            assertEquals(data.getTitle(), response.getTitle(), "Заголовок поста не совпадает!");
            assertEquals(data.getUserId(), response.getUserId(), "userId должен быть равен 1");
            assertEquals(data.getBody(), response.getBody(), "Body не совпадает");
        });
    }

    public void getPostById() {
        step("API: Получить пост по ID (GET /posts/20)", () -> {
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

    public void createPost() {
        step("API: Создать новый пост (POST /posts)", () -> {
            BodyLombokModelsUpdatePostTest data = new BodyLombokModelsUpdatePostTest();
            data.setTitle("Basil post");
            data.setBody("Fata viam invenient.");
            data.setUserId(1);

            ResponseLomboktUpdatePostTest response = given(createPostRequestSpec)
                    .body(data)
                    .when()
                    .post()
                    .then()
                    .spec(createPostResponseSpec)
                    .extract()
                    .as(ResponseLomboktUpdatePostTest.class);

            assertEquals(data.getTitle(), response.getTitle());
            assertEquals(data.getBody(), response.getBody());
            assertEquals(data.getUserId(), response.getUserId());
        });
    }

    public void deletePost() {
        step("API: Удалить пост (DELETE /posts/1)", () ->
                given(updatePostRequestSpec)
                        .when()
                        .delete()
                        .then()
                        .spec(updatePostResponseSpec)
        );
    }
}