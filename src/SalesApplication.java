import Repository.Repository;
import TableClasses.*;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("Skriv in färg: ");
        String color = sc.next().trim();
        System.out.println("skriv in storlek");
        int size = sc.nextInt();
        System.out.println("Skriv in märke");
        String brand = sc.next().trim();
        sales(color, size, brand);
    }

    public void sales(String inColor, int inSize, String inBrand) {
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
        filteredCustomers.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName()
                + " " + e.getAddress() + " " + e.getPostalCode() + " " + e.getCity()));
    }

    public static void main(String[] args) {
        new SalesApplication();
    }
}
