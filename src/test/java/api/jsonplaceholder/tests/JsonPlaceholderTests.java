package api.jsonplaceholder.tests;

import api.jsonplaceholder.steps.PostApiSteps;
import api.jsonplaceholder.steps.UserApiSteps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.ApiTestBase;

@Tag("api")
public class JsonPlaceholderTests extends ApiTestBase {

    private final PostApiSteps postSteps = new PostApiSteps();
    private final UserApiSteps userSteps = new UserApiSteps();

    @Test
    @DisplayName("Изменение поста с Lombok")
    public void lombokUpdatePostTest() {
        postSteps.updatePost();
    }

    @Test
    @DisplayName("GET поста по ID")
    public void getPostByIdTest() {
        postSteps.getPostById();
    }

    @Test
    @DisplayName("Создание нового поста")
    public void createPostTest() {
        postSteps.createPost();
    }

    @Test
    @DisplayName("Удаление поста")
    public void deletePostTest() {
        postSteps.deletePost();
    }

    @Test
    @DisplayName("Получаем всех пользователей")
    public void getAllUsersTest() {
        userSteps.getAllUsers();
    }

    @Test
    @DisplayName("Получаем пользователя по ID")
    public void getUserByIdTest() {
        userSteps.getUserById();
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID")
    public void getUserWithWrongIdTest() {
        userSteps.getUserWithWrongId();
    }
}