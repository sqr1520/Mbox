public class ERyder {
    private int bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;
    private String linkedAccount;
    private long linkedPhoneNumber;
    private int totalUsageInMinutes;
    private double totalFare;

    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this(bikeID, batteryLevel, isAvailable, kmDriven, "default_account", 0L, 0, 0.0);
    }
    public ERyder() {
        
    }

    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven,
                 String linkedAccount, long linkedPhoneNumber, int totalUsageInMinutes, double totalFare) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.linkedAccount = linkedAccount;
        this.linkedPhoneNumber = linkedPhoneNumber;
        this.totalUsageInMinutes = totalUsageInMinutes;
        this.totalFare = totalFare;
    }

    public void ride(int minutes) {
        if (this.batteryLevel > 0 && this.isAvailable) {
            this.batteryLevel -= minutes * 2;
            this.batteryLevel = Math.max(this.batteryLevel, 0);
            this.totalUsageInMinutes += minutes;
            this.totalFare = calculateFare(minutes);
            this.isAvailable = (this.batteryLevel > 20);
        } else {
            System.out.println("Cannot ride: Bike is not ready (low battery or occupied)");
        }
    }

    private double calculateFare(int minutes) {
        return BASE_FARE + (minutes * PER_MINUTE_FARE);
    }

    public void printBikeDetails(int minutes) {
        System.out.println("=== " + COMPANY_NAME + " Bike Details ===");
        System.out.println("Bike ID: " + this.bikeID);
        System.out.println("Current Battery Level: " + this.batteryLevel + "%");
        System.out.println("Availability: " + (this.isAvailable ? "Yes" : "No"));
        System.out.println("Total Distance Driven: " + this.kmDriven + " km");
        System.out.println("Linked Account: " + this.linkedAccount);
        System.out.println("Linked Phone Number: " + (this.linkedPhoneNumber == 0 ? "Unbound" : this.linkedPhoneNumber));
        System.out.println("Current Ride Duration: " + minutes + " minutes");
        System.out.println("Current Fare: $" + this.totalFare);
        System.out.println("======================");
    }

    public int getBikeID() { return bikeID; }
    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = Math.min(Math.max(batteryLevel, 0), 100);
    }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public double getKmDriven() { return kmDriven; }
    public void setKmDriven(double kmDriven) { this.kmDriven = kmDriven; }
    public String getLinkedAccount() { return linkedAccount; }
    public void setLinkedAccount(String linkedAccount) { this.linkedAccount = linkedAccount; }
    public long getLinkedPhoneNumber() { return linkedPhoneNumber; }
    public void setLinkedPhoneNumber(long linkedPhoneNumber) { this.linkedPhoneNumber = linkedPhoneNumber; }
    public int getTotalUsageInMinutes() { return totalUsageInMinutes; }
    public double getTotalFare() { return totalFare; }

    
}
