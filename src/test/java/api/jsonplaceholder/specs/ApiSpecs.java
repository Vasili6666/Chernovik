package api.jsonplaceholder.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;
import static io.restassured.RestAssured.with;

public class ApiSpecs {

    public static RequestSpecification forPath(String path) {
        return with()
                .spec(baseRequestSpec)
                .basePath(path);
    }

    public static ResponseSpecification baseResponse() {
        return baseResponseSpec;
    }


    public static RequestSpecification forUsers() {
        return forPath("/users");
    }

    public static RequestSpecification forUserById(int userId) {
        return forPath("/users/" + userId);
    }

    public static RequestSpecification forPosts() {
        return forPath("/posts");
    }

    public static RequestSpecification forPostById(int postId) {
        return forPath("/posts/" + postId);
    }
}