package d3arcolumbus.medEngineering.proxy;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.FirstBlock;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.block.SteelFurnace;
import d3arcolumbus.medEngineering.item.FirstItem;
import d3arcolumbus.medEngineering.superchest.BlockSuperchest;
import d3arcolumbus.medEngineering.superchest.BlockSuperchestPart;
import d3arcolumbus.medEngineering.superchest.TileSuperchest;
import d3arcolumbus.medEngineering.superchest.TileSuperchestPart;
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
        event.getRegistry().register(new BlockSuperchest());
        event.getRegistry().register(new BlockSuperchestPart());
        //TileEntity
        GameRegistry.registerTileEntity(TileSteelFurnace.class, MedEngineering.MODID + "tileSteelFurnace");
        GameRegistry.registerTileEntity(TileSuperchest.class, MedEngineering.MODID + "_superchest");
        GameRegistry.registerTileEntity(TileSuperchestPart.class, MedEngineering.MODID + "_superchestpart");
        //event.getRegistry().register(new DataBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //ItemBlock
        event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.steelfurnace).setRegistryName(ModBlocks.steelfurnace.getRegistryName()));
        //event.getRegistry().register(new ItemBlock(ModBlocks.dataBlock).setRegistryName(ModBlocks.dataBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockSuperchest).setRegistryName(ModBlocks.blockSuperchest.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockSuperchestPart).setRegistryName(ModBlocks.blockSuperchestPart.getRegistryName()));
        //Item
        event.getRegistry().register(new FirstItem());
    }
}
