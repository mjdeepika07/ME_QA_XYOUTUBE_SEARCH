package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;

import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;
        String url = "https://www.youtube.com/";
        WebDriverWait wait;
        SoftAssert softAssert = new SoftAssert();
        int dataInputStringIndex = 0;

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");
                
                //NOT NEEDED FOR SELENIUM MANAGER
                //WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

                wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        }


        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        @Test
        public void testCase01() throws InterruptedException{

                try{

                        String optionName = "About";

                        Wrappers.wrapper_navigateToUrl(driver, wait, url);

                        Wrappers.wrapper_clickMainMenu(driver, wait);

                        Wrappers.wrapper_clickSidebarBottomOption(driver, wait, optionName);

                        WebElement weMessageToPrint1 = driver.findElement(By.xpath("//section[@class='ytabout__content']/h1"));
                        WebElement weMessageToPrint2 = driver.findElement(By.xpath("//section[@class='ytabout__content']/p"));
                        
                        String messageToPrint = weMessageToPrint1.getText() + "\n" + weMessageToPrint2.getText();
                        Wrappers.wrapper_printMessageOnScreen(driver, wait, messageToPrint);

                        driver.navigate().back();

                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        //for(List<String> listOfRows : Object[][]){
                                
                        

                }
                catch(Exception e){
                        System.out.println(e.getMessage());
                }

        }

        @Test
        public void testCase02(){

                try{

                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);

                        Wrappers.wrapper_clickMainMenu(driver, wait);

                        String sidebarMainOption = "Films";
                        Wrappers.wrapper_clickSidebarMainOption(driver, wait, sidebarMainOption);

                        String sectionName = "Top selling";
                        Wrappers.wrapper_locateRightArrow(driver, wait, sidebarMainOption, sectionName);

                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);

                        WebElement weMovieName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//following-sibling::span[@id='video-title'])[16]")));
                        
                        Wrappers.wrapper_isMovieMatureOrNot(driver, wait, weMovieName);

         
                        Wrappers.wrapper_isMovieComedyOrAnimation(driver, wait, weMovieName);



                }
                catch(Exception e){
                        System.out.println(e.getMessage());
                }
        }



        @Test
        public void testCase03(){

                try{
                        Wrappers.wrapper_clickMainMenu(driver, wait);
                        String sidebarMainOption = "Music";
                        Wrappers.wrapper_clickSidebarMainOption(driver, wait, sidebarMainOption);
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        String sectionName = "India's Biggest Hits";
                        Wrappers.wrapper_locateRightArrow(driver, wait, sidebarMainOption, sectionName);
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        Integer noOfTracks = Wrappers.wrapper_getNumberOfTracks(driver, wait);
                        System.out.println("No of Tracks : " + noOfTracks);
                        //softAssert.assertTrue(noOfTracks<50,"The no. of tracks are greater than 50");
                        if(noOfTracks<=50)     
                                System.out.println("The no. of tracks are less than or equal to 50");
                                else
                                        System.out.println("The no. of tracks are greater than 50");    

                }
        
                catch(Exception e){
                        System.out.println(e.getMessage());
                }
        }


        @Test
        public void testCase04(){

                try{
                        
                        Wrappers.wrapper_clickMainMenu(driver, wait);
                        String sidebarMainOption = "News";
                        Wrappers.wrapper_clickSidebarMainOption(driver, wait, sidebarMainOption);
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        String sectionName = "Latest news posts";
                        //Wrappers.wrapper_clickShowMoreButton(driver,wait, By.xpath("(//button[@aria-label='Show more'])[2]"));
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        Wrappers.wrapper_getLatestNewsPostsTitleNBody(driver, wait, sectionName);
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        double totalCountOfFirstThreePosts = Wrappers.wrapper_getTotalCountOfLikes(driver, wait);
                        Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                        System.out.println("The total count of first three likes of the first 3 posts : " + totalCountOfFirstThreePosts);
                        
                        
                }
        
                catch(Exception e){
                        System.out.println(e.getMessage());
                }
        }


      

        @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
        public void testCase05(String sidebarMainOption) throws InterruptedException{
                //String sidebarMainOption;
                //System.out.println(sidebarMainOption);
                //String[] inputSearchStrings = {"Movies","Music","Games"};
                

                // SoftAssert softAssert = new SoftAssert();
                // softAssert.assertEquals(sidebarMainOption,inputSearchStrings[dataInputStringIndex]);
               
                //softAssert.assertAll();

                // if(sidebarMainOption.equals("Movies")){
                        
                //         sidebarMainOption = "Films";
                // }
                // else if(sidebarMainOption.equals("Games")){
                //         sidebarMainOption = "Gaming";
                //}

                //sidebarMainOption ="Games";
                // Wrappers.wrapper_clickMainMenu(driver, wait);
                // Wrappers.wrapper_clickSidebarMainOption(driver, wait, sidebarMainOption);
                //driver.navigate().back();
                WebElement weSearchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));    
                //input[@id="search"]
                weSearchbox.clear();
                weSearchbox.sendKeys(sidebarMainOption);
                weSearchbox.sendKeys(Keys.ENTER);


                Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);

                long totalCountOfViews = Wrappers.wrapper_getTotalNumberOfViewsCounts(driver, wait);
                //span[@class='inline-metadata-item style-scope ytd-video-meta-block'][1]"));
                Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                //dataInputStringIndex++;

                System.out.println("The Total amount of views below 1cr are  : " + totalCountOfViews);                


        }

          
        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }

}




















/*
 * 
 * 
 * // WebElement weSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));
                        // weSearch.clear();
                        // weSearch.sendKeys("Christian Music");

***************************
        //                 try {
        //     Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

****************************


//div[@id="dismissible"]//span[contains(text(),'Top selling')]/ancestor::div[@class="grid-subheader style-scope ytd-shelf-renderer"]/following-sibling::div//div[@id='right-arrow']
                        //div[@id="dismissible"]//a[@title='Top selling']/ancestor::div[@class="grid-subheader style-scope ytd-shelf-renderer"]/following-sibling::div//div[@id='right-arrow']
                        

                        //div[@id='right-arrow'])[4]

                        //div[@id='dismissible']//div[@class='yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response']


 */


                        //div[@id='dismissible']//span[contains(text(),'"+sectionName+"')]

                        //ytd-badge-supported-renderer[@class='style-scope ytd-grid-movie-renderer']/following-sibling::span[@id='video-title']
               