import TableClasses.Shoe;

@FunctionalInterface
public interface ShoeSearchInfo {
    boolean search(Shoe shoe, String searchWord);
}
