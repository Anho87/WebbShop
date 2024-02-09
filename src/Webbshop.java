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
    
    ShoeSearchInfo colorSearch = (shoe, search) -> shoe.getColor().getColor().equalsIgnoreCase(search);
    ShoeSearchInfo brandSearch = (shoe, search) -> shoe.getBrand().getBrand().toLowerCase().contains(search);
            
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
                options();
            }
        }
    }

    public void options() {
        System.out.println("Vill du lägga till produkter till en befintlig order eller skapa en ny?");
        System.out.println("1. Skapa en ny order.\n2. Lägg till i en befintlig order.\n3. Se gamla ordrar.\n4. Titta på utbudet.\n5. Logga ut.");
        int a = sc.nextInt();
        sc.nextLine();
        switch (a){
            case 1:
                orderId = 0;
                showCategories();
                break;
            case 2:
                System.out.println("Välj en befintlig order:");
                placedOrderList.stream().filter(e -> e.getCustomer().getId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
                System.out.println("Tryck 'b' för att gå tillbaka.");
                String userChoise = sc.next();
                if(userChoise.equalsIgnoreCase("b")){
                    options();
                }else{
                    orderId = Integer.parseInt(userChoise);
                    showCategories();   
                }
                break;
            case 3:
                viewOldOrders();
                break;
            case 4:
                viewShoes();
                break;
            case 5:
                System.out.println("Du loggades ut!");
                System.exit(0);
                break;
        }
    }
    
    public void viewShoes(){
        while (true){
            System.out.println("Vill du söka på färg eller märke,(skriv b för att gå tillbaka)?");
            String attribute = sc.nextLine();
            if(attribute.equalsIgnoreCase("b")){
                options();
            }else {
                System.out.println("Vilket ord vill du söka på?");
                String searchWord = sc.nextLine();
                if (attribute.equalsIgnoreCase("färg")) {
                    searchShoe(searchWord, colorSearch);
                } else if (attribute.equalsIgnoreCase("märke")) {
                    searchShoe(searchWord, brandSearch);
                }
            }
        }
    }
    
    public void viewOldOrders(){
        System.out.println("Välj en befintlig order:");
        placedOrderList.stream().filter(e -> e.getCustomer().getId() == currentCustomer.getId()).forEach(e -> System.out.println("OrderNr: " + e.getId()));
        orderId = sc.nextInt();
        for (Shoe shoe : shoeList) {
            for (OrderedItems orderedItem : orderedItemsList) {
                if (orderedItem.getShoe().getId() == shoe.getId() && orderedItem.getPlacedOrder().getId() == orderId) {
                    printShoeInfo(shoe.getId());
                    System.out.println();
                }
            }
        }
        String back;
        System.out.println("Tryck 'b' för att gå tillbaka.");
        while(true){
            back = sc.nextLine();
            if(back.equalsIgnoreCase("b")){
                options();
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
            options();
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
                        .anyMatch(category -> category.getBrand().getId() == brand.getId() && category.getCategoryName().getId() == chosenCategoryId))
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
                        .anyMatch(shoe -> shoe.getColor().getId() == color.getId() && shoe.getBrand().getId() == chosenBrandId))
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
                        .anyMatch(shoe -> shoe.getSize().getId() == size.getId() &&
                                shoe.getBrand().getId() == chosenBrandId &&
                                shoe.getColor().getId() == chosenColorId &&
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
                        .filter(e -> e.getBrand().getId() == chosenBrandId && e.getColor().getId() == chosenColorId && e.getSize().getId() == chosenSizeId).map(Shoe::getId).toList();
                productId = res.get(0);
                addToOrder();
            }catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }
    
    public void addToOrder(){
        r.addToCart(currentCustomer.getId(),orderId,productId);
        printShoeInfo(productId);
        System.out.print(" lades till i din order!");
        System.out.println();
        getAllData();
        if (orderId == 0){
            List<PlacedOrder> orderList = placedOrderList.stream()
                    .filter(e -> e.getCustomer().getId() == currentCustomer.getId()).toList();
            orderId = orderList.get(orderList.size()-1).getId();
            showCategories();  
        }else {
            showCategories();
        }
        
    }
    
    public void printShoeInfo(int shoeId){
        shoeList.stream().filter(shoe -> shoe.getId() == shoeId)
                .forEach(shoe -> 
                        System.out.print(shoe.getBrand().getBrand() + " " + shoe.getColor().getColor() + " " + shoe.getSize().getSize() + " " + shoe.getPrice() + "kr"));
    }
    
    public void searchShoe(String wordToSearchFor, ShoeSearchInfo ssi){
        shoeList.stream().filter(shoe -> ssi.search(shoe,wordToSearchFor) && shoe.getStock() > 0)
                .forEach(shoe -> System.out.println(shoe.getBrand().getBrand() + " " + shoe.getColor().getColor() + " " + shoe.getSize().getSize() + " " + shoe.getPrice() + "kr saldo:" + shoe.getStock()));
    }
}
    
        

