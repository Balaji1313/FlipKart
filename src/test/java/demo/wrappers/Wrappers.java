package demo.wrappers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Wrappers {
  /*
   * Write your selenium wrappers here
   */

  public static void searchProduct(WebElement element, String product) {

    try {
      element.sendKeys(product, Keys.ENTER);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void print(WebElement element, String product) {

    System.out.println("");

  }
}
