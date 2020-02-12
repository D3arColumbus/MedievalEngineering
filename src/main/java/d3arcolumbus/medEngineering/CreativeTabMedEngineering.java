package d3arcolumbus.medEngineering;

import d3arcolumbus.medEngineering.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabMedEngineering extends CreativeTabs {
    public CreativeTabMedEngineering() {
        super("Medieval Engineering");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.firstBlock));
    }
}
