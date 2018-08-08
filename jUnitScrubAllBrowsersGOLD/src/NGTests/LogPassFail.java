/**
 * 
 */
package NGTests;

/**
 * @author Allan
 *
 */
public class LogPassFail extends FileWrite{

	// declaration??
	

	public static int verifySameStringAnyCase(String TestName, String expected, String actual){
		boolean result = expected.equalsIgnoreCase(actual)  ;
		if (result) {
			FileWrite.logthis(LogCode.PASS, TestName + ",Expected: " + expected + ",Actual: " + actual);
			return 1;} else if (!result) {
				FileWrite.logthis(LogCode.FAIL, TestName + ",Expected: " + expected + ",Actual: " + actual);
				return 0;} else 
				{ FileWrite.logthis(LogCode.ERROR, TestName + ",Expected: " + expected + ",Actual: " + actual);
					return -1;}
	}
	
	public static int verifySameStringExactCase(String TestName, String expected, String actual){
		boolean result = expected.equalsIgnoreCase(actual)  ;
		if (result) {
			FileWrite.logthis(LogCode.PASS, TestName + ",Expected: " + expected + ",Actual: " + actual);
			return 1;} else if (!result) {
				FileWrite.logthis(LogCode.FAIL, TestName + ",Expected: " + expected + ",Actual: " + actual);
				return 0;} else 
				{ FileWrite.logthis(LogCode.ERROR, TestName + ",Expected: " + expected + ",Actual: " + actual);
					return -1;}
	}
	
	
	public static int verifyContainsSubstring(String TestName, String parentset, String subset){
		int placeholder = parentset.indexOf(subset);
		if (placeholder > 0) {
			FileWrite.logthis(LogCode.PASS, TestName + "," + parentset + " contains: " + subset);
			} else if (placeholder == -1) {
				FileWrite.logthis(LogCode.FAIL, TestName + "," + parentset + " does NOT contains " + subset);
				}  
		return placeholder;
	}  // verifyContainsSubstring

}



