package d3arcolumbus.medEngineering.steelfurnace;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.net.NoRouteToHostException;

public class TileSteelFurnace extends TileEntity {

    private boolean isFormed = false;
    private facing isFacing = facing.UNDEFINED;

    public facing getIsFacing() {
        return isFacing;
    }

    public void setIsFacing(facing isFacing) {
        this.isFacing = isFacing;
    }

    public enum facing{
        UNDEFINED,
        NORTH,  //negative z
        SOUTH,  //positive z
        EAST,   //positive x
        WEST    //negative x
    }

    public boolean isFormed() {
        return isFormed;
    }

    public void setFormed(boolean formed) {
        isFormed = formed;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);

    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return super.getCapability(capability, facing);
    }

}
