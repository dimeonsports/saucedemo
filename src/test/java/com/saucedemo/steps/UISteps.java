package com.saucedemo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class UISteps {

  private static final int EXPECTED_PRODUCT_COUNT = 6;

  private final WebDriver driver;
  private ProductsPage productsPage;
  private String mostExpensiveProductName;

  public UISteps(WebDriver driver) {
    this.driver = driver;
  }

  @Step("Login as {username}")
  public void login(String username, String password) {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.login(username, password);
    assertTrue(driver.getCurrentUrl().contains("/inventory"));
    productsPage = new ProductsPage(driver);
    attachScreenshot("After login");
  }

  @Step("Verify products page has 6 products")
  public void verifyProductCount() {
    assertEquals(EXPECTED_PRODUCT_COUNT, productsPage.getProductCount());
    Allure.addAttachment("Products", String.join("\n", productsPage.getProductNames()));
    attachScreenshot("Products page");
  }

  @Step("Add most expensive product to cart")
  public void addMostExpensiveToCart() {
    mostExpensiveProductName = productsPage.addMostExpensiveProductToCart();
    Allure.addAttachment("Added product", mostExpensiveProductName);
    attachScreenshot("After adding to cart");
  }

  @Step("Verify product is in cart")
  public void verifyCart() {
    CartPage cartPage = productsPage.openCart();
    assertTrue(cartPage.getCartItemNames().contains(mostExpensiveProductName));
    attachScreenshot("Cart page");
  }

  private void attachScreenshot(String name) {
    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    Allure.addAttachment(name, "image/png", new ByteArrayInputStream(screenshot), "png");
  }
}
