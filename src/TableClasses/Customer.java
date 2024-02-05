package TableClasses;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private int postalCode;
    private String city;
    private String emailAddress;
    private String password;
    private final String setTextYellow = "\u001B[33m";
    private final String turnOffTextYellow = "\u001B[0m";

    public Customer() {

    }

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

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
