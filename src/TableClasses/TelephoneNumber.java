package TableClasses;

public class TelephoneNumber {
    private int id;
    private Customer customer;
    private String telephoneNumber;
    
    public TelephoneNumber(){
        
    }

    public TelephoneNumber(int id, Customer customer, String telephoneNumber) {
        this.id = id;
        this.customer = customer;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "TelephoneNumber " +
                "Id: " + id +
                " Customer: " + customer +
                " TelephoneNumber: " + telephoneNumber;
    }
}
