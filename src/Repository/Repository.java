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
                     "select id, firstname, Lastname, adress, postalcode, city, emailadress, password from Customer"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String adress = rs.getString("adress");
                int postalCode =  rs.getInt("postalcode");
                String city = rs.getString("city");
                String emailadress = rs.getString("emailadress");
                String password = rs.getString("password");
                Customer temp = new Customer(id,firstName,lastName,adress,postalCode,city,emailadress,password);
                customerList.add(temp);
                System.out.println(firstName + " " + lastName + " " );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
        
        


    public void printAllShoes() {
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
                     "select customer.firstname, customer.Lastname, categoryname.categoryname, brand.brand, size.size, color.color from customer\n" +
                             "inner join placedorder on placedorder.customerid = customer.id\n" +
                             "inner join ordereditems on ordereditems.PlacedOrderid = placedorder.id\n" +
                             "inner join shoe on shoe.id = ordereditems.shoeid\n" +
                             "inner join size on shoe.sizeid = size.id\n" +
                             "inner join color on color.id = shoe.colorid\n" +
                             "inner join brand on shoe.brandid = brand.id\n" +
                             "inner join category on category.brandid = brand.id\n" +
                             "inner join categoryname on categoryname.id = category.CategoryNameID\n" +
                             "where categoryname.categoryname = 'sandaler'  and brand.brand like '%ecco%' and size.size = 38 and color.color = 'black';"
             )
        ) {
            while (rs.next()) {
                String firstName = rs.getString("customer.firstname");
                String lastName = rs.getString("customer.lastname");
                String categoryname = rs.getString("categoryname.categoryname");
                String brand = rs.getString("brand.brand");
                int size = rs.getInt("size.size");
                String color = rs.getString("color.color");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                OrderedItems temp = new OrderedItems(id,placedOrderId, shoeId);
                orderedItemsList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderedItemsList;
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
                     "select id, customerid from PlacedOrder"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                PlacedOrder temp = new PlacedOrder(id, customerId);
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
                     "select id, brandId, colorId, sizeId, price, stock from Shoe"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int brandId = rs.getInt("brandId");
                int colorId = rs.getInt("colorId");
                int sizeId = rs.getInt("sizeId");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");
                Shoe temp = new Shoe(id, brandId, colorId, sizeId, price, stock);
                shoeList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoeList;
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
                     "select id, size from Size"
             )
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int size = rs.getInt("size");
                Size temp = new Size(id, size);
                sizeList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
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
                TelephoneNumber temp = new TelephoneNumber(id, customerId, telephoneNumber);
                telephoneNumberList.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return telephoneNumberList;
    }
    
}
