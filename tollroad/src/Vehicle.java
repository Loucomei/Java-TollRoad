//Base class for the Vehicle type, aka SuperClass. This class will work as our core for other vehicle type so we don't
//need type extra data to every sub-class. We running here our base toString method and calculateBasicTripCost.
public abstract class Vehicle {

    protected String vehicleRegistration;
    protected String manufactureOfVehicle;

    public Vehicle (String vehicleRegistration, String manufactureOfVehicle){
        this.vehicleRegistration=vehicleRegistration;
        this.manufactureOfVehicle=manufactureOfVehicle;
    }
    public String getVehicleRegistration() {return this.vehicleRegistration;}

    public String getManufactureOfVehicle() {return this.manufactureOfVehicle;}

    public String toString() { return "Vehicle registration : " + vehicleRegistration +
            " and manufacture of vehicle: " + manufactureOfVehicle; }

    public abstract int calculateBasicTripCost();
}