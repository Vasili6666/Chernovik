package api.jsonplaceholder.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;
import static io.restassured.RestAssured.with;


public class UpdatePostSpecs {

    public static RequestSpecification updatePostRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/posts/1");

    public static ResponseSpecification updatePostResponseSpec = baseResponseSpec;
}

