package TableClasses;

public class Category {
    private final int id;
    private final Brand brand;
    private final CategoryName categoryName;
    
    public Category(int id, Brand brand, CategoryName categoryName) {
        this.id = id;
        this.brand = brand;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }
    public Brand getBrand() {
        return brand;
    }
    public CategoryName getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "Category: " +
                "Id: " + id +
                " BrandId: " + brand.getBrand() +
                " CategoryNameId: " + categoryName.getCategoryName();
    }
}
