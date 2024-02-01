package TableClasses;

public class Shoe {
    private int id;
    private int brandId;
    private int colorId;
    private int sizeId;
    private int price;
    private int stock;
    
    public Shoe(){
        
    }
    public Shoe(int id, int brandId, int colorId, int sizeId, int price, int stock) {
        this.id = id;
        this.brandId = brandId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
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
}
