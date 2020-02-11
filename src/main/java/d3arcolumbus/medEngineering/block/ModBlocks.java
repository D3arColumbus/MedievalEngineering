package d3arcolumbus.medEngineering.block;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":firstblock")
    public static FirstBlock firstBlock;
    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":steelfurnace")
    public static SteelFurnace steelfurnace;
    //@GameRegistry.ObjectHolder(MedEngineering.MODID + ":datablock")
    //public static DataBlock dataBlock;

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":superchest")
    public static FirstBlock blockSuperchest;
    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":superchest_part")
    public static SteelFurnace blockSuperchestPart;


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        firstBlock.initModel();
        //dataBlock.initModel();
        steelfurnace.initModel();
        blockSuperchest.initModel();
        blockSuperchestPart.initModel();

    }
}
