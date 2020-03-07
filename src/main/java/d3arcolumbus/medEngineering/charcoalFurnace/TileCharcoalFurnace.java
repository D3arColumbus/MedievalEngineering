package d3arcolumbus.medEngineering.charcoalFurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.util.MultiBlock;
import d3arcolumbus.medEngineering.util.TileEntityMultiblock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileCharcoalFurnace extends TileEntityMultiblock implements ITickable {

    private boolean formed = false;
    private boolean lit = false;
    private int progress = 0;
    private int size = 0;
    private int height = 0;
    private BlockPos posWood;
    private static final int MAX_PROGRESS = 40;

    public BlockPos getPosWood() {
        return posWood;
    }

    public void setPosWood(BlockPos posWood) {
        this.posWood = posWood;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if(height >= 0)
        this.height = height;
    }

    public void setFormed(boolean formed) {
        this.formed = formed;
    }



    public boolean isLit() {
        return lit;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if(progress >= 0)
        this.progress = progress;
        else
            MedEngineering.logger.warn("Charcoal Furnace: Invalid Progress");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size >= 0)
        this.size = size;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    @Override
    public void update() {
        if(!world.isRemote){
            if(isLit()){
                if(validFire()){
                    if(getProgress() < MAX_PROGRESS)
                        progress += 1;
                    else
                        finishBurn();
                }else{
                    progress = 0;
                    setLit(false);
                }
            }
        }

    }

    private void finishBurn() {

    }


    public void liteFire(){
        if(!isLit()){
            if(validFire()){
                setLit(true);
                setProgress(0);
            }
        }
    }



    private boolean validFire() {
        for(int i = getHeight() - 2; i > 0; i --){
            if(!(MultiBlock.checkFullLayer(world, getPosWood(), getSize() - 2, getSize() - 2, ModBlocks.blockHardenedClay))){
                return false;
            }
        }
        return true;
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        progress = compound.getInteger("progress");
        size = compound.getInteger("size");
        formed = compound.getBoolean("formed");
        lit = compound.getBoolean("lit");
        height = compound.getInteger("height");
        posWood = new BlockPos(0,0,0);
        posWood.add(compound.getInteger("posWoodX"),compound.getInteger("posWoodY"),compound.getInteger("posWoodZ"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("progress", progress);
        compound.setInteger("size", size);
        compound.setInteger("height", height);
        compound.setBoolean("formed", formed);
        compound.setBoolean("lit", lit);
        compound.setInteger("posWoodX", posWood.getX());
        compound.setInteger("posWoodY", posWood.getY());
        compound.setInteger("posWoodZ", posWood.getZ());
        return compound;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return super.hasCapability(capability, facing);
    }

    public boolean isFormed() {
        return formed;
    }
}
