package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static stepdefinitions.AddContactStepDefs.email;
import static stepdefinitions.AddContactStepDefs.password;

public class Authentication {

    // We will create a dynamic method in this class that returns a token as a String

    public static String generateToken() {
        String credentials = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";

        Response response = given().contentType(ContentType.JSON).body(credentials).post("https://thinking-tester-contact-list.herokuapp.com/users/login");
        response.prettyPrint();
        return response.jsonPath().getString("token");
    }

}
