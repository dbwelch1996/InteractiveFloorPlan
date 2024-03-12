package com.ECS160.UI;

import javax.swing.*;
import java.awt.datatransfer.Transferable;

public class FurnitureTransferHandler extends TransferHandler {
    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JLabel && c.getClientProperty("furniture") instanceof Furniture) {
            Furniture furniture = (Furniture) c.getClientProperty("furniture");
            return new FurnitureTransferable(furniture);
        }
        return null;
    }
}
