package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage {


    //Defino el constructor
    public FormPage(AppiumDriver driver) {
//        PageFactory.initElements(driver,this); //With Selenium will be enough with this
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);//Appium needs this
    }


    @AndroidFindBy(id = "nameField")
    private WebElement nameField; //la pongo en private porque quiero que para acceder a esto se haga a través del métdo que he creado más abajo getNameField()
    @AndroidFindBy(id = "radioFemale")
    public WebElement radioFemale;
    @AndroidFindBy(id = "spinnerCountry")
    private WebElement spinnerCountry;

    public WebElement getNameField() {
        System.out.println("Trying to find the Name Field...");
        return nameField;
    }

    public WebElement getCountry() {
        System.out.println("Selecting the option from dropdown...");
        return spinnerCountry;
    }


//            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))");


}
