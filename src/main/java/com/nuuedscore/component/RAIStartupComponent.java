package com.nuuedscore.component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import lombok.extern.slf4j.Slf4j;

/**
 * Domain Startup Component
 *
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@Component
public class RAIStartupComponent implements ApplicationContextAware {

	protected WebServer driver;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("Starting RAI Site Image capture...", this.getClass().getSimpleName());

		takeScreenShot();
	}

	public static void takeScreenShot() {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://nuuedscore.com");
		
		//take screenshot of the entire page             
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(
				driver);
		try {
			ImageIO.write(screenshot.getImage(), "PNG", new File("screenshots/" + "TEST.png"));
		} catch (IOException e) { 
			e.printStackTrace();
		}
		driver.quit();
	}
}