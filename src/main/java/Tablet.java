public class Tablet extends Item {
    double screenSize;

    // Constructor
    public Tablet(String c, String t, String i, String b, String cpu, double p, double sS) {
        super(c,t,i,b,cpu,p);
        setScreenSize(sS);
    }

    public String toString() {
        return super.toString() + "\t" + "\t" + "\t" + screenSize + "\t" + price;
    }
    // Getter and setter
    public double getScreenSize() {
        return screenSize;
    }
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
}
