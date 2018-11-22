package signIn;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ControllerClassTest {
	
	//Defining global objects
	public WebDriver driver = null;
	public SignUpProcessTest signUpPObj = new SignUpProcessTest();
	
	/**
	 * Prep the web browser the script will run on (Chrome)
	 * **/
	@Parameters({"URL"})
	@Test
	public  void pageSetup(String urlname) throws BiffException, IOException {
				
		//Launch the browser and the website  
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(urlname);
		driver.manage().window().maximize(); 
		
		WebDriverWait  block = new WebDriverWait(driver,10);
		block.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='register.php']")));
		
		//Pass test when this condition is met
		Assert.assertTrue(driver.findElement(By.xpath("//*[@class='head text-center']")).isDisplayed());
		System.out.println("URL: " + urlname );

	}
	
	/**
	 * This function make use of data provider to read user information
	 * **/
	@Test(dataProvider = "getRegistrationData" )
	public void userRegister(String first_name, String last_name, String fld_email, String fld_cemail, String fld_username, 
			String fld_password, String dob, String phone, String address, String city, String state, String zip) {
		
		//This action clicks on the register button	
		driver.findElement(By.xpath("//*[@href='register.php']")).click();
		
		//populate the web form
		driver.findElement(By.name("first_name")).sendKeys(first_name);
		driver.findElement(By.name("last_name")).sendKeys(last_name);
		driver.findElement(By.name("fld_email")).sendKeys(fld_email);
		driver.findElement(By.name("fld_cemail")).sendKeys(fld_cemail);
		driver.findElement(By.name("fld_username")).sendKeys(fld_username);
		driver.findElement(By.name("fld_password")).sendKeys(fld_password);
		driver.findElement(By.name("dob")).sendKeys(dob);
		driver.findElement(By.name("phone")).sendKeys(phone);
		driver.findElement(By.name("address")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("zip")).sendKeys(zip);
		
		//click the submit button
		driver.findElement(By.xpath("//*[@class='btn btn-default']")).click();
		
		//Conditional statement to determine if registration is successful or not
		//Verify if user is successfully registered
		//Verify if registration is unsuccessful
		if (driver.findElement(By.xpath("//*[@class='alert alert-success']")).isDisplayed()){
			
			System.out.println("Successful Registration.");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@class='alert alert-success']")).isDisplayed());
			
			//Invoke the sign in function then after the sign out function
			signUpPObj.signIn(driver,fld_username,fld_password);
			signUpPObj.signOut(driver);
			
		}
		else {
			
			System.out.println("Unsuccessful Registration. User alreeady exist");
			Assert.assertFalse(driver.findElement(By.xpath("//*[@class='alert alert-success']")).isDisplayed());
			
			//Invoke the sign in function then after the sign out function
			signUpPObj.signIn(driver,fld_username,fld_password);
			signUpPObj.signOut(driver);
	
		}
			
	}
	
	/**
	 * This function will only execute after all the test cases
	 * **/	
	@AfterSuite
	public  void driverKill() {
		
		// closing the driver after all test cases are executed the 
		System.out.println("GOOD BYE!!!");
		driver.close();

	}
	
	/**
	 * Data provider function 
	 * **/
	@DataProvider
	public Object[][] getRegistrationData() throws BiffException, IOException
	{
				
		//Create an object of File class to open xlsx file
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\userDetails.xls");
	    Workbook wb = Workbook.getWorkbook(inputStream);
	    
	    //Define the sheet name
	    String sheetName = "RegistrationData";
	    
	    // Access to Sheet with data
	    Sheet sh = wb.getSheet(sheetName);  
	    
		// To get the number of rows and the number of columns present in sheet
		int totalNoOfRows = sh.getRows();
		int totalNoOfCols = sh.getColumns();
		
		//Defining the data object array
		Object[][] RegistrationData = new Object[totalNoOfRows-1][totalNoOfCols];
		
		//Populating the array with the data from the external file (excel spreadsheet)
		for(int row = 1; row < totalNoOfRows; row++){
			for(int col = 0; col <totalNoOfCols; col++ ){
			
				RegistrationData[row-1][col] = sh.getCell(col, row).getContents();

			}
		}
		
		//return the the array
		return RegistrationData;
		
	}
	
	
	/**
	 * This function takes screenshot on Failure and or Success test
	 * **/
	public void getScreenShot(String result) throws WebDriverException, IOException, AWTException {
		
        Robot robot = new Robot();
        String format = "jpg";
        String fileName = "FullScreenshot" +result+"."+ format;
         
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
        ImageIO.write(screenFullImage, format, new File(fileName));
		
	}

}
