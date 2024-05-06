package com.process.fligthbooking;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.OutputType;
import org.testng.Assert;



import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.TakesScreenshot;

public class processUtility {
	
		static WebDriver driver;
		LocalDate currentDate = LocalDate.now();
		LocalDate fifthDate = currentDate.plusDays(5);
	    LocalDate tenthDate = currentDate.plusDays(15);
	    int day = fifthDate.getDayOfMonth();
	    int day1 = tenthDate.getDayOfMonth();
	    String fligthOne;
	    String fligthSecond;
	    String ticketCost;
	    String ticketCostreturn;
	    String exters;
	
	public static void takeSnapShot() throws IOException, InterruptedException {
		Thread.sleep(2000);
		File SrcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File DestFile=new File("/Users/anuanjani/Documents/Eclipse/WorkSpace/Selenium/casestudy/ExtentReport" + System.currentTimeMillis() + ".png");
		FileUtils.copyFile(SrcFile, DestFile);
	}
	    
	@SuppressWarnings("deprecation")
	public static void login() throws IOException {
		WebDriverManager.chromedriver().setup();
		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("geo.enabled", false);
		driver = new FirefoxDriver(options);
		
		Properties props = new Properties();
		FileReader reader = new FileReader("/Users/anuanjani/Documents/Eclipse/WorkSpace/Selenium/casestudy/config.properties");
		props.load(reader);
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(props.getProperty("URL"));
		driver.findElement(By.xpath("//*[text()='Accept All']")).click();
	}
	
	public void itineraryDetails() throws IOException, InterruptedException {
		Properties props = new Properties();
		FileReader reader = new FileReader("/Users/anuanjani/Documents/Eclipse/WorkSpace/Selenium/casestudy/config.properties");
		props.load(reader);
		
		driver.findElement(By.xpath("//*[@id='airport-origin']")).sendKeys(props.getProperty("POINTA"));
		driver.findElement(By.xpath("//*[text()=', United Arab Emirates']")).click();
		
		driver.findElement(By.xpath("//*[@id='airport-destination']")).sendKeys(props.getProperty("POINTB"));
		driver.findElement(By.xpath("//*[text()=', Oman']")).click();
		
		driver.findElement(By.xpath("//*[@class='booking-date-selected']")).click();
		driver.findElement(By.xpath("//*[@class='lightpick__months']/section/div[2]/div[text()='"+ day +"']")).click();
		driver.findElement(By.xpath("//*[@class='lightpick__months']/section/div[2]/div[text()='"+ day1 +"']")).click();
       
		driver.findElement(By.xpath("//div[@id='travellerData']/span[2]")).click();
        driver.findElement(By.xpath("//div[@class='plus adult']"));//.click();
        takeSnapShot();
        driver.findElement(By.xpath("//input[@tabindex='23']")).click();  
	}
	
	public void flightSelection() throws InterruptedException, IOException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='Accept All']"));
		
		if(element.isDisplayed()) {
			element.click();
		}
		
		Thread.sleep(6000);
		fligthOne = driver.findElement(By.xpath("//div[@id='14320894']/div[2]/div[3]")).getText();
		driver.findElement(By.xpath("//div[@id='14320894']/div[5]/div[1]/div")).click();
		ticketCost = driver.findElement(By.xpath("//*[@id='desktopHeader']/div[1]/fz-desktop-availability-list/div/div/div/div[2]/div[1]/div/"
				+ "div/div/div[3]/div[2]/div/fz-fare-brand-column/div/div[3]/div[1]/div[2]/div[2]/label")).getText();
		takeSnapShot();
		driver.findElement(By.xpath("//div[3]/div[2]/div/fz-fare-brand-column/div/div[3]/div[2]/fz-button/div/button")).click();
		
		Thread.sleep(4000);
		fligthSecond = driver.findElement(By.xpath("//div[@id='13906745']/div[2]/div[3]")).getText();
		driver.findElement(By.xpath("//*[@id='13906745']/div[5]/div[1]/div")).click();
		ticketCostreturn = driver.findElement(By.xpath("//*[@id='desktopHeader']/div[2]/div/div[1]/fz-desktop-availability-list/div/div/div/div/div[1]/div/div/div/div[3]/div[2]/div/fz-fare-brand-column/div/div[3]/div[1]/div[2]/div[2]/label")).getText();
		driver.findElement(By.xpath("//div[3]/div[2]/div/fz-fare-brand-column/div/div[3]/div[2]/fz-button/div/button")).click();
		takeSnapShot();
	}
	
	public void extrasBaggage() throws InterruptedException, IOException {
		Thread.sleep(9000);
		
		
		exters = driver.findElement(By.xpath("//div[1]/fz-baggage/div/div/div[2]/fz-currency-amount/div/fz-static-label[2]")).getText();
		driver.findElement(By.xpath("//div[@class='imageClass']/fz-image/img[@alt='+10 KG']")).click();
		takeSnapShot();
		driver.findElement(By.xpath("//*[@class='navigateToPassengerDetails ng-star-inserted']")).click();
	}
	
	public void passengerInformation() throws IOException, InterruptedException {
		Properties props = new Properties();
		FileReader reader = new FileReader("/Users/anuanjani/Documents/Eclipse/WorkSpace/Selenium/casestudy/config.properties");
		props.load(reader);
		
		driver.findElement(By.xpath("//*[@id='First_Name']")).sendKeys(props.getProperty("FirstName"));
		driver.findElement(By.xpath("//*[@id='Last_Name']")).sendKeys(props.getProperty("LastName"));
		driver.findElement(By.xpath("//*[@id='Email_Address']")).sendKeys(props.getProperty("Email"));
		driver.findElement(By.xpath("//*[@id='Male.Text']")).click();
		driver.findElement(By.xpath("//*[@id='Mobile_Number']")).sendKeys(props.getProperty("Mobile"));
		
		driver.findElement(By.xpath("//div[@class='country-code-dropdown']")).click();
		driver.findElement(By.xpath("//span[text()='United Arab Emirates']")).click();
		takeSnapShot();
		driver.findElement(By.xpath("//*[@id='span']")).click();
	}
	
	public void validateBookingDetails() throws IOException, InterruptedException{
		Properties props = new Properties();
		FileReader reader = new FileReader("/Users/anuanjani/Documents/Eclipse/WorkSpace/Selenium/casestudy/config.properties");
		props.load(reader);
		
		//Flight:
		String expectedFlightOne =driver.findElement(By.xpath("//*[@id='anchor-id0']/div[1]/fz-review-trip-details/div/div[2]/div/div[1]/div/div/div[1]/fz-dynamic-label/label/div/span")).getText();
		String flightDetails = fligthOne;
		String[] words = flightDetails.split("\\s+");
		String a0 = words.length > 0 ? words[0] : null;
        String a1 = words.length > 1 ? words[1] : null;
        String actualFligthOne = a0 +" " +a1;
		Assert.assertEquals(expectedFlightOne, actualFligthOne);
		takeSnapShot();
		
		String expectedFlightTwo =driver.findElement(By.xpath("//*[@id='anchor-id0']/div[2]/fz-review-trip-details/div/div[2]/div/div[1]/div/div/div[1]/fz-dynamic-label/label/div/span")).getText();
		String flightDetailsOne = fligthSecond;
		String[] word = flightDetailsOne.split("\\s+");
		String b0 = word.length > 0 ? word[0] : null;
        String b1 = word.length > 1 ? word[1] : null;
        String actualFligthTwo = b0 +" " +b1;
		Assert.assertEquals(expectedFlightTwo, actualFligthTwo);
		
		//Date:
		String DOD =driver.findElement(By.xpath("//div[1]/fz-review-trip-details/div/div[2]/div/div[2]/div/fz-flight-details-review/div/div/div/div[2]/div[1]/fz-date-core/div/p")).getText();
		int originDateOfDeparture = day;
		String[] word1 = DOD.split("\\s+");
		String str = word1.length > 0 ? word1[0] : null;
		String expectedDateOfDeparture = String.valueOf(Integer.parseInt(str));
		Assert.assertEquals(Integer.parseInt(expectedDateOfDeparture), originDateOfDeparture);
		
		String DOA =driver.findElement(By.xpath("//div[2]/fz-review-trip-details/div/div[2]/div/div[2]/div/fz-flight-details-review/div/div/div/div[2]/div[1]/fz-date-core/div/p")).getText();
		int originDateOfArrival = day1;
		String[] word2 = DOA.split("\\s+");
		String str1 = word2.length > 0 ? word2[0] : null;
		String expectedDateOfArrival = String.valueOf(Integer.parseInt(str1));
		Assert.assertEquals(Integer.parseInt(expectedDateOfArrival), originDateOfArrival);
		
		//route
		String expectedsegmentOne =driver.findElement(By.xpath("//div[text()='Departure from ']//strong")).getText();
		String actualsegmentOne = props.getProperty("POINTA");
		Assert.assertEquals(expectedsegmentOne, actualsegmentOne);
		
		String expectedsegmentTwo =driver.findElement(By.xpath("//div[text()='Return from ']//strong")).getText();
		String actualsegmentTwo = props.getProperty("POINTB");
		Assert.assertEquals(expectedsegmentTwo, actualsegmentTwo);
		
		//PassengersName
		String expectedPassengersName =driver.findElement(By.xpath("//div[@class='noShowLabelTitle']")).getText();
		String originlDestinationOne = props.getProperty("FirstName");
		String originlDestinatiotwo = props.getProperty("LastName");
		String originlPassengersName = "Mr. " + originlDestinationOne +" "+ originlDestinatiotwo;
		Assert.assertEquals(expectedPassengersName, originlPassengersName);
		takeSnapShot();
		
		//Fare and tax
		String total = driver.findElement(By.xpath("//div[@class='fareBreakdownSummary']/div[1]/div/fz-currency-amount/div/fz-static-label[2]/label")).getText();
		String fare = driver.findElement(By.xpath("//div[@class='fareBreakdownSummary']/div[2]/div/fz-currency-amount/div/fz-static-label[2]/label")).getText();
		String taxes = driver.findElement(By.xpath("//div[@class='fareBreakdownSummary']/div[3]/div/fz-currency-amount/div/fz-static-label[2]/label")).getText();
		String extras1 = driver.findElement(By.xpath("//div[@class='fareBreakdownSummary']/div[4]/div/fz-currency-amount/div/fz-static-label[2]/label")).getText();
		
		System.out.println("Total: " + total);
		System.out.println("fare: " + fare);
		System.out.println("Total taxes: " + taxes);
		System.out.println("extras One: " + extras1);
		
		System.out.println("ticket Cost: " + ticketCost);
		System.out.println("ticket Cost return: " + ticketCostreturn);
		System.out.println("exters: " + exters);
		
		// Payment button
		driver.findElement(By.xpath("//*[@id=\"span\"]")).click();
	}
	
	public void paymentAndConfirmation() throws InterruptedException, IOException {
		Thread.sleep(11000);
		driver.findElement(By.xpath("//div[text()='Pay later']/i")).click();
		driver.findElement(By.xpath("//button[@class='primary pay-now']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='modal-footer farecontainer__footer']/button[1]")).click();
		takeSnapShot();
		String pnrNumber = driver.findElement(By.xpath("//label[@id='flightBookingReferenceText']")).getText();
		String totalAmountDue = driver.findElement(By.xpath("//*[@class='currency-amount']/fz-static-label[2]/label")).getText();
		
		System.out.println("PNR/Refrence Number: " + pnrNumber);
		System.out.println("Total Amount Due: " + totalAmountDue);
		driver.quit();
	}
}