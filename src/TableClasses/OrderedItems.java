package TableClasses;

public class OrderedItems {
    private int id;
    private PlacedOrder placedOrder;
    private Shoe shoe;
    

    public OrderedItems(){
        
    }
    public OrderedItems(int id, PlacedOrder placedOrder, Shoe shoe) {
        this.id = id;
        this.placedOrder = placedOrder;
        this.shoe = shoe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlacedOrder getPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(PlacedOrder placedOrder) {
        this.placedOrder = placedOrder;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    @Override
    public String toString() {
        return  "OrderedItems: " +
                "Id: " + id +
                " PlacedOrderId: " + placedOrder +
                " ShoeId: " + shoe;
    }
}
