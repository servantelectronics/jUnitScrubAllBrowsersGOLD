package jUnitScrubAllBrowsers;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jUnitScrubAllBrowsers.FileWrite.LogCode;

/*import static org.testng.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
*/

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



public class Simple_demo {

	private static String whichBrowser = "Iexplore" ; //  = "Iexplore" ;  = "Firefox" ;  "Chrome"

	static String driverChrome="C:\\Users\\Allan\\AppData\\Roaming\\Selenium\\301\\ChromeWin32_227\\chromedriver.exe";
	static String driverFirefox="C:\\Users\\Allan\\workspace\\Selenium\\webdriver\\Firefox\\geckodriver.exe";
	static String driverIexplore="C://Users//Allan//AppData/Roaming//Selenium//301//IEDriverServer_x64_340//IEDriverServer.exe";
	
	WebDriver driver = chooseDriverFromBrowserName(whichBrowser);	//  ChromeDriver wd; //   FirefoxDriver wd;
	    
	static String cssSelectorUser = "input.infaField";   
	
	static String xPathPassword = "//div[@id='ifrm1_password']/input";
	
	static String TargetURL = "http://ipg-mdm-mdmdv:8080/bdd";
    static String expectTitle="Informatica MDM";  
    static String CopyRightVersion="Version 10.1.0";
    static String CopyRightYear="Copyright © 1993-2016 Informatica LLC. See patents at https://www.informatica.com/legal/patents.html. All rights reserved.";
    static int maxLength=200;
    static String DELIMCSV = ",";
    static int winwidth = 450; static int winheight = 450;
    	  
    @BeforeClass
    public void setUp()  {
       	System.setProperty("webdriver.chrome.driver", driverChrome);	

 
	
    }
    
    @Test
    public void helpme() {
		String strTemp="";
		
        driver.get(TargetURL); // "http://ipg-mdm-mdmdv:8080/e360/com.informatica.tools.mdm.web.auth/login");
		
        // driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(winwidth,winheight));
		
        driver.findElement(By.cssSelector("input.infaField")).click();
        driver.findElement(By.cssSelector("input.infaField")).clear();
        driver.findElement(By.cssSelector("input.infaField")).sendKeys("1234");
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("5680");
        driver.findElement(By.id("infaButton1")).click();
        strTemp=driver.findElement(By.cssSelector("div.infaLoginMsg")).getText();
        System.out.println("warning message DIV innerHTML is, '" +  strTemp + "'");
/*        driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        driver.findElement(By.cssSelector("div.innerContainer")).click();
        driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        driver.findElement(By.cssSelector("div.infaLoginMsg")).click();*/
    } // end helpme method
    
    
	@Test
	public void Test008FormFieldMax() {
		WebElement elem = driver.findElement(By.id("CopyRightVersion"));
		int inputLength = elem.getText().length();
		if ( inputLength <= maxLength) {
		System.out.println("PASS: User Entry Length " + inputLength + " is within MAX: " + maxLength + " chars.");
		} else { Assert.fail("FAIL: User Entry Length " + inputLength + " is GREATER than MAX: " + maxLength + " chars."); }
		
	}      	
	

	
	@Test
	public void Test009BadEntryWarning() {
		boolean boolTemp;
		WebElement elem = driver.findElement(By.xpath(xPathPassword));
	//	AnyPageObjects.isVisibleReturnsInnerText(driver, by);
		boolTemp = (isDisplayedAndClickable(By.xpath(xPathPassword), 10));
				if (boolTemp) {		
		//	Assert.assertTrue(!isVisibleBefore, idUser + "'s Required Warning visiblity is: " + false);
			elem.clear();
			elem.sendKeys("PW");
		    isDisplayedAndClickable(By.id("infaButton1"), 10);  
		elem.click();			
		} else Assert.fail("Test009aBadEntryWarning: expected warning not visible");
				

		
	}      	
	
	
	@Test
	public void Test010FormFieldReadOnly() {
		WebElement elem = driver.findElement(By.cssSelector(cssSelectorUser));
		Assert.assertSame(elem.isEnabled(), true);
	}      	
	
		
    
    
    @Test
    public void Test100CopywrightVersionlogElemAttributes() {
    	WebElement elem = driver.findElement(By.id("CopyRightVersion"));
    	System.out.println("expected: " + CopyRightVersion + ", found: " + elem.getText());
    	
    	Assert.assertNotNull(elem);
    	
    	FileWrite.logthis(LogCode.CSV, elem.getTagName() 
    			+ DELIMCSV + elem.getAttribute("id") 
    			+ DELIMCSV + elem.getAttribute("name")
    			+ DELIMCSV + elem.getAttribute("value")  			
    			+ DELIMCSV + elem.getAttribute("class")
    			+ DELIMCSV + elem.getAttribute("style")
    			+ DELIMCSV + elem.getAttribute("innerHTML")
    			+ DELIMCSV + elem.getAttribute("outerHTML")   			
    			);
        elem.click();
 
    	        
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    
    /*      --- -- - -- --- *** --- -- - -- ---  */	

	public static WebDriver chooseDriverFromBrowserName(String whichBrowser) {
		WebDriver driver= null;
		if (whichBrowser.equalsIgnoreCase("Firefox")) {
			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", "latest");
			capabilities.setCapability("platform", Platform.WINDOWS);
			capabilities.setCapability("name", "Testing Selenium");
			
		    System.setProperty("webdriver.gecko.driver", driverFirefox ); // "C:\\Users\\Allan\\workspace\\Selenium\\webdriver\\Firefox\\geckodriver.exe");
	 driver =  new FirefoxDriver();
		     
		 //	this.driver = new RemoteWebDriver(new URL("http://cnn.com"),capabilities);
		
			} else if (whichBrowser.equalsIgnoreCase("CHROME")){
				
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("version", "latest");
				capabilities.setCapability("platform", Platform.WINDOWS);
				capabilities.setCapability("name", "Testing Selenium");
//				System.setProperty("webdriver.chrome.driver",("C:/Users/Allan/AppData/Roaming/Selenium/301/ChromeWin32_227/chromedriver.exe").replace("/","//"));
				System.setProperty("webdriver.chrome.driver",driverChrome);
	 driver =  new ChromeDriver();
				 

			}
			else if(whichBrowser.equalsIgnoreCase("IEXPLORE")){
				//System.out.println("Internet Explorer DRIVER NOT YET INSTALLED, EXIT TEST");
				
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("version", "latest");
				capabilities.setCapability("platform", Platform.WINDOWS);
				capabilities.setCapability("name", "Testing Selenium");
				
			{ 	System.setProperty("webdriver.ie.driver", driverIexplore); // file.getAbsolutePath() );
			    driver = new InternetExplorerDriver(); 
				// above AND below work identically except below needs import java.io.File;
				// File file = new File("C:/Users/someuser/somepath/IEDriverServer_x64_340/IEDriverServer.exe".replace("/","//"));
				// File file = new File(driverIexplore);
			    // System.setProperty("webdriver.ie.driver", file.getAbsolutePath() );
			}

						
			} else {System.out.println("invalid browser, EXIT TEST");}

		
		return driver;
	}	

    
    //Will check to see if an element is not present Instead of returning exception this will return false
    public Boolean isDisplayed(By locator, Integer timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }

    public Boolean isDisplayedAndClickable(By locator, Integer timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));

        } catch (org.openqa.selenium.TimeoutException exception) {
            return false;
        }
        return true;
    }
	
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
