package steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BDDStyleMethod {

    public static void simpleGetPost(String postNumber){
        given().
                contentType(ContentType.JSON).
        when().
                get(String.format("http://localhost:3000/posts/%s", postNumber)).
        then().
                body("author", is("Karthik KK"));
    }

    public static void PerformContainsCollection(){
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
        .then()
                .body("author", containsInAnyOrder("Karthik KK", "Karthik KK", null))
                .statusCode(200);
    }

    //PATH PARAMETER
    public static void PerformPathParameter(){
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParam("post",2).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author", containsString("Karthik KK"));
    }

    //QUERY PARAMETER
    public static void PerformQueryParameter(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("id",1).
        when()
                .get("http://localhost:3000/posts/").
        then()
                .body("author", containsString("Karthik KK"));
    }

    //POST
    public static void PerformPOSTBodyParameter(){

        //This will be the Body in the POST request
        Map<String, String> postContent = new HashMap<>();
        postContent.put("id","5");
        postContent.put("title","Robotium course");
        postContent.put("author","ExecuteAutomation");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author", Is.is("ExecuteAutomation"));

    }

}
