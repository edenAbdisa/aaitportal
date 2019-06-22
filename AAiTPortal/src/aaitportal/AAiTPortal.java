/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaitportal;
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 *
 * @author Eden
 */
public class AAiTPortal {

    /**
     * @param args the command line arguments
     */
    static FileWriter fileWriter;
    static WebDriver webDriver; 
    static File file;
    static  boolean isNewLine=false;
    public static void main(String[] args) {  
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
                  
        webDriver = new ChromeDriver();  

        webDriver.get("https://portal.aait.edu.et/"); 
        
        webDriver.findElement(By.name("UserName")).sendKeys("ATR\0667\\09");
        webDriver.findElement(By.name("Password")).sendKeys("password"); 
        webDriver.findElement(By.xpath("//*[@id=\"home\"]/div[2]/div[2]/form/div[4]/div/button")).click();
        
        webDriver.get("https://portal.aait.edu.et/Home"); 
        
        webDriver.findElement(By.xpath("//*[@id=\"m2\"]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"m2\"]/ul/li[1]/a")).click();    
        
        String responseBodyContent=webDriver.findElement(By.xpath("/html/body")).getText();
       
        try { 
            fileWriter = new FileWriter(new File("UserData.txt"),true);
            for (int i = 0; i < responseBodyContent.length(); i++) {
                if(isNewLine){
                    isNewLine=false;
                    break;
                }
                if(responseBodyContent.charAt(i)=='\\'){
                    if(responseBodyContent.charAt(i+1)=='n'){
                        isNewLine=true;
                        fileWriter.write(System.lineSeparator());
                        break;
                    }
                }
               fileWriter.write(responseBodyContent.charAt(i));
            } 
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }    
}