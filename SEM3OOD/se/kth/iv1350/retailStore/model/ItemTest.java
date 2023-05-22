package se.kth.iv1350.retailStore.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.reatailStore.integration.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item;

    @BeforeEach
    void setUp() {
        ItemDTO itemDTO= new ItemDTO("potatis", 0, 1, 1111);
        item = new Item(itemDTO, 1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void changeItemQuantity() {
        item.uppdateQuantity(2);
        assertEquals(3, item.getQuantity(), "Wrong quantity");

        item.uppdateQuantity(13);
        assertEquals(16, item.getQuantity(), "Wrong quantity");

        item.uppdateQuantity(-100);
        assertEquals(0, item.getQuantity(), "Wrong quantity");
        
    }
}