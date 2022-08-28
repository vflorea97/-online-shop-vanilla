package ro.mycode.Controller;

import org.junit.jupiter.api.Test;
import ro.mycode.Models.Product;

import static org.junit.jupiter.api.Assertions.*;

class ControllerProductsTest {

    @Test
    void getProductById() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.getProductById(product.getId());

        assertEquals(product,controllerProducts.getProductById(product.getId()));
    }

    @Test
    void getProductByName() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.getProductByName(product.getName());

        assertEquals(product,controllerProducts.getProductByName(product.getName()));
    }

    @Test
    void addProduct() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);

        controllerProducts.addProduct(product);

        assertEquals(true,controllerProducts.getProductByName(product.getName()) != null);
    }

    @Test
    void pozitie() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.pozitie(product.getId());

        assertEquals(0,controllerProducts.pozitie(product.getId()));
    }

    @Test
    void removeProducts() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.removeProducts(product.getId());

        assertEquals(-1,controllerProducts.pozitie(product.getId()));
    }

    @Test
    void upDate() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.upDate(new Product(202,"Perie",18,19));

        assertEquals("Perie",controllerProducts.getProductById(product.getId()).getName());
        assertEquals(18,controllerProducts.getProductById(product.getId()).getPrice());
        assertEquals(19,controllerProducts.getProductById(product.getId()).getStock());
    }

    @Test
    void generareId() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.generareId();

        assertEquals(product.getId() + 1,controllerProducts.generareId());
    }

    @Test
    void produse() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.produse(product.getName());

        assertEquals(true,controllerProducts.produse(product.getName()) != null);
    }

    @Test
    void verificareProducts() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.verificareProducts(product.getName());

        assertEquals(true,controllerProducts.verificareProducts(product.getName()));
    }

    @Test
    void diminuareCantitate() {
        ControllerProducts controllerProducts = new ControllerProducts("new");
        Product product = new Product(202,"Periuta de dinti",17,15);
        controllerProducts.addProduct(product);

        controllerProducts.diminuareCantitate(product.getName(), 3);

        assertEquals(12, controllerProducts.getProductById(product.getId()).getStock());
    }
}