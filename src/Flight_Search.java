import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Flight_Search {

	static WebDriver driver;
	static WebDriverWait wait;

	// Selected button
	public static void selected(List<WebElement> clS, String button) {

		for (WebElement i : clS) {

			if (i.getAttribute("class").equalsIgnoreCase(button)) {

				System.out.println(i.getText() + " is Selected");
				break;

			}
		}

	}

	// Static Dropdown selection
	public static void statDrop(List<WebElement> clS) {
		int j = 0;
		for (WebElement i : clS) {

			Select t = new Select(i);
			if (j == 0) {
				t.selectByIndex(4);
			} else if (j == 1) {
				t.selectByIndex(1);
			} else {
				t.selectByIndex(2);
			}
			j++;

		}
	}

	public static void datePicker(String date, String monthyear) {
		boolean b = true;

		while (b) {

			WebElement group = driver
					.findElement(By.cssSelector("[class='ui-datepicker-group ui-datepicker-group-first']"));

			wait.until(ExpectedConditions
					.visibilityOfAllElements(group.findElement(By.cssSelector(".ui-datepicker-title"))));

			WebElement title = group.findElement(By.cssSelector(".ui-datepicker-title"));

			// getting month text
			String month = title.findElement(By.cssSelector(".ui-datepicker-month")).getText();

			// getting year text
			String year = title.findElement(By.cssSelector(".ui-datepicker-year")).getText();

			// string concating to further compare
			String monthYear1 = month.concat(year);

			if (monthYear1.equalsIgnoreCase(monthyear)) {
				wait.until(ExpectedConditions
						.visibilityOfAllElements(group.findElements(By.cssSelector("[data-handler='selectDay']"))));
				List<WebElement> dates = group.findElements(By.cssSelector("[data-handler='selectDay'] >a"));

				for (WebElement day : dates) {
					wait.until(ExpectedConditions.elementToBeClickable(day));
					String current = day.getText();

					if (current.equalsIgnoreCase(date)) {
						day.click();
						b = false;
						break;

					}
				}

			}

			else {

//				wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("[data-handler='next']"))));
				driver.findElement(By.cssSelector("[data-handler='next']")).click();

			}

		}

	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Demiurges\\Documents\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.spicejet.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);

		// actual title and urls
		String url_actual = "https://www.spicejet.com/";
		String title_actual = "SpiceJet - Flight Booking for Domestic and International, Cheap Air Tickets";

		// expected titles and urls
		String url_expected = driver.getCurrentUrl();
		String title_expected = driver.getTitle();

		// validating url and title
		Assert.assertEquals(url_actual, url_expected);
		Assert.assertEquals(title_actual, title_expected);

		String label = "select-label";
		String roundButton = "table[id='ctl00_mainContent_rbtnl_Trip'] label";

		List<WebElement> radioButton = driver.findElements(By.cssSelector(roundButton));
		selected(radioButton, label);

		// selecting round Trip
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		selected(radioButton, label);

		// from to destination selecting
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();

		driver.findElement(By.xpath("//a[@value='BLR']")).click();

		// selecting destination
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='IXB']"))
				.click();

		// calender start date picking
		datePicker("22", "June2021");
		
		//calender return date picking
		driver.findElement(By.id("ctl00_mainContent_view_date2")).click();
		datePicker("7", "August2021");

		// passenger dropdown selection
		driver.findElement(By.id("divpaxinfo")).click();
		List<WebElement> li = driver
				.findElements(By.xpath("//div[@id='divpaxOptions'] //select[contains(@id,'ctl00_mainContent_ddl')]"));
		System.out.println(li.size());
		statDrop(li);

		// currency dropdown selection
		List<WebElement> lin = driver.findElements(By.id("ctl00_mainContent_DropDownListCurrency"));
		System.out.println(lin.size());
		statDrop(lin);

		// checkbox
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();

		// url assertion
		String exp_url_booking = driver.getCurrentUrl();
		Assert.assertEquals("https://book.spicejet.com/Select.aspx", exp_url_booking);
		
		driver.close();

	}

}
