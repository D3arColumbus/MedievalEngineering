package d3arcolumbus.medEngineering.util;

import net.minecraft.tileentity.TileEntity;

public class TileEntityMultiblock extends TileEntity {
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
