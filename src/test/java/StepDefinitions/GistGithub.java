package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GistGithub {
	
	WebDriver driver = null;
	public String gistName = "Gist " + (new SimpleDateFormat("ddMMyyyyHHmmSS").format(new Date()));
	public String gistId;
	
	public String readIdFromFile() {
		String data = null;
		try {
            File myFile = new File("src/test/resources/data/gistId.txt");
            Scanner fileReader = new Scanner(myFile);
            
            if(fileReader.hasNextLine()){
                data = fileReader.nextLine();
                System.out.println(data);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Terjadi Kesalahan: " + e.getMessage());
            e.printStackTrace();
        }
		
		return data;
	}
	    
	@Given("User is on gist homepage")
	public void user_is_on_ebay_homepage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://gist.github.com/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Gist Homepage ']"))
        ));
	}
	
	@When("User navigate to sign in page")
	public void User_navigate_to_sign_in_page() {
		driver.findElement(By.xpath("//div/a[@data-ga-click='Header, sign in']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[contains(text(),'Sign in to')]"))
	    ));
	}
	
	@And("^User fill (.*) and (.*)$")
	public void User_fill_username_and_password(String username, String password) {
		driver.findElement(By.xpath("//*[contains(@id, 'login_field')]")).sendKeys(username);
		driver.findElement(By.xpath("//*[contains(@class, 'js-password-field')]")).sendKeys(password);
	}
	
	@And("User click sign in button")
	public void User_click_sign_in_button() {
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
	}
	
	@Then("User logged in to gist")
	public void User_logged_in_to_gist() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='All gists']"))
	    ));
	}
	
	@When("User click create new gist button")
	public void User_click_create_new_gist_button() {
		driver.findElement(By.xpath("//a[@aria-label='Create new gist']")).click();
	}
	
	@And("User fill new gist name")
	public void User_fill_new_gist_name() throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@id='gists']/input[@type='text']")).sendKeys(gistName);
	}
	
	@And("User fill gist content")
	public void User_fill_gist_content() {
		driver.findElement(By.xpath("//span[@role='presentation']")).sendKeys("This is gist content");
	}
	
	@And("User choose to create public gist")
	public void User_choose_to_create_public_gist() {
		driver.findElement(By.xpath("//summary[@aria-label='Select a type of pull request']")).click();
		driver.findElement(By.xpath("//span[@class='select-menu-item-heading'][contains(text(),'Create public gist')]")).click();
	}
	
	@And("User click create public gist button")
	public void User_click_create_public_gist_button() {
		driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Create public gist')]")).click();
	}
	
	@Then("User see that gist has been created")
	public void User_see_that_gist_has_been_created() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@itemprop='name']/a[contains(@href,'arbiminanda')]"))
	    ));
	    gistId = driver.findElement(By.xpath("//strong[@itemprop='name']/a[contains(@href,'arbiminanda')]")).getText();
	    try {
            FileWriter fileWriter = new FileWriter("src/test/resources/data/gistId.txt");
            fileWriter.write(gistId);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan karena: " + e.getMessage());
        }
	    driver.close();
	}
	
	@When("User click on profile menu")
	public void User_click_on_profile_menu() {
		driver.findElement(By.xpath("//summary[@class='Header-link name']")).click();
	}
	
	@And("User click your gists")
	public void User_click_your_gists() {
		driver.findElement(By.xpath("//a[@role='menuitem'][contains(text(),'Your gists')]")).click();
	}
	
	@Then("User see the list of gists")
	public void User_see_the_list_of_gists() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/strong[contains(text(),'" + readIdFromFile() + "')]"))
	    ));
	}
	
	@When("User click on spesific gist")
	public void User_click_on_spesific_gist() {
		driver.findElement(By.xpath("//a/strong[contains(text(),'" + readIdFromFile() + "')]")).click();
	}
	
	@And("User click on edit gist")
	public void User_click_on_edit_gist() throws InterruptedException {
		Thread.sleep(500);
		String edit_link = driver.findElement(By.xpath("//a[@aria-label='Edit this Gist']")).getAttribute("href");
		driver.get(edit_link);
	}
	
	@And("User edit gist content")
	public void User_edit_gist_content() {
		driver.findElement(By.xpath("//span[@role='presentation']")).sendKeys(" Edited");
	}

	@And("User click update gist button")
	public void User_click_update_gist_button() {
		driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Update public gist')]")).click();
	}
	
	@Then("User see gist edited")
	public void User_see_gist_edited() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Edited')]"))
	    ));
	    driver.close();
	}
	
	@And("User click on delete gist")
	public void User_click_on_delete_gist() throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@aria-label='Delete this Gist'][@type='submit'][contains(@class,'btn-danger')]")).click();
	}
	
	@And("User confirm delete alert")
	public void User_confirm_delete_alert() {
		driver.switchTo().alert().accept();
		driver.close();
	}
	
}