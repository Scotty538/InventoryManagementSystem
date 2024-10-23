import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Create a product catalogue using ArrayList of Items
        ArrayList<Item> productCatalogue;
        // Read file data into the library catalogue as Desktop/Laptop/Tablet objects (which are subclasses of the abstract class Item)
        productCatalogue = IO.readFile("src/main/resources/computers.txt");

        /*
        //DEBUG - Checking data was read in correctly
        // Print out library catalogue
        System.out.println("List of all items in the library:");
        for (Item i : productCatalogue) {
            System.out.print(i.toString());
        }
        */

        // The product catalogue is passed to the JFrame
        new ProductBrowserUpdatePanel(productCatalogue);

    }
}
