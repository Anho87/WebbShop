import Repository.Repository;
import TableClasses.*;

import java.util.*;
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

    public void options() {
        System.out.println(setTextYellow + "Vilken rapport vill du se?" + turnOffTextYellow);
        System.out.println("1. Specifika produkter\n2. Antal ordrar/Kund\n3. Totala Beställningsvärdet/Kund" +
                "\n4. Totala Beställningsvärdet/Stad\n5. Antal ordrar/Sko\n6. Avsluta");
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                sales();
                break;

            case "2":
                ordersPerCustomer();
                break;

            case "3":
                totalSalesamountPerCustomer();
                break;

            case "4":
                totalSalesAmountPerCity();
                break;

            case "5":
                totalSalesAmountPerShoe();
                break;

            case "6":
                System.exit(0);
        }
    }

    public void sales() {
        System.out.println("Skriv in färg: ");
        String inColor = sc.nextLine();
        System.out.println("skriv in storlek");
        int inSize = sc.nextInt();
        sc.nextLine();
        System.out.println("Skriv in märke");
        String inBrand = sc.nextLine();

        List<Shoe> filteredShoeList = shoeList.stream()
                .filter(shoe -> shoe.getBrand().getBrand().equalsIgnoreCase(inBrand)
                        && shoe.getSize().getSize() == inSize
                        && shoe.getColor().getColor().equalsIgnoreCase(inColor))
                .toList();

        if (!filteredShoeList.isEmpty()) {
            int shoeId = filteredShoeList.get(0).getId();

            List<Customer> filteredCustomers = customerList.stream()
                    .filter(customer -> placedOrderList.stream()
                            .anyMatch(placedOrder -> orderedItemsList.stream()
                                    .anyMatch(orderedItems -> shoeList.stream()
                                            .anyMatch(shoe -> shoe.getId() == shoeId
                                                    && shoe.getId() == orderedItems.getShoe().getId()
                                                    && orderedItems.getPlacedOrder().getId() == placedOrder.getId()
                                                    && placedOrder.getCustomer().getId() == customer.getId()))))
                    .toList();
            System.out.println(filteredCustomers.size());
            if (filteredCustomers.isEmpty()) {
                System.out.println("Ingen har köpt dessa skor");
                options();
            } else {
                System.out.println(setTextYellow + "Rapport: Specifika produkter" + turnOffTextYellow);
                filteredCustomers.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()
                        + " " + e.getAddress() + " " + e.getPostalCode() + " " + e.getCity()));
                options();
            }
        } else {
            System.out.println("Inga matchande skor hittades");
            options();
        }
    }

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
                        + ": Totala summan - " + totalAmount + "kr")));
        options();

    }

    public void ordersPerCustomer() {
        Map<Customer, Long> ordersCount = new HashMap<>();

        for (Customer customer : customerList) {
            int customerId = customer.getId();
            long ordersAmount = placedOrderList.stream()
                    .filter(placedOrder -> placedOrder.getCustomer().getId() == customerId)
                    .count();
            ordersCount.put(customer, ordersAmount);
        }

        System.out.println(setTextYellow + "Kundrapport: Antal ordrar" + turnOffTextYellow);
        ordersCount.forEach((customer, ordersAmount) -> {
            System.out.println(customer.getFirstName() + " " + customer.getLastName()
                    + ": Ordrar - " + ordersAmount);

        });
        System.out.println();
        options();
    }

    public void totalSalesAmountPerCity() {
        Map<String, Integer> cityTotal = new HashMap<>();

        for (PlacedOrder placedOrder : placedOrderList) {
            for (OrderedItems orderedItems : orderedItemsList) {
                if (orderedItems.getPlacedOrder().getId() == placedOrder.getId()) {
                    Shoe shoe = shoeList.stream()
                            .filter(s -> s.getId() == orderedItems.getShoe().getId())
                            .findFirst()
                            .orElse(null);

                    if (shoe != null) {
                        Customer customer = customerList.stream()
                                .filter(c -> c.getCity().equalsIgnoreCase(placedOrder.getCustomer().getCity()))
                                .findFirst()
                                .orElse(null);

                        if (customer != null) {
                            int orderTotal = cityTotal.getOrDefault(customer.getCity(), 0);
                            orderTotal += shoe.getPrice();
                            cityTotal.put(customer.getCity(), orderTotal);
                        }
                    }
                }
            }
        }

        System.out.println(setTextYellow + "Stads rapport: Totala beställningsvärde" + turnOffTextYellow);
        cityTotal.forEach((city, totalAmount) -> customerList.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(city))
                .findFirst().ifPresent(customer -> System.out.println(customer.getCity()
                        + ": Totala summan - " + totalAmount)));
        options();
    }

    public void totalSalesAmountPerShoe() {
        Map<Brand, Long> ordersCount = new HashMap<>();

        for (Brand brand : brandList) {
            int brandId = brand.getId();
            long ordersAmount = orderedItemsList.stream()
                    .filter(orderedItems -> orderedItems.getShoe().getBrand().getId() == brandId)
                    .count();
            ordersCount.put(brand, ordersAmount);
        }

        LinkedHashMap<Brand, Long> sortedMap = ordersCount.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println(setTextYellow + "Märkesrapport: Antal ordrar" + turnOffTextYellow);
        sortedMap.forEach((brand, ordersAmount) -> {
            System.out.println(brand.getBrand() + ": " + ordersAmount + "st");

        });

        options();
    }
    
    public static void main(String[] args) {
        new SalesApplication();
    }
}





   
