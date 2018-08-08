package jUnitScrubAllBrowsers;

//TODO SEPARATE HISTORY FILE LOG DURATION, TESTNAME, COUNT TO SEPARATE FILE // MOVE FILENAME TO LOG ROUTINE
//out of scope: history: one line per run/spring? line has  config CSV and steps and duration, filecount, average
//TODO DONE ENHANCE FILENAME by ClassNameSimple
//TODO DONE AUTO SELECT FILE EXTENSION as CSV or HTML
//TODO conditional <BR /> when output file is HTML
//TODO log output file uses window ENVIRONMENT varialbe: what about Apple, Linux
//TODO trap for NULL and formatting ERRORs in Config file

//TODO  2017-05-11-080049 two stub JUNIT @Tests fail and three pass


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;

import static java.util.Arrays.asList;

import java.util.Calendar;
//import java.util.Iterator;
import java.util.List;
import java.util.Properties;
//import java.io.*;
//import java.util.Properties;

//import javax.swing.Action;

//import java.util.logging.Logger;

import javax.swing.JOptionPane;

//import org.junit.After;

import jUnitScrubAllBrowsers.FileWrite.LogCode;

public class WebSmokeScrubGolden {   

public static String logPathFilename;  
public static String tempLogFile ;

public String errMessages; // append errMessages until a file name / path is selected from environment
public static WebDriver driver;  
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
public static String EXCLUDETAGS; 
public static String flagClearCookiesYN;
public static String flagUserConfirmationYN;
public static String flagWindowMinimizeYN;
public static String driverIexplore;
public static String driverFireFox;
public static String driverChrome;
public static String dotExtension;


/// BEGIN TESTS 'xMAIN'  testername=awhitworth@surgeforward.com

@Test
public void xmain() throws Exception { //String[] args) throws InterruptedException {
//1. READ CONFIG FILE
//2. CREATE OUTPUT FILE NAME/ PATH from class and DESKTOP environment variables
	String errMessages = "";
	ReadPropertyFile prop=null;
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
	driverIexplore = prop.pathIexplore().replace("\\","/").replace("//","/").replace("/","//"); // odd, but allows user to mix up slashes 
	driverFireFox = prop.pathFirefox().replace("\\","/").replace("//","/").replace("/","//");;
	driverChrome = prop.pathChrome().replace("\\","/").replace("//","/").replace("/","//");;

	//TODO ADD VALIDATION OF EACH CONFIG.PROPERTIES LINE (especially of comma/quote separate EXLUDETAGs)		
//done: TO DO READ DOTEXTENSION FROM CONFIG FILE HTML or CSV
dotExtension = "."+getReportFileExtension(prop, driver).toLowerCase(); // 
	String BaseFileName = FileWrite.affixTimeStamp(getClass().getSimpleName()); // FileWrite.affixTimeStamp(null);
	
//CREATE LOG FILE OUTPUT TO DESKTOP - NO FILEWRITE WILL WORK WITHOUT THIS 
	logPathFilename=FileWrite.createOutputFilepath(BaseFileName, dotExtension); // IT CALLS FILEWRITE BEFORE LOGFILEPATHNAME even EXISTS
	tempLogFile = logPathFilename; // THIS CAN BE REMOVED or USED LATER for a separate HISTORY file
		
//TODO validate formatted URL in all legal variations of [ http.www | http | www | ].domain.ABC 		
if (errMessages.trim().length()>1)	{FileWrite.logthis(LogCode.WARN,errMessages);}			
	WebDriver driver = chooseDriverFromBrowserName(whichBrowser);
  FileWrite.writeln(logPathFilename, whichBrowser + " Browser webDriver");   // (filePathName, text);logger.info(whichBrowser + " Browser webDriver");
//  FileWrite.logthis(LogCode.INFO,whichBrowser + " Browser webDriver");	
	
// Set implicit wait      System.out.println("driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);"); // handled by jUnit and TestNG already??
  
System.out.println("log file full path/name is: " + logPathFilename );
FileWrite.logthis(LogCode.INFO, "log file full path/name is: " + logPathFilename );

// { // FileWrite.logthis(LogCode.INFO, "Logging HTML to " + logPathFilename); 	FileWrite.logthis(LogCode.WARN, "config.properties has INVALID VALUE: Logging will default to CSV to " + logPathFilename);

System.out.println(FileWrite.readSystemProperties(true));
FileWrite.logthis(LogCode.INFO, FileWrite.readSystemProperties(true));  
//ASK USER CONFIRMATION PROP.
	if (prop.readFlagUserConfirmationYN().equalsIgnoreCase("Y")) {	
	System.out.println(FileWrite.readSystemProperties(true));
	   int qy = askUserYN("Confirm ''YES'' to RUN TEST with THESE SETTINGS!  ''NO'' to HALT test!",FileWrite.readSystemProperties(true) + 
			   "\r\nlog file: " + logPathFilename + "\r\n ====\r\n" +
			   prop.printlnPropsReadPropertyFile());
		 if (qy==1 || qy==-1 ) {  		 // driver.close(); driver.quit();  driver.dismiss();
		     System.out.println("CANCELLED BY USER!");
		     EMERGENCY_STOP_TEST("CANCELLED BY USER: at Confirmation of Properties!"); }
	} // end if ask user confirmation

//START TEST: NAVIGATE THEN OUTPUT INVENTORY TO FILE 		
  System.out.println("Starting TEST of " + TargetURL + " at " + datetimestamp()+ 0);
   startTestTime = System.nanoTime();  
  
if (prop.readFlagClearCookiesYN().equalsIgnoreCase("Y")) { 
	System.out.println("clearing cookies");
	driver.manage().deleteAllCookies();
	driver.manage().window().setSize(new Dimension(550, 450));
	FileWrite.writeln(logPathFilename, "COOKIES CLEARED");
} else {FileWrite.writeln(logPathFilename, "COOKIES NOT CLEARED");}

   
  FileWrite.logthis(LogCode.INFO, "Navigating to TargetURL: " + TargetURL);
  driver.navigate().to(TargetURL);   //.back(); 		// driver.get(TargetURL);		

//TODO fix or split redundant tempLogFile / logPathFilename: may need separate filename for history or HTML, CSV 
	
//getReportFileExtension	askReportCSVorHTML(prop, driver); // akw
{ if (dotExtension.toLowerCase().contains("csv")) { //dotExtension
			writeWebElementsToCSV(driver); }
	else if (dotExtension.toLowerCase().contains("htm"))
	{ writeWebElementsToHTML(driver);}
	else {EMERGENCY_STOP_TEST("TEST  HALT: CAN NOT DETERMINE FILE FORMAT ''CSV'' or ''HTM''");}
} // end IF choose output file format
		

/*	testTitle();
testRedirection();
test001_CCform004_caption();
test001_CCform003_CustomerTier(); 
test001_CCform002_Reorder();
test001_FindbyXPath();  
test001_CCform001a();  
endTestCleanup(); */




	
} // end of xMain test 

/*      --- -- - -- --- *** --- -- - -- ---  */
//	@Test
	public void  printlnConfigProperties() throws IOException { //  main(String[] args) throws IOException {
			
	// System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName()); // output : doit
	Method lastMethodCalled = this.getClass().getEnclosingMethod();
	System.out.println(lastMethodCalled.getName());
	

		
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
	
//	@Test
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
	


// @Test  
	public void test001_FindbyXPath()  {
	   Method lastMethodCalled = getClass().getEnclosingMethod(); 
	List<WebElement> listwidg1 = driver.findElements(By.xpath("//input[@test-id='test-username']"));
	int listsize = listwidg1.size();
	WebElement widg1 = listwidg1.get(0);
	String strTemp = widg1.getText();	
	if (listsize > 1) 
		{ FileWrite.logthis(LogCode.WARN, lastMethodCalled +",test object NOT unique (count=="+ listsize + ")");}
	if (listsize == 1)
		{ FileWrite.logthis(LogCode.PASS, lastMethodCalled + ",test object found having ID (" + widg1 + "), Class (" + widg1 + "), Name (" + widg1 + "), Value (" + widg1 + ")");
	}

//		WebElement widg2 = driver.findElement(By.id("someID"));
//		WebElement widg3 = driver.findElement(By.name(strTemp));
//		WebElement widg4 = driver.findElement(By.cssSelector(EXCLUDETAGS));
//		WebElement ele1 = driver.findElement(By.cssSelector(".primary-btn"));
//		WebElement ele2 = driver.findElement(By.cssSelector(".btn.primary-btn"));
//		WebElement ele3 = driver.findElement(By.cssSelector("input[id=email]"));
		// findElement(By.xpath("//input[@test-id='test-username']");

		System.out.println("begin @Test test001_CCform001 find widget1 using xPath:("+ strTemp + ")");
//		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
		if (widg1!=null){
			FileWrite.logthis(LogCode.PASS, "widg1 ,Exists");
		//	FileWrite.logthis(LogCode.PASS, "widg1 ,Exists");
		
		}
		LogPassFail.verifySameStringExactCase(lastMethodCalled.toString(),expectTitle,strTemp);

		
	//	assertEquals(strTemp,expectTitle);
	}
 
// @Test
	public void test001_CCform001a()  {
	   driver.navigate().to(TargetURL); 
		WebElement widg2 = driver.findElement(By.id("someID"));
		String strTemp = widg2.getText();

		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());

		
		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
 

	public void test001_CCform001b_maxlength()  {
		WebElement widg3 = driver.findElement(By.xpath("//input[@test-id='test-username']"));
		String strTemp = widg3.getText();
//		WebElement widg2 = driver.findElement(By.id("someID"));
//		WebElement widg3 = driver.findElement(By.name(strTemp));
//		WebElement widg4 = driver.findElement(By.cssSelector(EXCLUDETAGS));

		
		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());

		
		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}


	public void test001_CCform001c_negative() {
		WebElement widg4 = driver.findElement(By.name("foobar"));
		String strTemp = widg4.getText();
		// findElement(By.xpath("//input[@test-id='test-username']");

		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());

		
		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
 

	public void test001_CCform001d_negative() throws Exception {
		WebElement widg5 = driver.findElement(By.cssSelector("input[id=email]"));
		String strTemp;			
		if (!(widg5==null)){ 
		strTemp = widg5.getText();
		} else { strTemp = "NOT FOUND"; }
		
		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());


		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
 

// @Test  
	public void test001_CCform002_Reorder() throws Exception {
		WebElement widg6 = driver.findElement(By.id("someID"));
		String strTemp;			
		if (!(widg6==null)){ 
		strTemp = widg6.getText();
		} else { strTemp = "NOT FOUND"; }

		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());

		
		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
 

 
//   @Test
	public void test001_CCform003_CustomerTier()  {
		WebElement widg7 = driver.findElement(By.cssSelector(".primary-btn"));
		String strTemp;			
		if (!(widg7==null)){ 
		strTemp = widg7.getText();
		} else { strTemp = "NOT FOUND"; }

		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());


		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
 
// @Test
	public void test001_CCform004_caption() {
		WebElement widg8 = driver.findElement(By.id("someID"));
		String strTemp;			
		if (!(widg8==null)){ 
		strTemp = widg8.getText();
		} else { strTemp = "NOT FOUND"; }

		Method lastMethodCalled = this.getClass().getEnclosingMethod();
		System.out.println(lastMethodCalled.getName());

		
		System.out.println("begin @Test testTitle using expectTitle:("+ strTemp + ")");
		FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ strTemp + ")");
	//	assertEquals(strTemp,expectTitle);
	}
    
	
//@Test  
public void testTitle() throws Exception {
	String strTemp = driver.getTitle();

	System.out.println("begin @Test testTitle using expectTitle:("+ expectTitle + ")");
//	FileWrite.logthis(LogCode.INFO, "begin @Test testTitle using expectTitle:("+ expectTitle + ")");
	FileWrite.logthis(LogCode.INFO, "begin @TestTitle actual title '" + driver.getTitle()  + "' should equal expected '" + expectTitle + "'");
	assertEquals(strTemp,expectTitle);
}  // end testTitle()
		

		

@Test
public void testRedirection() throws Exception {
	String strTemp = driver.getCurrentUrl();
	String strMessage = "begin @Test REDIRECTION comparing TargetURL: ("+ TargetURL + ") with getCurrentUrl ("+ strTemp+")";
	System.out.println(strMessage);
	FileWrite.logthis(LogCode.INFO, strMessage);		
	assertEquals(strTemp ,TargetURL);
}
	


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

private String getReportFileExtension(ReadPropertyFile prop, WebDriver driver) { // called BEFORE FILEWRITE makes LOGNAME
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


/*      *****   *****   *****   ~ ~ ~ ~     *****   *****   *****      */

public int writeWebElementsToHTML(WebDriver driver) {
	int x = 1;

	// CONSTANTs for logging to an HTML table
	final String HTMLtableBegin = "<TABLE align=left border=1 width=\"88%\">";
	final String HTMLtableEnd = "</TABLE>";		
//	final String ROWTOPCENTERED = "<TR valign=top align=center><TD>";		
	final String ROWNEW = "<TR ><TD>" ;  	// String ROWNEW = "<TR valign=top><TD>" ;
	final String ROWEND = "</TD></TR>" ;  // " &nbsp; </TD></TR>" ;
	final String DELIM = "</TD><TD>" ;
//	@SuppressWarnings("unused")
	String bgcolor = ""; // light green #0D0D0A	lightcyan #0C0CFF;	

//EXCLUDE TAGS FROM REPORT //TODO ADD MAX NOTATION TO LIMIT WHEN DIV IS TOO BIG		
	List<WebElement> elements=driver.findElements(By.xpath(".//*"));
	// String[] excludeList = EXCLUDETAGS.split(";")	;	  // input.split("\\s+");
	List<String> excludeList = 	asList(EXCLUDETAGS.split("\\s*,\\s*"));
//	INCLUDE IN TEST RESULTS HEADER: EXCLUDEDTAG LIST  //List<String> list = ["A", "B", "C"];
	System.out.println("THESE HTML TAGS WILL BE EXCLUDED:");
	int xyz=0;
	for (String element: excludeList) {
	      System.out.println(++xyz + ": " + element);
	}

	FileWrite.write(tempLogFile,HTMLtableBegin);
	//Now iterate through List and do the required operation with each individual element
	int zy = 0;

//write top row of column names	
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
public void test99Cleanup(WebDriver driver) { // SWAG calls CLOSE inside try/catch and at end ??
	System.out.println("Ready to shutdown from within test99Cleanup");
	
	FileWrite.logthis(FileWrite.LogCode.INFO, "Ready to shutdown");
	
		driver.close();
		driver.quit();
	
	String prettyTimeFormat = reportElapsedTime();  // endTestTime, startTestTime
	FileWrite.logthis(FileWrite.LogCode.INFO, "Elapsed Time: " + prettyTimeFormat); // prettyTimeFormat);
	FileWrite.writeln(FileWrite.fpathName.toString(), "***END OF TEST***"); // uses parent's WEBDRIVER
		if (!(driver==null)){
			driver.close();
			driver.quit();			}
	System.exit(0);// TestElapsedTime

}

/*      --- -- - -- --- *** --- -- - -- ---  */
public static void EMERGENCY_STOP_TEST(String Warning) { // SWAG calls CLOSE inside try/catch and at end ??
	System.out.println("SYSTEM EXIT: " + Warning);
	FileWrite.logthis(FileWrite.LogCode.INFO, "SYSTEM EXIT: "+ Warning);
	// String prettyTimeFormat = reportElapsedTime(endTestTime, startTestTime);
	// FileWrite.logthis(FileWrite.LogCode.INFO, "Elapsed Time: " + prettyTimeFormat); // prettyTimeFormat);

FileWrite.writeln(FileWrite.fpathName.toString(), "***END OF TEST***"); // uses parent's WEBDRIVER

try {
System.out.println("shutting down driver / open browser windows of '' " + whichBrowser +"''");
	driver.close();
	driver.quit();
} catch (Exception e) {
//TODO Auto-generated catch block
	FileWrite.writeln(FileWrite.fpathName.toString(), "failed to close driver: MANUALLY CLOSE any REMAINING instances of '' " + whichBrowser +"''");  
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
//			System.setProperty("webdriver.chrome.driver",("C:/Users/Allan/AppData/Roaming/Selenium/301/ChromeWin32_227/chromedriver.exe").replace("/","//"));
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
public static void writeWebElementsToCSV(WebDriver driver) {         //(Logger logger, WebDriver driver) {
	
//	 final String CSVtableBegin = "";
	 final String ROWNEW = "\""; 
	 final String ROWEND = "\"\n";
	 final String DELIM = "\",\""; 
	 
List<WebElement> elements=driver.findElements(By.xpath(".//*"));   //       t=".//*
	
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


/*      --- -- - -- --- *** --- -- - -- ---  */	
public static String datetimestamp() {
	String Tstamp = new SimpleDateFormat( "yyyy-MMddHH-mmss" ).format( Calendar.getInstance().getTime() );  // yyyy-MM-dd_HHmmss.SS
	return Tstamp;
}
}


