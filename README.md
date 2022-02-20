# Selenium Cucumber BDD

Tools Used: Java, Selenium, Cucumber, Gherkin, JUnit

# Setting up and running tests

* Open project as Maven Project in Eclipse, Intellij, or other IDE 
* In this repository, the chrome driver for Google Chrome version 97 is already available. 
	* In case your Google Chrome version is different, you can download chrome driver from [this url](https://chromedriver.chromium.org/downloads) according to your version. Place chrome driver in the following folder of directory
```
{YourProjectPath}src/test/resources/drivers/chromedriver.exe
```
* In this repository, there are 2 feature files consisting of:
	* ProductEbay.feature, contains test scenario to access ebay produt by search
	* GistGithub.feature, contains several test scenarios to create, see list, edit, and delete gist
* You can run this testing by running TestRunner file using JUnit, the directory of TestRunner is:
```
{YourProjectPath}src/test/java/TestRunner
```
* You can replace the username and password in GistGithub.feature file with yout github account