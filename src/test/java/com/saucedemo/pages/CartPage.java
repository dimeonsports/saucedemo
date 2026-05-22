package com.saucedemo.pages;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

  private final By cartItemNames = By.className("inventory_item_name");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getCartItemNames() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemNames));
    return driver.findElements(cartItemNames).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}
