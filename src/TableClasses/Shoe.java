package TableClasses;

public class Shoe {
    private final int id;
    private final Brand brand;
    private final Color color;
    private final Size size;
    private final int price;
    private final int stock;
    
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
    
    public Brand getBrand() {
        return brand;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
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
