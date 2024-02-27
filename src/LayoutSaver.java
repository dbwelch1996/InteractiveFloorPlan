import java.io.*;
import java.util.List;

public class LayoutSaver {

    public void saveLayout(List<Furniture> furnitureList, Floor floor, String filePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // Write the furniture list and floor object to the output stream
            outputStream.writeObject(furnitureList);
            outputStream.writeObject(floor);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions or display error message
        }
    }

    public List<Furniture> loadFurniture(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            // Read the furniture list from the input stream
            return (List<Furniture>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions or display error message
            return null;
        }
    }

    public Floor loadFloor(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            // Read the floor object from the input stream
            return (Floor) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions or display error message
            return null;
        }
    }
}
