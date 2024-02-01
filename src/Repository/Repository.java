package Repository;

import TableClasses.Customer;

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
                System.out.println(firstName + " " + lastName + " " + categoryname + " " + brand + " " + size + " " + color);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
