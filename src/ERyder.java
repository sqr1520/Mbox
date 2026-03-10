
class ERyder {
    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    public ERyder() {
        this.bikeID = 0;
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
        
    }


    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    public void ride() {
        if (this.batteryLevel > 0 && this.isAvailable) {
            System.out.println("the bike is available");
        } else {
            System.out.println("the bike is not available");
        }
    }

    public void printBikeDetails() {
        System.out.println("bikeID: " + this.bikeID);
        System.out.println("battery level: " + this.batteryLevel + "%");
        System.out.println("availability: " + (this.isAvailable ? "yes" : "no"));
        System.out.println("distance: " + this.kmDriven + " km");
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("wrong");
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}

public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        System.out.println("bike1：");
        bike1.printBikeDetails();

        ERyder bike2 = new ERyder(1001, 80, true, 150.5);
        System.out.println("bike2：");
        bike2.ride();
        System.out.println("bike2：");
        bike2.printBikeDetails();

       
    }
}
