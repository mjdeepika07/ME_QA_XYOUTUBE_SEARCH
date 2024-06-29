package demo.wrappers;
//package mjdeepika07-ME_QA_XYOUTUBE_SEARCH.src.main.java.demo.wrappers;
///Users/garikimukkulakamalakar/dev/mjdeepika07-ME_QA_XYOUTUBE_SEARCH/src/main/java/demo

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.runtime.model.ExceptionThrown;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.sdk.metrics.internal.concurrent.DoubleAdder;

import java.time.Duration;
import java.util.List;

public class Wrappers {
    static JavascriptExecutor js;
    static SoftAssert softAssert = new SoftAssert();
    
    /*
     * Write your selenium wrappers here
     */

    public static void wrapper_navigateToUrl(ChromeDriver driver, WebDriverWait wait, String url){

        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
        Assert.assertEquals(driver.getCurrentUrl(),url);

    }

    public static void wrapper_clickMainMenu(ChromeDriver driver, WebDriverWait wait){

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
                        );
        
        WebElement weMainMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='contentContainer']")));
    
        weMainMenu.click();
        

    }

    public static void wrapper_clickSidebarBottomOption(ChromeDriver driver, WebDriverWait wait, String optionName){

        // WebElement weGuide = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='contentContainer']")));
        // weGuide.click();
        
        WebElement weAbout = driver.findElement(By.xpath("//a[@slot='guide-links-primary' and text()='"+optionName+"']"));
        js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", weAbout);
        //wait.until(ExpectedConditions.elementToBeClickable(weAbout));
        weAbout.click();
    }


    public static void wrapper_clickSidebarMainOption(ChromeDriver driver, WebDriverWait wait, String sidebarMainOption){

        try {

            wait.until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );

            
            //WebElement weSidebarMainOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//yt-formatted-string[@class='title style-scope ytd-guide-entry-renderer' and contains(text(),'"+sidebarMainOption+"')]")));
            
            WebElement weSidebarMainOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//yt-formatted-string[@class='title style-scope ytd-guide-entry-renderer' and contains(text(),'"+sidebarMainOption+"')]")));
            js.executeScript("arguments[0].scrollIntoView(true);", weSidebarMainOption);
            
            weSidebarMainOption.click();
            Thread.sleep((new java.util.Random().nextInt(3)+2)*1000);


        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

    public static void wrapper_locateRightArrow(ChromeDriver driver, WebDriverWait wait, String sideMainOption, String sectionName){

        try{

            WebElement weRightArrow = null;
            if(sideMainOption.equals("Films")){
                weRightArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dismissible']//span[contains(text(),'"+sectionName+"')]/ancestor::div[@class='grid-subheader style-scope ytd-shelf-renderer']/following-sibling::div//div[@id='right-arrow']//button")));
                while(weRightArrow.isDisplayed()){

                    Thread.sleep((new java.util.Random().nextInt(3) + 2) * 1000);
                    
                    weRightArrow.click();
                    
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
                        );
                    
    
                }
            
            }
            else if(sideMainOption.equals("Music")){

                WebElement weSectionName = driver.findElement(By.xpath("//span[@id='title' and contains(text(),\"India's Biggest Hits\")]"));


                //weRightArrow1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='right-arrow']//button")));
                js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].scrollIntoView(true);", weSectionName);
                //if(weSectionName.getText().equals(sectionName)){

                    WebElement weRightArrow1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='title' and contains(text(),\"India's Biggest Hits\")]/ancestor::div[@class='grid-subheader style-scope ytd-shelf-renderer']/following-sibling::div[@id='contents']//div[@id='right-arrow']//button")));
                    while(weRightArrow1.isDisplayed()){

                        Thread.sleep((new java.util.Random().nextInt(3)+2)*1000);

                        weRightArrow1.click();
                        
                        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
                        );
                    }
                   

                //}
            }
            
        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        

    }

    public static int wrapper_getNumberOfTracks(ChromeDriver driver, WebDriverWait wait){

        List<WebElement> weListOfTracks = driver.findElements(By.xpath("//span[@id='title' and contains(text(),\"India's Biggest Hits\")]/ancestor::div[@class='grid-subheader style-scope ytd-shelf-renderer']/following-sibling::div[@id='contents']//div[@id='scroll-outer-container']//h3[@class='style-scope ytd-compact-station-renderer']/following-sibling::p"));
        WebElement weLastTrackInList = weListOfTracks.get(weListOfTracks.size()-1);
        int noOfTracks = wrapper_getSplitString(weLastTrackInList);
        
        return noOfTracks;
    }

    
    public static int wrapper_getSplitString(WebElement weNoOfTracks){
        int noOfTracks = 0;
        String noOfTracksStr = weNoOfTracks.getText();
        String[] noOfTracksSplitStr = noOfTracksStr.split(" ");
        String noOfTracksModifiedStr = noOfTracksSplitStr[0];
        noOfTracks = Integer.parseInt(noOfTracksModifiedStr);
        return noOfTracks;
    }


    public static void wrapper_printMessageOnScreen(ChromeDriver driver, WebDriverWait wait, String messageToPrint){

        System.out.println(messageToPrint);
        
    }

    public static void wrapper_isMovieMatureOrNot(ChromeDriver driver, WebDriverWait wait, WebElement weMovieName){

       
        String movieName = weMovieName.getAttribute("title");
        System.out.println("The last movieName under Top selling category : " + movieName);
        WebElement weAdultOrNot = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//following-sibling::span[@title='"+movieName+"']/../../following-sibling::ytd-badge-supported-renderer/div[2]/p")));
        System.out.println(" Is the movie marked 'A' for Mature or not : " + weAdultOrNot.getText());
        softAssert.assertTrue(weAdultOrNot.getText().contains("A"));
        softAssert.assertAll();
       
    }



    public static void wrapper_isMovieComedyOrAnimation(ChromeDriver driver, WebDriverWait wait, WebElement weMovieName){
        
        String movieName = weMovieName.getAttribute("title");

        WebElement weIsMovieComedyOrAnimation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//following-sibling::span[@title='"+movieName+"']/../following-sibling::span")));
        
        softAssert.assertTrue(weIsMovieComedyOrAnimation.getText().contains("Comedy"),"The movie is NOT a Comedy movie!");
        softAssert.assertFalse(weIsMovieComedyOrAnimation.getText().contains("Animation"),"The movie is an Animation movie!");
        //softAssert.assertAll();
    
    
    }

    public static void wrapper_getLatestNewsPostsTitleNBody(ChromeDriver driver, WebDriverWait wait, String sectionName){
        
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
        try{
            List<WebElement> weListOfLatestNewsPostTitles = driver.findElements(By.xpath("//span[@id='title'and contains(text(),'"+sectionName+"')]/ancestor::div[@id='dismissible']/div[@id='contents']/ytd-rich-item-renderer//div[@id='header']//a[@id='author-text']/span[@class='style-scope ytd-post-renderer']"));
            System.out.println(weListOfLatestNewsPostTitles.size());
            js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView(true);", weListOfLatestNewsPostTitles.get(0));
            Thread.sleep((new java.util.Random().nextInt(3)+2)*1000);


            List<WebElement> weListOfLatestNewsPostBody = driver.findElements(By.xpath("//span[@id='title'and contains(text(),'"+sectionName+"')]/ancestor::div[@id='dismissible']/div[@id='contents']/ytd-rich-item-renderer//div[@id='body']//yt-formatted-string[@id='home-content-text']/span[@dir='auto'][1]"));
            
            System.out.println(weListOfLatestNewsPostBody.size());


            for(int i = 0; i < 3; i++){
                    System.out.println(weListOfLatestNewsPostTitles.get(i).getText());
                    System.out.println(weListOfLatestNewsPostBody.get(i).getText());

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        

    }

    public static double wrapper_getTotalCountOfLikes(ChromeDriver driver, WebDriverWait wait){
        double totalCountOfLikesFirstThreePosts = 0;
        String likesCountStr=null;

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );

        try{
        List<WebElement> weListOfCountOfLikes = driver.findElements(By.xpath("//span[@id='vote-count-middle']"));
        
        
        for(int i = 0; i < weListOfCountOfLikes.size(); i++){
            
            //int likesCountEachInt = 0;
            double numericCountOfLikes = 0;
            if(weListOfCountOfLikes.get(i).getText()==""){
                //continue;
                numericCountOfLikes = 0;

            }
            else{
                likesCountStr = weListOfCountOfLikes.get(i).getText().trim().toUpperCase();
                numericCountOfLikes = Wrappers.wrapper_getNumericCountOfLikes(likesCountStr);
            }
            totalCountOfLikesFirstThreePosts = totalCountOfLikesFirstThreePosts + numericCountOfLikes;


        }
    
        System.out.println("totalCountOfLikesFirstThreePosts : " + totalCountOfLikesFirstThreePosts);
        
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        
        return totalCountOfLikesFirstThreePosts;
        
    }

    public static long wrapper_getNumericCountOfLikes(String likesCountStr){

        likesCountStr = likesCountStr.toUpperCase().trim();

        long finalNumericCountOfLikes=0;
        char lastChar = likesCountStr.charAt(likesCountStr.length()-1);
        long multiplier = 1;

        switch(lastChar) {
            case 'K': multiplier = 1000;
                    break;
            case 'M': multiplier = 1000000;
                    break;
            case 'B': multiplier = 1000000000;
                    break;

        
            default:
                if(Character.isDigit(lastChar)){
                    finalNumericCountOfLikes = Long.parseLong(likesCountStr);
                    return finalNumericCountOfLikes;
                
                }
                throw new IllegalArgumentException("Invalid format: " + likesCountStr);
        
        
        }


        // Extract the numeric part before the last character
        String numericPart = likesCountStr.substring(0, likesCountStr.length() - 1);
        double number = Double.parseDouble(numericPart);

        // Calculate the final value
        return (long) (number * multiplier);


        // finalNumericCountOfLikes = Long.parseLong(likesCountStr) * multiplier;
        // System.out.println("switch"+finalNumericCountOfLikes );
        // return finalNumericCountOfLikes;
    }

    public static void wrapper_clickShowMoreButton(ChromeDriver driver, WebDriverWait wait, By showMoreXpath){

        WebElement weShowMore = driver.findElement(showMoreXpath);
        weShowMore.click();

    }

    public static long wrapper_getTotalNumberOfViewsCounts(ChromeDriver driver, WebDriverWait wait){
        long countOfViews;
        String noOfViewsString;
        long totalCountOfViews = 0;
        long prevTotalCountOfViews = 0;


        try{
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );

            List<WebElement> wetotalViewsXpath = driver.findElements(By.xpath("//div[@id='metadata-line']/span[@class='inline-metadata-item style-scope ytd-video-meta-block']"));
            System.out.println("Size of List: " + wetotalViewsXpath.size());
            
            for(int i = 0; i < wetotalViewsXpath.size()-1; i++){
                
                WebElement webElement = wetotalViewsXpath.get(i);
                if(!wetotalViewsXpath.get(i).isDisplayed())
                    js.executeScript("arguments[0].scrollIntoView(true);", webElement);
                
                System.out.println("webElement.getText : " + webElement.getText()); 

                if(!webElement.getText().contains("views"))
                    continue;
                
                noOfViewsString = webElement.getText().split("views")[0].trim().split(" ")[0].trim();
                System.out.println("noOfViewsString : " + noOfViewsString);
                countOfViews = Wrappers.wrapper_getNumericCountOfLikes(noOfViewsString);
                System.out.println("countOfViews : " + countOfViews);
                
                totalCountOfViews = prevTotalCountOfViews + countOfViews;
                prevTotalCountOfViews = totalCountOfViews;
                System.out.println("prevTotalCountOfViews  :"+prevTotalCountOfViews);

                if(totalCountOfViews > 10000000){
                    return prevTotalCountOfViews;    
                }    
            
            //Gaming 
            //div[@id='details']//span[@class='inline-metadata-item style-scope ytd-video-meta-block']
            }
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }


    return totalCountOfViews;
    }
}
















 //span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//following-sibling::span[@title='The Wolf of Wall Street']/
        //p[@class='style-scope ytd-badge-supported-renderer']/../../../h3

        //p[@class='style-scope ytd-badge-supported-renderer']/../../../a/h3/span[@title='The Wolf of Wall Street']

         //span[contains(text(),'Top selling')]/ancestor::div[@id='dismissible']//following-sibling::span[@title='The Wolf of Wall Street']/../../following-sibling::ytd-badge-supported-renderer/div[2]/p


            //WebElement weSidebarMainOption = driver.findElement(By.xpath("//yt-formatted-string[@class='title style-scope ytd-guide-entry-renderer' and contains(text(),'"+sidebarMainOption+"')]"));
            
            // if(!weSidebarMainOption.isDisplayed()){
            //     js = (JavascriptExecutor) driver;
            //     js.executeScript("arguments[0].scrollIntoView(true);", weSidebarMainOption);
            // }

            //Thread.sleep((new java.util.Random().nextInt(3)+2)*1000);
           