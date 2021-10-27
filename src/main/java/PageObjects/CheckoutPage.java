package PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage {


    //Defino el constructor
    public CheckoutPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);//Appium needs this
    }


    @AndroidFindBy(id = "productPrice")
    private List<WebElement> productList;

    @AndroidFindBy(id = "totalAmountLbl")
    public WebElement totalAmount;

public List<WebElement> getProductList(){
    return productList;
    }


}
