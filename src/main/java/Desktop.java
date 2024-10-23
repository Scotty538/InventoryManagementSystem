public class Desktop extends Item {

    int memorySize;
    int ssdCapacity;

    // Constructor
    public Desktop(String c, String t, String i, String b, String cpu, double p, int mS, int ssd) {
        super(c,t,i,b,cpu,p);
        setMemorySize(mS);
        setSsdCapacity(ssd);
    }

    public String toString() {
        return super.toString() + "\t" + memorySize + "\t" + ssdCapacity + "\t" + price;
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
}
