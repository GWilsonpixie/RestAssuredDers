
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class _03_ApiTestExtract {

    @Test
    public void extractingJsonPath() {

String ulkeAdi=
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .extract().path("country") // PATH i country olan değeri EXTRACT yap
        ;

        System.out.println("ulkeAdi = " + ulkeAdi);
        Assert.assertEquals(ulkeAdi,"United States");


    }

    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız

        String stateName =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().path("places[0].state"); // places dizisinin ilk elemanının state değeri alınır

        System.out.println("stateName = " + stateName);
        Assert.assertEquals(stateName, "California"); // stateName'in "California" olup olmadığını doğrular
    }

    @Test
    public void extractingJsonPath3() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // limit bilgisinin 10 olduğunu testNG ile doğrulayınız.

        int limitValue =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .extract().path("meta.pagination.limit"); // meta.pagination.limit değerini çekiyoruz

        System.out.println("Limit Değeri = " + limitValue);
        Assert.assertEquals(limitValue, 10); // limitValue'nun 10 olduğunu doğruluyoruz
    }

    @Test
    public void extractingJsonPath4() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // data daki bütün id leri nasıl alırız

        List <Integer> idler=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.id");
        System.out.println("idler = " + idler);


    }
    @Test
    public void extractingJsonPath5() {
        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // data daki bütün name leri nasıl alırız

        List <String> names=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.name");
        System.out.println("names = " + names);


    }

    @Test
    public void extractingJsonPathResponseAll() {

        // Soru : "https://gorest.co.in/public/v1/users"  endpoint in den dönen
        // data daki bütün name leri nasıl alırız

        Response donenData=
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().response();

        List <Integer> idler=donenData.path("data.id");
                List <String> names= donenData.path("data.name");
                int limit=donenData.path("meta.pagination.limit");

        System.out.println("idler = " + idler);
        System.out.println("names = " + names);
        System.out.println("limit = " + limit);

        Assert.assertTrue(idler.contains(7520589));
        Assert.assertTrue(names.contains("Gemini Mahajan II"));
        Assert.assertTrue(limit==10);


    }



}














