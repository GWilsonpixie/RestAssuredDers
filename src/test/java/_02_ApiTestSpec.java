import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _02_ApiTestSpec {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void Setup() {
        baseURI = "https://gorest.co.in/public/v1";

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();
    }

    @Test
    public void Test1() {
        given()
                .spec(requestSpecification)
                .when()
                .get("/users")  // baseURI ile birlikte /users endpoint'i çağırılıyor
                .then()
                .spec(responseSpec);

    }

    @Test
    public void Test2() {
        given()
                .spec(requestSpecification)
                .queryParam("page", 2) // 2. sayfa için query parametresi ekleniyor
                .when()
                .get("/users") // baseURI ile birlikte /users endpoint'i çağırılıyor
                .then()
                .spec(responseSpec);

    }
}
