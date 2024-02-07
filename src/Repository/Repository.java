package Repository;

import TableClasses.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    Properties p = new Properties();

    public List<Customer> getCustomerData() {
        List<Customer> customerList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from Customer"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Customer temp = getCustomer(id);
                customerList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public List<OrderedItems> getOrderItemsData() {
        List<OrderedItems> orderedItemsList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, placedOrderId, shoeId from OrderedItems"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int placedOrderId = rs.getInt("placedOrderId");
                int shoeId = rs.getInt("shoeId");
                PlacedOrder tempPlacedOrder = getPlacedOrder(placedOrderId);
                Shoe tempShoe = getShoe(shoeId);
                OrderedItems temp = new OrderedItems(id, tempPlacedOrder, tempShoe);
                orderedItemsList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderedItemsList;
    }
    
    public Shoe getShoe (int inShoeId){
        Shoe temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            PreparedStatement stmt = c.prepareStatement(
                    "select id, brandId, colorId, sizeId, price, stock from Shoe where shoe.id = ?"
            )
        ) {
            stmt.setInt(1,inShoeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int brandId = rs.getInt("brandId");
                int colorId = rs.getInt("colorId");
                int sizeId = rs.getInt("sizeId");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");
                Brand tempBrand = getBrand(brandId);
                Color tempColor = getColor(colorId);
                Size tempSize = getSize(sizeId);
                temp = new Shoe(id, tempBrand, tempColor, tempSize, price, stock);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public PlacedOrder getPlacedOrder(int inPlacedOrderId){
        PlacedOrder temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement(
                     "select id, customerid from PlacedOrder where placedorder.id = ?"
             )
        ) {
            stmt.setInt(1,inPlacedOrderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                Customer tempCustomer = getCustomer(customerId);
                temp = new PlacedOrder(id, tempCustomer);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public List<PlacedOrder> getPlacedOrderData() {
        List<PlacedOrder> placedOrderList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from PlacedOrder"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                PlacedOrder temp = getPlacedOrder(id);
                placedOrderList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placedOrderList;
    }

    public List<Shoe> getShoeData() {
        List<Shoe> shoeList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from Shoe"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Shoe temp = getShoe(id);
                shoeList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoeList;
    }

    public Size getSize(int inSizeId){
        Size temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement(
                     "select id, size from Size where size.id = ?"
             )
        ) {
            stmt.setInt(1,inSizeId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int size = rs.getInt("size");
                temp = new Size(id, size);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Color getColor(int inColorId){
        Color temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement(
                     "select id, color from Color where color.id = ?"
             )
        ) {
            stmt.setInt(1,inColorId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String color = rs.getString("color");
                temp = new Color(id, color);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
    
    public Brand getBrand(int inBrandId){
        Brand temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement(
                     "select id, brand from Brand where brand.id = ?"
             )
        ) {
            stmt.setInt(1,inBrandId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String brand = rs.getString("brand");
                temp = new Brand(id, brand);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public List<Size> getSizeData() {
        List<Size> sizeList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from Size"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Size temp = getSize(id);
                sizeList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
    }

    public Customer getCustomer(int inCustomerId){
        Customer temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             
             PreparedStatement stmt = c.prepareStatement(
                     "select id, firstname, Lastname, address, postalcode, city, emailaddress, password from Customer where customer.id = ?"
             )
        ) {
            stmt.setInt(1,inCustomerId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String address = rs.getString("address");
                int postalCode = rs.getInt("postalcode");
                String city = rs.getString("city");
                String emailaddress = rs.getString("emailaddress");
                String password = rs.getString("password");
                temp = new Customer(id, firstName, lastName, address, postalCode, city, emailaddress, password);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public List<TelephoneNumber> getTelephoneNumberData() {
        List<TelephoneNumber> telephoneNumberList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, customerId, telephoneNumber from TelephoneNumber"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                String telephoneNumber = rs.getString("telephoneNumber");
                Customer tempCustomer = getCustomer(customerId);
                TelephoneNumber temp = new TelephoneNumber(id, tempCustomer, telephoneNumber);
                telephoneNumberList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneNumberList;
    }

    public List<Brand> getBrandData() {
        List<Brand> brandList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from Brand"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Brand temp = getBrand(id);
                brandList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public List<Category> getCategoryData() {
        List<Category> categoryList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, brandId, categoryNameId from Category"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int brandId = rs.getInt("brandId");
                int categoryNameId = rs.getInt("categoryNameId");
                Brand tempBrand = getBrand(brandId);
                CategoryName tempCategoryName = getCategoryName(categoryNameId);
                Category temp = new Category(id, tempBrand, tempCategoryName);
                categoryList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    
    public CategoryName getCategoryName(int inCategoryNameId){
        CategoryName temp = null;
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement(
                     "select id, categoryName from CategoryName where categoryname.id = ?"
             )
        ){
            stmt.setInt(1,inCategoryNameId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String categoryName = rs.getString("categoryName");
                temp = new CategoryName(id,categoryName);
                return temp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return temp;
    }

    public List<CategoryName> getCategoryNameData() {
        List<CategoryName> categoryNameList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from CategoryName"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                CategoryName temp = getCategoryName(id);
                categoryNameList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryNameList;
    }

    public List<Color> getColorData() {
        List<Color> colorList = new ArrayList<>();
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from Color"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Color temp = getColor(id);
                colorList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colorList;
    }

    public void addToCart(int customerNumber, int orderNumber, int product) {
        try (FileInputStream fileInput = new FileInputStream("src/settings.properties")) {
            p.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = c.prepareCall(
                     "call addToCart(?,?,?)"
             )
        ) {
            stmt.setInt(1, customerNumber);
            stmt.setInt(2, orderNumber);
            stmt.setInt(3, product);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}