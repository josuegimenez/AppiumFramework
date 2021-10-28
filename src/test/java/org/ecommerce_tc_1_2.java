package org;


import PageObjects.CheckoutPage;
import PageObjects.FormPage;
import example.Utilities;
import example.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

//todo Clickamos en los 2 primeros productos
//todo Check that that the price of all the products added to the cart equals to the total amount shown
//todo al final hemos añadido que clicke en el checkbo, haga un long press en el terms and conditions, le de al ok y clicke en el enlace para ir al browser


public class ecommerce_tc_1_2 extends base {
    //    public static void main(String[] args) throws IOException, InterruptedException {
    @BeforeTest
    public void server() {
    service=startServer();
    }
    @Test
    public void totalValidation() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");//General-Store.apk

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        service=startServer();
        FormPage formPage = new FormPage(driver);
        Utilities utilities = new Utilities(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        //FIRST SCREEN
        formPage.getNameField().sendKeys("Josu");
        formPage.radioFemale.click();
        formPage.getCountry().click();

        utilities.scrollToText("Argentina");
//        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))");
        driver.findElementByXPath("//*[@text='Argentina']").click();
        driver.findElementById("btnLetsShop").click();

        int count = driver.findElements(By.id("productName")).size();
        for (int i = 0; i < count; i++)
            driver.findElements(By.id("productAddCart")).get(i).click();

        driver.findElementById("appbar_btn_cart").click();

        count = checkoutPage.getProductList().size();
        float sum = 0;
        for (int i = 0; i < count; i++) {
            float price = Float.parseFloat(checkoutPage.getProductList().get(i).getText().substring(1));
            sum = price + sum;
        }

        System.out.println(sum);
        float totalPurchase = Float.parseFloat(checkoutPage.totalAmount.getText().substring(2));
        System.out.println(totalPurchase);
        Assert.assertEquals(totalPurchase, sum);

        WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
        TouchAction t = new TouchAction(driver);
        t.tap(tapOptions().withElement(element(checkbox))).perform();

        WebElement tc = driver.findElementByXPath("//*[@text='Please read our terms of conditions']");
        t.longPress(longPressOptions().withElement(element(tc))).perform();

        Assert.assertTrue(driver.findElementById("alertTitle").isDisplayed());
        Thread.sleep(2000);
        driver.findElementById("android:id/button1").click();
        driver.findElementById("btnProceed").click();

        service.stop();
    }


}