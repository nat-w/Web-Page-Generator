import java.io.FileNotFoundException;

//------------------------------------
// Assignment 3
// Question 1
// Written by Natalia Whiteley 40044353
//------------------------------------
public class InvalidFileException extends Exception {
    private String message;

    /**
     * Constructors
     */
    public InvalidFileException() {
        message = "Error: input file XXXX cannot be found";
    }

    public InvalidFileException(String message) {
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
