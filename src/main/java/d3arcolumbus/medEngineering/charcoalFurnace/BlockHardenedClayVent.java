package d3arcolumbus.medEngineering.charcoalFurnace;

import d3arcolumbus.medEngineering.block.BlockBase;
import net.minecraft.block.material.Material;

public class BlockHardenedClayVent extends BlockBase {
    private static String name = "blockhardenedclayvent";
    public BlockHardenedClayVent() {
        super(Material.CLAY, name, name);
    }
}
