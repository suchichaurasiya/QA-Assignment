package Config;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static ExecutionEngine.DriverScript.OR;

import ExecutionEngine.DriverScript;

import org.openqa.selenium.support.ui.Select;
import Utility.Log;

public class ActionKeywords {



 //Creating a driver object referencing WebDriver interface
 public static WebDriver driver;


 //Instantiating driver object and launching browser
 public static void openBrowser(String data, String object) {
  Log.info("Opening Browser");
  try {

   //If value of the parameter is Mozilla, this will execute
   if (data.equals("Mozilla")) {
    System.setProperty("webdriver.gecko.driver", "C:\\Users\\Acro\\Documents\\suchi\\selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
    driver = new FirefoxDriver();
    Log.info("Mozilla browser started");
   } else if (data.equals("IE")) {
    System.setProperty("webdriver.ie.driver", "C:\\Users\\Acro\\Documents\\suchi\\selenium\\IEDriver64\\IEDriverServer.exe");
    driver = new InternetExplorerDriver();
    Log.info("IE browser started");
   } else if (data.equals("Chrome")) {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Acro\\Documents\\suchi\\selenium\\chromedriver\\chromedriver.exe");
    driver = new ChromeDriver();
    Log.info("Chrome browser started");
   }

   int implicitWaitTime = (10);
   driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
  } catch (Exception e) {
   Log.info("Not able to open the Browser --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }



 //Using get() method to open a webpage
 public static void navigate(String data, String object) {
  try {
   Log.info("Opening page URL");
   driver.get(Constants.URL);
  } catch (Exception e) {
   Log.info("Not able to Open page URL--- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 //Clicking
 public static void click(String data, String object) {
  try {
   Log.info("Clicking button   ---" + object);
   WebDriverWait wait = new WebDriverWait(driver, 5);
   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
   driver.findElement(By.xpath(OR.getProperty(object))).click();
  } catch (Exception e) {
   Log.info("Not able to  to Click--" + object + "---" + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 //Entering Email address to register
 public static void enterEmail(String data, String object) {
  try {
   Log.info("Entering Email Address");
   WebDriverWait wait2 = new WebDriverWait(driver, 5);
   wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
   WebElement emailadd = driver.findElement(By.xpath(OR.getProperty(object)));
   emailadd.sendKeys(data);
  } catch (Exception e) {
   Log.info("Not able to Enter Email Address --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 //Provide Password
 public static void providePassword(String data, String object) {
  try {
   Log.info("Providing Password ");
   WebDriverWait wait4 = new WebDriverWait(driver, 5);
   wait4.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
   driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
  } catch (Exception e) {
   Log.info("Not able to Provide Password --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 //Provide details
 public static void provideDetails(String data, String object) {
  try {
   Log.info("Providing registration details");

   driver.findElement(By.xpath("//*[@type='radio' and @name='id_gender' and @value='1']")).click();

   driver.findElement(By.xpath("//*[@id='customer_firstname' and @name='customer_firstname']")).sendKeys("suchi");

   driver.findElement(By.xpath("//*[@id='customer_lastname' and @name='customer_lastname']")).sendKeys("chaurasiya");


   Select daydropdown = new Select(driver.findElement(By.xpath("//*[@id='days' and @name='days']")));
   daydropdown.selectByValue("29");

   Select monthdropdown = new Select(driver.findElement(By.xpath("//*[@id='months' and @name='months']")));
   monthdropdown.selectByValue("8");

   Select yeardropdown = new Select(driver.findElement(By.xpath("//*[@id='years' and @name='years']")));
   yeardropdown.selectByValue("1991");

   driver.findElement(By.xpath("//*[ @id='newsletter' and @type='checkbox']")).click();


   driver.findElement(By.xpath("//*[ @id='optin' and @type='checkbox']")).click();

   driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("belapur");

   driver.findElement(By.xpath("//*[@id='city']")).sendKeys("Mumbai");

   Select state = new Select(driver.findElement(By.xpath("//*[@id='id_state' and @name='id_state']")));
   state.selectByVisibleText("Utah");


   driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("22222");

   driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("6736256278");

   driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("def@sw.com");
  } catch (Exception e) {
   Log.info("Not able to Provide Registration Details --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }


 public static void closeBrowser(String data, String object) {
  try {
   Log.info("Closing Browser");
   //driver.quit();
  } catch (Exception e) {
   Log.info("Not able to Close Browser --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 public static void hover(String data, String object) {
  try {
   Log.info("Hover over");
   WebDriverWait wait = new WebDriverWait(driver, 5);
   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
   Actions action = new Actions(driver);
   WebElement btn = driver.findElement(By.xpath(OR.getProperty(object)));
   action.moveToElement(btn).perform();
   //Thread.sleep(5000);
  } catch (Exception e) {
   Log.info("Not able to Hover --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }
 public static void scroll (String data, String object) {
	  try {
	   Log.info("Scroll page");
 JavascriptExecutor js = (JavascriptExecutor)driver;
 js.executeScript("window.scrollTo(0,500);");
	  }
	  catch (Exception e) {
		   Log.info("Not able to scroll---" + e.getMessage());
		   DriverScript.bResult = false;
		  }
 }
 public static int a, b, c, d, e, f;
 public static void getproductcost(String data, String object) {
  try {
   a = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText());
  } catch (Exception e) {
   Log.info("Not able to getproductcost---" + e.getMessage());
   DriverScript.bResult = false;
  }
 }
 public static void gettotalshipping(String data, String object) {
  try {
   b = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText());
  } catch (Exception e) {
   Log.info("Not able to gettotalshipping---" + e.getMessage());
   DriverScript.bResult = false;
  }
 }
 public static void getTotal(String data, String object) {
  try {
   c = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText());
  } catch (Exception e) {
   Log.info("Not able to getTotal- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }
 public static void getperunitcost(String data, String object) {
  try {
   d = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText());
  } catch (Exception e) {
   Log.info("Not able to getperunitcost\r\n" + "---- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 public static void getquantity(String data, String object) {
  try {
   e = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value"));
  } catch (Exception e) {
   Log.info("Not able to getquantity" + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 public static void getTax(String data, String object) {
  try {
   e = Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText());
  } catch (Exception e) {
   Log.info("Not able to gettax" + e.getMessage());
   DriverScript.bResult = false;
  }
 }


 public static void verifyTotalCost1(String data, String object) {
  try {
   if (a == b + c) {
    Log.info("Total Cost is correct ");
   } else if (a != b + c) {
    Log.info("Total Cost is incorrect ");
   }

  } catch (Exception e) {
   Log.info("Not able to verify --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 public static void verifyProductCost1(String data, String object) {
  try {
   if (a == d * e) {
    Log.info("Total Cost is correct on " + object + "page");
   } else if (a != d * e) {
    Log.info("Total Cost is incorrect on " + object + "page");
   }

  } catch (Exception e) {
   Log.info("Not able to verify --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }

 public static void verifyTotalCost2(String data, String object) {
  try {
   if (a == b + c + f) {
    Log.info("Total Cost is correct i.e equal to product cost + shipping cost + tax");
   } else if (a != b + c + f) {
    Log.info("Total Cost is incorrect ");
   }

  } catch (Exception e) {
   Log.info("Not able to verify --- " + e.getMessage());
   DriverScript.bResult = false;
  }
 }
 
  
 public static void verifyTotalCost3(String data, String object) {
	  try {
	   if (a == Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText())) {
	    Log.info("Total Cost is same on both payments & Order History page. ");
	   } else if (a !=Integer.parseInt(driver.findElement(By.xpath(OR.getProperty(object))).getText())) {
	    Log.info("Total Cost is not same across payments & Order History page. ");
	   }

	  } catch (Exception e) {
	   Log.info("Not able to verify --- " + e.getMessage());
	   DriverScript.bResult = false;
	  }
	 }

}