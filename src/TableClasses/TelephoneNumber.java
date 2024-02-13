package TableClasses;

public class TelephoneNumber {
    private final int id;
    private final Customer customer;
    private final String telephoneNumber;
    

    public TelephoneNumber(int id, Customer customer, String telephoneNumber) {
        this.id = id;
        this.customer = customer;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    

    @Override
    public String toString() {
        return "TelephoneNumber " +
                "Id: " + id +
                " Customer: " + customer +
                " TelephoneNumber: " + telephoneNumber;
    }
}
