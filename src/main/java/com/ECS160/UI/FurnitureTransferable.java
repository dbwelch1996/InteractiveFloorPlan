
package com.ECS160.UI;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class FurnitureTransferable implements Transferable {
    private Furniture furniture;
    public static final DataFlavor FURNITURE_FLAVOR = new DataFlavor(Furniture.class, "Furniture");

    public FurnitureTransferable(Furniture furniture) {
        this.furniture = furniture;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { FURNITURE_FLAVOR };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return FURNITURE_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return furniture;
    }
}
