package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {


    //Defino el constructor
    public HomePage(AppiumDriver driver) {
//        PageFactory.initElements(driver,this); //With Selenium will be enough with this
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);//Appium needs this
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
    public WebElement Preferences;


}
