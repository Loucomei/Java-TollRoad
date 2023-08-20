//Van class that represents van vehicle used to divide vehicles easier due to different payment rules and variables.
//In Van class we need payload data and we will be calculating the cost of trip based on how heavy is the payload.
public class Van extends Vehicle{

    private int payload;

    public Van(String vehicleRegistration, String manufactureOfVehicle, int payload){
        super(vehicleRegistration, manufactureOfVehicle);
        this.payload=payload;
    }

    public int getPayload(){return this.payload;}


    //overriding calculateBasicTripCost method from Vehicle to calculate how much does it cost to carry certain amount of
    //payload when payload weights less or equal to 600kg it costs 500p when payload weights over 600kg but less
    //or equal to 800k the cost is 750p when payload is over 800kg then cost goes up to 1000p
    @Override
    public int calculateBasicTripCost() {
        if (payload <= 600){
            return 500;
        }
        else if (payload <= 800){
            return 750;
        }
        else {
            return 1000;
        }
    }

    @Override
    public String toString() {return super.toString() + " your total = " + calculateBasicTripCost() + "p";}

    public static void main(String[] args) {
     Van firstVanTest= new Van("AEE45", "Citroen", 600);
     Van secondVanTest= new Van("LALA3", "Ford", 300);
     Van thirdVanTest= new Van("ABEN32", "Opel", 800);
     Van fourthVanTest= new Van("ALEL321", "BMW", 700);
     Van fifthVanTest= new Van("AOEPAO22", "Ford", 893);

        System.out.println(firstVanTest);
        System.out.println(secondVanTest);
        System.out.println(thirdVanTest);
        System.out.println(fourthVanTest);
        System.out.println(fifthVanTest);
    }

}
