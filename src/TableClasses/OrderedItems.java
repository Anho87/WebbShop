package TableClasses;

public class OrderedItems {
    private int id;
    private int placedOrderId;
    private int shoeId;
    

    public OrderedItems(){
        
    }
    public OrderedItems(int id, int placedOrderId, int shoeId) {
        this.id = id;
        this.placedOrderId = placedOrderId;
        this.shoeId = shoeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlacedOrderId() {
        return placedOrderId;
    }

    public void setPlacedOrderId(int placedOrderId) {
        this.placedOrderId = placedOrderId;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }
    @Override
    public String toString() {
        return  "OrderedItems: " +
                "Id: " + id +
                " PlacedOrderId: " + placedOrderId +
                " ShoeId: " + shoeId;
    }
}
