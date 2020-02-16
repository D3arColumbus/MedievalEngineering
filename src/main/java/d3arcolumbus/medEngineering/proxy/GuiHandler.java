package d3arcolumbus.medEngineering.proxy;

import d3arcolumbus.medEngineering.container.DemoContainerSteelFurnace;
import d3arcolumbus.medEngineering.gui.DemoGuiSteelFurnace;
import d3arcolumbus.medEngineering.steelfurnace.ContainerSteelFurnace;
import d3arcolumbus.medEngineering.steelfurnace.GuiSteelFurnace;
import d3arcolumbus.medEngineering.steelfurnace.TileSteelFurnace;
import d3arcolumbus.medEngineering.tile.DemoTileSteelFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.awt.*;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof DemoTileSteelFurnace)
            return new DemoContainerSteelFurnace(player.inventory, (DemoTileSteelFurnace) te);
        if(te instanceof TileSteelFurnace)
            return new ContainerSteelFurnace(player.inventory, (TileSteelFurnace) te);
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof DemoTileSteelFurnace) {
            DemoTileSteelFurnace containerTileEntity = (DemoTileSteelFurnace) te;
            return new DemoGuiSteelFurnace(containerTileEntity, new DemoContainerSteelFurnace(player.inventory, containerTileEntity));
        }
        if (te instanceof TileSteelFurnace) {
            TileSteelFurnace containerTileEntity = (TileSteelFurnace) te;
            return new GuiSteelFurnace(containerTileEntity, new ContainerSteelFurnace(player.inventory, containerTileEntity));
        }

        return null;
    }
}
