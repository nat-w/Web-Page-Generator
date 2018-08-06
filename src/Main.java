//------------------------------------
// Assignment 3
// Question 1
// Written by Natalia Whiteley 40044353
//------------------------------------
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Main {
    public static File data = new File("src/Data");

    public static void main(String[] args) {
        boolean quit = false;
        int choice;
        Scanner in = new Scanner(System.in);


        // Displays the main menu until user quits
        while(!quit) {
            System.out.print("1. List\n2. Process\n3. Exit\nEnter the number of your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    try {
                        PrintWriter out;
                        list(data, out = new PrintWriter("log.txt"));
                        out.close();
                    }
                    catch (FileNotFoundException e) {
                        System.out.println("Error creating print writer");
                    }
                    catch (EmptyFolderException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        process(data);
                    }
                    catch (InvalidFileException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    quit = true;
                    System.out.print("Program closing, goodbye.");
                    break;
            }
        }
    }

    /**
     * Lists the file directories to a file called log.txt
     * @param mainFile Data file that contains other files
     * @param out File to print to
     * @throws EmptyFolderException Empty folder
     */
    public static void list(File mainFile, PrintWriter out) throws EmptyFolderException {

        for (File subFile : mainFile.listFiles()) {
            if (subFile.isDirectory() && subFile.listFiles().length > 0) {
                out.println(subFile.getAbsolutePath());
                list(subFile, out);
            }
            else if (subFile.isDirectory())
                throw new EmptyFolderException("Error: folder '" + subFile.getName() + "' is empty.");
            else
                out.println(subFile.getAbsolutePath());
        }
    }

    /**
     * Creates html files with pictures
     * @param mainFile the file containing files with the pictures
     * @throws InvalidFileException if file is not image
     */
    public static void process(File mainFile) throws InvalidFileException{
        int count = 0;

        for (File subfile : mainFile.listFiles()) {
            if (subfile.isDirectory() && subfile.listFiles().length > 0) {
                try {
                    PrintWriter out = new PrintWriter(subfile.getName() + ".html");
                    createHtmlFile(subfile, out, subfile.getName());
                    for (File image : subfile.listFiles()) {
                        // check if file is image
                        if (ImageIO.read(image) != null) {
                            // html for pic column
                            out.println("<div class=\"column\">\n<img src=\"src/Data/" + subfile.getName() + "/" + image.getName() +
                                    "\">\n</div>");
                            count++;
                        }
                       if (count % 4 == 0)
                           // html to end row and start a new row
                           out.println("</div>\n<div class=\"row\">");
                    }
                    // html to end body
                    out.println("</div>");
                    endHtmlFile(subfile, out);
                    out.close();
                }
                catch (FileNotFoundException e) {
                    System.out.println("Error creating print writer. File not found.");
                }
                catch (IOException e) {
                    throw new InvalidFileException("Error. File is not an image.");
                }
            }
        }
    }

    /**
     * Creates the heading of the html files
     * @param file html file
     * @param out print writer
     * @param className title for the html page
     */
    public static void createHtmlFile(File file, PrintWriter out, String className) {
        // html setup and first row
        out.println("<html>\n<head>\n<link rel=\"stylesheet\" href=\"assignment3.css\">\n</head>\n" +
                "<body>\n<div style=\"text-align:center\">\n<h1>"+ className +"</h1>\n</div>\n" +
                "<div class=\"row\">");
    }

    /**
     * Adds closing tags for html file
     * @param file html file
     * @param out print writer
     */
    public static void endHtmlFile(File file, PrintWriter out) {
        // html ending
        out.println("</body>\n</html>");
    }
}
