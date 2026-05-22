package com.saucedemo.pages;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

  private final By productItems = By.className("inventory_item");
  private final By productName = By.className("inventory_item_name");
  private final By productPrice = By.className("inventory_item_price");
  private final By addToCartBtn = By.cssSelector("button.btn_inventory");
  private final By cartLink = By.className("shopping_cart_link");

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getProductNames() {
    return getItems().stream()
        .map(item -> item.findElement(productName).getText())
        .collect(Collectors.toList());
  }

  public String addMostExpensiveProductToCart() {
    WebElement mostExpensive =
        getItems().stream()
            .max(
                Comparator.comparingDouble(
                    item -> parsePrice(item.findElement(productPrice).getText())))
            .orElseThrow();
    String name = mostExpensive.findElement(productName).getText();
    mostExpensive.findElement(addToCartBtn).click();
    return name;
  }

  public CartPage openCart() {
    driver.findElement(cartLink).click();
    wait.until(ExpectedConditions.urlContains("/cart"));
    return new CartPage(driver);
  }

  private List<WebElement> getItems() {
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItems));
  }

  private double parsePrice(String text) {
    return Double.parseDouble(text.replace("$", ""));
  }
}
