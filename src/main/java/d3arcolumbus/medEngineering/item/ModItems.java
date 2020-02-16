package d3arcolumbus.medEngineering.item;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {


    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":itemhammer")
    public static ItemHammer itemHammer;
    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":itemsteelingot")
    public static ItemSteelIngot itemSteelIngot;

    @SideOnly(Side.CLIENT)
    public static void initModels() {

        itemHammer.initModel();
        itemSteelIngot.initModel();
    }
}
