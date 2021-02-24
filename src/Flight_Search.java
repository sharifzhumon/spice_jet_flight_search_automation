import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Flight_Search {
	
	//Selected button
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
		
		
		//selecting round Trip
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		selected(radioButton,label);
		
		//from to destination selecting
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		
		driver.findElement(By.xpath("//a[@value='BLR']")).click();
		Thread.sleep(1000l);

		//selecting destination
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='IXB']")).click();
		
		Thread.sleep(2000l);
		
		
		//calender current date picking
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
		
		

	}

}
