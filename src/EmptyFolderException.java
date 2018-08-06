//------------------------------------
// Assignment 3
// Question 1
// Written by Natalia Whiteley 40044353
//------------------------------------
public class EmptyFolderException extends Exception {
    private String message;

    /**
     * Constructors
     */
    public EmptyFolderException() {
        message = "Error: folder is empty";
    }

    public EmptyFolderException(String message) {
        this.message = message;
    }

    /**
     * Get message
     * @return the error message
     */
    public String getMessage() {
        return message;
    }
}
