package TableClasses;

public class PlacedOrder {
    private final int id;
    private final Customer customer;
    
    public PlacedOrder(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    @Override
    public String toString() {
        return "PlacedOrder: " +
                "Id: " + id +
                " Customer: " + customer;
    }
}
