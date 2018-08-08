// 1. get basic @Test Annotations @Before @After to work
// 2. make UNIVERSAL JUNIT TEST FOR ANY WEB ELEMENT       
// 2. read URL and TEST ENVIRON CONSTANTS from

// Element must be user-editable in order to clear it.
// FAILED: Test002_nextPageCopyRight




// ifrm1_user   ifrm1_password ifrm1_blogin infaButton1 CopyRightVersion CopyRightYear

package jUnitScrubAllBrowsers;

//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

// import jUnitScrubAllBrowsers.FileWrite.LogCode; needs a filename before it can be called

  // import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

// import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.*; //ChromeDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test00_NGtest {

	ChromeDriver wd;
	    
	String driverChrome="C:\\Users\\Allan\\AppData\\Roaming\\Selenium\\301\\ChromeWin32_227\\chromedriver.exe";
    String driverFirefox="C:\\Users\\Allan\\workspace\\Selenium\\webdriver\\Firefox\\geckodriver.exe";
    String driverIexplore="C://Users//Allan//AppData/Roaming//Selenium//301//IEDriverServer_x64_340//IEDriverServer.exe";
    
    //String str101chars = "a0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789"
    static String idUser = "ifrm1_user";
    static String idPassword ="ifrm1_password";
    static String TargetURL = "http://ipg-mdm-mdmdv:8080/bdd";
    static String reDirTargetURL="http://ipg-mdm-mdmdv:8080/e360/com.informatica.tools.mdm.web.auth/login";
    static String expectTitle="Informatica MDM";  
    static String CopyRightVersion="Version 10.1.0";
    static String CopyRightYear="Copyright © 1993-2016 Informatica LLC. See patents at https://www.informatica.com/legal/patents.html. All rights reserved.";    
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
    public int dimwidth=650; public int dimheight=450;
    
    

    
    @BeforeClass   
    public void setUpFirefox()  {
       
    System.setProperty("webdriver.chrome.driver", driverChrome);
 	wd = new ChromeDriver();  // wd = new FirefoxDriver(); //
    //    System.setProperty("webdriver.gecko.driver", driverFirefox );
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // wd.manage().window().maximize();
        wd.manage().window().setSize(new Dimension(dimwidth,dimheight));
        wd.get(TargetURL);
        
        // getTestName
        // make Logfile for TestName
        
        } // @Before setUP()
    
	@Test
	public void Test001_LoginPageLoads() { // Test006loginSuccessLoadsNewPage() 
	    String thisTitle = wd.getTitle();
		String thisURL = wd.getCurrentUrl();
// loaded page will have redirected URL  reDirTargetURL
		Assert.assertTrue(thisURL.equalsIgnoreCase(reDirTargetURL),TargetURL + " resolved to " + thisURL);
// loaded page will have ExpectedTitle expectTitle
		Assert.assertTrue(thisTitle.equalsIgnoreCase(expectTitle),expectTitle + " should match actual: " + thisTitle);
		WebElement elemUsr = wd.findElement(By.id("ifrm1_user")); //.clear();
		boolean isDisplayed = elemUsr.isDisplayed();
		Assert.assertTrue(isDisplayed,"Element ifrml_user " + " visibility is " + isDisplayed);
	}      	

/*    @Test
    public void Test002_nextPageCopyRight() {
       	WebElement elem = wd.findElement(By.id("CopyRightYear")); // watch out: ID is a "string" in HTML, but variable CopyRightYear in Java 
       	
     String strTemp = "CopyRight string found is : " + elem.getText();
       	System.out.println(strTemp);
       	
    	Assert.assertNotNull(elem);
    	Assert.assertSame(CopyRightYear, elem.getText());	
    	Assert.assertTrue(strTemp.contains("Copyright © 1993-2016"));

    } */
    

    @Test
    public void Test003_loginButtonExists() {
    	Assert.assertTrue(wd.findElement(By.id("infaButton1")).isEnabled());  // .click();      
    }
    
    

    @Test
    public void Test004_loginFormUsername() {
    	Assert.assertTrue(wd.findElement(By.id("ifrm1_user")).isEnabled());  // .click();      
    }

    @Test
    public void Test004_loginFormPasswordTextbox() {
    	Assert.assertTrue(wd.findElement(By.id("ifrm1_password ")).isEnabled());  // .click();      
    }  // end Test004_loginFormPasswordTextbox

    
	@Test
	public void Test005loginWarning() {   //TODO ADD TEXT FOR MAX CHARS 300
//	 WebElement elemU = wd.findElement(By.id("ifrm1_user"));
//	 WebElement elemP = wd.findElement(By.id("ifrm1_password"));
// WebElement elemU = wd.findElement(By.cssSelector("#ifrm1_user > input:nth-child(1)"));
// WebElement elemP = wd.findElement(By.cssSelector("#ifrm1_password > input:nth-child(1)"));
		
//	    Assert.assertTrue(driver.findElement(commHome).isDisplayed());
//	    By commHome = By.id("home-newProjectModalOpen");

		
	/*	WebElement elemU = wd.findElement(By.xpath(".//*[@id='ifrm1_user']/input"));
		WebElement elemP = wd.findElement(By.xpath(".//*[@id='ifrm1_password']/input"));
		*/
	
		//         wd.get("http://ipg-mdm-mdmdv:8080/e360/com.informatica.tools.mdm.web.auth/login");
        wd.findElement(By.cssSelector("input.infaField")).click();
        wd.findElement(By.cssSelector("input.infaField")).clear();
        wd.findElement(By.cssSelector("input.infaField")).sendKeys("1234");
        
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("5680");
        
        wd.findElement(By.id("infaButton1")).click();
        
/*        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.cssSelector("div.innerContainer")).click();
        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
*/		
		
		
/*		elemU.sendKeys("abc"); //  .ty  .clear();
		elemP.sendKeys("xyz");    // clear();  //.click();
*/		
		System.out.println("Test005loginWarning display and clickable state is : " + isDisplayedAndClickable(By.id("infaButton1"), 10));
		
	      int userhitthis = askUserYN("PAUSING FOR YOUR OK", "PRESS ENTER ANY BUTTON TO CONTINUE");
			System.out.println("paused at line 163: user hit " + userhitthis);
			System.out.println(wd.findElement(By.className("infaLoginMsg")).isDisplayed());

			
/*			"//input[@id='gbqfq']"
			
	     // wd.findElement(By.id("infaButton1")).click(); 
	      wd.findElement(By.id(".//*[@id='infaButton1']")).click();
	      WebDriverWait wait = new WebDriverWait(driver, 20);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div[2]/span/center/form/table/tbody/tr/td[2]/div/div/input")));

      ebDriver driver = new FirefoxDriver();
      String baseUrl = "http://www.google.com";
      Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
      selenium.open("http://www.google.com");
      selenium.isElementPresent(XPath Variable);
      driver.findElement(By.id("gbqfq")).sendKeys("xyz");*/

	//	Assert.assertTrue(wd.findElement(By.className("infaLoginMsg")).isDisplayed());
		/*  <div class="infaLoginMsg" style="">Either the user name does not exist or the password is not valid.</div>
		 	<div class="innerContainer">  
         	<div class="infaLoginMsg" style="display:none"></div>  		 */
			
	} // end Test005loginWarning

	

	@Test
	public void Test006FormFieldRequired() {
		
		WebElement elem = wd.findElement(By.id(idUser));
		if (elem==null) {System.out.println("EXPECTED Element id " + idUser + "is NULL");}
		boolean isVisibleBefore = elem.isDisplayed();
//		Assert.assertTrue(!isVisibleBefore, idUser + "'s Required Warning visiblity is: " + false);
		elem.click();
		// elem = wd.findElement(By.id(idUser));
		boolean isVisibleAfter = elem.isDisplayed(); 
		
		System.out.println(idUser + "'s Required Warning visiblity BEFORE is: " + isVisibleBefore + ". Expected " + true);
		
		System.out.println(idUser + "'s Required Warning visiblity AFTER is: " + isVisibleAfter + ". Expected " + true);
		
	//	Assert.assertTrue(isVisibleAfter, idUser + "'s Required Warning visiblity is: " + isVisibleAfter + ". Expected " + true);
	}      	
	
		
	      	
	
	@Test
	public void Test010FormFieldReadOnly() {
		WebElement elem = wd.findElement(By.id("CopyRightYear"));
		boolean isOK = elem.isEnabled();
		  // 		Assert.assertEquals(strActual, CopyRightYear, "found " + strActual + ", expected: " + CopyRightYear);
		System.out.println("Element expected to be READ ONLY (disabled) is: " + isOK);
		Assert.assertTrue(isOK,"Element expected to be READ ONLY (disabled) is: " + isOK);
	}      	
	
		
    
    
    @Test
    public void Test100logElemAttributes() {
    	WebElement elem = wd.findElement(By.id("CopyRightVersion"));
    	System.out.println(elem.getTagName() 
    			+ DELIMCSV + elem.getAttribute("id") 
    			+ DELIMCSV + elem.getAttribute("name")
    			+ DELIMCSV + elem.getAttribute("value")  			
    			+ DELIMCSV + elem.getAttribute("class")
    			+ DELIMCSV + elem.getAttribute("style")
    			+ DELIMCSV + elem.getAttribute("innerHTML")
    			+ DELIMCSV + elem.getAttribute("outerHTML")   			
    			);
        elem.click();
 
 
        Assert.assertNotNull(elem);
    }

    
    
 

	@AfterClass
    public void tearDown() {
        wd.quit();
	} // end tear down @AfterClass

// NO TESTS BELOW

	
	
	
	
	
	
	
	
	
	
	
public Boolean isDisplayedAndClickable(By locator, Integer timeout) {
    try {
        WebDriverWait wait = new WebDriverWait(wd, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    } catch (org.openqa.selenium.TimeoutException exception) {
        return false;
    }
    return true;
}
	
	
	
	
	
	
	public static int askUserYN(String msgTitle, String msg) {                  //(WebDriver driver) {
		int qy = JOptionPane.showConfirmDialog(null,
				 msg, msgTitle,JOptionPane.YES_NO_OPTION);		
		return qy;  //  if (qy==1 || qy==-1 ) { System.out.println("CANCELLED BY USER!"); System.exit(1);  	 }
	}

	
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }


}  // end of file / class










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

/*        wd.findElement(By.cssSelector("img")).click();
wd.findElement(By.cssSelector("div.infaPHeaderLogo")).click();
wd.findElement(By.cssSelector("div.loginFormContainer")).click();
wd.findElement(By.cssSelector("label")).click(); 

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
        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        	// wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("County67");

        wd.findElement(By.cssSelector("input.infaField")).click();

        wd.findElement(By.xpath("//label[@for='ifrm1_password']")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.id("infaButton1")).click();

        \
                wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        	// wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys("County67");

        wd.findElement(By.cssSelector("input.infaField")).click();

        wd.findElement(By.xpath("//label[@for='ifrm1_password']")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.id("infaButton1")).click();
        // class="infaLoginMsg" style="display:none"

        // infaLoginMsg 
        
        

        wd.findElement(By.cssSelector("div.infaLoginMsg")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).click();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).clear();
        wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")).sendKeys();
        wd.findElement(By.cssSelector("input.infaField")).click();

        wd.findElement(By.cssSelector("input.infaField")).clear();
        wd.findElement(By.cssSelector("input.infaField")).sendKeys();
        wd.findElement(By.cssSelector("div.loginFormContainer")).click();
        wd.findElement(By.id("infaButton1")).click();

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


    	Assert.assertTrue(strTemp.contains("© 1993"));
   	Assert.assertTrue(strTemp.contains("2016"));
    	
    	// Copyright © 1993-2016
        //("http://ipg-mdm-mdmdv:8080/e360/com.informatica.tools.mdm.web.auth/login");
   
      // wd.findElement(By.cssSelector("input.infaField")).click();
*
*
* .//*[@id='mainContainer']/div[3]/div[1]/text()
* .//*[@id='mainContainer']/div[3]/div[1]
* innerText
* .infaLoginMsg
* ownerElement.firstChild.data
*/



/*		WebElement elemU = wd.findElement(By.xpath("//div[@id='ifrm1_user']/input")); 
WebElement elemP = wd.findElement(By.xpath("//div[@id='ifrm1_password']/input")); */

//	WebElement elemU = wd.findElement(By.xpath("//div[@id='ifrm1_user']/input")); 
//	WebElement elemP = wd.findElement(By.xpath("//*[@id=\"ifrm1_password\"]/input")); 

/*
 * <input class="infaField" type="password" value="" placeholder="" name="" maxlength="200" style="width: 300px;">
 * 
 * <input class="infaField" type="text" value="" placeholder="" name="" maxlength="200" style="width: 300px;">
 * 
 * #ifrm1_user > input  )select9or(
 * //*[@id="ifrm1_password"]/input
 * 
 * <span class="infaButtonTextContainer">Log In</span>
 * <button type="button" class="infaButton infaRoundedCorners" id="infaButton1"><span class="infaButtonTextContainer">Log In</span></button>
 * #infaButton1
 * //*[@id="infaButton1"]
 *       By passiveRepeaterHoverAntennaField = By.xpath("//*[text() = 'Antennas' and @class = 'uppercase']");
 *       
 *       <input class="infaField" value="" placeholder="" name="" maxlength="200" style="width: 300px;" type="text">
 *       
 *       #ifrm1_user > input:nth-child(1)
 *       html body div#mainContainer.mainContainer div.innerContainer div#LoginForm.ui-widget.infaForm-Login div#ifrm1_.infaForm div.infaFormField div#ifrm1_user.Ffield input.infaField
 *       
 *       #ifrm1_password > input:nth-child(1)

 *            WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.pl/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
        WebElement element = driver.findElement(By.name("q")); 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
WebElement query = driver.findElement(By.xpath("//html/body/div[2]/span/center/form/table/tbody/tr/td[2]/div/div/input"));

query.sendKeys("asd")
 *       
 *       
 */
