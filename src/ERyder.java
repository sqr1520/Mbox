
class ERyder {
    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    public static final String COMPANY_NAME = "ERyder";        
    public static final double BASE_FARE = 1.0;        
    public static final double PER_MINUTE_FARE =0.5;
    private int totalUsageInMinutes;
    private double totalFare;
    public static final String LINKED_ACCOUNT;        
    public static final long LINKED_PHONE_NUMBER;

    public ERyder() {
        this.bikeID = 0;
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
        this.LINKED_ACCOUNT = idonnotknow;
        this.LINKED_PHONE_NUMBER = 123456789;
        
    }


    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven, String LINKED_ACCOUNT, long LINKED_PHONE_NUMBER, int totalUsageInMinutes, double totalFare) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT =LINKED_ACCOUNT;
        this.LINKED_PHONE_NUMBER = LINKED_PHONE_NUMBER;
        this.totalFare =totalFare;
        this.totalUsageInMinutes = totalUsageInMinutes;
    }

    public void ride() {
        if (this.batteryLevel > 0 && this.isAvailable) {
            System.out.println("the bike is available");
        } else {
            System.out.println("the bike is not available");
        }
    }

    public void printBikeDetails(int usagelnMinutes) {
        double Fare = calculateFare(usagelnMinutes);
        System.out.println("bikeID: " + this.bikeID);
        System.out.println("battery level: " + this.batteryLevel + "%");
        System.out.println("availability: " + (this.isAvailable ? "yes" : "no"));
        System.out.println("distance: " + this.kmDriven + " km");
        System.out.println("Linked Account: " + this.linkedAccount);
        System.out.println("Linked Phone Number: " + this.linkedPhoneNumber);
        System.out.println("Bike ID: " + this.bikeID);
        System.out.println("Usage in Minutes: " + usageInMinutes );
        System.out.println("Total Fare: " + Fare);
    }
    private double calculateFare(int usagelnMinutes){
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
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
        bike1.printBikeDetails(30);

        ERyder bike2 = new ERyder(1001, 80, true, 150.5);
        System.out.println("bike2：");
        bike2.ride();
        System.out.println("bike2：");
        bike2.printBikeDetails(30);

       
    }
}
