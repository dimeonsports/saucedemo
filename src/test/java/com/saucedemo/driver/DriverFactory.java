package com.saucedemo.driver;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

  public static WebDriver createDriver() {
    String browser = System.getProperty("browser", "chrome");
    return switch (browser) {
      case "chrome" -> createChromeDriver();
      case "firefox" -> createFirefoxDriver();
      default -> throw new IllegalArgumentException("Unknown browser: " + browser);
    };
  }

  private static WebDriver createChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-features=PasswordLeakDetection");
    if (isHeadless()) {
      options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
    }
    options.setExperimentalOption(
        "prefs", Map.of("profile.password_manager_leak_detection", false));
    return new ChromeDriver(options);
  }

  private static WebDriver createFirefoxDriver() {
    FirefoxOptions options = new FirefoxOptions();
    options.addPreference("signon.rememberSignons", false);
    if (isHeadless()) {
      options.addArguments("-headless");
    }
    return new FirefoxDriver(options);
  }

  private static boolean isHeadless() {
    return Boolean.parseBoolean(System.getProperty("headless", "false"));
  }
}
