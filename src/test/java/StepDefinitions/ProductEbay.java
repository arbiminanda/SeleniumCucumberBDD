package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductEbay {
	
	WebDriver driver = null;
	    
	@Given("User is on ebay homepage")
	public void user_is_on_ebay_homepage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com");
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.id("gh-logo"))
        ));
	}
	
	@When("^User fill search (.*)$")
	public void User_fill_search_keyword(String keyword) {
		driver.findElement(By.id("gh-ac")).sendKeys(keyword);
	}
	
	@And("User choose category for search product")
	public void User_choose_category_for_search_product() {
		driver.findElement(By.id("gh-cat")).click();
		driver.findElement(By.xpath("//option[contains(text(),'Computers/Tablets & Networking')]")).click();
	}
	
	@And("User click search submit button")
	public void User_click_search_submit_button() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Then("^Page loaded and user match the search results with (.*)$")
	public void Page_loaded_and_user_match_the_search_results_with_keyword(String keyword) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
	    wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1[contains(text(),'results')]"))
        ));
	    wait.until(ExpectedConditions.or(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'" + keyword + "')]"))
		));
	    List<WebElement> match_first_element = driver.findElements(By.xpath("//ul/li[1]/div/div/a/h3[contains(text(),'" + keyword + "')]"));
	    List<WebElement> match_first_element_upper_case = driver.findElements(By.xpath("//ul/li[1]/div/div/a/h3[contains(text(),'" + keyword.toUpperCase() + "')]"));
	    if (match_first_element.isEmpty() && match_first_element_upper_case.isEmpty()) {
	    	driver.findElement(By.xpath("//ul/li[1]/div/div/a/h3[contains(text(),'" + keyword.toLowerCase() + "')]"));
	    }
	    driver.close();
	}
	
}