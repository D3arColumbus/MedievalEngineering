package d3arcolumbus.medEngineering.block;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block {

    public BlockBase(Material materialIn, String registryName, String unlocalizedName) {
        super(materialIn);
        setCreativeTab(MedEngineering.medEngineeringTab);
        setHardness(2F);
        setUnlocalizedName(MedEngineering.MODID + unlocalizedName);
        setRegistryName(registryName);

    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
