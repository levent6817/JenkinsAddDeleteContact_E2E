package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pages.ContactListContactPage;
import pages.ContactListHomePage;
import utilities.Driver;

import java.util.List;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


public class AddContactStepDefs {
    ContactListContactPage contactListContactPage = new ContactListContactPage();
    ContactListHomePage contactListHomePage = new ContactListHomePage();
    public static String email;
    public static String password;
    Response response;

    @Given("user goes to {string}")
    public void user_goes_to(String url) {

        Driver.getDriver().get(url);

    }

    @When("user enters email as {string}")
    public void user_enters_email_as(String email) {
        AddContactStepDefs.email = email;
        contactListHomePage.email.sendKeys(email);

    }

    @When("user enters password as {string}")
    public void user_enters_password_as(String password) {
        AddContactStepDefs.password = password;
        contactListHomePage.password.sendKeys(password);

    }

    @When("user clicks on Submit button")
    public void user_clicks_on_submit_button() {

        contactListHomePage.submit.click();

    }

    @When("user clicks on Add a New Contact button")
    public void user_clicks_on_add_a_new_contact_button() {

        contactListContactPage.addContact.click();

    }

    @When("user enters First Name as {string}")
    public void user_enters_first_name_as(String firstname) {

        contactListContactPage.firstName.sendKeys(firstname);

    }

    @When("user enters Last Name as {string}")
    public void user_enters_last_name_as(String lastname) {

        contactListContactPage.lastName.sendKeys(lastname);

    }

    @When("user enters Date of Birth as {string}")
    public void user_enters_date_of_birth_as(String dob) {

        contactListContactPage.birthdate.sendKeys(dob);

    }

    @When("user enters contact Email as {string}")
    public void user_enters_contact_email_as(String email) {

        contactListContactPage.email.sendKeys(email);

    }

    @When("user enters Phone as {string}")
    public void user_enters_phone_as(String phone) {

        contactListContactPage.phone.sendKeys(phone);

    }

    @When("user enters Street Address1 as {string}")
    public void user_enters_street_address1_as(String street1) {
        contactListContactPage.street1.sendKeys(street1);
    }

    @When("user enters Street Address2 as {string}")
    public void user_enters_street_address2_as(String street2) {
        contactListContactPage.street2.sendKeys(street2);
    }

    @When("user enters City as {string}")
    public void user_enters_city_as(String city) {
        contactListContactPage.city.sendKeys(city);
    }

    @When("user enters State or Province as {string}")
    public void user_enters_state_or_province_as(String state) {
        contactListContactPage.stateProvince.sendKeys(state);
    }

    @When("user enters Postal Code as {string}")
    public void user_enters_postal_code_as(String postalCode) {
        contactListContactPage.postalCode.sendKeys(postalCode);
    }

    @When("user enters Country {string}")
    public void user_enters_country(String country) {
        contactListContactPage.country.sendKeys(country);
    }

    @When("user clicks on contact Submit button")
    public void user_clicks_on_contact_submit_button() {
        contactListContactPage.submit.click();
    }


    @Then("close browser")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        Driver.closeDriver();

    }

    @And("validate Contact by API request {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string},  {string}")
    public void validateContactByAPIRequest(String firstname, String lastname, String dob, String email, String phone, String street1, String street2, String city, String province, String postcode, String country) {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        response = given(spec).get("/contacts/");
        response.prettyPrint();

        JsonPath json = response.jsonPath();

        String actualFirstname = (String) json.getList("findAll{it.email=='" + email + "'}.firstName").get(0);
        String actualLastName = (String) json.getList("findAll{it.email=='" + email + "'}.lastName").get(0);
        String actualBirthdate = (String) json.getList("findAll{it.email=='" + email + "'}.birthdate").get(0);
        String actualEmail = (String) json.getList("findAll{it.email=='" + email + "'}.email").get(0);
        String actualPhone = (String) json.getList("findAll{it.email=='" + email + "'}.phone").get(0);
        String actualStreet1 = (String) json.getList("findAll{it.email=='" + email + "'}.street1").get(0);
        String actualStreet2 = (String) json.getList("findAll{it.email=='" + email + "'}.street2").get(0);
        String actualCity = (String) json.getList("findAll{it.email=='" + email + "'}.city").get(0);
        String actualStateProvince = (String) json.getList("findAll{it.email=='" + email + "'}.stateProvince").get(0);
        String actualPostalCode = (String) json.getList("findAll{it.email=='" + email + "'}.postalCode").get(0);
        String actualCountry = (String) json.getList("findAll{it.email=='" + email + "'}.country").get(0);

        assert actualFirstname.equals(firstname) : "firstname did NOT match";
        assert actualLastName.equals(lastname) : "lastname did NOT match";
        assert actualBirthdate.equals(dob) : "birthdate did NOT match";
        assert actualEmail.equals(email) : "email did NOT match";
        assert actualPhone.equals(phone) : "phone did NOT match";
        assert actualStreet1.equals(street1) : "street1 did NOT match";
        assert actualStreet2.equals(street2) : "street2 did NOT match";
        assert actualCity.equals(city) : "city did NOT match";
        assert actualStateProvince.equals(province) : "province did NOT match";
        assert actualPostalCode.equals(postcode) : "postcode did NOT match";
        assert actualCountry.equals(country) : "country did NOT match";


    }

    @And("delete the contact")
    public void deleteTheContact() {

        List<String> contactIdList = response.jsonPath().getList("_id");
        for (String contactId : contactIdList) {
            response = given(spec).delete("contacts/" + contactId);
            assertTrue(response.asString().equals("Contact deleted"));
        }
    }
}
