package Base;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenShotListener extends BaseTest implements ITestListener {

    public void onTestFailure(ITestResult results) {
        System.out.println("Failed Test Case");
        try {
            screenshotError(results.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
