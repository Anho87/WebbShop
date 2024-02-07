package TableClasses;

public class Size {
    private final int id;
    private final int size;
    
    public Size(int id, int size) {
        this.id = id;
        this.size = size;
    }
    public int getId() {
        return id;
    }
    
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Size: " +
                "Id: " + id +
                " Size: " + size;
    }
}
