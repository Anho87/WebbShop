import Repository.Repository;
import TableClasses.*;
import com.google.protobuf.StringValue;

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

        getAllData();
        login();

    }

    public void getAllData() {
        customerList = r.getCustomerData();
        orderedItemsList = r.getOrderItemsData();
        placedOrderList = r.getPlacedOrderData();
        shoeList = r.getShoeData();
        sizeList = r.getSizeData();
        telephoneNumberList = r.getTelephoneNumberData();
        brandList = r.getBrandData();
        categoryList = r.getCategoryData();
        categoryNameList = r.getCategoryNameData();
        colorList = r.getColorData();
    }


    public void login() {
        System.out.println("Välkommen!");
        System.out.print("Skriv in din mailadress: ");
        userName = sc.nextLine().trim();
        System.out.print("Skriv in ditt lösenord: ");
        password = sc.nextLine().trim();
        for (Customer c : customerList) {
            if (c.getEmailAddress().equalsIgnoreCase(userName) && c.getPassword().equalsIgnoreCase(password)) {
                loggedIn = true;
                currentCustomer = c;
                orders();
            }
        }
    }

    public void orders() {
        System.out.println("Välkommen " + currentCustomer.getFirstName());
        System.out.println("Vill du lägga till produkter till en befintlig order eller skapa en ny?");
        System.out.println("1. Skapa en ny order.\n2. Lägg till i en befintlig order.");
        int a = sc.nextInt();
        if (a == 1) {
            orderId = 0;
            showShoes();
        } else {
            System.out.println("Välj en befintlig order:");
            placedOrderList.stream().filter(e -> e.getCustomerId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
            orderId = sc.nextInt();
        }

    }

    public void showShoes() {
        while (true) {
            System.out.println("Välj en kategori");
            for (int i = 1; i < categoryNameList.size() + 1; i++) {
                for (CategoryName c : categoryNameList) {
                    if (c.getId() == i) {

                        System.out.println(i + ": " + c.getCategoryName());
                    }
                }
            }
            int a = sc.nextInt();
            printShoesOfCategory(a);
        }

    }

    public void printShoesOfCategory(int a) {
        brandList.stream()
                .filter(brand -> categoryList.stream()
                        .anyMatch(category -> category.getBrandId() == brand.getId() && category.getCategoryNameId() == a))
                .forEach(e -> System.out.println(e.getBrand()));
    }
}
    
        

