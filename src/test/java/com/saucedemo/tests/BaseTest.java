package com.saucedemo.tests;

import com.saucedemo.driver.DriverFactory;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

  protected WebDriver driver;

  private class FailureTracker implements TestExecutionExceptionHandler {
    boolean failed = false;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
        throws Throwable {
      failed = true;
      throw throwable;
    }
  }

  @RegisterExtension final FailureTracker failureTracker = new FailureTracker();

  @BeforeEach
  void setUp() {
    driver = DriverFactory.createDriver();
    driver.manage().window().maximize();
  }

  @AfterEach
  void tearDown() {
    if (driver != null) {
      if (failureTracker.failed) takeScreenshot();
      driver.quit();
    }
  }

  @Attachment(value = "Page screenshot", type = "image/png")
  private byte[] takeScreenshot() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }
}
