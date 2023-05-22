package se.kth.iv1350.reatailStore.integration;

/**
 * Item Data Transfer Object. Contains information about items sush as Vat, price, item ID.
 */
public class ItemDTO {
    private final int itemVAT;
    private final int itemPrice;
    private final String itemDescription;
    private final int itemID;

    /**
     * Constructor for the ItemDTO. Gets information about the item and generates a DTO.
     * @param Vat. VAT for a specific item
     * @param itemPrice. Price for a specific item
     * @param itemID. Item identifier for a specific item.
     */
    public ItemDTO(String itemDescription, int Vat, int itemPrice, int itemID){
        this.itemDescription = itemDescription;
        this.itemVAT = Vat;
        this.itemPrice = itemPrice;
        this.itemID = itemID;
    }

    /**
     * Gets the itemID from an itemDTO
     * @return Returns the itemID
     */
    public int getItemID(){
        return itemID;
    }

    /**
     * Gets the price of an itemDTO
     * @return Returns the price
     */
    public int getItemPrice(){
        return itemPrice;
    }

    /**
     * Gets the Vat for an itemDTO
     * @return Returns the item VAT
     */
    public int getItemVat(){
        return itemVAT;
    }

    /**
     * Gets item description from an itemDTO which is just the items name at the moment
     * @return Returns item description in a String
     */
    public String getItemDescription(){
        return itemDescription;

    }

    
}
