package d3arcolumbus.medEngineering.proxy;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.BlockHardenedClay;
import d3arcolumbus.medEngineering.block.FirstBlock;
import d3arcolumbus.medEngineering.block.ModBlocks;

import d3arcolumbus.medEngineering.item.ItemHammer;
import d3arcolumbus.medEngineering.item.ItemSteelIngot;
import d3arcolumbus.medEngineering.steelfurnace.BlockSteelFurnace;
import d3arcolumbus.medEngineering.steelfurnace.TileSteelFurnace;
import d3arcolumbus.medEngineering.tile.DemoTileSteelFurnace;
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
       // event.getRegistry().register(new SteelFurnace());

        event.getRegistry().register(new BlockSteelFurnace());

        event.getRegistry().register(new BlockHardenedClay());
    }

    @SubscribeEvent
    public static void registerTile(RegistryEvent.Register<Item> event) {
        GameRegistry.registerTileEntity(DemoTileSteelFurnace.class, MedEngineering.MODID + "demoTileSteelFurnace");
        GameRegistry.registerTileEntity(TileSteelFurnace.class, MedEngineering.MODID + "tileSteelFurnace");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //ItemBlock
        event.getRegistry().register(new ItemBlock(ModBlocks.firstBlock).setRegistryName(ModBlocks.firstBlock.getRegistryName()));
       // event.getRegistry().register(new ItemBlock(ModBlocks.Demosteelfurnace).setRegistryName(ModBlocks.Demosteelfurnace.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockSteelFurnace).setRegistryName(ModBlocks.blockSteelFurnace.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockHardenedClay).setRegistryName(ModBlocks.blockHardenedClay.getRegistryName()));
        //event.getRegistry().register(new ItemBlock(ModBlocks.dataBlock).setRegistryName(ModBlocks.dataBlock.getRegistryName()));
        //Item
        event.getRegistry().register(new ItemHammer());
        event.getRegistry().register(new ItemSteelIngot());
    }
}
