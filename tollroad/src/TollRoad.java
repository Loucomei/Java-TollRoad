import java.util.LinkedList;
import java.util.Objects;

//this class is used to add customers into customer account, find customers by their registration number and also charge them for their journey.
public class TollRoad {

    //I decided to use linked list instead of array list because it seems more efficient in this case, the linked list
    // is faster to use when adding or removing objects. So, in our case is more useful to use something faster to add
    //the customers quicker so the code can process quicker as well.
    private  LinkedList<CustomerAccount> customerAccountData= new LinkedList<>(); //deleted static
    private int moneyMade =0;



    public LinkedList<CustomerAccount> getCustomerAccountData(){return customerAccountData;}
    public int getMoneyMade(){return moneyMade;}

    public void setCustomerAccountData(LinkedList<CustomerAccount> customerAccountData){this.customerAccountData=customerAccountData;}

    public boolean addCustomer(CustomerAccount account){
        return customerAccountData.add(account);
    }


    //method used to find customer by registration number, when registration is given the method will go through the list
    //to find customer when customer is found it will return the customer number from the list and if the customer
    //is not found it will throw an exception
    public  CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException{
        int customerNumber=0;
        boolean carNotFound=false;

        for (int i =0; i<customerAccountData.size(); i++) {
            if (Objects.equals(customerAccountData.get(i).getCar().getVehicleRegistration(), regNum)) {
                carNotFound = false;
                break;
            }
            carNotFound = true;
            customerNumber++;
        }
        if (carNotFound){
            throw new CustomerNotFoundException("Customer not found");
        }
        return customerAccountData.get(customerNumber);
    }

    public void chargeCustomer (String registrationNumber) throws CustomerNotFoundException, InsufficientAccountBalanceException{
            CustomerAccount p1 = findCustomer(registrationNumber);
            moneyMade += p1.makeTrip();
    }



    public static void main(String[] args) throws InsufficientAccountBalanceException, CustomerNotFoundException {

       TollRoad road1= new TollRoad();

       Truck firstTruck= new Truck("AF1","opel",3);
       Truck secondTruck= new Truck("BA3","opel",3);

       CustomerAccount persona1= new CustomerAccount("Yola","L",2000,firstTruck, CustomerAccount.Discount.NONE);
       CustomerAccount persona2= new CustomerAccount("Jhon","B",2031,secondTruck, CustomerAccount.Discount.NONE);
       //testing adding the customers into the tollRoad
       road1.addCustomer(persona1);
       road1.addCustomer(persona2);
       //testing the money being made in tollRoad
       road1.chargeCustomer("AF1");
       road1.chargeCustomer("BA3");
       System.out.println(road1.moneyMade);




    }


}
