import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Assert;
import org.junit.Test;

public class HomePageTest {

    @Test
    public void verificarProductoIsVisibleInHomePage(){
        try (Playwright playwright = Playwright.create()) {
            //1 Ingresar a saucedemo.com
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");

            //2 loguearse
            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            //3 Buscar en la lista de productos de HomePage el producto: Test.allTheThings() T-Shirt (Red)
            page.click("img.inventory_item_img[alt='Test.allTheThings() T-Shirt (Red)']");


            //4 Validar la imagen del producto, contra el catagolo de productos
            boolean itemIsDisplayed = page.isVisible("img.inventory_details_img[alt='Test.allTheThings() T-Shirt (Red)']");
            Assert.assertTrue(itemIsDisplayed);
        }
    }

}
