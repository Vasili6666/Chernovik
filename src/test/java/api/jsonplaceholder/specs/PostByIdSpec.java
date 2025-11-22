package api.jsonplaceholder.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;
import static io.restassured.RestAssured.with;


public class PostByIdSpec {

    public static RequestSpecification postByIdRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/posts/20");

    public static ResponseSpecification postByIdResponseSpec = baseResponseSpec;
}