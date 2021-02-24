import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Flight_Search {
	
	//li element output
	public static void selected(List<WebElement> clS, String button) {
		int j=0;
		int k=0;

		for (WebElement i : clS) {
			k++;
			if (i.getAttribute("class").equalsIgnoreCase(button)) {
				j=1;
				System.out.println(i.getText() + " is Selected");
				break;
				
			}
		}
//		System.out.println(j+""+k);
	}

	public static void main(String[] args) throws InterruptedException {
		
System.setProperty("webdriver.chrome.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\chromedriver.exe");
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.spicejet.com/");
		
		Thread.sleep(2000l);
		
		// actual title and urls
		String url_actual = "https://www.spicejet.com/";
		String title_actual = "SpiceJet - Flight Booking for Domestic and International, Cheap Air Tickets";

		// expected titles and urls
		String url_expected = driver.getCurrentUrl();
//		System.out.println(url_expected);
		String title_expected = driver.getTitle();
//		System.out.println(title_expected);

		// validating url and title
		Assert.assertEquals(url_actual, url_expected);
		Assert.assertEquals(title_actual, title_expected);
		
		String label= "select-label";
		String roundButton="table[id='ctl00_mainContent_rbtnl_Trip'] label";
		
		List <WebElement> radioButton= driver.findElements(By.cssSelector(roundButton));
		selected(radioButton,label);
	}

}
