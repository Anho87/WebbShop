import Repository.Repository;
import TableClasses.*;

import java.util.List;
import java.util.Scanner;

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

        switch (a) {
            case 1 -> {
                orderId = 0;
                showCategories();
            }

            case 2 -> {
                System.out.println("Välj en befintlig order:");
                placedOrderList.stream().filter(e -> e.getCustomerId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
                orderId = sc.nextInt();
                showCategories();
            }
            
        }


    }

    public void showCategories() {
        System.out.println("Välj en kategori:");
        for (int i = 1; i < categoryNameList.size() + 1; i++) {
            System.out.println(i + ": " + categoryNameList.get(i - 1).getCategoryName());
        }
        int chosenCategory = sc.nextInt();
        int chosenCategoryId = categoryNameList.get(chosenCategory - 1).getId();
        System.out.println(chosenCategoryId);
        printShoesOfChosenCategory(chosenCategoryId);
    }

    public void printShoesOfChosenCategory(int chosenCategoryId) {
        System.out.println("Välj en sko:");
        List<Brand> chosenBrand = brandList.stream()
                .filter(brand -> categoryList.stream()
                        .anyMatch(category -> category.getBrandId() == brand.getId() && category.getCategoryNameId() == chosenCategoryId))
                .toList();
        //System.out.println(chosenBrand.size());
        //System.out.println(chosenBrand);
        for (int i = 1; i < chosenBrand.size() + 1; i++) {
            System.out.println(i + ": " + chosenBrand.get(i - 1).getBrand());
        }
        int userChoise = sc.nextInt();
        int chosenBrandId = chosenBrand.get(userChoise - 1).getId();
        //System.out.println(chosenBrandId);
        //System.out.println(chosenBrand.get(userChoise - 1).getBrand());
        printColorOfChosenShoe(chosenBrandId);
    }

    public void printColorOfChosenShoe(int chosenBrandId) {
        List<Color> colorOfChosenBrand = colorList.stream()
                .filter(color -> shoeList.stream()
                        .anyMatch(shoe -> shoe.getColorId() == color.getId() && shoe.getBrandId() == chosenBrandId))
                .toList();
        System.out.println(colorOfChosenBrand);
        for (int i = 1; i < colorOfChosenBrand.size() + 1; i++) {
            System.out.println(i + ": " + colorOfChosenBrand.get(i - 1).getColor());
        }
        int userChoise = sc.nextInt();
        int chosenColorId = colorOfChosenBrand.get(userChoise - 1).getId();
        //System.out.println(chosenColorId);
        //System.out.println(colorOfChosenBrand.get(userChoise -1).getColor());
        printSizesOfChosenColor(chosenColorId, chosenBrandId);
    }

    public void printSizesOfChosenColor(int chosenColorId, int chosenBrandId) {
        List<Size> sizesOfChosenColor = sizeList.stream()
                .filter(size -> shoeList.stream()
                        .anyMatch(shoe -> shoe.getSizeId() == size.getId() &&
                                shoe.getBrandId() == chosenBrandId &&
                                shoe.getColorId() == chosenColorId &&
                                shoe.getStock() > 0))
                .toList();
        System.out.println("size of the list is: " + sizesOfChosenColor.size());
        for (int i = 1; i < sizesOfChosenColor.size() + 1; i++) {
            System.out.println(i + ": " + sizesOfChosenColor.get(i - 1).getSize());
        }
        int userChoise = sc.nextInt();
        int chosenSizeId = sizesOfChosenColor.get(userChoise - 1).getId();
        //System.out.println(userChoise);
        //System.out.println(sizesOfChosenColor.get(userChoiseOfSize - 1).getSize());
        List<Integer> res = shoeList.stream()
                .filter(e -> e.getBrandId() == chosenBrandId && e.getColorId() == chosenColorId && e.getSizeId() == chosenSizeId).map(Shoe::getId).toList();
        productId = res.get(0);
        //System.out.println("ProdukId är " + productId);
        addToOrder();
    }

    public void addToOrder() {
        r.addToCard(currentCustomer.getId(), orderId, productId);
    }
}
    
        

