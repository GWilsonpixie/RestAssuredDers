
import Model.Location;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class _04_ApiTestPOJO {

@Test
public void extractJsonAll_POJO(){

    Location locationNesnesi=
    given().

            when().get("http://api.zippopotam.us/us/90210")

            .then()
            .extract().body().as(Location.class) // tüm body yi all Location.class (kalıba göre) cevir

            ;
    System.out.println("locationNesnesi.getCountry() = " + locationNesnesi.getCountry());
    System.out.println("locationNesnesi = " + locationNesnesi);



}
}
