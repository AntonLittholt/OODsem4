package se.kth.iv1350.reatailStore.integration;

import se.kth.iv1350.reatailStore.integration.*;
import java.util.ArrayList;
import se.kth.iv1350.retailStore.model.*;

/**
 * External inventory System that keeps track of the stores inventory.
 */
public class InventorySystem {

    ArrayList<ItemDTO> itemDTOs = new ArrayList<>();

    /**
     * The current made up inventory.
     */
    public InventorySystem(){
        itemDTOs.add(new ItemDTO("smör",20, 100, 0));
        itemDTOs.add(new ItemDTO("trocadero",10, 50, 1));
        itemDTOs.add(new ItemDTO("mjölk",5, 25, 2));
        itemDTOs.add(new ItemDTO("Databas fel",0, 0, 10));
    }

    /**
     * fetches item information from the inventory.
     * @param itemID. Item identifier. Used to search for item in the inventory system.
     * @return Returns a itemDataTransferObject with information about the item.
     * @throws ItemNotFoundException when no matching item ID is found in the inventory system.
     * @throws DataBaseFailureException when database can not be called.
     */
    public ItemDTO fetchItemInfo(int itemID) throws ItemNotFoundException, DataBaseFailureException{

        if(isDatabaseNotWorking(itemID)){
            throw new DataBaseFailureException();
        }

        for (int i = 0; i < itemDTOs.size(); i++) {
            if(isItemFound(itemID, i)){  
                return itemDTOs.get(i);
            }
        }
        throw new ItemNotFoundException(itemID);
    }

    private boolean isDatabaseNotWorking(int itemID){
        if(itemID == 10)
            return true;
        return false;
    }

    private boolean isItemFound(int itemID, int index){
        if(itemID == itemDTOs.get(index).getItemID()){
            return true;
        }
        return false;
    }

    /**
     * Uppdates the inventory after a sale
     * @param cart. Cart object with the items that was bought
     */
    public void uppdateInventory(Cart cart){
        //Uppdating...
    }
    
}
