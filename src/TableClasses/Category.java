package TableClasses;

public class Category {
    private int id;
    private int brandId;
    private int categoryNameId;

    public Category(){
        
    }
    public Category(int id, int brandId, int categoryNameId) {
        this.id = id;
        this.brandId = brandId;
        this.categoryNameId = categoryNameId;
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

    public int getCategoryNameId() {
        return categoryNameId;
    }

    public void setCategoryNameId(int categoryNameId) {
        this.categoryNameId = categoryNameId;
    }

    @Override
    public String toString() {
        return "Category: " +
                "Id: " + id +
                " BrandId: " + brandId +
                " CategoryNameId: " + categoryNameId;
    }
}
