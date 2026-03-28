public class Main{
    public static void main(String[] args) {
        ERyder bike1 = new ERyder();
        System.out.println("bike1：");
        bike1.printBikeDetails(30);

        ERyder bike2 = new ERyder(1001, 80, true, 150.5);
        System.out.println("bike2：");
        bike2.ride(10);
        System.out.println("bike2：");
        bike2.printBikeDetails(30);
        UserRegistration user = new UserRegistration();
        user.registration();
        System.out.println(user);
       AdminPanel adminPanel=new AdminPanel();
         adminPanel.userManagementOptions();
        

    }
}




