import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Assert;
import org.junit.Test;

public class FirstTestPlaywright {

    @Test
    public void LaunchBrowser() {
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");


            //click element by Text
//            page.click("text=LOGIN");

            //click element by ID
//            page.click("id=login-button");
//            page.click("#login-button");

            //click element by CSS
//            page.click("input#login-button");
//            page.click("input.submit-button.btn_action");
//            page.click("div input.submit-button.btn_action");
//            page.click("input[type='submit']");


//            page.type("[placeholder=Username]", "standard_user");
//            page.type("[placeholder=Password]", "secret_sauce");
//            page.click("id=login-button");


            //by Xpath
//            page.click("//form/input[@type='submit']");
//            page.click("//form/input[@name='login-button']");
//            page.click("//input[@id='login-button']");

//            page.type("//form/div/input[@name='user-name']", "standard_user");


            //interactuar con los dropdowns
//            page.type("[placeholder=Username]", "standard_user");
//            page.type("[placeholder=Password]", "secret_sauce");
//            page.click("id=login-button");
//            page.selectOption("select.product_sort_container", "hilo");


// interactuar
//            page.type("[placeholder=Username]", "standard_user");
//            page.type("[placeholder=Password]", "secret_sauce");
//            page.click("id=login-button");
//            String textElement= page.innerText("a#item_1_title_link div");
//            System.out.println("texto : " + textElement);

//Validar si existe elemento
//            page.type("[placeholder=Username]", "standard_user");
//            page.type("[placeholder=Password]", "secret_sauce");
//            page.click("id=login-button");
//            boolean isHomePage = page.isVisible("span.title");
//            Assert.assertTrue(isHomePage);



            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            page.click("text=Sauce Labs Backpack");
            page.click("id=add-to-cart-sauce-labs-backpack");
            page.click("a.shopping_cart_link");
            boolean itemIsDisplayed = page.isVisible("text=Sauce Labs Backpack");
            Assert.assertTrue(itemIsDisplayed);
        }
    }


}
