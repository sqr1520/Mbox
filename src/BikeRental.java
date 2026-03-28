import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class BikeRental {
    private static final BikeDatabase[] BikeDatabase = null;
    boolean isRegisteredUser;
    String emailAddress;
    String location;
    String bikeID;
    boolean locationValid;

    LinkedList<ActiveRental> activeRentals = new LinkedList<>();
    LinkedList<BikeDatabase> bikeDatabase = new LinkedList<>();


    public void simulateApplicationlnput(){
        System.out.println("This is the simulation of the e-bike rental process");
        Scanner sc = new Scanner(System.in);
        String emailAddress = sc.nextLine();
        String location = sc.nextLine();
        System.out.println("Simulating the analysis of the rental request");
        this.isRegisteredUser = isRegisteredUser(emailAddress);
        this.emailAddress = emailAddress;
        this.location = location;
        String bikeID = analyseRequest();
        if(!locationValid){
            return;
        }
        System.out.println("Simulating e-bike reservation… ");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals… ");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after the trip ends…");
        viewActiveRentals();
    }

    // Added method to check if user is registered
    private boolean isRegisteredUser(String emailAddress) {
        // Dummy implementation: always returns true
        // Replace with actual logic as needed
        return true;
    }

    private String analyseRequest(){
        if(isRegisteredUser=true){
            System.out.println("Weclome back"+emailAddress);
        }else{
            System.out.println("You're not our registered user.Please consider registering");
            UserRegistration user=new UserRegistration();
            user.registration();
            String bikeID=validateLocation(location);
            return bikeID;
        }
        return null;
    }

    private String validateLocation(String location){
        for(BikeDatabase bike:bikeDatabase){
            if(bike.getLocation().equals(location)&&bike.isAvailable()){
                System.out.println("A bike is avalidble at the location you requested. ");
                locationValid=true;
                return bike.getBikeID();
            }
            System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        }
        return null;
    }
    private void reserveBike(String bikeID){
        if(bikeID!=null){
            for(BikeDatabase bikes:bikeDatabase){
                if(bikes.getBikeID().equals(bikeID)){
                    LocalDateTime tripStartTime=LocalDateTime.now();
                    setlsAvailable(false);
                    setLastUsedTime(tripStartTime);
                    System.out.println(" Reserving the bike with the (bikeID). Please following the on-screen instructions\r\n" + //
                                                "to locate the bike and start your pleasant journey.");  
                    ActiveRental newRental=new ActiveRental(bikeID,emailAddress,tripStartTime);
                    activeRentals.add(newRental);
                    break;                                                       
                }else{
                    System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
                }

            }

        }
    }
    private void setLastUsedTime(LocalDateTime tripStartTime) {
        throw new UnsupportedOperationException("Unimplemented method 'setLastUsedTime'");
    }

    private void setlsAvailable(boolean b) {
        throw new UnsupportedOperationException("Unimplemented method 'setlsAvailable'");
    }

    private void viewActiveRentals(){
        if(activeRentals.isEmpty()){
            System.out.println("No active rentals at the moment");
        }else{
            for(ActiveRental rental:activeRentals){
                System.out.println("BikeID:"+rental.getBikeID());
                System.out.println("EmailAddress:"+rental.getEmailAddress());
                System.out.println("TripStartTime:"+rental.getTripStartTime());
            }
        }

    }

    private void removeTrip(String bikeID){
        java.util.Iterator<ActiveRental> iterator = activeRentals.iterator();
        while(iterator.hasNext()){
           if(iterator.next().getBikeID().equals(bikeID)){
                iterator.remove();
                break;
                    }
                for(BikeDatabase bikes:BikeDatabase){
                    if(bikes.getBikeID().equals(bikeID)){
                        ( bikes).setAvailable(true);
                        bikes.setLastUsedTime(LocalDateTime.now());
                    }
                    System.out.println("Your trip has ended. Thank you for riding with us.");
                    break;
                }    
                }
                


    }



}

