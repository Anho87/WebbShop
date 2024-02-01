import Repository.Repository;
import TableClasses.Customer;

import java.util.ArrayList;
import java.util.List;

public class Webbshop {
    
    private List<Customer> customerList = new ArrayList<>();
    private Repository r = new Repository();
    public static void main(String[] args) {
        new Webbshop();
    }
    
    public Webbshop(){
        
       // r.printAllShoes();
        customerList = r.getCustomerData();
        for (Customer c: customerList) {
            System.out.println(c);
        }
        
    }
}
