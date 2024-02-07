package TableClasses;

public class Brand {
    private final int id;
    private final String brand;

   
    public Brand(int id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

  

    public String getBrand() {
        return brand;
    }

  

    @Override
    public String toString() {
        return "Brand: " +
                "Id: " + id +
                " Brand: " + brand;
    }
}
