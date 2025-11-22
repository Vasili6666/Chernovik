package api.jsonplaceholder.tests;

import api.jsonplaceholder.steps.PostApiSteps;
import api.jsonplaceholder.steps.UserApiSteps;
import api.jsonplaceholder.testdata.TestData;
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
        postSteps.updatePost(
                TestData.PostData.POST_ID,
                TestData.PostData.UPDATED_TITLE,
                TestData.PostData.UPDATED_BODY,
                TestData.PostData.USER_ID
        );
    }

    @Test
    @DisplayName("GET поста по ID")
    public void getPostByIdTest() {
        postSteps.getPostById(TestData.PostData.ANOTHER_POST_ID);
    }

    @Test
    @DisplayName("Создание нового поста")
    public void createPostTest() {
        postSteps.createPost(
                TestData.PostData.NEW_POST_TITLE,
                TestData.PostData.NEW_POST_BODY,
                TestData.PostData.USER_ID
        );
    }

    @Test
    @DisplayName("Удаление поста")
    public void deletePostTest() {
        postSteps.deletePost(TestData.PostData.POST_ID);
    }

    @Test
    @DisplayName("Получаем всех пользователей")
    public void getAllUsersTest() {
        userSteps.getAllUsers();
    }

    @Test
    @DisplayName("Получаем пользователя по ID")
    public void getUserByIdTest() {
        userSteps.getUserById(TestData.UserData.EXISTING_USER_ID);
    }

    @Test
    @DisplayName("Получаем пользователя по неверному ID")
    public void getUserWithWrongIdTest() {
        userSteps.getUserWithWrongId(TestData.UserData.NON_EXISTING_USER_ID);
    }
}