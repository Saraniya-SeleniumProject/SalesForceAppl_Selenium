package UserMenu_Dropdown;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import com.relevantcodes.extentreports.*;

public class SelectMyProfileoptionfromUserMenuforUsernameDropdown{
	public static ExtentReports reports ;
	public static	ExtentTest logger;
	private static ChromeDriver driver;	

@BeforeTest

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
public SelectMyProfileoptionfromUserMenuforUsernameDropdown() throws IOException, InterruptedException

{

logger = reports.startTest("SelectUserMenuForUsernameDropdown");

String data[][] = getDataInput("C:\\Users\\senid\\Desktop\\SELENIUM DOCS\\TestData XL Sheets","SelectMyProfileoptionfromUserMenuforUsernameDropdown.xls","SelectMyProfileoption");

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
 Thread.sleep(5000);
 
 //USER-PROFILE DROPDOWN 
WebElement UserProfile = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
UserProfile.click();

//MyProfileSubOption.click
WebElement MyProfileSubOption = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/div[1]/div[3]/div[1]/div[1]/div[2]/div[3]/a[1]"));
		MyProfileSubOption.click();


//CloseEditProfile
 WebElement CloseEditProfile = driver.findElement(By.xpath("//a[@id='aboutMeX']"));
 CloseEditProfile.click();
 Thread.sleep(2000);

 //Verify LINK ,POST & SHAREPOST
WebElement UserProfilePage1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]"));
if(UserProfilePage1.isDisplayed())
{
  WebElement PostLink=driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
  PostLink.click();
  System.out.println("POST LINK CLICKED");
   driver.switchTo().frame(0);
   WebElement WritePost = driver.findElement(By.cssSelector("body.chatterPublisherRTE.cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders:nth-child(2) > p:nth-child(1)"));	
   String PostText  = data[3][2];
   enter_data_textbox3(WritePost,PostText,"PostText");
  Thread.sleep(5000);
  System.out.println("DATA ENTERED");
  WebElement SharePostButton= driver.findElement(By.cssSelector("#publishersharebutton"));
  SharePostButton.click();
  Thread.sleep(2000);
  System.out.println("share POST IS clicked");
  WebElement FileLink=driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
  FileLink.click();
  System.out.println("FILE LINK CLICKED");
  WebElement UploadFileFromComputer =driver.findElement(By.cssSelector("#chatterUploadFileAction"));
  UploadFileFromComputer.click();
  WebElement ChooseFromFile = driver.findElement(By.id("chatterFile"));
  ChooseFromFile.click();
  String FilePath  = data[4][2];
  enter_data_textbox4(ChooseFromFile,FilePath,"FilePath");
  WebElement ActionPhoto = driver.findElement(By.xpath("//span[@class='profileImage chatter-avatarFull chatter-avatar']//img[@class='chatter-photo']"));
  Actions action = new Actions(driver);
  action.moveToElement(ActionPhoto).build().perform();
  WebElement AddPhoto = driver.findElement(By.xpath("//a[@id='uploadLink'] "));
  AddPhoto.click();
    String primarywindow = driver.getWindowHandle();
	 
	 for (String handle : driver.getWindowHandles())
	 {
		 System.out.println("No of handles " +handle);
		 driver.switchTo().window(handle);
	 }
	 
     Thread.sleep(5000);
     WebElement UploadProfilePhotoWindow = driver.findElement(By.xpath("//h2[@id='uploadPhotoTitle']"));
     if (UploadProfilePhotoWindow.isDisplayed())
     {
     WebElement UploadProfilePhotoWindowClose= driver.findElement(By.className("dialogClose"));
     UploadProfilePhotoWindowClose.click();
     Thread.sleep(5000);
     driver.switchTo().window(primarywindow);
     }
     logger.log(LogStatus.PASS,"Test passed");
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
		else
		{
		logger.log(LogStatus.FAIL,textbox_name + " button was not enabled");
		System.out.println(textbox_name + " textbox was not enabled");
		}
		}
		else
		{
		logger.log(LogStatus.FAIL,textbox_name + " button was not displayed");
		System.out.println(textbox_name + " textbox was not displayed");
		}
		}
}
}