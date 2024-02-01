import Repository.Repository;
import TableClasses.*;

import java.util.ArrayList;
import java.util.List;

public class Webbshop {

    private List<Customer> customerList;
    private List<OrderedItems> orderedItemsList;
    private List<PlacedOrder> placedOrderList;
    private List<Shoe> shoeList;
    private List<Size> sizeList;
    private List<TelephoneNumber> telephoneNumberList;
    private Repository r = new Repository();

    public static void main(String[] args) {
        new Webbshop();
    }

    public Webbshop() {

        // r.printAllShoes();
        customerList = r.getCustomerData();
        customerList.forEach(e -> System.out.println(e.toString()));

        System.out.println();

        // r.printAllOrderedItems();
        orderedItemsList = r.getOrderItemsData();
        orderedItemsList.forEach(e -> System.out.println(e.toString()));

        System.out.println();
        
        placedOrderList = r.getPlacedOrderData();
        placedOrderList.forEach(e -> System.out.println(e.toString()));

        System.out.println();
        
        shoeList = r.getShoeData();
        shoeList.forEach(e -> System.out.println(e.toString()));

        System.out.println();
        
        sizeList = r.getSizeData();
        sizeList.forEach(e -> System.out.println(e.toString()));

        System.out.println();
        
        telephoneNumberList = r.getTelephoneNumberData();
        telephoneNumberList.forEach(e -> System.out.println(e.toString()));
        
        
    }
}
        

