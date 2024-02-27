import java.util.ArrayList;
import java.util.List;

public class FurnitureManager {
    private List<Furniture> furnitureList;

    public FurnitureManager() {
        furnitureList = new ArrayList<>();
        // Load furniture icons here
    }

    public void addFurniture(Furniture furniture) {
        furnitureList.add(furniture);
    }

    public void removeFurniture(Furniture furniture) {
        furnitureList.remove(furniture);
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }
}
