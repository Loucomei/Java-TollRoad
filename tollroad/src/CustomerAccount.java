//class is used to decleare the data for customer account when new customer is created the values from this class will
//be applied. This class will also charge customers for their joruney accordingly to the vehicle they own.
public class CustomerAccount implements Comparable <CustomerAccount>{

    public enum Discount{NONE, STAFF,FRIENDS_AND_FAMILY}
    private String firstName;
    private String lastName;
    private double accountBalance;
    private  Vehicle car;
    private Discount discounts;

    //I didn't fallow the specification about the constructor because I found that for testing this worked the best
    //and also later on in assigning the discounts from splitting the file it was more clear and nicer to read.
    public CustomerAccount(String firstName, String lastName, double accountBalance, Vehicle car, Discount discounts){
        this.firstName=firstName;
        this.lastName=lastName;
        this.accountBalance=accountBalance;
        this.car=car;
        this.discounts=discounts;


    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public double getAccountBalance(){return accountBalance;}
    public Vehicle getCar (){return car;}
    public Discount getDiscount(){return discounts;}


    //method which is triggered by makeTrip method when Staff discount is applied, it returns a value of the trip cost from
    //the right Vehicle sub-class and then it works out 50% of the value which will be substructure from the total of
    // calculateBasicTripCost method from right sub-class
    public double activateStaffDiscount()
    {return Math.floor(car.calculateBasicTripCost()*50 /100);}

    //same as above but this time it gets triggered by applying Friends and family discount and this discount is 10%
    //so this method works out 10% of the total value from calculateBasicTripCost method from Vehicle sub-class
    public double activateFriendsAndFamilyDiscount()
    {return Math.floor(car.calculateBasicTripCost()*10/100);}

    //this method is to remove a discount which has been applied within account at fist but they might not have it anymore
    public Discount deactivateDiscount()
    {return discounts = Discount.NONE;}

    //this method allows to add more funds to the account
    public int addFunds(int amount){
        int newAccountBalance = (int) (amount + accountBalance);
        this.accountBalance=newAccountBalance;
        return  newAccountBalance;
    }

    //In this method we can apply the right trigger for the discount so we have 3 cases of ifs and they will be triggered
    //while the customer account is made so they have right discount associated with them it will be triggered everytime
    //they make a trip and the ifs will call the right methods which will calculate te 50% or 10% off the total value
    //this value is then subtracted here and returns the total of 50% for staff or 90% for friends and family
    public  int makeTrip() throws InsufficientAccountBalanceException {
        int total = car.calculateBasicTripCost();

        if (discounts == Discount.STAFF){
            double appliedStaffDiscount = total - activateStaffDiscount();
            if (accountBalance-appliedStaffDiscount<0) {
                throw new InsufficientAccountBalanceException("Insufficient funds");
            }
            accountBalance=accountBalance-appliedStaffDiscount;
            return (int)  (appliedStaffDiscount);
        }
        else if (discounts == Discount.FRIENDS_AND_FAMILY){
            double appliedFAFDiscount= total- activateFriendsAndFamilyDiscount();
            if (accountBalance-appliedFAFDiscount<0) {
                throw new InsufficientAccountBalanceException("Insufficient funds");
            }
            accountBalance=accountBalance-appliedFAFDiscount;
            return (int) (appliedFAFDiscount);
        }

        if (accountBalance-total<0) {
            throw new InsufficientAccountBalanceException("Insufficient funds");
        }
        accountBalance=  accountBalance-total;
            return total;
    }
    //compare to method exists to check if different customer have the same vehicle registration, it will return 0 if
    //they have the same vehicle registration, -1 when they are different but alphabetically the registration of the
    //first vehicle is second and 1 when fist vehicle registration comes first alphabetically

    public int compareTo(CustomerAccount anotherCustomerAccount){
        int check= car.vehicleRegistration.compareTo(anotherCustomerAccount.car.vehicleRegistration);
        if (check<0){
            return 1;
        }
        else if (check>0){
            return -1;
        }
        else {
            return 0;
        }
    }

    public String toString() {
        try {
            return "Your account balance is: " + accountBalance + " After this trip it will be: "+
                    makeTrip();
        } catch (InsufficientAccountBalanceException e) {
            throw new RuntimeException(e);
        }
    }


    //test
    public static void main(String[] args) throws InsufficientAccountBalanceException {
        Vehicle firstVehicleTest= new Car("ABC123", "Opel", 3);
        Vehicle secondVehicleTest= new Car( "BDP123", "Volvo",7);
        Vehicle thirdVehicleTest = new Truck("ACD20","Kia",2);


        CustomerAccount firstCustomerTest= new CustomerAccount("Mel","Sam", 500,
                firstVehicleTest,Discount.STAFF);

        CustomerAccount secondCustomerTest = new CustomerAccount("Josh","Lai",1000,
                secondVehicleTest,Discount.NONE);

        CustomerAccount thirdCustomerTest = new CustomerAccount("Megan","Salty",1500,
                thirdVehicleTest,Discount.FRIENDS_AND_FAMILY);

        CustomerAccount fourthCustomerTest = new CustomerAccount("Student","Poor",0,
                thirdVehicleTest,Discount.FRIENDS_AND_FAMILY);



        //test if the Staff discount works
        System.out.println(firstCustomerTest);
        //test if no discount works
        System.out.println(secondCustomerTest);
        //test if F&F discount works
        System.out.println(thirdCustomerTest);
        //Insufficient funds exception test
        //System.out.println(fourthCustomerTest.makeTrip());

        //addFunds test
        System.out.println(firstCustomerTest.addFunds(100));

        //CompareTo test
        System.out.println(firstCustomerTest.compareTo(secondCustomerTest));
        System.out.println(thirdCustomerTest.compareTo(firstCustomerTest));
        System.out.println(fourthCustomerTest.compareTo(thirdCustomerTest));
    }


}
