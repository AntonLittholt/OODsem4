package se.kth.iv1350.retailStore.model;

import se.kth.iv1350.reatailStore.integration.*;
import java.util.ArrayList;

/**
 * Represents the item cart which contains items that will be bought.
 */
public class Cart {
    ArrayList<Item> cart = new ArrayList<>();

    /**
     * Constructor for a cart
     */
    public Cart(){
    }
    
    /**
     * Adds a new item (or multiple) to the cart. If the item alredy exists in the cart it just changes
     * the quantity of that item. Otherwise creates a new item and adds that to the cart
     * @param quantity. Quantity of the item that will be added
     * @param iteminfo. the ItemDTO of the item that will be added
     * @return Returns the uppdated cart object with all items
     */
    public Cart addToCart(int quantity, ItemDTO itemInfo){

        if(itemExistInCart(itemInfo)){

            int itemIndex = getItemIndex(itemInfo);
            incrementItem(quantity, itemIndex);
        }
        else{
            addItem(new Item(itemInfo, quantity));
        }
        
        return this;
    }

    private boolean itemExistInCart(ItemDTO itemDTO){

        for (int i = 0; i < cart.size(); i++) {
            if(itemDTO.getItemID() == cart.get(i).getItemID()){ 
                return true;
            }
        }
        
        return false;
    }

    private void incrementItem(int quantity, int itemIndex){
        cart.get(itemIndex).uppdateQuantity(quantity);
    }

    private void addItem(Item item){
        cart.add(item);
    }

    private int getItemIndex(ItemDTO itemDTO){
        int itemIndex = 0;
        for (int i = 0; i < cart.size(); i++) {
            if(itemDTO.getItemDescription() == cart.get(i).getItemName()){  //fixa
                itemIndex = i;
            }
        }

        return itemIndex;
    }

    /**
     * Gets an ArrayList containing all items in cart
     * @return Returns an ArrayList with the items
     */
    public ArrayList<Item> getItemsInCart() {
        return cart;
    }
    
}
