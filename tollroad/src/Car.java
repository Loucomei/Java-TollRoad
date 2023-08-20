//Car class that represents car vehicle used to divide vehicles easier due to different payment rules and data.
//Here we need number of seats that car has to calculate the right amount the person needs to pay.
public class Car extends Vehicle {

    private int numberOfSeats;

    public Car (String vehicleRegistration, String manufactureOfVehicle, int numberOfSeats){
        super(vehicleRegistration,manufactureOfVehicle);
        this.numberOfSeats=numberOfSeats;
    }

    public int getNumberOfSeats(){return this.numberOfSeats;}

    //Overriding calculateBasicTripCost method from vehicle to calculate the right amount for customer
    //when customer has 5 or fewer seats in the car they have to pay 500p hence £5 or if they have 6 or more they will
    //have to pay 600 p hence £6
    @Override
    public int calculateBasicTripCost() {
        if (numberOfSeats <= 5){
            return 500;
        }
        else {
            return 600;
        }
    }

    @Override
    public String toString() {  return super.toString() + " Your total = "  + calculateBasicTripCost() + "p " ;}


    public static void main(String[] args) {
        Car firstCarTest= new Car("74747G", "Honda", 4);
        Car secondCarTest= new Car("ABH6J", "Toyota", 20);
        Car thirdCarTest= new Car("ALA9J", "Nisan", 5);

        System.out.println(firstCarTest);
        System.out.println(secondCarTest);
        System.out.println(thirdCarTest);
    }


}
