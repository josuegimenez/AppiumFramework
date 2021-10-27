package example;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {//Creamos una nueva clase porque aquí van los métodos que son comunes, como hacer scroll
    AndroidDriver<AndroidElement> driver;

public Utilities(AndroidDriver<AndroidElement> driver){
    this.driver= driver;
}

    public void scrollToText(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");


    }

}
