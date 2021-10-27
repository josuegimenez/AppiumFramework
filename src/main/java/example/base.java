package example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class base {
    public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/global.properties");
        prop.load(fis);
        String appnombre= String.valueOf(prop.get(appName));

        File appDir= new File("src");
        File app=new File(appDir,appnombre);
        DesiredCapabilities cap = new DesiredCapabilities();

//        cap.setCapability("chromedriverExecutable","/Users/jgimenez/JOSU/appium/src/chromedriver2");
//        System.setProperty("webdriver.chrome.driver", "/Users/jgimenez/JOSU/appium/src/chromedriver2");

        String device= String.valueOf(prop.get("device"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);

        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".SplashActivity");
        //UI automator
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,10);

        AndroidDriver<AndroidElement> driver =new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }

}
