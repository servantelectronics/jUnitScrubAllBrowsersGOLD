package NGTests;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class WebSmokeInventoryWIPTESTNGTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }


  @Test
  public void chooseDriverFromBrowserName() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void endTestCleanup() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void getReportFileExtension() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void printlnConfigProperties() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void test99Cleanup() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void testCompareTitles() {
    throw new RuntimeException("Test not implemented");
  }

  @Test
  public void testRedirection() {
    throw new RuntimeException("Test not implemented");
  }
}
