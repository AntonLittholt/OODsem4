package se.kth.iv1350.retailStore.model;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.reatailStore.integration.*;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    Cart cart;
    ItemDTO itemPotatisDTO;
    ItemDTO itemOstDTO;
    

    @BeforeEach
    void setUp() {

        itemPotatisDTO= new ItemDTO("potatis", 0, 1, 1);
        itemOstDTO= new ItemDTO("ost", 0, 10, 112);
        
        cart = new Cart();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addToCart() {

        cart.addToCart(1, itemPotatisDTO);
        assertEquals("potatis", cart.getItemsInCart().get(0).getItemName(), "Wrong item in cart");
        assertEquals(1, cart.getItemsInCart().get(0).getQuantity(), "WRONG quantity of added item");

        cart.addToCart(4, itemPotatisDTO);
        assertEquals(5, cart.getItemsInCart().get(0).getQuantity(), "Wrong quantity of added item");
        assertEquals(1, cart.getItemsInCart().size(),"WRONG number of items in cart");

        cart.addToCart(3, itemOstDTO);
        assertEquals(2, cart.getItemsInCart().size(),"WRONG number of items in cart");
        assertEquals(3, cart.getItemsInCart().get(1).getQuantity(), "WRONG quantity of added item");

        
    }
}
