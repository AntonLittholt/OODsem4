package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.reatailStore.integration.InventorySystem;
import se.kth.iv1350.reatailStore.integration.ItemDTO;

/**
 * Class representing an item(or multiple of the same item) in the store.
 */
public class Item {
    private int price;
    private int vat;
    private String name;
    private int quantity;
    private int itemID;

    /**
     * Constructor for item objects. 
     * @param itemDTO. ItemDTO used for getting all relevant information about the item.
     * @param quantity represent the quantity of that item
     */
    public Item(ItemDTO itemDTO, int quantity){
        this.quantity = quantity;
        this.itemID = itemDTO.getItemID();
        this.price = itemDTO.getItemPrice();
        this.vat = itemDTO.getItemVat();
        this.name = itemDTO.getItemDescription();
        
    }

    /**
     * Gets the name of a specific item
     * @return Returns a String with the item name
     */
    public String getItemName(){
        return name;
    }

    /**
     * Gets the price of a specific item
     * @return Returns an int with the items price
     */
    public int getItemPrice(){
        return price;
    }

    /**
     * Gets the VAT of a specific item
     * @return Returns an int with the items VAT
     */
    public int getItemVat(){
        return vat;
    }

    /**
     * Gets the quantity of a specific item
     * @return Returns an int with the quantity of the item
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Gets the ID of a specific item
     * @return Returns an int with the itemID
     */
    public int getItemID(){
        return itemID;
    }

    /**
     * Uppdates the quantity of a specific item. The uppdated item quantity cant be negative.
     * @param quantity. The quantity that should be added to the already existing item quantity
     */
    public void uppdateQuantity(int quantity){
        if(isUppdatedQuantityNegative(quantity)){
            this.quantity = 0;
        }
        else{
            this.quantity += quantity;
        }
    }

    private boolean isUppdatedQuantityNegative(int quantity){
        if(-quantity > this.quantity){
            return true;
        }
        return false;
    }
    
}
