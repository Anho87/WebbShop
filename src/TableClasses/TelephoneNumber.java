package TableClasses;

public class TelephoneNumber {
    private int id;
    private int customerId;
    private String telephoneNumber;
    
    public TelephoneNumber(){
        
    }

    public TelephoneNumber(int id, int customerId, String telephoneNumber) {
        this.id = id;
        this.customerId = customerId;
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "TelephoneNumber: " +
                "Id: " + id +
                " CustomerId: " + customerId +
                " TelephoneNumber: " + telephoneNumber;
    }
}
