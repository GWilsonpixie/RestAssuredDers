import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ApiTest {

    @Test
    public void Test1() {

        // Endpoint i yani url yi cağırmadan önce hazırlıkların yapıldığı bölüm : request , gidecek body,token
        // Endpoint i cağrıldiği bölüm : Endpointin cağrılması (metod:GET, POST, PUT...)
        // Endpoint in cağrıldıktan sonraki bölüm : response,Test(Assert),data

        given().
                //1.bölümle ilgili isler:giden, body,token
                        when().
                //2.bölümle ilgili isler:metod,endpoint
                        then();
        //3.bölümle ilgili isler: gelendata, assert,test


    }

    @Test
    public void statusCodeTest() {


        given().

                when().get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönen data kısmı
                //.log().all()  //  dönen bütün bilgiler
                .statusCode(200)  // dönen değer kodu 200 mü?
        ;
    }

    @Test
    public void contentTypeTest() {


        given().

                when().get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönen data kısmı
                //.log().all()  //  dönen bütün bilgiler
                .statusCode(200)  // dönen değer kodu 200 mü?
                .contentType(ContentType.JSON) //dönen data nın tipi json mi?
        ;
    }

    @Test
    public void checkCountryInResponseBody() {


        given().

                when().get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // dönen data kısmı
                //.log().all()  //  dönen bütün bilgiler
                .statusCode(200)  // dönen değer kodu 200 mü?
                .contentType(ContentType.JSON) //dönen data nın tipi json mi?
                .body("country", equalTo("United States"))// country yi dışarı almadan
        // bulundu yeri (path i) vererek içerde assertion yapıyorum.Bunu hamcrest kütüphanesi yapıyor

//        pm.test("Ulke Bulunamadı", function () {
//        pm.expect(pm.response.json().message).to.eql("Country not found");
//    });

        ;
    }

    @Test
    public void checkCountryInResponseBody2() {

        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu doğrulayınız

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                log().body(). // dönen data kısmı
                statusCode(200). // dönen değer kodu 200 mü?
                contentType(ContentType.JSON). // dönen data'nın tipi JSON mı?
                body("places[0].state", equalTo("California")); // ilk place elemanının state değerini doğrula


    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // place dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given().
                when().
                get("http://api.zippopotam.us/tr/01000").
                then().
                log().body().
                statusCode(200).
                contentType(ContentType.JSON).
                body("places.'place name'", hasItem("Dörtağaç Köyü")); // "place name" içinde "Dörtağaç Köyü" var mı?


    }

    @Test
    public void bodyArrayHasSizeTest() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen
        // place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                log().body().
                statusCode(200).
                contentType(ContentType.JSON).
                body("places", hasSize(1)); // places dizisinin uzunluğu 1 mi?

    }

    @Test
    public void combiningTest() {

        given().
                when().
                get("http://api.zippopotam.us/us/90210").
                then().
                body("places", hasSize(1)).
                body("places[0].state", equalTo("California")).
                body("places.'place name'", hasItem("Beverly Hills"));  // yukarıda olduğu gibi istenilen kadar test eklenebilir


    }

    @Test
    public void pathParamTest() {

        given()
                .pathParam("ulke", "us")
                .pathParam("postaKodu", 90210)
                .log().uri() //olusacak endpoint i yazdıralım
                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKodu}")
                .then()
                .log().body();
    }


    @Test
    public void queryParamTest() {
        // https://gorest.co.in/public/v1/users?page=1

        given()
                .param("page", 3)
                .log().uri()

                .when()
                .get("https://gorest.co.in/public/v1/users?page=1")// url ile uri yi ayırdın yukarıda param koyarak data yı oraya koydun ve burası url oldu
                .then()
                .log().body();

    }

    @Test
    public void queryParamTest2() {
        // https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki donen page degerlerinin
        // çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.

        for (int pageNo = 1; pageNo <= 10; pageNo++) {
            given().
                    queryParam("page", pageNo).
                    when().
                    get("https://gorest.co.in/public/v1/users").
                    then().
                    log().body().
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("meta.pagination.page", equalTo(pageNo)); // dönen page değeri çağrılan pageNo ile aynı mı?
        }

    }

}






