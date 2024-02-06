import Repository.Repository;
import TableClasses.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SalesApplication {
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
    private final String setTextYellow = "\u001B[33m";
    private final String turnOffTextYellow = "\u001B[0m";
    private Repository r = new Repository();

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

    public SalesApplication() {
        getAllData();
        options();
    }
    public void options(){
        System.out.println(setTextYellow + "Vilken rapport vill du se?" + turnOffTextYellow);
        System.out.println("1. Specifika produkter\n2. Antal ordrar/kund\n3. Totala Beställningsvärdet/kund" +
                "\n4. Top 5 mest sålda produkterna\n5 Avsluta");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Skriv in färg: ");
                String color = sc.next().trim();
                System.out.println("skriv in storlek");
                int size = sc.nextInt();
                System.out.println("Skriv in märke");
                String brand = sc.next().toLowerCase();
                //sales(color, size, brand);
                break;

            case 2:
                ordersPerCustomer();
                break;

            case 3:
                totalSalesamountPerCustomer();
                break;

            case 4:
                //topFiveMostSoldShoes();
                break;

            case 5:
                System.exit(0);
        }
    }

    /*public void sales(String inColor, int inSize, String inBrand) {
        List<Color> colorIdList = colorList.stream().filter(color -> color.getColor().equalsIgnoreCase(inColor)).toList();
        int colorId = colorIdList.get(0).getId();
        List<Size> sizeIdList = sizeList.stream().filter(size -> size.getSize() == inSize).toList();
        int sizeId = sizeIdList.get(0).getId();
        List<Brand> brandIdList = brandList.stream().filter(brand -> brand.getBrand().toLowerCase().contains(inBrand)).toList();
        int brandId = brandIdList.get(0).getId();
        List<Customer> filteredCustomers = customerList.stream()
                .filter(customer -> placedOrderList.stream()
                        .anyMatch(placedOrder -> orderedItemsList.stream()
                                .anyMatch(orderedItems -> shoeList.stream()
                                        .anyMatch(shoe -> shoe.getColorId() == colorId
                                                && shoe.getSizeId() == sizeId
                                                && shoe.getBrandId() == brandId
                                                && shoe.getId() == orderedItems.getShoeId()
                                                && orderedItems.getPlacedOrderId() == placedOrder.getId()
                                                && placedOrder.getCustomerId() == customer.getId()))))
                .toList();

        if (filteredCustomers.isEmpty()) {
            System.out.println("Ingen match hittades");
            options();
        } else {
            System.out.println(setTextYellow + "Rapport: Specifika produkter" + turnOffTextYellow);
            filteredCustomers.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()
                    + " " + e.getAddress() + " " + e.getPostalCode() + " " + e.getCity()));
            options();
        }
    }*/

    public void totalSalesamountPerCustomer() {
        Map<Integer, Integer> customerTotal = new HashMap<>();

        for (PlacedOrder placedOrder : placedOrderList) {
            for (OrderedItems orderedItems : orderedItemsList) {
                if (orderedItems.getPlacedOrder().getId() == placedOrder.getId()) {
                    Shoe shoe = shoeList.stream()
                            .filter(s -> s.getId() == orderedItems.getShoe().getId())
                            .findFirst()
                            .orElse(null);

                    if (shoe != null) {
                        Customer customer = customerList.stream()
                                .filter(c -> c.getId() == placedOrder.getCustomer().getId())
                                .findFirst()
                                .orElse(null);

                        if (customer != null) {
                            int orderTotal = customerTotal.getOrDefault(customer.getId(), 0);
                            orderTotal += shoe.getPrice();
                            customerTotal.put(customer.getId(), orderTotal);
                        }
                    }
                }
            }
        }

        System.out.println(setTextYellow + "Kundrapport: Totala beställningsvärde" + turnOffTextYellow);
        customerTotal.forEach((customerId, totalAmount) -> customerList.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst().ifPresent(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()
                        + ": Total summa - " + totalAmount)));
        options();

    }

    public void ordersPerCustomer() {
        Map<Integer, Integer> ordersCount = new HashMap<>();

        for (PlacedOrder placedOrder : placedOrderList) {
            int customerId = placedOrder.getCustomer().getId();
            int ordersAmount = ordersCount.getOrDefault(customerId, 0);
            ordersAmount++;
            ordersCount.put(customerId, ordersAmount);
        }

        System.out.println(setTextYellow + "Kundrapport: Antal ordrar" + turnOffTextYellow);
        ordersCount.forEach((customerId, ordersAmount) -> {
            customerList.stream()
                    .filter(c -> c.getId() == customerId)
                    .findFirst().ifPresent(customer -> System.out.println(customer.getFirstName() + " " + customer.getLastName()
                            + ": Total Orders - " + ordersAmount));

        });
        options();
    }

    /*public void topFiveMostSoldShoes() {
        Map<Integer, Integer> topBrandMap = shoeList.stream()
                .filter(shoe -> orderedItemsList.stream()
                        .anyMatch(orderedItem -> orderedItem.getShoe().getId() == shoe.getId()))
                .collect(Collectors.groupingBy(Shoe::getBrandId, Collectors.summingInt(i -> 1)));

        System.out.println(setTextYellow + "Topplista över mest sålda varumärken:" + turnOffTextYellow);
        topBrandMap.entrySet().stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .limit(5)
                .forEach(e -> brandList.stream()
                        .filter(b -> b.getId() == e.getKey())
                        .findFirst().ifPresent(brand -> System.out.println(brand.getBrand() + ": " + e.getValue() + " st")));
        options();
    }*/


    public static void main(String[] args) {
        new SalesApplication();
    }
}





   
