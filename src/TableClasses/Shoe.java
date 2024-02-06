package TableClasses;

public class Shoe {
    private int id;
    private Brand brand;
    private Color color;
    private Size size;
    private int price;
    private int stock;
    
    public Shoe(){
        
    }

    public Shoe(int id, Brand brand, Color colo, Size size, int price, int stock) {
        this.id = id;
        this.brand = brand;
        this.color = colo;
        this.size = size;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Shoe: " +
                "Id: " + id +
                " BrandId: " + brand.getBrand() +
                " ColorId: " + color.getColor() +
                " SizeId: " + size.getSize() +
                " Price: " + price +
                " Stock: " + stock;
    }
}
