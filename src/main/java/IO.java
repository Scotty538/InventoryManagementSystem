import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// Reading in the file contents and populating an ArrayList of Items with the relevant child classes.
public class IO {
    public static ArrayList<Item> readFile(String filename) {
        ArrayList<Item> temp = new ArrayList<Item>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
            try {
                while (sc.hasNextLine()) { //returns a boolean value
                    String line = sc.nextLine();
                    if (line.startsWith("D")) {
                        String category = "Desktop PC";
                        String[] input = line.split(",");
                        String type = input[1];
                        String id = input[2];
                        String brand = input[3];
                        String cpuFamily = input[4];
                        int memorySize = Integer.parseInt(input[5]);
                        int ssdCapacity = Integer.parseInt(input[6]);
                        double price = Double.parseDouble(input[7]);
                        temp.add(new Desktop(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity));
                    } else if (line.startsWith("L")) {
                        String category = "Laptop";
                        String[] input = line.split(",");
                        String type = input[1];
                        String id = input[2];
                        String brand = input[3];
                        String cpuFamily = input[4];
                        int memorySize = Integer.parseInt(input[5]);
                        int ssdCapacity = Integer.parseInt(input[6]);
                        double screenSize = Double.parseDouble(input[7]);
                        double price = Double.parseDouble(input[8]);
                        temp.add(new Laptop(category, type, id, brand, cpuFamily, price, memorySize, ssdCapacity, screenSize));
                    } else if (line.startsWith("T")) {
                        String category = "Tablet";
                        String[] input = line.split(",");
                        String type = input[1];
                        String id = input[2];
                        String brand = input[3];
                        String cpuFamily = input[4];
                        double screenSize = Double.parseDouble(input[5]);
                        double price = Double.parseDouble(input[6]);
                        temp.add(new Tablet(category, type, id, brand, cpuFamily, price, screenSize));
                    }
                }
            } finally {
                if (sc != null) {
                    sc.close();  //closes the scanner
                }
            }
        } catch (IOException e) {
            // No point opening the system if there is no computer data to view/update
            System.out.println("\nERROR.\nNo data can be loaded as the designated file was not found in the source directory.");
            System.out.println("The programme will now close.\n");
            System.exit(99);
        }
        return temp;
    }
}

