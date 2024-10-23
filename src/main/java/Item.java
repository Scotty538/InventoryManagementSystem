
public abstract class Item {
    protected String category;
    protected String type;
    protected String id;
    protected String brand;
    protected String cpuFamily;
    protected double price;


    // Constructor
    public Item(String c, String t, String i, String b, String cpu, double p) {
        setCategory(c);
        setType(t);
        setId(i);
        setBrand(b);
        setCpuFamily(cpu);
        setPrice(p);
    }

    // Getters and setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpuFamily() {
        return cpuFamily;
    }

    public void setCpuFamily(String cpuFamily) {
        this.cpuFamily = cpuFamily;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

// Overloaded toString() in order to easily print out basic library catalogue for debugging reading-in;
    //    public String simpleCatalogue() {
    //        return String.format("%-12s %-18s %s", "ID: " + id, "Type: " + type, "Title: " + title);
    //    }

    public String toString() {
        return      "\n" + category + "    " + type + "    \t" + id + "\t" + brand + "\t" + cpuFamily + "\t";
    }

    // Created method to produce String array to more easily populate DefaultModelTable in the JTable.
    public String[] toArray() {
        String[] output = {category, type, id, brand, cpuFamily, Double.toString(price)};
        return output;
    }
}

