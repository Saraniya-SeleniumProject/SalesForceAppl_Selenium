package Leads;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;

public class CheckNewButtonOnLeadsHome{
public static ExtentReports reports ;
public static	ExtentTest logger;
private static ChromeDriver driver;

@BeforeTest
public static void reporting()
{
//String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\SELENIUMDOCS\\Report\\"+fileName;
reports = new ExtentReports(reportpath);
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
ChromeDriver driver = new ChromeDriver();
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

 

@Test
public CheckNewButtonOnLeadsHome() throws IOException

{

logger = reports.startTest("CheckNewButtonOnLeadsHome");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\SELENIUMDOCS\\TestDataXLSheets","CheckNewButtonOnLeadsHome.xls","CheckNewButtonOnLeadsHome");


//Username textbox WebElement
WebElement username = driver.findElement(By.xpath("//input[@name ='username']"));
String username_data = data[1][2];
enter_data_textbox1(username,username_data,"Username");

//password textbox WebElement
WebElement password = driver.findElement(By.xpath("//input[@name='pw']"));
String password_data = data[2][2];
enter_data_textbox2(password,password_data,"Password");

//Log In button WebElement
WebElement LoginButton = driver.findElement(By.xpath("//input[@name='Login']"));
//Click on Login Button
LoginButton.click();
//logger.log(LogStatus.PASS,"The LogIn button was clicked");

//DISABLE THE POP-UP WINDOW AFTER LOGIN
WebElement LightningExperiencePopUp = driver.findElement(By.className("dialogClose"));
LightningExperiencePopUp.click();
 

WebElement LeadsTab = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
LeadsTab.click();


WebElement NewButton = driver.findElement(By.xpath("//input[@name='new']"));
 NewButton.click();

 WebElement lastname = driver.findElement(By.id("name_lastlea2"));
 String lastname_data = data[3][2];
 enter_data_textbox3(lastname,lastname_data,"lastname");
 
 WebElement companyname = driver.findElement(By.id("lea3"));
 String companyname_data = data[4][2];
 enter_data_textbox4(companyname,companyname_data,"companyname"); 
 
 @SuppressWarnings("unused")
WebElement LeadStatusField = driver.findElement(By.xpath("//label[contains(text(),'Lead Status')]"));
 Select 	LeadStatusoption = new Select(driver.findElement(By.id("lea13")));
LeadStatusoption.selectByValue("Working");

WebElement SaveButton = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
SaveButton.click();

logger.log(LogStatus.PASS,"Test passed");
driver.quit();
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


public static void enter_data_textbox2(WebElement textbox,String inputData, String textbox_name)
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

public static void enter_data_textbox3(WebElement textbox,String inputData, String textbox_name)
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
public static void enter_data_textbox4(WebElement textbox,String inputData, String textbox_name)
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


