package d3arcolumbus.medEngineering.proxy;

import d3arcolumbus.medEngineering.container.ContainerSteelFurnace;
import d3arcolumbus.medEngineering.gui.GuiSteelFurnace;
import d3arcolumbus.medEngineering.tile.DemoTileSteelFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof DemoTileSteelFurnace) {
            return new ContainerSteelFurnace(player.inventory, (DemoTileSteelFurnace) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof DemoTileSteelFurnace) {
            DemoTileSteelFurnace containerTileEntity = (DemoTileSteelFurnace) te;
            return new GuiSteelFurnace(containerTileEntity, new ContainerSteelFurnace(player.inventory, containerTileEntity));
        }
        return null;
    }
}
