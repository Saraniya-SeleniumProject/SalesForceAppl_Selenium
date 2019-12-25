package Create_Opportunities;

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

public class CreateANewOpty{
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
public CreateANewOpty() throws IOException

{

logger = reports.startTest("CreateANewOpty");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\SELENIUMDOCS\\TestDataXLSheets","CreateANewOpty.xls","CreateANewOpty");


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
 

WebElement OpportunitiesTab = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
OpportunitiesTab.click();

WebElement NewOpportunityButton = driver.findElement(By.xpath("//input[@name='new']"));
NewOpportunityButton.click();

WebElement OpportunityNameField = driver.findElement(By.id("opp3"));
String OpportunityNameField_data = data[3][2];
enter_data_textbox3(OpportunityNameField,OpportunityNameField_data,"OpportunityNameField");

WebElement AccountNameField = driver.findElement(By.id("opp4"));
String AccountNameField_data = data[4][2];
enter_data_textbox4(AccountNameField,AccountNameField_data,"AccountNameField ");

WebElement CloseDate= driver.findElement(By.id("opp9"));
String CloseDate_data = data[5][2];
enter_data_textbox5(CloseDate,CloseDate_data,"CloseDate");

@SuppressWarnings("unused")
WebElement StageField = driver.findElement(By.xpath("//label[contains(text(),'Stage')]"));
Select stageoption = new Select(driver.findElement(By.id("opp11")));
stageoption.selectByValue("Meeting Scheduled");

@SuppressWarnings("unused")
WebElement LeadSourceField = driver.findElement(By.xpath("//label[contains(text(),'Lead Source')]"));
   Select leadsourceoption = new Select(driver.findElement(By.id("opp6")));
    leadsourceoption.selectByValue("External Referral");
    
    WebElement SaveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
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

public static void enter_data_textbox5(WebElement textbox,String inputData, String textbox_name)
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

