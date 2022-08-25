import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.Assert;
import org.junit.Test;

public class ShoppingCartTest {

    @Test
    public void agregarProductosAlCarrito(){
        try (Playwright playwright = Playwright.create()) {
            //1 Iniciar sesion en saucedemo con el usuario standard_user
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");
            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            //2 hacerle click en add to cart en cualquiera de los productos visualizados en el homepage
            page.click("id=add-to-cart-sauce-labs-backpack");
            page.click("id=add-to-cart-sauce-labs-bike-light");
            page.click("id=add-to-cart-test.allthethings()-t-shirt-(red)");


            //3 hacer click en el icono en la parte superior derecha
            page.click(".shopping_cart_link");

            //4 Validar la lista de productos que aparezcan en la pagina del carrito coincidan con los seleccionados para agregarlos al carrito
            boolean removeFromCartSauceLabsBackpackDisplayed = page.isVisible("id=remove-sauce-labs-backpack");
            boolean removeFromCartSauceLabsBikeLightIsDisplayed = page.isVisible("id=remove-sauce-labs-bike-light");
            boolean removeFromCartTestAllthethingsTShirtRedIsDisplayed = page.isVisible("id=remove-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(removeFromCartSauceLabsBackpackDisplayed);
            Assert.assertTrue(removeFromCartSauceLabsBikeLightIsDisplayed);
            Assert.assertTrue(removeFromCartTestAllthethingsTShirtRedIsDisplayed);
        }
    }


    @Test
    public void realizarCheckoutProductosCarrito(){
        try (Playwright playwright = Playwright.create()) {
            //1 Iniciar sesion en saucedemo con el usuario standard_user
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");
            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            //2 hacerle click en add to cart en cualquiera de los productos visualizados en el homepage
            page.click("id=add-to-cart-sauce-labs-backpack");
            page.click("id=add-to-cart-sauce-labs-bike-light");
            page.click("id=add-to-cart-test.allthethings()-t-shirt-(red)");

            //3 hacer click en el icono en la parte superior derecha
            page.click(".shopping_cart_link");

            //4 Validar la lista de productos que aparezcan en la pagina del carrito coincidan con los seleccionados para agregarlos al carrito
            boolean removeFromCartSauceLabsBackpackDisplayed = page.isVisible("id=remove-sauce-labs-backpack");
            boolean removeFromCartSauceLabsBikeLightIsDisplayed = page.isVisible("id=remove-sauce-labs-bike-light");
            boolean removeFromCartTestAllthethingsTShirtRedIsDisplayed = page.isVisible("id=remove-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(removeFromCartSauceLabsBackpackDisplayed);
            Assert.assertTrue(removeFromCartSauceLabsBikeLightIsDisplayed);
            Assert.assertTrue(removeFromCartTestAllthethingsTShirtRedIsDisplayed);

            //5 Hacer click en el boton checkout
            page.click("#checkout");

            //6 Rellenar los datos requeridos. y luego hacer click en el boton continue
            page.type("[id=first-name]", "Robert");
            page.type("[id=last-name]", "Cabrera");
            page.type("[id=postal-code]", "0000");
            page.click("#continue");

            //7  hacer click en el boton finish
            boolean isVisibleFinishButton = page.isVisible("id=finish");
            Assert.assertTrue(isVisibleFinishButton);
            page.click("#finish");

            boolean isVisibleBackToProductsButton = page.isVisible("id=back-to-products");
            Assert.assertTrue(isVisibleBackToProductsButton);
            page.click("#back-to-products");
        }
    }

    @Test
    public void eliminarProductosDelCarritoDesdeHomePage(){
        try (Playwright playwright = Playwright.create()) {
            //1 Iniciar sesion en saucedemo con el usuario standard_user
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");
            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            //2 hacerle click en add to cart en cualquiera de los productos visualizados en el homepage
            page.click("id=add-to-cart-sauce-labs-backpack");
            page.click("id=add-to-cart-sauce-labs-bike-light");
            page.click("id=add-to-cart-test.allthethings()-t-shirt-(red)");

            //3 hacer click en el icono en la parte superior derecha
            page.click(".shopping_cart_link");

            //4 Validar la lista de productos que aparezcan en la pagina del carrito coincidan con los seleccionados para agregarlos al carrito
            boolean removeFromCartSauceLabsBackpackDisplayed = page.isVisible("id=remove-sauce-labs-backpack");
            boolean removeFromCartSauceLabsBikeLightIsDisplayed = page.isVisible("id=remove-sauce-labs-bike-light");
            boolean removeFromCartTestAllTheThingsTShirtRedIsDisplayed = page.isVisible("id=remove-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(removeFromCartSauceLabsBackpackDisplayed);
            Assert.assertTrue(removeFromCartSauceLabsBikeLightIsDisplayed);
            Assert.assertTrue(removeFromCartTestAllTheThingsTShirtRedIsDisplayed);

            //5  volver al home page
            page.click("#continue-shopping");

            //6  hacer click en el boton remove, de alguno de los productos que se agregaron al carrito
            page.click("id=remove-test.allthethings()-t-shirt-(red)");

            //7  hacer click en el icono del carrito en la parte superior derecha
            page.click(".shopping_cart_link");

            //8  Validar que los productos eliminados ya no aparezcan en el carrito
            removeFromCartSauceLabsBackpackDisplayed = page.isVisible("id=remove-sauce-labs-backpack");
            removeFromCartSauceLabsBikeLightIsDisplayed = page.isVisible("id=remove-sauce-labs-bike-light");
            removeFromCartTestAllTheThingsTShirtRedIsDisplayed = page.isVisible("id=remove-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(removeFromCartSauceLabsBackpackDisplayed);
            Assert.assertTrue(removeFromCartSauceLabsBikeLightIsDisplayed);
            Assert.assertFalse(removeFromCartTestAllTheThingsTShirtRedIsDisplayed);
        }
    }

    @Test
    public void eliminarProductosDesdeElCarrito(){
        try (Playwright playwright = Playwright.create()) {
            //1 Iniciar sesion en saucedemo con el usuario standard_user
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)).newPage();
            page.setViewportSize(1920, 1080);
            page.navigate("https://www.saucedemo.com/");
            page.type("[placeholder=Username]", "standard_user");
            page.type("[placeholder=Password]", "secret_sauce");
            page.click("id=login-button");

            //2 hacerle click en add to cart en cualquiera de los productos visualizados en el homepage
            page.click("id=add-to-cart-sauce-labs-backpack");
            page.click("id=add-to-cart-sauce-labs-bike-light");
            page.click("id=add-to-cart-test.allthethings()-t-shirt-(red)");

            //3 hacer click en el icono en la parte superior derecha
            page.click(".shopping_cart_link");

            //4 Validar la lista de productos que aparezcan en la pagina del carrito coincidan con los seleccionados para agregarlos al carrito
            boolean removeFromCartSauceLabsBackpackDisplayed = page.isVisible("id=remove-sauce-labs-backpack");
            boolean removeFromCartSauceLabsBikeLightIsDisplayed = page.isVisible("id=remove-sauce-labs-bike-light");
            boolean removeFromCartTestAllTheThingsTShirtRedIsDisplayed = page.isVisible("id=remove-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(removeFromCartSauceLabsBackpackDisplayed);
            Assert.assertTrue(removeFromCartSauceLabsBikeLightIsDisplayed);
            Assert.assertTrue(removeFromCartTestAllTheThingsTShirtRedIsDisplayed);

            //5 hacer click en el boton remove en cualquiera de los productos del carrito
            page.click("id=remove-test.allthethings()-t-shirt-(red)");

            //6 Hacer click en continue shopping
            page.click("#continue-shopping");

            //7 Verificar que el producto que se elimino del carrito debe aparecer nuevamente para agregarlo al carrito
            boolean addToCartTestAllTheThingsTShirtRedIsDisplayed = page.isVisible("id=add-to-cart-test.allthethings()-t-shirt-(red)");
            Assert.assertTrue(addToCartTestAllTheThingsTShirtRedIsDisplayed);
        }
    }
}
