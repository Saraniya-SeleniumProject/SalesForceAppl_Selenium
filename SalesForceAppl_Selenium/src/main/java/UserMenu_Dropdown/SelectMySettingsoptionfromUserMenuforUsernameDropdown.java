package UserMenu_Dropdown;


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

public class SelectMySettingsoptionfromUserMenuforUsernameDropdown{
	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	

@BeforeTest
public static void reporting()
{
//String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String fileName = new SimpleDateFormat("'SampleTestExtentDemo_'yyyyMMddHHmm'.html'").format(new Date());
String reportpath="C:\\Users\\senid\\Desktop\\SELENIUM DOCS\\Report"+fileName;

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
public SelectMySettingsoptionfromUserMenuforUsernameDropdown() throws Exception

{

logger = reports.startTest("SelectMySettingsoptionfromUserMenuforUsernameDropdown");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\SELENIUM DOCS\\TestData XL Sheets","SelectMySettingsoptionfromUserMenuforUsernameDropdown.xls","SelectMySettingsoption");

//Username textbox WebElement
WebElement username = driver.findElement(By.xpath("//input[@name ='username']"));
String username_data = data[1][2];
enter_data_textbox1(username,username_data,"Username");

//password textbox WebElement
WebElement password = driver.findElement(By.xpath("//input[@name='pw']"));
String password_data = data[2][2];
enter_data_textbox2(password,password_data,"Password");

//RememberMeChkBox.click
WebElement RememberMeChkBox = driver.findElement(By.xpath("//input[@id='rememberUn']"));
RememberMeChkBox.click();


//Log In button WebElement
WebElement LoginButton = driver.findElement(By.xpath("//input[@name='Login']"));
//Click on Login Button
LoginButton.click();
//logger.log(LogStatus.PASS,"The LogIn button was clicked");

//DISABLE THE POP-UP WINDOW AFTER LOGIN
WebElement LightningExperiencePopUp = driver.findElement(By.className("dialogClose"));
LightningExperiencePopUp.click();
 Thread.sleep(5000);
 
 //USER-PROFILE DROPDOWN 
WebElement UserProfile = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
UserProfile.click();


WebElement MySettingsSubOption = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/a[2]"));
MySettingsSubOption.click();

WebElement MySettingsPage = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]"));
        if(MySettingsPage.isDisplayed())
        	
{
WebElement PersonalLink = driver.findElement(By.xpath("//div[@id='PersonalInfo']//span[@class='accordionIcon expand_icon']"));
PersonalLink.click();
WebElement LoginHistorySubLink = driver.findElement(By.xpath("//span[@id='LoginHistory_font']"));
LoginHistorySubLink.click();
WebElement LoginHistoryPage= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]"));
if (LoginHistoryPage.isDisplayed())
{
WebElement DownloadHistoryLink = driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
DownloadHistoryLink.click();
Thread.sleep(3000);
}
WebElement DisplayAndLayoutLink = driver.findElement(By.xpath("//div[@id='DisplayAndLayout']//a[@class='header setupFolder']"));
DisplayAndLayoutLink.click();
WebElement CustomizeMyTabsLink = driver.findElement(By.xpath("//span[@id='CustomizeTabs_font']"));
CustomizeMyTabsLink.click();
WebElement CustomizeMyTabsPage= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]"));
if (CustomizeMyTabsPage.isDisplayed())
{
WebElement CustomAppDropdownMenu = driver.findElement(By.xpath("//label[contains(text(),'Custom App:')]"));
Select select = new Select(CustomAppDropdownMenu);
select.selectByIndex(3);
WebElement AvailableTabsList = driver.findElement(By.xpath("//label[contains(text(),'Available Tabs')]"));
if ( AvailableTabsList.isSelected())
{
	WebElement ReportsOptionUnderAvailableTabsList = driver.findElement(By.xpath("//option[contains(text(),'Reports')]"));
	ReportsOptionUnderAvailableTabsList.click();
	WebElement ADDICON = driver.findElement(By.xpath("//img[contains(@class,'rightArrowIcon')]"));
	ADDICON.click();
	
	WebElement SaveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[contains(@name,'save')]"));
	SaveButton.click();
	
	Thread.sleep(3000);
	
WebElement EmailLink = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
EmailLink.click();
@SuppressWarnings("unused")
WebElement EmailSetttingsLink = driver.findElement(By.xpath("//span[@id='EmailSettings_font']"));
CustomizeMyTabsLink.click();
	 WebElement EmailSettingsPage= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]"));
	 if (EmailSettingsPage.isDisplayed())
		 {
		 WebElement EmailName = driver.findElement(By.id("sender_name"));
		 String EmailName_data = data[3][2];
		 enter_data_textbox3(EmailName,EmailName_data,"EmailName");
		 WebElement EmailAddress = driver.findElement(By.id("sender_email"));
		 String EmailAddress_data = data[4][2];
		 enter_data_textbox4(EmailAddress,EmailAddress_data,"EmailAddress");
		 
		 WebElement AutomaticBCC = driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		 AutomaticBCC.click();
		 WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[contains(@name,'save')]"));
		 saveButton.click();
		 WebElement SuccessMessage = driver.findElement(By.xpath("//div[@class='messageText']"));
		 SuccessMessage.getText();
		 if (SuccessMessage.getText() .equalsIgnoreCase("Your settings have been successfully saved"))
		 {
			 System.out.println ("Success");
		 }
		 Thread.sleep(3000);
		 WebElement CalendarAndRemainderLink = driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		 CalendarAndRemainderLink.click();
    	 WebElement ActivityRemindersLink = driver.findElement(By.xpath("///span[@id='Reminders_font']"));
    	 ActivityRemindersLink.click();
    	 WebElement ActivityRemindersPage= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]"));
    	 if (ActivityRemindersPage.isDisplayed())
    		 {
		        WebElement OpenATestReminderButton = driver.findElement(By.xpath("//input[@id='testbtn']"));
		        OpenATestReminderButton.click();
		        String primarywindow = driver.getWindowHandle();
				 
				 for (String handle : driver.getWindowHandles())
				 {
					 System.out.println("No of handles " +handle);
					 driver.switchTo().window(handle);
				 }
				 
			     Thread.sleep(5000);
			     WebElement SampleEventWindow = driver.findElement(By.xpath("//div[@id='summary']"));
			     if (SampleEventWindow.isDisplayed())
			     {
			     WebElement SampleEventWindowPopUp = driver.findElement(By.className("dialogClose"));
			     SampleEventWindowPopUp.click();
			     Thread.sleep(5000);
			     driver.switchTo().window(primarywindow);
			     }
    		 }
    	 
		 }
}
}logger.log(LogStatus.PASS,"Test passed");
driver.quit();
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

public static void enter_data_textbox4(WebElement textbox,String inputData, String textbox_name)
{
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
}