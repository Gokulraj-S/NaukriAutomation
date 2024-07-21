package gokul.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	
	public void handleLocationPopup() {
        try {
            // Using AppleScript to handle the popup
            String[] script = {
                "osascript",
                "-e", "tell application \"Safari\"",
                "-e", "activate",
                "-e", "tell application \"System Events\"",
                "-e", "click button \"Allow\" of window \"Google\" of application process \"Safari\"",
                "-e", "end tell",
                "-e", "end tell"
            };

            Process process = Runtime.getRuntime().exec(script);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@BeforeTest(alwaysRun=true)
	public void initilize() throws IOException {
		FileInputStream fis = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/test/java/gokul/data/GlobalData.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		System.out.println(prop.getProperty("browser"));
		if(prop.getProperty("browser").equals("safari")) {
			
		driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.naukri.com/nlogin/login?URL=https://www.naukri.com/mnjuser/homepage");
		handleLocationPopup();
	}

	public List<HashMap<String, String>> read() throws IOException {
		String stringJSONContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "/src/test/java/gokul/data/Login.JSON"),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Gokul");
		List<HashMap<String, String>> JSONData = mapper.readValue(stringJSONContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return JSONData;
	}

	public String getScreenshot(WebDriver driver, String methodName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/Screenshots/"+methodName+".png";
		FileUtils.copyFile(src, new File(dest));
		return dest;
	}
}
