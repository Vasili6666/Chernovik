package api.jsonplaceholder.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;
import static io.restassured.RestAssured.with;


public class UserByIdSpec {
    public static RequestSpecification getUserByIdRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/users/4");

    public static ResponseSpecification getUserByIdResponseSpec = baseResponseSpec;

}
