package org.example.services;

import org.example.database.ProductDAO;
import org.example.entities.Product;
import org.example.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductDAO productDAO;
    @InjectMocks
    private ProductService productService;
    int id = 5;
    String login = "Sasha";
    String password = "12345";

    @Test
    public void getAllProductsTest() {
        ArrayList<Product> productList = getProducts();
        when(productDAO.getAllProductsFromProductDAO()).thenReturn(productList);
        List<Product> products = productService.getAllProducts();
        assertEquals(productList.size(), products.size());
    }
    private ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product first = new Product(5, 1, "Purina One", 70, 0);
        Product second = new Product(1,2, "Optimeal", 40, 28);

        products.add(0, first);
        products.add(1, second);
        return products;
    }
    private ArrayList<Product> getUserProducts(int id){
        ArrayList<Product> userProducts = new ArrayList<>();
        for (int i = 0; i<getProducts().size(); i++){
            if (getProducts().get(i).user_id == id){
                userProducts.add(getProducts().get(i));
            }
        }
        return userProducts;
    }
    @Test
    public void getUserProductsTest(){
        User user = new User(id, login, password);
        when(productDAO.getAllBoughtProductsFromProductDAO(user.getId())).thenReturn(getUserProducts(user.getId()));
        ArrayList<Product> products = productService.getAllBoughtProducts(user);
        assertEquals(getUserProducts(user.getId()).size(), products.size());
    }
}