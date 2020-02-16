package d3arcolumbus.medEngineering.item;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {

    public ItemBase(String registryName, String unlocalizedName) {
        setRegistryName(registryName);        // The unique name (within your mod) that identifies this item
        setUnlocalizedName(MedEngineering.MODID + "." + unlocalizedName);     // Used for localization (en_US.lang)
        setCreativeTab(MedEngineering.medEngineeringTab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
