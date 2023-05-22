package se.kth.iv1350.retailStore.model;


/**
 * Exception for when an item ID is not matched in the inventory system.
 */
public class ItemNotFoundException extends Exception {
        /**
         * Constructs an ItemNotFoundException with a default message.
         */
        public ItemNotFoundException() {
            super("Item ID can not be found in the inventory system.");
        }
        /**
         * ItemNotFoundException with information about the item ID that did not give a match in inventory system.
         * @param itemID The item ID that could not be matched.
         */
        public ItemNotFoundException(int itemID) {
            super("Item ID: "+itemID+" can not be found in the inventory system.");
        }


}
