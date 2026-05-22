package com.saucedemo.tests;

import com.saucedemo.config.TestConfig;
import com.saucedemo.steps.UISteps;
import org.junit.jupiter.api.Test;

public class SauceDemoTest extends BaseTest {

  @Test
  void sauceDemoTest() {
    UISteps steps = new UISteps(driver);
    steps.login(TestConfig.USERNAME, TestConfig.PASSWORD);
    steps.verifyProductCount();
    steps.addMostExpensiveToCart();
    steps.verifyCart();
  }
}
