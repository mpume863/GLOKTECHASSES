package signIn;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class listeners implements ITestListener{
	
	ControllerClassTest cntlClsObj = new ControllerClassTest();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		String successScrnSht = "Success_"+result.getName();
		
		try {
			cntlClsObj.getScreenShot(successScrnSht);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String failureScrnSht = "failure_"+result.getName();
		
		try {
			cntlClsObj.getScreenShot(failureScrnSht);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}