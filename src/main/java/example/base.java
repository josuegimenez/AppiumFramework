package example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

public class base {

    public static AppiumDriverLocalService service;

    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsRunning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();//start the server
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }


    public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/java/global.properties");
        prop.load(fis);
        String appnombre = String.valueOf(prop.get(appName));

        File appDir = new File("src");
        File app = new File(appDir, appnombre);
        DesiredCapabilities cap = new DesiredCapabilities();

//        cap.setCapability("chromedriverExecutable","/Users/jgimenez/JOSU/appium/src/chromedriver2");
//        System.setProperty("webdriver.chrome.driver", "/Users/jgimenez/JOSU/appium/src/chromedriver2");

        String device = String.valueOf(prop.get("device"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);

        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".SplashActivity");
        //UI automator
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);

        if(device.contains("Emulator"))
            startEmulator();


        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }


    public static void startEmulator() throws IOException, InterruptedException {
        String[] emulator = { "sh", "src/main/java/resources/startEmulator.sh"};

        Runtime.getRuntime().exec(emulator);//"src/main/java/resources/startEmulator.sh"
        Thread.sleep(6000);
    }

}
