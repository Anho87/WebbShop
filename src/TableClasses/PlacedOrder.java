package TableClasses;

public class PlacedOrder {
    private int id;
    private int customerId;
    
    public PlacedOrder(){
        
    }

    

    public PlacedOrder(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
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

    @Override
    public String toString() {
        return "PlacedOrder: " +
                "Id: " + id +
                " CustomerId: " + customerId;
    }
}
