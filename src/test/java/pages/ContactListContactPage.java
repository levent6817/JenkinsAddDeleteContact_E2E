package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ContactListContactPage {

    public ContactListContactPage() {PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(id = "logout")
    public WebElement logout;

    @FindBy(id = "add-contact")
    public WebElement addContact;

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "birthdate")
    public WebElement birthdate;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "street1")
    public WebElement street1;

    @FindBy(id = "street2")
    public WebElement street2;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "stateProvince")
    public WebElement stateProvince;

    @FindBy(id = "postalCode")
    public WebElement postalCode;

    @FindBy(id = "country")
    public WebElement country;

    @FindBy(id = "submit")
    public WebElement submit;



    


}

