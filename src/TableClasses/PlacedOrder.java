package TableClasses;

public class PlacedOrder {
    private int id;
    private Customer customer;
    
    public PlacedOrder(){
        
    }

    

    public PlacedOrder(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
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

    @Override
    public String toString() {
        return "PlacedOrder: " +
                "Id: " + id +
                " Customer: " + customer;
    }
}
