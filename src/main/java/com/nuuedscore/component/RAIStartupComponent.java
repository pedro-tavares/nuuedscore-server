package com.nuuedscore.component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.nuuedscore.domain.StudentResource;
import com.nuuedscore.repository.StudentResourceRepository;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * RAI Startup Component
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Component
public class RAIStartupComponent implements ApplicationContextAware {

	@Autowired
	StudentResourceRepository studentResourceRepository;
	
	protected static WebServer server;
	protected static WebDriver driver;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("Starting RAI Site Image capture...", this.getClass().getSimpleName());

		setup();
		
		//takeScreenShot("https://nuuedscore.com");
	
		//String site = "http://developers.opened.com";
		//takeScreenShot(site);
		//takeScreenShotWithScroll(site);
		
		List<StudentResource> studentResources = studentResourceRepository.findAll();
		for (StudentResource sr: studentResources) {
			takeScreenShot(sr.getId(), sr.getResource());
			//takeScreenShotWithScroll(sr.getId(), sr.getResource());
		}
		
	}

	public static void setup() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	public static void takeScreenShot(Long id, String site) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(site);
		
		File src;
		
		//take screenshot of the page         
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("screenshots/" + id + "-1.png"));
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		//driver.quit();
	}

	public static void takeScreenShotWithScroll(Long id, String site) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(site);

		//take screenshot of the entire page             
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(10000)).takeScreenshot(
				driver);

		try {
			ImageIO.write(screenshot.getImage(), "PNG", new File("screenshots/" + id + "-2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//driver.quit();
	}
}