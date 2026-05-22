package com.saucedemo.pages;

import com.saucedemo.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

  private final By usernameField = By.id("user-name");
  private final By passwordField = By.id("password");
  private final By loginButton = By.id("login-button");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void open() {
    driver.get(TestConfig.BASE_URL);
  }

  private void enterUsername(String username) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
  }

  private void enterPassword(String password) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
  }

  private void clickLoginButton() {
    driver.findElement(loginButton).click();
    wait.until(ExpectedConditions.urlContains("/inventory"));
  }

  public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
  }
}
