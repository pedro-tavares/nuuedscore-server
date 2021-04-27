package com.nuuedscore.component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
import com.nuuedscore.domain.TeacherResource;
import com.nuuedscore.repository.StudentResourceRepository;
import com.nuuedscore.repository.TeacherResourceRepository;

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
//@Component
public class RAIStartupComponent implements ApplicationContextAware {

	@Autowired
	StudentResourceRepository studentResourceRepository;

	@Autowired
	TeacherResourceRepository teacherResourceRepository;
	
	protected static WebServer server;
	protected static WebDriver driver;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("Starting RAI Site Image capture...", this.getClass().getSimpleName());

		setup();
		
		String site;
		//site = "http://nuuedscore.com";
		//site = "http://developers.opened.com";
//		site = "https://www.knovationlearning.com";
//		takeScreenShot("student", 0L, site);
//		takeScreenShotWithScroll("student", 0L, site);

		List<String> advanceList = Arrays.asList(
				"http://www.openlearningworld.com/section_personality_development.html",
				"http://developers.opened.com",
				"http://SBAC",
				"http://study.com"
		);
		
		int counter = 1;
		List<StudentResource> studentResources = studentResourceRepository.findAll();
		for (StudentResource sr: studentResources) {
			if (!sr.getResource().contains("youtube.com")) {
				
				// parse non advancing
				if (advanceList.contains(sr.getResource())) {
					log.info("SKIPPING Screen Shot for {}", sr.getResource());
					continue;
				}

				// drive
				takeScreenShot("student", counter, sr.getId(), sr.getResource());
//				takeScreenShotWithScroll("student", sr.getId(), sr.getResource());
			}
			counter++;
		}
		
		counter = 1;
		List<TeacherResource> teacherResources = teacherResourceRepository.findAll();
		for (TeacherResource tr: teacherResources) {
			if (!tr.getResource().contains("youtube.com")) {
				takeScreenShot("teacher", counter, tr.getId(), 	tr.getResource());
				takeScreenShotWithScroll("teacher", counter, tr.getId(), tr.getResource());
			}
			counter++;
		}
		
	}

	public static void setup() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	public static void takeScreenShot(String folder, Integer counter, Long id, String site) {
		String path = "resources/" + folder + "/" + id + "-1.png";
		
		File file = new File(path);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (!file.exists()) {	
			log.info("takeScreenShot:{}-{}-{}-[{}]", folder, counter, id, site);
			
			driver.get(site);
			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(src, new File(path));
			} catch (IOException e) { 
				e.printStackTrace();
			}
			
			//driver.quit();
		}
	}
	
	public static void takeScreenShotWithScroll(String folder, Integer counter, Long id, String site) {
		String path = "resources/" + folder + "/" + id + "-2.png";
				
		File file = new File(path);
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		if (!file.exists()) {
			log.info("takeScreenShotWithScroll:{}-{}-{}-[{}]", folder, counter, id, site);
			
			driver.get(site);
	
			//take screenshot of the entire page             
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(10000)).takeScreenshot(
					driver);
	
			try {
				ImageIO.write(screenshot.getImage(), "PNG", new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//driver.quit();
	}
}