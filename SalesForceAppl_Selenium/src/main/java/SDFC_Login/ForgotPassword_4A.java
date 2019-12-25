package SDFC_Login;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;

public class ForgotPassword_4A{
	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	

@BeforeTest()

public static void reporting()
{
//String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\SELENIUM DOCS\\Report"+fileName;
{
reports = new ExtentReports(reportpath);

}
}
public static String[][] getDataInput(String filepath,String filename, String Sheetname) throws IOException
{

//Get the Xl path
File xlfile = new File(filepath+"\\"+filename);

//access to the Xl path
FileInputStream xlaccess = new FileInputStream(xlfile);

//access to workbook
HSSFWorkbook Wb = new HSSFWorkbook(xlaccess);

//Access the sheet
HSSFSheet sheet = Wb.getSheet(Sheetname);

int rowCount = sheet.getLastRowNum();
int columnCount = sheet.getRow(0).getLastCellNum();

System.out.println(rowCount);
System.out.println(columnCount);

String [][] readData = new String [rowCount][columnCount];
for(int i=0;i<=rowCount;i++)
{
for(int j=0;j <sheet.getRow(i).getLastCellNum();j++)
{
//System.out.println(sheet.getRow(i).getCell(j).getStringCellValue()+ "||");
readData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
}
//System.out.println();
}
return readData;

}

@BeforeMethod()
public static void setForChromeDriverLaunchBrowser() throws InterruptedException
{

//set up chrome drive
System.setProperty("webdriver.chrome.driver", "C:\\Users\\senid\\Downloads\\chromedriver_win32\\chromedriver.exe");
driver = new ChromeDriver();
//maximize the window
driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//Launch website
driver.get("https://login.salesforce.com");
Thread.sleep(5000);

}

@AfterTest
public static void Report_close()
{
reports.endTest(logger);
reports.flush();
}

// @AfterMethod
// public static void TearDown() throws InterruptedException
// {
// Thread.sleep(3000);
// driver.quit();
//
// }

@Test
public  ForgotPassword_4A() throws Exception

{

logger = reports.startTest("ForgotPassword_4A");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\SELENIUM DOCS\\TestData XL Sheets","ForgotPassword_4A.xls","ForgotPassword_4A");

//Username textbox WebElement
WebElement username = driver.findElement(By.xpath("//input[@name ='username']"));
String username_data = data[1][2];
enter_data_textbox1(username,username_data,"Username");

//password textbox WebElement
WebElement password = driver.findElement(By.xpath("//input[@name='pw']"));
password.clear();

//Selecting the RememberMeChkBox
WebElement RememberMeChkBox = driver.findElement(By.xpath("//input[@id='rememberUn']"));
RememberMeChkBox.click();

WebElement ForgotPasswordLink = driver.findElement(By.id("forgot_password_link"));
ForgotPasswordLink.click();

WebElement ForgotPasswordPage = driver.findElement(By.id("wrapper"));
ForgotPasswordPage.click();

WebElement usernameForgotPwd= driver.findElement(By.id("un"));
usernameForgotPwd.sendKeys("charu.vasu-we86@force.com");
Thread.sleep(3000);
WebElement continueButton= driver.findElement(By.id("continue"));
continueButton.click();

WebElement CheckUrMailPage = driver.findElement(By.id("main"));
Thread.sleep(5000);
if (CheckUrMailPage.isDisplayed())
{
WebElement ConfirmationMsg = driver.findElement(By.xpath("//p[contains(text(),'ve sent you an email with a link to finish rese')]"));
String actualConfirmationMsg = ConfirmationMsg.getText();
String ExpectedConfirmationMsg=  "We’ve sent you an email with a link to finish resetting your password.";

if(actualConfirmationMsg.equals(ExpectedConfirmationMsg))
{
	WebElement ReturnToLogin = driver.findElement(By.xpath("//a[@class='primary button wide mb16']"));
	ReturnToLogin.click();
	Thread.sleep(5000);
	WebElement LoginPage = driver.findElement(By.id("main"));
	if (LoginPage.isDisplayed())
	{
		
     	   logger.log(LogStatus.PASS,"test passed");
             System.out.println ("test passed");
       }
else {
logger.log(LogStatus.FAIL,"test failed");
System.out.println ("test failed"); 
}
}
}
}

public static void enter_data_textbox1(WebElement textbox,String inputData, String textbox_name)
{
if (textbox.isDisplayed()== true)
{
if (textbox.isEnabled() == true)
{
textbox.sendKeys(inputData);

if(textbox.getAttribute("value").equals(inputData))
{
logger.log(LogStatus.PASS,"'"+inputData+ "' was entered in '"+textbox_name+ "' textbox ");
System.out.println("'"+inputData+ "' was entered in '"+textbox_name+ "' textbox ");
}
else
{
logger.log(LogStatus.FAIL,"'"+inputData+ "' was not entered in '"+textbox_name+ "' textbox ") ;
System.out.println("'"+inputData+ "' was not entered in '"+textbox_name+ "' textbox ");
}
}
}
}
}

