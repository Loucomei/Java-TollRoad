// truck class that represents truck vehicle used to divide vehicles easier due to different payment rules and
//data needed. In Truck class we need number of trailers that are attached to the truck to be able to calculate the right
//cost of the trip the truck will take.
public class Truck extends Vehicle{

    private int numTrailers;

    public Truck(String vehicleRegistration, String manufactureOfVehicle, int numTrailers){
        super(vehicleRegistration, manufactureOfVehicle);
        this.numTrailers=numTrailers;
    }

    public int getNumTrailers(){return this.numTrailers;}


    //overriding calculateBasicTripCost method to adjust the price for the number of trailers that the truck can carry
    //if truck can carry one or less it will be charged 1250p if it carries more than 1500p will be charged
    @Override
    public int calculateBasicTripCost() {
        if(numTrailers <= 1){
            return 1250;
        }
        else {
            return 1500;
        }
    }

    @Override
    public String toString() {return super.toString() + " your total = " + calculateBasicTripCost() + "p";}

    public static void main(String[] args) {
        Truck firstTruckTest= new Truck("ABABABA123", "lOD", 0);
        Truck secondTruckTest= new Truck("yayay1", "SA",1);
        Truck thirdTruckTest= new Truck ("AFJSAL13", "ororo", 2);

        System.out.println(firstTruckTest);
        System.out.println(secondTruckTest);
        System.out.println(thirdTruckTest);
    }


}
