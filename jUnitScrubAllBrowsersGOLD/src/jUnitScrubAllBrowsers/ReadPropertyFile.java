package jUnitScrubAllBrowsers;  //TODO reduce steps

//import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// TODO MAKE CASE INSENSITIVE
	public class ReadPropertyFile {
		protected Properties prop = null;
		protected InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream("config.properties");
// /SeleniumScrubDub20170501a/src/config.properties
		public  ReadPropertyFile() throws Exception { 
			prop = new Properties();
			prop.load(input); 		}		

		public String askBrowserName() 	{return prop.getProperty("browserName"); }
		public String expectTitle() 	{ return prop.getProperty("expectTitle"); }
		public String pathChrome() 	{ return prop.getProperty("driverChrome"); }
		public String pathFirefox() 	{return prop.getProperty("driverFirefox"); }
		public String pathIexplore() 	{ return prop.getProperty("driverIexplore"); }
		public String excludeTags() { return prop.getProperty("excludeTags"); } 
		public String readFlagClearCookiesYN() 	{ return prop.getProperty("flagClearCookiesYN"); }   
		public String readFlagWindowMinimizeYN() 	{  return prop.getProperty("flagWindowMinimizeYN"); }
		public String readFlagUserConfirmationYN() 	{  return prop.getProperty("flagUserConfirmationYN"); }
		public String readHostName() 	{ return prop.getProperty("hostName"); }
		public String readHostPort() 	{ return prop.getProperty("hostPort"); }
		public String readHostUrl() 	{ return prop.getProperty("hostURL"); }
		public String readPassword() 	{ return prop.getProperty("password"); }
		public String baseURL(){  return prop.getProperty("baseURL");	}
		public String readUsername() 	{ return prop.getProperty("username"); }
		public String xpathTarget() 	{ return prop.getProperty("xpathTarget"); }
		public String YNreportCSV() 	{ return prop.getProperty("YNreportCSV"); }
		public String YNreportHTML() 	{ return prop.getProperty("YNreportHTML"); }
		/* NOT YET USED
		 *  hostName=localhost
			hostPort=4444
			hostURL=127.0.0.1
			password=gorgonzola
			testername=awhitworth@surgeforward.com 
			username=awhitwor
		 */

		
		public String printlnPropsReadPropertyFile() { return "browser name: " + askBrowserName() +
				"\nexpectTitle: " + expectTitle() +
				"\nTargetURL = " + baseURL() +
				"\nxpathTarget = " + xpathTarget() +
				"\nEXCLUDETAGS = " + excludeTags() + 
				"\nflagClearCookiesYN = " + readFlagClearCookiesYN() +
				"\nflagUserConfirmationYN = " + readFlagUserConfirmationYN() +
				"\nflagWindowMinimizeYN = " + readFlagWindowMinimizeYN()  ;
				}
			
			
		
 } // end public class ReadPropertyFile
