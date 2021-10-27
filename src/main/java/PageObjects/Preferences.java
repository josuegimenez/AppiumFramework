package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Preferences {
    //Defino el constructor
    public Preferences(AppiumDriver driver) {
//        PageFactory.initElements(driver,this); //With Selenium will be enough with this
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);//Appium needs this
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
    public WebElement PreferencesDependencies;

    @AndroidFindBy(id = "android:id/checkbox")
    public WebElement uno;

    @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
    public WebElement dos;

    @AndroidFindBy(className = "android.widget.EditText")
    public WebElement tres;

    @AndroidFindBy(className = "android.widget.Button")
    public List<WebElement> cuatro;

}
