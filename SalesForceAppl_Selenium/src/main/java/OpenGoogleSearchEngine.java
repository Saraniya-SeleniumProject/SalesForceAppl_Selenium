import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenGoogleSearchEngine {

	
		public static void main(String[] args) throws InterruptedException 
		{
		    System.setProperty("webdriver.chrome.driver", "C:\\Users\\senid\\Downloads\\chromedriver_win32_2\\chromedriver.exe");
	        ChromeDriver driver = new ChromeDriver();
			
			driver.get("https://selenium-prd.firebaseapp.com/");
			driver.manage().window().maximize();
			
			
			WebElement username= driver.findElement(By.id("email_field"));
			username.sendKeys("admin123@gmail.com");

		    WebElement password = driver.findElement(By.id("password_field"));
		    password.sendKeys("admin123");
			
		    WebElement Login = driver.findElement(By.xpath("//button[contains(text(),'Login to Account')]"));
		    Login.click();
		    
		    Thread.sleep(3000);
		    
		    // Navigating through the Home tab //
		    WebElement HomeTab = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div.navbar > a:nth-child(1)"));
			HomeTab.click();
			Thread.sleep(3000);
			WebElement Name = driver.findElement(By.name("name"));
			Name.sendKeys("Saranya");
			WebElement Father_Name = driver.findElement(By.id("lname"));
			Father_Name.sendKeys("Vasu");
			WebElement Postal_Address = driver.findElement(By.id("postaladdress"));
			Postal_Address.sendKeys("90 bay drive, 90019");
			WebElement Personal_Address = driver.findElement(By.id("personaladdress"));
			Personal_Address.sendKeys("90 bay drive, 90019");
			WebElement Sex_Female = driver.findElement(By.xpath("//span[2]//input[1]"));
			Sex_Female.click();
			WebElement CityName = driver.findElement(By.name("city"));
			Select dropdown1=new Select(CityName);
			dropdown1.selectByIndex(0);
			WebElement CourseName = driver.findElement(By.name("course"));
			Select dropdown2=new Select(CourseName);
			dropdown2.selectByIndex(1);
			WebElement DistrictName = driver.findElement(By.name("district"));
			Select dropdown3=new Select(DistrictName);
			dropdown3.selectByIndex(2);
			WebElement StateName = driver.findElement(By.name("state"));
			Select dropdown4=new Select(StateName);
			dropdown4.selectByIndex(3);
			WebElement Pincode = driver.findElement(By.id("pincode"));
			Pincode.sendKeys("90019");
			WebElement Email_ID = driver.findElement(By.id("emailid"));
		     Email_ID.sendKeys("abc@123.com"); 
		     
		     Thread.sleep(3000);
		     
		     WebElement SwitchTo = driver.findElement(By.xpath("//button[contains(text(),'Switch To')]"));
			 SwitchTo.click();
			 WebElement AlertOptionUnderSwitchTo = driver.findElement(By.xpath("//a[contains(text(),'Alert')]"));
	         AlertOptionUnderSwitchTo.click();
	         WebElement WindowAlertSubOption = driver.findElement(By.xpath("//button[contains(text(),'Window Alert')]"));
			 WindowAlertSubOption.click();
			 Thread.sleep(4000);
			 Alert WindowAlertTab = driver.switchTo().alert();
			 WindowAlertTab.accept();
			/* Thread.sleep(1000);
			 WebElement PromptAlertSubOption = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div:nth-child(2) div.login > button.bootbutton:nth-child(7)"));
			 PromptAlertSubOption.click();
		     Thread.sleep(4000);
			 Alert PromptAlertTab= driver.switchTo().alert();
			 PromptAlertTab.sendKeys("saran");
			 Thread.sleep(4000);
			 WebElement usermessage = driver.findElement(By.xpath("//p[@id='Selenium']"));
			 System.out.println(usermessage.getText()); */
			
			 Thread.sleep(3000);
			 
		     WebElement SwitchTo1 = driver.findElement(By.xpath("//button[contains(text(),'Switch To')]"));
			 SwitchTo1.click();
			 WebElement windowsoptionUnderSwitchTo = driver.findElement(By.cssSelector("div:nth-child(2) div.navbar div.dropdown:nth-child(2) div.dropdown-content > a:nth-child(2)"));
			 windowsoptionUnderSwitchTo.click();
			 WebElement opennewtabSubOption = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div.login a:nth-child(1) > button.bootbutton"));
			 opennewtabSubOption.click();
			 
			 String primarytab = driver.getWindowHandle();
			 
			 for (String handle : driver.getWindowHandles())
			 {
				 System.out.println("No of handles " +handle);
				 driver.switchTo().window(handle);
			 }
			 
		     Thread.sleep(5000);
		     WebElement googlesearch = driver.findElement(By.name("q"));
		     googlesearch.sendKeys("selenium");
		     Thread.sleep(5000);
		     driver.switchTo().window(primarytab);
		     WebElement HomeTab2 = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div.navbar > a:nth-child(1)"));
				HomeTab2.click();
				
				WebElement SwitchTo2 = driver.findElement(By.xpath("//button[contains(text(),'Switch To')]"));
				 SwitchTo2.click();
				 WebElement windowsoption1UnderSwitchTo = driver.findElement(By.cssSelector("div:nth-child(2) div.navbar div.dropdown:nth-child(2) div.dropdown-content > a:nth-child(2)"));
				 windowsoption1UnderSwitchTo.click();	
			WebElement opennewWindowSubOption = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div.login a:nth-child(1) > button.bootbutton"));
			opennewWindowSubOption.click();
				 
				 String primarywindow = driver.getWindowHandle();
				 
				 for (String handle : driver.getWindowHandles())
				 {
					 System.out.println("No of handles " +handle);
					 driver.switchTo().window(handle);
				 }
				 
			     Thread.sleep(5000);
			     WebElement googlesearch1 = driver.findElement(By.name("q"));
			     googlesearch1.sendKeys("selenium");
			     Thread.sleep(5000);
			     driver.switchTo().window(primarywindow);
			     WebElement HomeTab3 = driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div.navbar > a:nth-child(1)"));
					HomeTab3.click();
					
					/* tabs sub option under Switch TO */
					WebElement SwitchTo3 = driver.findElement(By.xpath("//button[contains(text(),'Switch To')]"));
					 SwitchTo3.click();
					 WebElement TabsSubOption = driver.findElement(By.xpath("//a[contains(text(),'Tabs')]"));
					TabsSubOption.click();
					WebElement clickLondon =driver.findElement(By.xpath("//button[contains(text(),'London')]"));
					clickLondon.click();
					 WebElement usermessageLondon = driver.findElement(By.xpath("//p[contains(text(),'London is the capital city of England.')]"));
					 System.out.println(usermessageLondon.getText());
					 
					 Thread.sleep(2000);
					 WebElement clickParis =driver.findElement(By.xpath("//button[contains(text(),'Paris')]"));
						clickParis.click();
						 WebElement usermessageParis = driver.findElement(By.xpath("//p[contains(text(),'Paris is the capital of France.')]"));
						 System.out.println(usermessageParis.getText());
						 
						 Thread.sleep(2000);
						 WebElement clickTokyo =driver.findElement(By.xpath("//button[contains(text(),'Tokyo')]"));
						clickTokyo.click();
						 WebElement usermessageTokyo = driver.findElement(By.xpath("//p[contains(text(),'London is the capital of Japan.')]"));
						 System.out.println(usermessageTokyo.getText());
						 
			 Thread.sleep(1000);
		     WebElement Logout =driver.findElementByXPath("//a[contains(text(),'Logout')]");
		     Logout.click();
		       
			Thread.sleep(3000);
			driver.quit();
		}

	}


