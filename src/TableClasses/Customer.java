package TableClasses;

public class Customer {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int postalCode;
    private final String city;
    private final String emailAddress;
    private final String password;
    private final String setTextYellow = "\u001B[33m";
    private final String turnOffTextYellow = "\u001B[0m";
    
    public Customer(int id, String firstName, String lastName, String address, int postalCode, String city, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return setTextYellow + "Customer: " + setTextYellow +
                "Id: " + turnOffTextYellow + id + setTextYellow +
                " FirstName: " + turnOffTextYellow + firstName + setTextYellow +
                " LastName: " + turnOffTextYellow + lastName + setTextYellow +
                " Address: " + turnOffTextYellow + address + setTextYellow +
                " PostalCode: " + turnOffTextYellow + postalCode + setTextYellow +
                " City: " + turnOffTextYellow + city + setTextYellow +
                " EmailAdress: " + turnOffTextYellow + emailAddress + setTextYellow +
                " Password: " + turnOffTextYellow + password;
    }
}
