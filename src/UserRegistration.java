import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid = false;
    private boolean minorAndBirthday = false;
    private boolean minor = false;
    private boolean ageValid = false;
    private boolean cardNumberValid = false;
    private boolean cardStillValid = false;
    private boolean validCVV = false;

    public void registration() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:" +
                "Register as a Regular User" + "Register as a VIP User" + "Please enter your choice(1 or 2)");
        int choice = sc.nextInt();
        sc.nextInt();

        if (choice == 1) {
            userType = "Regular User";
        } else if (choice == 2) {
            userType = "VIP User";
        } else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.println("input your full name.");
        fullName = sc.nextLine();

        System.out.println("input your email address");
        emailAddress = sc.nextLine();
        System.out.println("Checking your email address's validity");
        emailValid = analyseEmail(emailAddress);

        System.out.println("enter your date of birth as YYYY-MM-DD:");
        dateOfBirth = sc.nextLine();
        System.out.println("Checking your age validity");
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("enter your card number");
        cardNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Checking your card number's validity");
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.println("enter the card expiry date (MM/YY)");
        cardExpiryDate = sc.nextLine();
        System.out.println("Checking if your card is still valid");
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.println("enter your CVV");
        cvv = sc.nextInt();
        sc.nextLine();
        System.out.println("Checking if your CVV's validity");
        validCVV = analyseCVV(cvv);

        finalCheckpoint();
        sc.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email address is valid");
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
        }
        return true;
    }


    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();

        if (age < 12 || age > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user.Have a nice day");
            System.exit(0);
        }
        boolean isBirthday = (dob.getMonth() == currentDate.getMonth() && dob.getDayOfMonth() == currentDate.getDayOfMonth());
        if ("VIP User".equals(userType)) {
            if (isBirthday) {
                if (age <= 18 && age > 12) {
                    System.out.println("Happy Birthday" + "You get 25% discount on the VIP subscription fee for being today and being under 18");
                    minor = true;
                }
            }

        }
        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits;
        String firstTwoStr = cardNumStr.substring(0, 2);
        firstTwoDigits = Integer.parseInt(firstTwoStr);

        int firstFourDigits;
        String firstFourStr = cardNumStr.substring(0, 4);
        firstFourDigits = Integer.parseInt(firstFourStr);

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && (cardNumStr.startsWith("4"))) {
            cardProvider = "VISA";
        } else if (cardNumStr.length() == 16) {
            if ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720)) {
                cardProvider = "MasterCard";
            }

        } else if ((cardNumStr.length() == 15) && (cardNumStr.startsWith("34")) || (cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
        } else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card. Going back to the start of the registration.");
            registration();
        }
        return true;
    }

    private boolean analyseCardExpiryDate(String cardExpiryDate) {
        int month;
        String firstTwoString = cardExpiryDate.substring(0, 2);
        month = Integer.parseInt(firstTwoString);

        int year;
        String firstFourString = cardExpiryDate.substring(3, 5);
        year = Integer.parseInt(firstFourString);
        year = year + 2000;

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year >= currentYear) {
            if (month >= currentMonth) {
                System.out.println("The card is still valid");
            }
        } else {
            System.out.println("Sorry,your card has expired. Please use a different card. Going back to the start of the registration process");
            registration();
        }
        return true;
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        if ("American Express".equals(cardProvider)) {
            validCVV = cvvStr.length() == 4;
        } else if ("VISA".equals(cardProvider)) {
            validCVV = cvvStr.length() == 3;
        } else if ("MasterCard".equals(cardProvider))
            validCVV = cvvStr.length() == 3;
        else {
            validCVV = false;
        }

        if (validCVV) {
            System.out.println("Card CVV is valid");
        } else {
            System.out.println("Invalid CVV for the given card. Going back to the start of the registration process");
            registration();
        }
        return validCVV;
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && analyseCVV(cvv)) {
            chargeFee();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) {
                System.out.println("Invalid email address");
            } else if (!ageValid) {
                System.out.println("Invalid age");
            } else if (!cardNumberValid) {
                System.out.println("Invalid card number");
            } else if (!cardStillValid) {
                System.out.println("Card has expired");
            } else if (!validCVV) {
                System.out.println("Invalid CVV");
            }
            registration();
        }

    }

    private void chargeFee(){
        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(0,cardNumStr.length()-4);
    if(minorAndBirthday){
    feeToCharge = VIP_DISCOUNT_UNDER_18_BIRTHDAY;    }
     else if (minor) {
     feeToCharge =VIP_DISCOUNT_UNDER_18;
        } else {
        feeToCharge=VIP_BASE_FEE;
    }
     System.out.println("Thank you for your payment.A fee of "+feeToCharge+" has been charged to your card ending with "+lastFourDigits);
    }


    @Override
    public String toString(){
        String cardNumStr = String.valueOf(cardNumber);
        String censoredPart=cardNumStr.substring(0,cardNumStr.length()-4).replaceAll(".","*");
        String LastFourDigits =cardNumStr.substring(cardNumStr.length()-4);
        String censoredNumber = censoredPart+LastFourDigits;
        return "Registration successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate;
    }

}

    

