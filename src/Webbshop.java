import Repository.Repository;
import TableClasses.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Webbshop {
    
    private Scanner sc = new Scanner(System.in);
    private List<Customer> customerList;
    private List<OrderedItems> orderedItemsList;
    private List<PlacedOrder> placedOrderList;
    private List<Shoe> shoeList;
    private List<Size> sizeList;
    private List<TelephoneNumber> telephoneNumberList;
    private List<Brand> brandList; 
    private List<Category> categoryList;
    private List<CategoryName> categoryNameList;
    private List<Color> colorList;
    private Repository r = new Repository();
    private String userName;
    private String password;
    private boolean loggedIn = false;
    private Customer currentCustomer;
    private int productId;
    private int orderId;

    public static void main(String[] args) {
        new Webbshop();
    }

    public Webbshop() {

        // r.printAllShoes();
        
        customerList = r.getCustomerData();
        //customerList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();

        // r.printAllOrderedItems();
        orderedItemsList = r.getOrderItemsData();
        //orderedItemsList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        placedOrderList = r.getPlacedOrderData();
        //placedOrderList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        shoeList = r.getShoeData();
        //shoeList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        sizeList = r.getSizeData();
        //sizeList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        telephoneNumberList = r.getTelephoneNumberData();
        //telephoneNumberList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        brandList = r.getBrandData();
        //brandList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        categoryList = r.getCategoryData();
        //categoryList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        categoryNameList = r.getCategoryNameData();
        //categoryNameList.forEach(e -> System.out.println(e.toString()));

        //System.out.println();
        
        colorList = r.getColorData();
        //colorList.forEach(e -> System.out.println(e.toString()));

        //shoeList.forEach(e -> System.out.println(e));

        //brandList.stream().filter(e -> e.getBrand(shoeList.stream().filter(f -> f.getStock() > 0))).forEach(e -> System.out.println(e));
       /* brandList.stream()
                .filter(brand -> shoeList.stream()
                        .anyMatch(shoe -> shoe.getBrandId() == brand.getId() && shoe.getStock() > 0))
                .forEach(e -> System.out.println(e.getBrand()));*/
        /*placedOrderList.stream().filter(placedOrder -> customerList.stream()
                .anyMatch(customer -> customer.getId() == placedOrder.getCustomerId() && customer.getFirstName().equalsIgnoreCase("Mayo")))
                .forEach(e -> System.out.println(e.getId()));*/
        login();
    }
    public void login(){
        System.out.println("Välkommen!");
        System.out.print("Skriv in din mailadress: ");
        userName = sc.nextLine().trim();
        System.out.print("Skriv in ditt lösenord: ");
        password = sc.nextLine().trim();
        for (Customer c: customerList) {
            if(c.getEmailAddress().equalsIgnoreCase(userName)&& c.getPassword().equalsIgnoreCase(password)){
                loggedIn = true;
                currentCustomer = c;
                orders();
            }
        }
    }
    public void orders(){
        System.out.println("Välkommen " + currentCustomer.getFirstName());
        System.out.println("Vill du lägga till produkter till en befintlig order eller skapa en ny?");
        System.out.println("1. Skapa en ny order.\n2. Lägg till i en befintlig order.");
        int a = sc.nextInt();
        if(a == 1){
            orderId = 0;
        }else{
            System.out.println("Välj en befintlig order:");
            placedOrderList.stream().filter(e -> e.getCustomerId() == currentCustomer.getId()).forEach(e-> System.out.println("OrderNr: " + e.getId()));
            orderId = sc.nextInt();
        }
        
    }
}
        

