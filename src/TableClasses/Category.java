package TableClasses;

public class Category {
    private int id;
    private Brand brand;
    private CategoryName categoryName;

    public Category(){
        
    }
    public Category(int id, Brand brand, CategoryName categoryName) {
        this.id = id;
        this.brand = brand;
        this.categoryName = categoryName;
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

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category: " +
                "Id: " + id +
                " BrandId: " + brand.getBrand() +
                " CategoryNameId: " + categoryName.getCategoryName();
    }
}
