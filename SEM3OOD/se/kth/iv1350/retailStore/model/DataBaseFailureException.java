package se.kth.iv1350.retailStore.model;


/**
 * Exception to indicate that the database can not be called
 */
public class DataBaseFailureException extends Exception{

        /**
         * Throws DatabaseFailureException with error message.
         */
        public DataBaseFailureException() {
            super("Failed to connect to Database.");
        }
}
