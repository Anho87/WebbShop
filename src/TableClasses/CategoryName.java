package TableClasses;

public class CategoryName {
    private final int id;
    private final String categoryName;
    
    public CategoryName(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
    public int getId() {
        return id;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    @Override
    public String toString() {
        return "CategoryName: " +
                "Id: " + id +
                " CategoryName: " + categoryName;
    }
}
