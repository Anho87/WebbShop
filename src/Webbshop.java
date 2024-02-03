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
        String userName;
        String password;
        System.out.println("Välkommen!");
        System.out.print("Skriv in din mailadress: ");
        userName = sc.nextLine().trim();
        System.out.print("Skriv in ditt lösenord: ");
        password = sc.nextLine().trim();
        for (Customer c : customerList) {
            if (c.getEmailAddress().equalsIgnoreCase(userName) && c.getPassword().equalsIgnoreCase(password)) {
                currentCustomer = c;
                System.out.println("Välkommen " + currentCustomer.getFirstName());
                orders();
            }
        }
    }

    public void orders() {
        System.out.println("Vill du lägga till produkter till en befintlig order eller skapa en ny?");
        System.out.println("1. Skapa en ny order.\n2. Lägg till i en befintlig order.\n3. Se gamla ordrar.\n4. Logga ut.");
        int a = sc.nextInt();
        switch (a){
            case 1:
                orderId = 0;
                showCategories();
                break;
            case 2:
                System.out.println("Välj en befintlig order:");
                placedOrderList.stream().filter(e -> e.getCustomerId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
                System.out.println("Tryck 'b' för att gå tillbaka.");
                String userChoise = sc.next();
                if(userChoise.equalsIgnoreCase("b")){
                    orders();
                }else{
                    orderId = Integer.parseInt(userChoise);
                    showCategories();   
                }
                break;
            case 3:
                oldOrders();
                break;
            case 4:
                System.out.println("Du loggades ut!");
                System.exit(0);
                break;
        }
    }
    
    public void oldOrders(){
        System.out.println("Välj en befintlig order:");
        placedOrderList.stream().filter(e -> e.getCustomerId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
        orderId = sc.nextInt();
        List<String> tempList = r.orderInfo(orderId);
        tempList.forEach(e -> System.out.println(e));
        String back;
        System.out.println("Tryck 'b' för att gå tillbaka.");
        while(true){
            back = sc.nextLine();
            if(back.equalsIgnoreCase("b")){
                orders();
            }
        }
    }

    public void showCategories() {
        System.out.println("Välj en kategori:");
        for (int i = 1; i < categoryNameList.size() + 1; i++) {
            System.out.println(i + ": " + categoryNameList.get(i - 1).getCategoryName());
        }
        System.out.println("Tryck 'b' för att gå tillbaka.");
        String chosenCategory = sc.next();
        if(chosenCategory.equalsIgnoreCase("b")){
            orders();
        }else{
            try{
                int chosenCategoryId = categoryNameList.get(Integer.parseInt(chosenCategory) - 1).getId();
                printShoesOfChosenCategory(chosenCategoryId);
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void printShoesOfChosenCategory(int chosenCategoryId) {
        System.out.println("Välj en sko:");
        List<Brand> chosenBrand = brandList.stream()
                .filter(brand -> categoryList.stream()
                        .anyMatch(category -> category.getBrandId() == brand.getId() && category.getCategoryNameId() == chosenCategoryId))
                .toList();
        for (int i = 1; i < chosenBrand.size() + 1; i++) {
            System.out.println(i + ": " + chosenBrand.get(i - 1).getBrand());
        }
        System.out.println("Tryck 'b' för att gå tillbaka.");
        String userChoise = sc.next();
        if(userChoise.equalsIgnoreCase("b")){
            showCategories();
        }else{
            try{
                int chosenBrandId = chosenBrand.get(Integer.parseInt(userChoise) - 1).getId();
                printColorOfChosenShoe(chosenBrandId,chosenCategoryId);
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void printColorOfChosenShoe(int chosenBrandId, int chosenCategoryId) {
        System.out.println("Välj en färg:");
        List<Color> colorOfChosenBrand = colorList.stream()
                .filter(color -> shoeList.stream()
                        .anyMatch(shoe -> shoe.getColorId() == color.getId() && shoe.getBrandId() == chosenBrandId))
                .toList();
        for (int i = 1; i < colorOfChosenBrand.size() + 1; i++) {
            System.out.println(i + ": " + colorOfChosenBrand.get(i - 1).getColor());
        }
        System.out.println("Tryck 'b' för att gå tillbaka.");
        String userChoise = sc.next();
        if(userChoise.equalsIgnoreCase("b")){
            printShoesOfChosenCategory(chosenCategoryId);
        }else{
            try{
                int chosenColorId = colorOfChosenBrand.get(Integer.parseInt(userChoise) - 1).getId();
                printSizesOfChosenColor(chosenColorId, chosenBrandId, chosenCategoryId);
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void printSizesOfChosenColor(int chosenColorId, int chosenBrandId,int chosenCategoryId) {
        System.out.println("Välj en storlek:");
        List<Size> sizesOfChosenColor = sizeList.stream()
                .filter(size -> shoeList.stream()
                        .anyMatch(shoe -> shoe.getSizeId() == size.getId() &&
                                shoe.getBrandId() == chosenBrandId &&
                                shoe.getColorId() == chosenColorId &&
                                shoe.getStock() > 0))
                .toList();
        for (int i = 1; i < sizesOfChosenColor.size() + 1; i++) {
            System.out.println(i + ": " + sizesOfChosenColor.get(i - 1).getSize());
        }
        System.out.println("Tryck 'b' för att gå tillbaka.");
        String userChoise = sc.next();
        if(userChoise.equalsIgnoreCase("b")){
            printColorOfChosenShoe(chosenBrandId,chosenCategoryId);
        }else{
            try{
                int chosenSizeId = sizesOfChosenColor.get(Integer.parseInt(userChoise) - 1).getId();
                List<Integer> res = shoeList.stream()
                        .filter(e -> e.getBrandId() == chosenBrandId && e.getColorId() == chosenColorId && e.getSizeId() == chosenSizeId).map(Shoe::getId).toList();
                productId = res.get(0);
                addToOrder();
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }
    
    public void addToOrder(){
        r.addToCard(currentCustomer.getId(),orderId,productId);
        String shoeInfo = r.shoeInfo(productId);
        if(orderId == 0){
            System.out.println(shoeInfo + " lades till i en ny order!");   
        }else{
            System.out.println(shoeInfo + " lades till i ordernummer: " + orderId);
        }
        getAllData();
        showCategories();
    }
}
    
        

