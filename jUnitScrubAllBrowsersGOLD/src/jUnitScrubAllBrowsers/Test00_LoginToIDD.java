// 1. get basic @Test Annotations @Before @After to work
// 2. make UNIVERSAL JUNIT TEST FOR ANY WEB ELEMENT       
// 2. read URL and TEST ENVIRON CONSTANTS from  

// ifrm1_user   ifrm1_password ifrm1_blogin infaButton1 CopyRightVersion CopyRightYear

package jUnitScrubAllBrowsers;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
/*import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;*/

import jUnitScrubAllBrowsers.FileWrite.LogCode;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@Test
public class Test00_LoginToIDD {

    static WebDriver driver= null; 
    static String whichBrowser = "Iexplore";  // "Firefox";  "Iexplore"; Chrome
    static String idUser="ifrm1_user";
    static String idPassword="frm1_password";
    static String idSubmitPWbutton="infaButton1";
    
    public static String driverChrome="C://Users//Allan//AppData//Roaming//Selenium//301//ChromeWin32_227//chromedriver.exe";
    public static String driverFirefox="C:\\Users\\Allan\\workspace\\Selenium\\webdriver\\Firefox\\geckodriver.exe";
    public static String driverIexplore="C://Users//Allan//AppData/Roaming//Selenium//301//IEDriverServer_x64_340//IEDriverServer.exe";
        
    static String TargetURL = "http://ipg-mdm-mdmdv:8080/bdd";
    static String expectTitle="Informatica MDM";  
    static String CopyRightVersion="Version 10.1.0";
    static String CopyRightYear="Copyright © 1993-2016 Informatica LLC. See patents at https://www.informatica.com/legal/patents.html. All rights reserved.";
    static int maxLength=30;
    static String DELIMCSV = ",";
    static String DELIMHTM = "</TD><TD>";
    static String RECORDSTARTHTM = "<TR>";
    static String RECORDENDHTM = "</TR>";
    static String RECORDSTARTCSV = "\"";
    static String RECORDENDCSV = "\"\n";
    static String LINEENDHTM = "<BR />";
    static String USERNAME  = "awhitwor";
    static String PW = "gorgonzola";
    public String thisCurrentURL;
    
    
/*	public Test00_LoginToIDD() {
		// TODO Auto-generated constructor stub
	}
    */
   

	@BeforeSuite
    public void setUpFirefox() throws Exception {
		
		driver  = chooseDriverFromBrowserName(whichBrowser);            //         driver  = new ChromeDriver();
        // System.setProperty("webdriver.chrome.driver", driverChrome);
       // done below in chooseDriverFromBrowserName  System.setProperty("webdriver.gecko.driver", driverFirefox );
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(450, 450));
        driver.get(TargetURL);
        
        // getTestName
        // make Logfile for TestName
        
        } // @Before setUP()
    
    
    @Test
    public void Test001_PageLoads() throws MalformedURLException, IOException {
// load page     	// 	getResponseCode
    	int responsecode= getResponseCode(TargetURL);
    	System.out.println("http: response code is: " + responsecode );
    //	Assert.assertTrue(responsecode < 400 && responsecode >= 200);
    	// driver.get(TargetURL);
    	System.out.println("TITLE expected: " + expectTitle + ", found: " + driver.getTitle());
    	assertEquals(expectTitle, driver.getTitle());

//        thisCurrentURL = driver.getCurrentUrl();
	  }
    
    @Test public void Test001a_Copyright() {
    	WebElement elem = driver.findElement(By.id("CopyRightYear"));
    	
//    	Assert.assertNotNull(elem);
    	System.out.println("CopyRightYear expected: " + CopyRightYear + ", found: " + elem.getText());
    //	Assert.assertSame(CopyRightYear, elem.getText());
    }
    
    @Test
    public void Test002_nextPageCopyRight() {
       	WebElement elem = driver.findElement(By.id("CopyRightYear"));
//    	Assert.assertNotNull(elem);
       	System.out.println("Expected CopyRightYear (length: " + CopyRightYear.length() + "\nFound (length: " + elem.getText().length() + ") " + elem.getText());
    	Assert.assertSame(CopyRightYear, elem.getText());	
    }

    @Test
    public void Test003_loginButtonExists() {
        assertTrue(driver.findElement(By.id("infaButton1")).isEnabled());  // .click();      
        
    }
    

	
    

    @Test
    public void Test004_loginFormUsername() {
        assertTrue(driver.findElement(By.id("ifrm1_user")).isEnabled());  // .click();      

    }  

    public void Test004_loginFormPasswordTextbox() {
        assertTrue(driver.findElement(By.id("ifrm1_password ")).isEnabled());  // .click();      
    }  // end Test004_loginFormPasswordTextbox

    
	@Test
	public void Test005loginWarning() {   //TODO ADD TEXT FOR MAX CHARS 300
		String strTemp;
		WebElement elemU = driver.findElement(By.cssSelector("input.infaField"));
		WebElement elemP = driver.findElement(By.xpath("//div[@id='ifrm1_password']/input"));
		WebElement elemBut = driver.findElement(By.id("infaButton1"));

		
		elemU.click();
		elemU.clear();
		elemU.sendKeys("1234");

		elemP.click();
		elemP.clear();
		elemP.sendKeys("9876");
		
		elemBut.click();

/*		
        driver.findElement(By.cssSelector("input.infaField")).click();
        driver.findElement(By.cssSelector("input.infaField")).clear();
        driver.findElement(By.cssSelector("input.infaField")).sendKeys("1234");
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("5680");*/
		
        // driver.findElement(By.id("infaButton1")).click();
        strTemp=driver.findElement(By.cssSelector("div.infaLoginMsg")).getText();
        System.out.println("warning message DIV innerHTML is, '" +  strTemp + "'");
		
		
		System.out.println("Test005loginWarning: (isDisplayed) is" + driver.findElement(By.className("infaLoginMsg")).isDisplayed());
	//	assertTrue(driver.findElement(By.className("infaLoginMsg")).isDisplayed());
		/*  <div class="infaLoginMsg" style="">Either the user name does not exist or the password is not valid.</div>
		 	<div class="innerContainer">  
         	<div class="infaLoginMsg" style="display:none"></div>  		 */
	} // end Test005loginWarning

	
	@Test
	public void Test006loginSuccessLoadsNewPage() {
		WebElement elemUsr = driver.findElement(By.id("ifrm1_user")); //.clear();
		elemUsr.clear();
		elemUsr.sendKeys(USERNAME);
		try {
			this.wait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 WebElement elemPW =  driver.findElement(By.id("ifrm1_password")); //.clear();
		 elemPW.clear();
		 elemPW.sendKeys(PW);

		 // ifrm1_user   ifrm1_password ifrm1_blogin infaButton1
		 
		driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("County67");
		try {
			this.wait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("ifrm1_password ")).clear();  //.click();
		driver.findElement(By.id("infaButton1")).click(); 
		assertTrue(driver.findElement(By.className("infaLoginMsg")).isDisplayed());
		
		
	}      	

	@Test
	public void Test008FormFieldMax() {
		WebElement elem = driver.findElement(By.id("CopyRightVersion"));
		int inputLength = elem.getText().length();
		if ( inputLength <= maxLength) {
		System.out.println("PASS: User Entry Length " + inputLength + " is within MAX: " + maxLength + " chars.");
		} else { Assert.fail("FAIL: User Entry Length " + inputLength + " is GREATER than MAX: " + maxLength + " chars."); }
		
	}      	
	
	@Test
	public void Test009FormFieldEmptyWarning() {
		boolean boolTemp;
		WebElement elem = driver.findElement(By.id(idUser));
	//	AnyPageObjects.isVisibleReturnsInnerText(driver, by);
		boolTemp = (isDisplayedAndClickable(By.id(idUser), 10));
				if (boolTemp) {		
		//	Assert.assertTrue(!isVisibleBefore, idUser + "'s Required Warning visiblity is: " + false);
			elem.clear();
		//	elem.click();			
		} else Assert.fail("Test009FormFieldEmptyWarning: expected warning not visible");
				
/*		// boolean isVisibleBefore = elem.isDisplayed();
		System.out.println("BEFORE: warning visibility is: " + isVisibleBefore);
		// elem = driver.findElement(By.id(idUser));
		boolean isVisibleAfter = elem.isDisplayed();
		System.out.println("AFTER: warning visibility is: " + isVisibleBefore);
		Assert.assertTrue(isVisibleAfter, idUser + "'s Required Warning visiblity is: " + isVisibleAfter);*/
		
	}
	
	@Test
	public void Test009aBadEntryWarning() {
		boolean boolTemp;
		WebElement elem = driver.findElement(By.id(idPassword));
	//	AnyPageObjects.isVisibleReturnsInnerText(driver, by);
		boolTemp = (isDisplayedAndClickable(By.id(idPassword), 10));
				if (boolTemp) {		
		//	Assert.assertTrue(!isVisibleBefore, idUser + "'s Required Warning visiblity is: " + false);
			elem.clear();
			elem.sendKeys("PW");
		    this.isDisplayedAndClickable(By.id(idSubmitPWbutton), 10);
		//	elem.click();			
		} else Assert.fail("Test009aBadEntryWarning: expected warning not visible");
				
/*		// boolean isVisibleBefore = elem.isDisplayed();
		System.out.println("BEFORE: warning visibility is: " + isVisibleBefore);
		// elem = driver.findElement(By.id(idUser));
		boolean isVisibleAfter = elem.isDisplayed();
		System.out.println("AFTER: warning visibility is: " + isVisibleBefore);
		Assert.assertTrue(isVisibleAfter, idUser + "'s Required Warning visiblity is: " + isVisibleAfter);*/
		
	}      	
	
	
	@Test
	public void Test010FormFieldReadOnly() {
		WebElement elem = driver.findElement(By.id(idUser));
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

    
	@AfterSuite
    public void tearDown() {
        driver.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    
	 
	 public static int getResponseCode(String urlString) throws MalformedURLException, IOException{
		    URL url = new URL(urlString);
		    HttpURLConnection huc = (HttpURLConnection)url.openConnection();
		    huc.setRequestMethod("GET");
		    huc.connect();
		    return huc.getResponseCode();
		    // http://stackoverflow.com/questions/6509628/how-to-get-http-response-code-using-selenium-webdriver-with-java#6512785
		}

	 /*      --- -- - -- --- *** --- -- - -- ---  */	

		public static WebDriver chooseDriverFromBrowserName(String whichBrowser) {  // whichBrowser = prop.askBrowserName();
			WebDriver driver = null;
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
//					System.setProperty("webdriver.chrome.driver",("C:/Users/Allan/AppData/Roaming/Selenium/301/ChromeWin32_227/chromedriver.exe").replace("/","//"));
					System.setProperty("webdriver.chrome.driver",driverChrome);
		 driver  =  new ChromeDriver();
					 

				}
				else if(whichBrowser.equalsIgnoreCase("IEXPLORE")){
					//System.out.println("Internet Explorer DRIVER NOT YET INSTALLED, EXIT TEST");
					
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability("version", "latest");
					capabilities.setCapability("platform", Platform.WINDOWS);
					capabilities.setCapability("name", "Testing Selenium");
					
				{ 	System.setProperty("webdriver.ie.driver", driverIexplore); // file.getAbsolutePath() );
				    driver  = new InternetExplorerDriver(); 
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


}










/*
 * 
 * 
 * BUTTON, Log In
 * 
<div id="ifrm1_blogin" class="Ffield">
<button id="infaButton1" class="infaButton infaRoundedCorners" type="button">
<span class="infaButtonTextContainer">Log In</span>
</button>
</div>


 * 
 * Version 10.1.0
 * <span class="version" id="CopyRightVersion">Version 10.1.0</span>
 * 
 * 
 * Version 10.1.0 Copyright © 1993-2016 Informatica LLC. See patents at https://www.informatica.com/legal/patents.html. All rights reserved
	<div class="copyRightYear">
	<span id="CopyRightVersion" class="version">Version 10.1.0</span>
	<span id="CopyRightYear" class="year">Copyright © 1993-2016 Informatica LLC. See patents at https://www.informatica.com/legal/patents.html. All rights reserved.</span>
	<div class="clear-both"/>
	</div>
	#CopyRightVersion
	html body div#mainContainer.mainContainer span#CopyRightVersion.version
	<span class="version" id="CopyRightVersion">Version 10.1.0</span>
	
 * *
 */



/*
 * I have created a wrapper method click() in a MyElements class that looks like this:
 public static void click(WebDriver driver, By by) {
 (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
  driver.findElement(by).click();  }
 // Of course, the 10 second timeout is arbitrary and it’s better to replace this with some constant value. 
 // Now, every time I want to perform a click in my test I can simply call:
?
MyElements.click(driver, By.id("loginButton");
MyElements.getPropsOrNull(driver, By.id("loginButton");
MyElements.isVisibleReturnsInnerText(driver, By.id("loginButton");

<div class="error-code" jscontent="errorCode" jstcache="7">ERR_NAME_NOT_RESOLVED</div>

 *     	
 */

//("http://ipg-mdm-mdmdv:8080/e360/com.informatica.tools.mdm.web.auth/login");

/*        driver.findElement(By.cssSelector("img")).click();
driver.findElement(By.cssSelector("div.infaPHeaderLogo")).click();
driver.findElement(By.cssSelector("div.loginFormContainer")).click();
driver.findElement(By.cssSelector("label")).click(); 

*
*
*LOGIN-wip-itape vpn info-2017-05-09-0956.txt
*<div class="infaLoginMsg" style="">Either the user name does not exist or the password is not valid.</div>
*
div class="innerContainer">  
                <div class="infaLoginMsg" style="display:none"></div>
                <div id="LoginForm"> </div>
                <div id='customLogoImage'></div>
            </div>

*
*<div class="infaLoginMsg">Either the user name does not exist or the password is not valid.</div>
*#mainContainer > div.innerContainer > div.infaLoginMsg
*
*<div class="infaLoginMsg">Either the user name does not exist or the password is not valid.</div>
*//*[@id="mainContainer"]/div[3]/div[1]
*
        driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        	// driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("County67");

        driver.findElement(By.cssSelector("input.infaField")).click();

        driver.findElement(By.xpath("//label[@for='ifrm1_password']")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.id("LoginForm")).click();
        driver.findElement(By.id("infaButton1")).click();

        \
                driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        	// driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("County67");

        driver.findElement(By.cssSelector("input.infaField")).click();

        driver.findElement(By.xpath("//label[@for='ifrm1_password']")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.id("LoginForm")).click();
        driver.findElement(By.id("infaButton1")).click();
        // class="infaLoginMsg" style="display:none"

        // infaLoginMsg 
        
        

        driver.findElement(By.cssSelector("div.infaLoginMsg")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        driver.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys();
        driver.findElement(By.cssSelector("input.infaField")).click();
           driver.findElement(By.id("infaButton1")).click();


        driver.findElement(By.cssSelector("input.infaField")).clear();
        driver.findElement(By.cssSelector("input.infaField")).sendKeys();
        driver.findElement(By.cssSelector("div.loginFormContainer")).click();
        driver.findElement(By.id("infaButton1")).click();

http://www.obeythetestinggoat.com/how-to-get-selenium-to-wait-for-page-load-after-a-click.html

    @contextmanager
    def wait_for_page_load(self, timeout=30):
        old_page = self.browser.find_element_by_tag_name('html')
        yield
        WebDriverWait(self.browser, timeout).until(
            staleness_of(old_page)
        )

    def test_stuff(self):
        # example use
        with self.wait_for_page_load(timeout=10):
            self.browser.find_element_by_link_text('a link')
            # nice!

*
*
*looks like this:
 public static void click(WebDriver driver, By by) {
 (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
  driver.findElement(by).click();  }
 // Of course, the 10 second timeout is arbitrary and it’s better to replace this with some constant value. 
 // Now, every time I want to perform a click in my test I can simply call:
?
MyElements.click(driver, By.id("loginButton");
MyElements.getPropsOrNull(driver, By.id("loginButton");
MyElements.isVisibleReturnsInnerText(driver, By.id("loginButton");
*
*
*/
