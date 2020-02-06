package d3arcolumbus.medEngineering.item;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {

    @GameRegistry.ObjectHolder("medengineering:firstitem")
    public static FirstItem firstitem;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        MedEngineering.logger.info(firstitem.toString());
        firstitem.initModel();
    }
}
