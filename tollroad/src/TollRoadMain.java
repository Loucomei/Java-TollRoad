import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class TollRoadMain {

    //method is used to read the txt file and assign the customers into the list link by the variables that are
    //created in CustomerAccount class, the method uses scanner for easy split of the document. The method also
    //uses switch to find what type of vehicle customer owns.
    public static TollRoad initialiseTollRoadFromFile() throws FileNotFoundException {

        TollRoad myTollRoad = new TollRoad();
        File inputFile = new File ("customerData.txt");
        Scanner scan = new Scanner(inputFile);
        scan.useDelimiter("#");
        Scanner lineScan;
        LinkedList<CustomerAccount> customerAccountsData= new LinkedList<>();
        String firstName, lastName, vehicleRegistration, manufactureOfVehicle,typeOfVehicle;
        double accountBalance;
        CustomerAccount.Discount discounts;
        int vehicleLimits;

        while(scan.hasNext()){
            lineScan= new Scanner(scan.next());
            lineScan.useDelimiter(",");

                typeOfVehicle=lineScan.next();
                vehicleRegistration=lineScan.next();
                firstName=lineScan.next();
                lastName=lineScan.next();
                manufactureOfVehicle=lineScan.next();
                vehicleLimits=Integer.parseInt(lineScan.next());
                accountBalance=Double.parseDouble(lineScan.next());
                discounts= CustomerAccount.Discount.valueOf(lineScan.next());

            switch (typeOfVehicle) {
                case "Van" : {
                    Vehicle car = new Van(vehicleRegistration, manufactureOfVehicle, vehicleLimits);
                    CustomerAccount firstCustomer = new CustomerAccount(firstName, lastName, accountBalance, car,discounts);
                    customerAccountsData.add(firstCustomer);
                    break;
                }
                case "Car" : {
                    Vehicle car = new Car(vehicleRegistration, manufactureOfVehicle, vehicleLimits);
                    CustomerAccount firstCustomer = new CustomerAccount(firstName, lastName, accountBalance, car,discounts);
                    customerAccountsData.add(firstCustomer);
                    break;
                }
                case "Truck" : {
                    Vehicle car = new Truck(vehicleRegistration, manufactureOfVehicle, vehicleLimits);
                    CustomerAccount firstCustomer = new CustomerAccount(firstName, lastName, accountBalance, car,discounts);
                    customerAccountsData.add(firstCustomer);
                    break;
                }
            }

        }

        myTollRoad.setCustomerAccountData(customerAccountsData);
        return myTollRoad;
    }

    //method is used to read the file and execute two different methods, the file is read and split with use of scanner
    //while splitting the method finds which of the method should be called add founds or make trip, it will be then
    //call the right method and run a try though it, if there is not enough funds to make the tip the exception will be thrown
    //if the customer doesn't exist the exception also will be thrown. In successful tries the methods will run correctly

    public void simulateFromFile(TollRoad road) throws FileNotFoundException {
        File inputFile2= new File("transactions.txt");
        Scanner scan2= new Scanner(inputFile2);
        scan2.useDelimiter("\\$");
        Scanner lineScan2;
        String whichMethod, vehicleRegistration;
        int amount;

        while(scan2.hasNext()){
            lineScan2= new Scanner(scan2.next());
            lineScan2.useDelimiter(",");
            whichMethod=lineScan2.next();

            if(whichMethod.equals("addFunds")){
                vehicleRegistration=lineScan2.next();
                amount=Integer.parseInt(lineScan2.next());
                try {
                    CustomerAccount person = road.findCustomer(vehicleRegistration);
                    int indexToFind = road.getCustomerAccountData().indexOf(person);
                    road.getCustomerAccountData().get(indexToFind).addFunds(amount);
                    System.out.println(vehicleRegistration + ": " + amount + " amount added successfully ");
                }
                catch(CustomerNotFoundException e){
                    System.out.println(vehicleRegistration + ": " + " add Funds failed. CustomerAccount does not exist");
                }
            }
            else if(whichMethod.equals("makeTrip")){
                vehicleRegistration=lineScan2.next();

                try{
                    road.chargeCustomer(vehicleRegistration);
                    System.out.println(vehicleRegistration + ": " + " Trip completed successfully" + road.getMoneyMade() );
                }
                catch(CustomerNotFoundException er){
                    System.out.println(vehicleRegistration +": " + " makeTrip failed. CustomerAccount does not exist");
                }
                catch(InsufficientAccountBalanceException e){
                    System.out.println(vehicleRegistration + ": " + "makeTrip failed. Insufficient funds");
                }
            }
        }


    }

    public static void main(String[] args) throws FileNotFoundException, InsufficientAccountBalanceException, CustomerNotFoundException {

            TollRoad file1 = initialiseTollRoadFromFile();
            TollRoadMain mainRoad = new TollRoadMain();
            System.out.println(file1);
            mainRoad.simulateFromFile(file1);
            System.out.println("total of the money made : " + file1.getMoneyMade());
}}
