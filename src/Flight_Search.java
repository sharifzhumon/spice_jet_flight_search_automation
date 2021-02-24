import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Flight_Search {
	
	//Selected button
	public static void selected(List<WebElement> clS, String button) {


		for (WebElement i : clS) {
		
			if (i.getAttribute("class").equalsIgnoreCase(button)) {
				
				System.out.println(i.getText() + " is Selected");
				break;
				
			}
		}

	}

	//Static Dropdown selection
	public static void statDrop(List<WebElement> clS) throws InterruptedException {
		Thread.sleep(1000l);
		int j=0;
		for (WebElement i : clS) {
			
			
			Select t= new Select(i);
			if(j==0) {
				t.selectByIndex(4);
			} else if (j==1) {
				t.selectByIndex(1);
			} else {
				t.selectByIndex(2);
			}
			j++;
			
			Thread.sleep(1000l);
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
		
		//passenger dropdown selection
		driver.findElement(By.id("divpaxinfo")).click();
		List <WebElement> li= driver.findElements(By.xpath("//div[@id='divpaxOptions'] //select[contains(@id,'ctl00_mainContent_ddl')]"));
		System.out.println(li.size());
		statDrop(li);
		
		//currency dropdown selection
		List <WebElement> lin= driver.findElements(By.id("ctl00_mainContent_DropDownListCurrency"));
		System.out.println(lin.size());
		statDrop(lin);
		
		//checkbox
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();
		
		Thread.sleep(1000l);
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		
		//url assertion
		String exp_url_booking= driver.getCurrentUrl();
		Assert.assertEquals("https://book.spicejet.com/Select.aspx", exp_url_booking);

	}

}
