package d3arcolumbus.medEngineering.proxy;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.FirstBlock;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.block.SteelFurnace;
import d3arcolumbus.medEngineering.item.FirstItem;
import d3arcolumbus.medEngineering.tile.TileSteelFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(MedEngineering.instance, new GuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new FirstBlock());
        event.getRegistry().register(new SteelFurnace());
        //TileEntity
        GameRegistry.registerTileEntity(TileSteelFurnace.class, MedEngineering.MODID + "tileSteelFurnace");
        //event.getRegistry().register(new DataBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //ItemBlock
        event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.steelfurnace).setRegistryName(ModBlocks.steelfurnace.getRegistryName()));
        //event.getRegistry().register(new ItemBlock(ModBlocks.dataBlock).setRegistryName(ModBlocks.dataBlock.getRegistryName()));
        //Item
        event.getRegistry().register(new FirstItem());
    }
}
