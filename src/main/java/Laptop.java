public class Laptop extends Item {
    int memorySize;
    int ssdCapacity;
    double screenSize;

    // Constructor
    public Laptop(String c, String t, String i, String b, String cpu, double p, int mS, int ssd, double sS) {
        super(c,t,i,b,cpu,p);
        setMemorySize(mS);
        setSsdCapacity(ssd);
        setScreenSize(sS);
    }

    public String toString() {
        return super.toString() + "\t" + memorySize + "\t" + ssdCapacity + "\t" + "\t" + screenSize + "\t" + price;
    }

    // Getters and setters

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getSsdCapacity() {
        return ssdCapacity;
    }

    public void setSsdCapacity(int ssdCapacity) {
        this.ssdCapacity = ssdCapacity;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}

