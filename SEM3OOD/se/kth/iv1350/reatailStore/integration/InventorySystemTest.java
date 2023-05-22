package se.kth.iv1350.reatailStore.integration;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.reatailStore.*;
import se.kth.iv1350.retailStore.model.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for exceptions from the fetchItemInfo method in the inventorySytem class
 */

class InventorySystemTest {
    private InventorySystem inventorySystem;
    private int mjölkID = 2;
    private int smörID = 0;
    private int trocaderoID = 1;
    private int databasFelID = 10;
    private int idThatDontExist = 5;

    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        inventorySystem = new InventorySystem();
    }

    @AfterEach
    void tearDown() {

    }

    /**
     * checks that itemNotFoundException is not thrown when item ID is matched in inventory System
     */
    @Test
    void fetchItemInfo() throws ItemNotFoundException, DataBaseFailureException{

        try{
            itemDTO = inventorySystem.fetchItemInfo(mjölkID);
            itemDTO = inventorySystem.fetchItemInfo(smörID);
            itemDTO = inventorySystem.fetchItemInfo(trocaderoID);
            
        }
        catch(ItemNotFoundException exc){
            assertTrue(false, "item does exist in the inventory System but ItemNotFoundException is thrown");
        }

    }

    /**
     * checks that exception is thrown when an item ID that is not in the inventory system is fetched.
     * Also checks that correct error message is thrown.
     */
    @Test
    void fetchItemInfoException() throws ItemNotFoundException, DataBaseFailureException{

        try{
            itemDTO = inventorySystem.fetchItemInfo(idThatDontExist);
            fail("Could match with an itemID that does not exist in inventory system");
        }
        catch(ItemNotFoundException exc){
            assertTrue(exc.getMessage().contains(Integer.toString(idThatDontExist)), "Not correct item ID for ItemNotFoundException");
        }
        
    }

    /**
     * checks that databaseFailure is thrown when it is supposed to with correct error message
     */
    @Test
    void fetchItemInfoDataBaseException() throws ItemNotFoundException, DataBaseFailureException{
        try{
            itemDTO = inventorySystem.fetchItemInfo(databasFelID);
            fail("Should throw DataBaseFailureException");
        }
        catch(DataBaseFailureException exc){
            assertTrue(exc.getMessage().contains("Failed to connect to Database"), "Wrong Exception message");
        }
        
    }


}


