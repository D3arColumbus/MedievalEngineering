package d3arcolumbus.medEngineering.block;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.charcoalFurnace.BlockCharcoalFurnace;
import d3arcolumbus.medEngineering.charcoalFurnace.BlockHardenedClayVent;
import d3arcolumbus.medEngineering.steelfurnace.BlockSteelFurnace;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":firstblock")
    public static FirstBlock firstBlock;

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":blocksteelfurnace")
    public static BlockSteelFurnace blockSteelFurnace;

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":blockhardenedclay")
    public static BlockHardenedClay blockHardenedClay;

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":blockcharcoalfurnace")
    public static BlockCharcoalFurnace blockCharcoalFurnace;

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":blockhardenedclayvent")
    public static BlockHardenedClayVent blockHardenedClayVent;


    @SideOnly(Side.CLIENT)
    public static void initModels() {
        firstBlock.initModel();
        blockSteelFurnace.initModel();
        blockHardenedClay.initModel();
        blockCharcoalFurnace.initModel();
        blockHardenedClayVent.initModel();


    }
}
