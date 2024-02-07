package TableClasses;

public class OrderedItems {
    private final int id;
    private final PlacedOrder placedOrder;
    private final Shoe shoe;
    
    public OrderedItems(int id, PlacedOrder placedOrder, Shoe shoe) {
        this.id = id;
        this.placedOrder = placedOrder;
        this.shoe = shoe;
    }

    public int getId() {
        return id;
    }
    
    public PlacedOrder getPlacedOrder() {
        return placedOrder;
    }

    public Shoe getShoe() {
        return shoe;
    }

    @Override
    public String toString() {
        return  "OrderedItems: " +
                "Id: " + id +
                " PlacedOrderId: " + placedOrder +
                " ShoeId: " + shoe;
    }
}
