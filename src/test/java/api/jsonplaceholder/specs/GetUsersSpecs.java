package api.jsonplaceholder.specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;
import static io.restassured.RestAssured.with;
import static api.jsonplaceholder.specs.BaseSpecs.baseRequestSpec;
import static api.jsonplaceholder.specs.BaseSpecs.baseResponseSpec;

public class GetUsersSpecs {

    public static RequestSpecification getUsersRequestSpec = with()
            .spec(baseRequestSpec)
            .basePath("/users");

    public static ResponseSpecification getUsersResponseSpec = baseResponseSpec;
}