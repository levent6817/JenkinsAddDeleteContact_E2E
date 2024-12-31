package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authentication.generateToken;

public class ContactListBaseUrl {

    public static RequestSpecification spec;

    static {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization","Bearer "+generateToken())
                .build();
    }

}
