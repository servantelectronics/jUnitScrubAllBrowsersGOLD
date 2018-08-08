// TODO SEPARATE HISTORY FILE LOG DURATION, TESTNAME, COUNT TO SEPARATE FILE // MOVE FILENAME TO LOG ROUTINE
//    out of scope: history: one line per run/spring? line has  config CSV and steps and duration, filecount, average
// TODO DONE ENHANCE FILENAME by ClassNameSimple
// TODO DONE AUTO SELECT FILE EXTENSION as CSV or HTML
// TODO conditional <BR /> when output file is HTML
// TODO log output file uses window ENVIRONMENT varialbe: what about Apple, Linux
// TODO trap for NULL and formatting ERRORs in Config file

//  TODO  2017-05-11-080049 two stub JUNIT @Tests fail and three pass
// last ran last ran Saturday 2017-05-13

package NGTests;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;

//import static java.util.Arrays.asList;

import java.util.Calendar;
import java.util.List;
// import java.util.Iterator;
//import java.util.List;
import java.util.Properties;
/* import java.io.*;
 import java.util.Properties;
 
 import org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.Arrays.asList;

 import javax.swing.Action;

import java.util.logging.Logger;
*/
import javax.swing.JOptionPane;

import org.apache.commons.lang3.time.DurationFormatUtils;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.BeforeClass;
// import org.junit.After;
//import org.junit.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jUnitScrubAllBrowsers.FileWrite;
import jUnitScrubAllBrowsers.FileWrite.LogCode;
import jUnitScrubAllBrowsers.LogPassFail;
import jUnitScrubAllBrowsers.ReadPropertyFile;

public class WebSmokeInventoryWIPTESTNG{   // WebSmokeInventory WebSmokeInventoryGolden.java
	// C:\Users\Allan\workspace\SeleniumScrubSmoke201705021400\jUnitScrubAllBrowsers
	public static String logPathFilename;  
	public String tempLogFile ;

	public String errMessages; // append errMessages until a file name / path is selected from environment
	public WebDriver driver;  
	static int MAXCHARS = 99;
	public static long startTestTime; 
	public long startUserTime;
	public static long endTestTime;
	public long endUserTime;
	// BELOW will initialized when file config.properties is read
	public static String TargetURL;  
	private static String whichBrowser ; 
	public static String expectTitle; 
	public static String xpTarget; 
	public static String EXCLUDETAGS; // = "HEADHTMLBODYDIVMETASCRIPTLINKFONT"; 
	public static String flagClearCookiesYN;
	public static String flagUserConfirmationYN;
	public static String flagWindowMinimizeYN;
	public static String driverIexplore;
	public static String driverFireFox;
	public static String driverChrome;
	public static String dotExtension;

	public static ReadPropertyFile prop=null;
	// public static String BaseFileName = getClass().getSimpleName();
	String BaseFileName = getClass().getSimpleName(); // this.getClass().getEnclosingMethod().getName();
	
/// BEGIN TESTS 'xMAIN'  testername=awhitworth@surgeforward.com
	
	@BeforeClass
	public void xmain() { //String[] args) throws InterruptedException {  throws Exception
		
		 PrepareTestEnvironment();
		// Method lastMethodCalled = this.getClass().getEnclosingMethod();
		 
		 
// START TEST: NAVIGATE THEN OUTPUT INVENTORY TO FILE 		
        System.out.println("Starting TEST of " + TargetURL + " at " + datetimestamp()+ 0);
         startTestTime = System.nanoTime();  
        
	if (prop.readFlagClearCookiesYN().equalsIgnoreCase("Y")) { 
		System.out.println("clearing cookies");
		driver.manage().deleteAllCookies();
		driver.manage().window().setSize(new Dimension(350, 450));
		FileWrite.writeln(logPathFilename, "COOKIES CLEARED");
	} else {FileWrite.writeln(logPathFilename, "COOKIES NOT CLEARED");}

         
        FileWrite.logthis(LogCode.INFO, "Navigating to TargetURL: " + TargetURL);
        driver.navigate().to(TargetURL);   //.back(); 		// driver.get(TargetURL);		



/*	testTitle();
	testRedirection();
	test001_CCform004_caption();
	test001_CCform003_CustomerTier(); 
	test001_CCform002_Reorder();
	test001_FindbyXPath();  
	test001_CCform001a();  
	endTestCleanup(); */
	
	
	
	
//  used endTestTime = System.nanoTime(); within elapsedtime inside of test99Cleanup   
// test99Cleanup();
		
} // end of xMain test 

	/*      --- -- - -- --- *** --- -- - -- ---  */
		@Test
		public void  printlnConfigProperties() throws IOException { //  main(String[] args) throws IOException {
				
	//	System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); // output : doit
	// 	Method lastMethodCalled = this.getClass().getEnclosingMethod();
	//	System.out.println(lastMethodCalled.getName());
			
			Properties prop = new Properties();
			InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream("config.properties");
			// /jUnitTemplate0425a/src/config.properties
			prop.load(input);

			System.err.println("test reading property should show url: " + prop.getProperty("baseURL"));	
	//		System.err.println("url: "  + prop.getProperty("url"));
			System.err.println("username: "  + prop.getProperty("username"));
			System.err.println("password: "  + prop.getProperty("password"));		
			System.err.println("driverFirefox: "  + prop.getProperty("driverFirefox"));
			System.err.println("flagClearCookiesYN: "  + prop.getProperty("flagClearCookiesYN"));
			System.err.println("driverchrome:"  + prop.getProperty("driverChrome"));
			System.err.println("driveriexplore: "  + prop.getProperty("driverIexplore"));
			System.err.println("flagUserConfirmationYN: "  + prop.getProperty("flagUserConfirmationYN"));
			System.err.println("flagWindowMinimizeYN:"  + prop.getProperty("flagWindowMinimizeYN"));
			System.out.println(prop.stringPropertyNames());
			
			}		
		
		@Test
		public void testCompareTitles()  {
			Method lastMethodCalled = getClass().getEnclosingMethod();
			String strTemp = driver.getTitle();
			System.out.println("test if expectTitle (" + expectTitle + ")"
					+ "equals current title(" + strTemp +  ")");
			/// assertEquals("Google", driver.getTitle());  	//	TestCase.
			//  			assertNotEquals(expectTitle, strTemp);
			// verifySameStringAnyCase verifySameStringExactCase verifyContainsSubstring
			LogPassFail.verifySameStringAnyCase("pasted testCompareTitles",expectTitle,strTemp);
			LogPassFail.verifySameStringAnyCase(lastMethodCalled.toString(),expectTitle,strTemp);
		} // testCompareTitles
		


	@Test
	public void testRedirection() throws Exception {
		String strTemp = driver.getCurrentUrl();
		String strMessage = "begin @Test REDIRECTION comparing TargetURL: ("+ TargetURL + ") with getCurrentUrl ("+ strTemp+")";
		System.out.println(strMessage);
		FileWrite.logthis(LogCode.INFO, strMessage);		
		AssertJUnit.assertEquals(strTemp ,TargetURL);
	}
		
	
@AfterClass
	public void test99Cleanup() { // SWAG calls CLOSE inside try/catch and at end ??
	endTestTime = System.nanoTime(); 
		System.out.println("Ready to shutdown from within test99Cleanup");
		
		FileWrite.logthis(FileWrite.LogCode.INFO, "Ready to shutdown");
		
			driver.close();
			driver.quit();
		
		String prettyTimeFormat = reportElapsedTime();  // endTestTime, startTestTime
		FileWrite.logthis(FileWrite.LogCode.INFO, "Elapsed Time: " + prettyTimeFormat); 
		FileWrite.writeln(logPathFilename, "***END OF TEST*** at " + prettyTimeFormat); 
			if (!(driver==null)){
				driver.close();
				driver.quit();			}
		System.exit(0);// TestElapsedTime

	}  // test99Cleanup

	
	
	
	
	
	

	
	
	public void endTestCleanup() {
		String strMsg ="Starting @Test: endTestCleanup";
		endTestTime = System.nanoTime();  // used within elapsedtime inside of test99Cleanup
		FileWrite.logthis(LogCode.INFO, strMsg);
		System.out.println(strMsg);
	//	test99Cleanup( driver); // closes browser when called from inside xMain but not from separat @Test nor @After
		
		// below should be redundant, unnecessary / copied from because Firefox not closing after I moved test99Cleanup out of xmain into @After 
		// if (!(driver==null)){
/*			driver.close();
			driver.quit();
			try {
				wait(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);// TestElapsedTime
*/		
	}



/*      --- -- - -- --- END NO TEST BELOW, non test methods only *** --- -- - -- ---  */

















	/*      --- -- - -- --- *** --- -- - -- ---  */ 

	static String getReportFileExtension(ReadPropertyFile prop) { // called BEFORE FILEWRITE makes LOGNAME
		if (prop.YNreportCSV().toUpperCase()==prop.YNreportHTML().toUpperCase()) {
			System.out.println("config.properties CONFLICT - choose ONLY one of CSV and HTML reports\n switching to CSV");
			return "CSV";
		} else if (prop.YNreportCSV().toUpperCase().equalsIgnoreCase("Y"))        {
			System.out.println("Logging to CSV to " + logPathFilename);
			return "CSV"; 
			 }
		else if  (prop.YNreportHTML().toUpperCase().equalsIgnoreCase("Y"))      
		   { System.out.println("Logging HTML to " + logPathFilename);
			return "HTML";
		   }	
		else { //	FileWrite.logthis(LogCode.WARN, "config.properties has INVALID VALUE: Logging will default to CSV to " + logPathFilename);
		System.out.println("config.properties has INVALID VALUE: Logging CSV to " + logPathFilename);
		return "CSV";
			
		}  // end if else if else    YNreportCSV
	}
	

	 /*    --- -- - -- --- *** --- -- - -- ---  */  
	public  void writeWebElementsToCSV(WebDriver driver) { 	//(Logger logger, WebDriver driver) {
		
	//	 final String CSVtableBegin = "";
		 final String ROWNEW = "\""; 
		 final String ROWEND = "\"\n";
		 final String DELIM = "\",\""; 
		 
	List<WebElement> elements=driver.findElements(By.xpath(xpTarget));   //       t=".//*
		
	// write top row of caption / column names
	FileWrite.writeln(tempLogFile,ROWNEW + "#" 
			+ DELIM + "tagName" 
			+ "</td><td width=\"20%\">" + "xPath" 
			+ DELIM + "ID" 
			+ "</td><td width=\"20%\">" + "CLASS" 
			+ DELIM + "NAME" 
			+ DELIM +  "value"			
			+ DELIM +  "innerHTML"
			+ DELIM +  "outerHTML"			
			+ DELIM + "Style"						
			+ ROWEND );	
	 
	//Now iterate through List and do the required operation with each individual element
	int zy = 0; // initialize outside of loop, only increment when TAB not excluded
		// System.out.println(ROWNEW + zy + DELIM + "tagName" + DELIM + "id" + DELIM + "class" 		+ DELIM + "name" + DELIM + "getText" + ROWEND );

	FileWrite.writeln(tempLogFile,ROWNEW + "#" + DELIM + "tagName" + DELIM + "xPath" + DELIM + "tagName" + DELIM + "id" + DELIM + "class" + DELIM + "name" + DELIM + "getText" + ROWEND );
		
	for(WebElement ele:elements) {
		  if (!EXCLUDETAGS.contains(ele.getTagName().toUpperCase()) ) {
			zy++;
							//    ele.getText();   //It will print innertext of each element
				FileWrite.write(tempLogFile,ROWNEW);
				FileWrite.write(tempLogFile,zy + DELIM);		
				FileWrite.write(tempLogFile,ele.getTagName() + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("xpath")  + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("id") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("class") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("name") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("value") + DELIM);				
				FileWrite.write(tempLogFile,ele.getAttribute("innerHTML") + DELIM); // getText
				FileWrite.write(tempLogFile,ele.getAttribute("outerHTML") + DELIM); // getText		
				FileWrite.write(tempLogFile,ele.getAttribute("style") );					
				
				
			}  // if not excluded
	 } // for each webelement
  }  // end method writeWebElements


	
	
//	      *****   *****   *****   ~ ~ ~ ~     *****   *****   *****      
	
	public  int writeWebElementsToHTML(WebDriver driver) {
		int x = 1;
	
		// CONSTANTs for logging to an HTML table
 		final String HTMLtableBegin = "<TABLE align=left border=1 width=\"88%\">";
 		final String HTMLtableEnd = "</TABLE>";		
// 		final String ROWTOPCENTERED = "<TR valign=top align=center><TD>";		
 		final String ROWNEW = "<TR ><TD>" ;  	// String ROWNEW = "<TR valign=top><TD>" ;
 		final String ROWEND = "</TD></TR>" ;  // " &nbsp; </TD></TR>" ;
 		final String DELIM = "</TD><TD>" ;
 	//	@SuppressWarnings("unused")
		String bgcolor = ""; // light green #0D0D0A	lightcyan #0C0CFF;	

// EXCLUDE TAGS FROM REPORT //TODO ADD MAX NOTATION TO LIMIT WHEN DIV IS TOO BIG		
		List<WebElement> elements=driver.findElements(By.xpath(xpTarget));
		// String[] excludeList = EXCLUDETAGS.split(";")	;	  // input.split("\\s+");
		List<String> excludeList = Arrays.asList(EXCLUDETAGS.split("\\s*,\\s*")); //	asList(EXCLUDETAGS.split("\\s*,\\s*"));
//		INCLUDE IN TEST RESULTS HEADER: EXCLUDEDTAG LIST  //List<String> list = ["A", "B", "C"];
		System.out.println("THESE HTML TAGS WILL BE EXCLUDED:");
		int xyz=0;
		for (String element: excludeList) {
		      System.out.println(++xyz + ": " + element);
		}

		FileWrite.write(tempLogFile,HTMLtableBegin);
		//Now iterate through List and do the required operation with each individual element
		int zy = 0;

// write top row of column names	
		FileWrite.writeln(tempLogFile,ROWNEW + "#" 
				+ DELIM + "tagName" 
				+ "</td><td width=\"20%\">" + "xPath" 
				+ DELIM + "ID" 
				+ "</td><td width=\"20%\">" + "CLASS" 
				+ DELIM + "NAME" 
				+ DELIM +  "value"			
				+ DELIM +  "innerHTML"
				+ DELIM +  "outerHTML"			
				+ DELIM + "Style"						
				+ ROWEND );	
		for(WebElement ele:elements)
		{ if ( 	!(excludeList.contains(ele.getTagName().toUpperCase())) )
		 { 	zy++;
		 	if ( (zy & 1) == 0 ) { bgcolor = "''"; } else { bgcolor = "BGCOLOR='#c5ffff;'"; }	// lightgreen #0D0F0A #0D0D0A	lightcyan #0C0CFF;
		  	// String bgcolor = "#dcf395;"; //  #dcf395;	green #0D0D0A;	lightcyan #0C0CFF;	#c5ffff;

//TODO TESTTEST WITH EXCEPTIONS but in REALTEST USE NOT! EXCLUDEFLAG CONTAINS		
						
				FileWrite.write(tempLogFile,"<TR valign=top " + bgcolor + "><TD>");  
			//	FileWrite.write(tempLogFile,ROWNEW);			// FileWrite.write(tempLogFile,ROWNEW);		
				FileWrite.write(tempLogFile,zy + DELIM);
				FileWrite.write(tempLogFile,ele.getTagName() + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("xpath")  + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("id") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("class") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("name") + DELIM);
				FileWrite.write(tempLogFile,ele.getAttribute("value") + DELIM);				
				FileWrite.write(tempLogFile,ele.getAttribute("innerHTML") + DELIM); // getText
				FileWrite.write(tempLogFile,ele.getAttribute("outerHTML") + DELIM); // getText		
				FileWrite.write(tempLogFile,ele.getAttribute("style") );				
				FileWrite.writeln(tempLogFile, ROWEND);
				
			//	int maxLength = (ele.getText().length() < MAXCHARS)?ele.getText().length():MAXCHARS;
			//	System.out.print(ele.getText().substring(0, maxLength)); 		System.out.println(ROWEND);
					
			} else {FileWrite.logthis(LogCode.WARN, "Excluded TAG: "+ ele.getTagName() );} //if !EXCLUDETAG..
		}  // end for each element
		FileWrite.write(tempLogFile,HTMLtableEnd);
		return x;
	} // end writeElementsToHTML




	public String reportElapsedTime(/* uses constants */) {
	//	endTestTime = System.nanoTime(); // long estimatedTime = System.nanoTime() - startTime;
		long TestElapsedTime = endTestTime - startTestTime; // Duration.ofNanos(
		java.time.Duration.ofNanos(TestElapsedTime); // .ofMillis(TestElapsedTime);
		String prettyTimeFormat = DurationFormatUtils.formatDuration(TestElapsedTime / 1000000, "HH:mm:ss.S");
		System.out.println("Elapsed Time: " + prettyTimeFormat); // nanoseconds  TestElapsedTime
		return prettyTimeFormat;
	}

/*      --- -- - -- --- *** --- -- - -- ---  */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*      --- -- - -- --- *** --- -- - -- ---  */
	public  void EMERGENCY_STOP_TEST(String Warning) { // SWAG calls CLOSE inside try/catch and at end ??
		System.out.println("SYSTEM EXIT: " + Warning);
		FileWrite.logthis(FileWrite.LogCode.INFO, "SYSTEM EXIT: "+ Warning);
		// String prettyTimeFormat = reportElapsedTime(endTestTime, startTestTime);
		// FileWrite.logthis(FileWrite.LogCode.INFO, "Elapsed Time: " + prettyTimeFormat); // prettyTimeFormat);
	
	FileWrite.writeln(logPathFilename, "***END OF TEST***"); // uses parent's WEBDRIVER
	
	try {
	System.out.println("shutting down driver / open browser windows of '' " + whichBrowser +"''");
		driver.close();
		driver.quit();
	} catch (Exception e) {
// TODO Auto-generated catch block
		FileWrite.writeln(logPathFilename, "failed to close driver: MANUALLY CLOSE any REMAINING instances of '' " + whichBrowser +"''");  
		e.printStackTrace();
	}

		// driver.close();
/*	if (!(driver==null)){
		driver.close();
		driver.quit();
	}*/
		System.exit(999);
	}

	
/*      --- -- - -- --- *** --- -- - -- ---  */
	public static int askUserYN(String msgTitle, String msg) {                  //(WebDriver driver) {
		int qy = JOptionPane.showConfirmDialog(null,
				 msg, msgTitle,JOptionPane.YES_NO_OPTION);		
		return qy;  //  if (qy==1 || qy==-1 ) { System.out.println("CANCELLED BY USER!"); System.exit(1);  	 }
	}
	
	
/*      --- -- - -- --- *** --- -- - -- ---  */	

	public static WebDriver chooseDriverFromBrowserName(String whichBrowser) {
		WebDriver driver= null;
		if (whichBrowser.equalsIgnoreCase("Firefox")) {
			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("version", "latest");
			capabilities.setCapability("platform", Platform.WINDOWS);
			capabilities.setCapability("name", "Testing Selenium");
			
		    System.setProperty("webdriver.gecko.driver", driverFireFox ); // "C:\\Users\\Allan\\workspace\\Selenium\\webdriver\\Firefox\\geckodriver.exe");
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

/*      --- -- - -- --- *** --- -- - -- ---  */	
	public static Duration  timediff(long before, long after) { // uses int sometime = System.nanoTime();

		long td = (after - before)/1000000; 
		String prettyTimeFormat = DurationFormatUtils.formatDuration(td, "HH:mm:ss.S");
 System.out.println("prettyTimeFormat diff: " + prettyTimeFormat);
 System.out.println("Duration of java.time nanos: " + java.time.Duration.ofNanos(td));
		// endTestTime = System.nanoTime();   // long estimatedTime = System.nanoTime() - startTime;
 
		return   java.time.Duration.ofNanos(td) ;		
					
	} // timediff

	
/*      --- -- - -- --- *** --- -- - -- ---  */	
	public static String datetimestamp() {
		String Tstamp = new SimpleDateFormat( "yyyy-MMddHH-mmss" ).format( Calendar.getInstance().getTime() );  // yyyy-MM-dd_HHmmss.SS
		return Tstamp;
	}



public void PrepareTestEnvironment() {
	// 1. READ CONFIG FILE
	// 2. CREATE OUTPUT FILE NAME/ PATH from class and DESKTOP environment variables
			String errMessages = "";
	//		ReadPropertyFile prop=null;
			try { prop = new ReadPropertyFile(); } catch (Exception e1) {	
				errMessages=errMessages + "\n" + "TEST HALTED: CANNOT READ PROPERTIES FILE 'config.properties' "
							+ "in line 79 of 'xMain'";
			System.err.println(errMessages);
			e1.printStackTrace(); } 
			
			expectTitle = prop.expectTitle(); // ="IPG - Intertape Polymer Group";
			TargetURL = prop.readTargetUrl();
			xpTarget = prop.xpathTarget(); // = "//div/*";   //  "//a/*";   //  "//div/*";
			EXCLUDETAGS = prop.excludeTags().toUpperCase();
			whichBrowser = prop.askBrowserName();
			flagClearCookiesYN = prop.readFlagClearCookiesYN();
			flagUserConfirmationYN = prop.readFlagUserConfirmationYN();
			flagWindowMinimizeYN = prop.readFlagWindowMinimizeYN();
			driverIexplore = prop.pathIexplore().replace("\\\\","/").replace("//","/").replace("/","//"); // not so silly, handles error editing config.properties
			driverFireFox = prop.pathFirefox().replace("\\\\","/").replace("//","/").replace("/","//");;
			driverChrome = prop.pathChrome().replace("\\\\","/").replace("//","/").replace("/","//");;
//TODO ADD VALIDATION OF EACH CONFIG.PROPERTIES LINE (especially of comma/quote separate EXLUDETAGs)		
	// done: TO DO READ DOTEXTENSION FROM CONFIG FILE HTML or CSV
			// String getExt = new  getReportFileExtension;
		dotExtension = "."+getReportFileExtension(prop).toLowerCase(); //
//		String dummyfilename = FileWrite.affixTimeStamp(BaseFileName); // FileWrite.affixTimeStamp(null); akw

	// CREATE LOG FILE OUTPUT TO DESKTOP - NO FILEWRITE WILL WORK WITHOUT THIS 
			logPathFilename=FileWrite.createOutputFilepath(BaseFileName, dotExtension); // IT CALLS FILEWRITE BEFORE LOGFILEPATHNAME even EXISTS
			tempLogFile = logPathFilename; // THIS CAN BE REMOVED or USED LATER for a separate HISTORY file
			
			
//TODO fix or split redundant tempLogFile / logPathFilename: may need separate filename for history or HTML, CSV 
			
				//	getReportFileExtension	askReportCSVorHTML(prop, driver); // akw
			{ if (dotExtension.toLowerCase().contains("csv")) { //dotExtension
						writeWebElementsToCSV(driver); }
				else if (dotExtension.toLowerCase().contains("htm"))
				{ writeWebElementsToHTML(driver);}
				else {EMERGENCY_STOP_TEST("TEST  HALT: CAN NOT DETERMINE FILE FORMAT ''CSV'' or ''HTM''");}
			} // end IF choose output file format
									
			
			
			
			
			

	// TODO validate formatted URL in all legal variations of [ http.www | http | www | ].domain.ABC 		
		if (errMessages.trim().length()>1)	{FileWrite.logthis(LogCode.WARN,errMessages);}			
		/*	WebDriver */ driver = chooseDriverFromBrowserName(whichBrowser);
		    FileWrite.writeln(logPathFilename, whichBrowser + " Browser webDriver");   // (filePathName, text);logger.info(whichBrowser + " Browser webDriver");
//		    FileWrite.logthis(LogCode.INFO,whichBrowser + " Browser webDriver");	
			
	 // Set implicit wait      System.out.println("driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);"); // handled by jUnit and TestNG already??
		    
	  System.out.println("log file full path/name is: " + logPathFilename );
	  FileWrite.logthis(LogCode.INFO, "log file full path/name is: " + logPathFilename );
		
	  // { // FileWrite.logthis(LogCode.INFO, "Logging HTML to " + logPathFilename); 	FileWrite.logthis(LogCode.WARN, "config.properties has INVALID VALUE: Logging will default to CSV to " + logPathFilename);

	System.out.println(FileWrite.readSystemProperties(true));
	FileWrite.logthis(LogCode.INFO, FileWrite.readSystemProperties(true));  
	// ASK USER CONFIRMATION PROP.
			if (prop.readFlagUserConfirmationYN().equalsIgnoreCase("Y")) {	
			System.out.println(FileWrite.readSystemProperties(true));
			   int qy = askUserYN("Confirm ''YES'' to RUN TEST with THESE SETTINGS!  ''NO'' to HALT test!",FileWrite.readSystemProperties(true) + 
					   "\r\nlog file: " + logPathFilename + "\r\n ====\r\n" +
					   prop.printlnPropsReadPropertyFile());
	   		 if (qy==1 || qy==-1 ) {  		 // driver.close(); driver.quit();  driver.dismiss();
				     System.out.println("CANCELLED BY USER!");
				     EMERGENCY_STOP_TEST("CANCELLED BY USER: at Confirmation of Properties!"); }
			} // end if ask user confirmation
   } // end preparetestenvironment called by @BeforeClass
}
