package d3arcolumbus.medEngineering.item;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":firstitem")
    public static FirstItem firstitem;
    @GameRegistry.ObjectHolder(MedEngineering.MODID + ":itemhammer")
    public static ItemHammer itemHammer;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        firstitem.initModel();
        itemHammer.initModel();
    }
}
