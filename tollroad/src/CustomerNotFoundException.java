public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException (String noCustomer){
        super(noCustomer);
    }
}
