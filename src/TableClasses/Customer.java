package TableClasses;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String adress;
    private int postalCode;
    private String city;
    private String emailAdress;
    private String password;
    
    private final String contactFile = "src/contacts.txt";
    private final String setTextYellow = "\u001B[33m";
    private final String turnOffTextYellow = "\u001B[0m";

    public Customer() {

    }

    public Customer(int id, String firstName, String lastName, String adress, int postalCode, String city, String emailAdress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
        this.emailAdress = emailAdress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
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
                " Address: " + turnOffTextYellow + adress + setTextYellow +
                " PostalCode: " + turnOffTextYellow + postalCode + setTextYellow +
                " City: " + turnOffTextYellow + city + setTextYellow +
                " EmailAdress: " + turnOffTextYellow + emailAdress + setTextYellow +
                " Password: " + turnOffTextYellow + password;
    }
}
