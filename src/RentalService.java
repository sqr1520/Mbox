import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    private LinkedList<ActiveRental> activeRentals;
    private BikeService bikeService; 

    public RentalService(LinkedList<ActiveRental> activeRentals, BikeService bikeService) {
        this.activeRentals = activeRentals;
        this.bikeService = bikeService;
    }

    public void startRental(String bikeID, String emailAddress) {
        if (bikeService.reserveBike(bikeID)) { 
            ActiveRental newRental = new ActiveRental(bikeID, emailAddress, LocalDateTime.now());
            activeRentals.add(newRental);
        }
    }

    public void endRental(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentals.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                bikeService.releaseBike(bikeID); 
                System.out.println("Your trip has ended. Thank you for riding with us.");
                return;
            }
        }
        System.out.println("No active rental found for bike ID: " + bikeID);
    }

    public void cancelRental(String bikeID) {
        endRental(bikeID);
    }

    public void trackActiveRentals() {
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals at the moment.");
        } else {
            for (ActiveRental rental : activeRentals) {
                System.out.println("BikeID: " + rental.getBikeID());
                System.out.println("Email: " + rental.getEmailAddress());
                System.out.println("Start Time: " + rental.getTripStartTime() + "\n");
            }
        }
    }
}

