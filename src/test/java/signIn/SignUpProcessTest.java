package signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SignUpProcessTest{
	
	/**
	 * Sign in the register/existing user
	 * **/
	public void signIn(WebDriver driver,String fld_username, String fld_password){
		
		//Clicks the log in button
		driver.findElement(By.xpath("//*[@href='login.php']")).click();
		
		//Populates the user name and password and log in
		WebElement txtUserName = driver.findElement(By.name("_txtUserName"));
		WebElement txtPassword = driver.findElement(By.name("_txtPassword"));
		txtUserName.sendKeys(fld_username);
		txtPassword.sendKeys(fld_password);
		txtPassword.submit();
		
		//Conditional statement to determine if log in is successful or not
		//Verify if user is successfully logged in
		//Verify if login is unsuccessful
		if(driver.findElement(By.xpath("//*[@href='logout.php']")).isDisplayed()) {
			
			Assert.assertTrue(driver.findElement(By.xpath("//*[@href='logout.php']")).isDisplayed());
			System.out.println("The User is Successfully logged in");
						
		}else 
			if (driver.findElement(By.xpath("//*[@class='alert alert-danger alert-normal-danger']")).isDisplayed()){
			
			Assert.assertFalse(driver.findElement(By.xpath("//*[@class='alert alert-danger alert-normal-danger']")).isDisplayed());
			System.out.println("The user LogIn is unsuccessful");
			
		} 		
	}

	/**
	 * Sign out the logged in User
	 * **/
	public void signOut(WebDriver driver) {
		
		//clicks the log in button
		driver.findElement(By.xpath("//*[@href='logout.php']")).click();
		
		//Pass test if this condition is met
		WebElement  logOut  = driver.findElement(By.xpath("//*[@href='login.php']"));
		Assert.assertTrue(logOut.isDisplayed());
		System.out.println("The User is successfully logged out");
		
	}

}
