import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javax.swing.ImageIcon;

public class TransferableFurniture implements Transferable {
    public static final DataFlavor FURNITURE_FLAVOR = new DataFlavor(Furniture.class, "Furniture");
    private ImageIcon icon;
    private Furniture furniture;

    public TransferableFurniture(ImageIcon icon, Furniture furniture) {
        this.icon = icon;
        this.furniture = furniture;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{FURNITURE_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(FURNITURE_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        if (isDataFlavorSupported(flavor)) {
            return furniture;
        } else {
            return null;
        }
    }
}
