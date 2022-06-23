import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ?
            "username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "accessKey" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","Java TestNG Android");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", "lt://"); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);

            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
            color.click();
            Thread.sleep(1000);

            MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
            text.click();

            MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
            toast.click();

            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Thread.sleep(2000);

            MobileElement geo = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
            geo.click();
            Thread.sleep(5000);

            MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
            home.click();

            MobileElement speedtest = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/speedTest");
            speedtest.click();
            Thread.sleep(5000);

            MobileElement Home = (MobileElement) driver.findElementByAccessibilityId("Home");
            Home.click();

            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();

            MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
            url.sendKeys("https://www.lambdatest.com");

            MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
            find.click();

            //MARKING TEST AS PASSED VIA LAMBDA HOOKS            
            driver.executeScript('lambda-status=passed');

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
            //MARKING TEST AS FAILED VIA LAMBDA HOOKS            
            driver.executeScript('lambda-status=failed');
            driver.quit();
            }
        }


    }
}
