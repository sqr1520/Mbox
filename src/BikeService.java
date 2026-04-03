import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class BikeService {
    private LinkedList<BikeDatabase> bikeDatabase;
    private ArrayDeque<BikeRequest> bikeRequests = new ArrayDeque<>();  
  
    public BikeService(LinkedList<BikeDatabase> bikeDatabase) {
        this.bikeDatabase = bikeDatabase;
    }

    public String validateLocation(String location) {
        for (BikeDatabase bike : bikeDatabase) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    public boolean reserveBike(String bikeID) {
        for (BikeDatabase bike : bikeDatabase) {
            if (bike.getBikeID().equals(bikeID) && bike.isAvailable()) {
                bike.setAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Reserving the bike with ID: " + bikeID + ". Please follow on-screen instructions to locate it.");
                return true;
            }
        }
        System.out.println("Sorry, we're unable to reserve the bike at this time.");
        return false;
    }
    BikeRequest request =new BikeRequest(getUserEmail(), getLocation());

    public boolean releaseBike(String bikeID) {
        for (BikeDatabase bike : bikeDatabase) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Bike " + bikeID + " has been released.");
                return true;
            }
        }
        System.out.println("Bike " + bikeID + " not found in database.");
        return false;
    }

    private String getLocation() {
        throw new UnsupportedOperationException("Unimplemented method 'getLocation'");
    }

    private String getUserEmail() {
        throw new UnsupportedOperationException("Unimplemented method 'getUserEmail'");
    }

    public Bike getBike() {
        throw new UnsupportedOperationException("Unimplemented method 'getBike'");
    }
}




