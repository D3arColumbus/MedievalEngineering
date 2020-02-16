package d3arcolumbus.medEngineering.steelfurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.item.ModItems;
import d3arcolumbus.medEngineering.tile.DemoTileSteelFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.NoRouteToHostException;

public class TileSteelFurnace extends TileEntity implements ITickable {

    private boolean isFormed = false;
    private facing isFacing = facing.UNDEFINED;

    public static final int SIZE = 3;
    public static final int INPUT_ITEM = 1;
    public static final int INPUT_FUEL = 1;
    public static final int OUTPUT_ITEM = 1;
    public static final int MAX_PROGRESS = 40;
    private int progress = 0;

    public facing getIsFacing() {
        return isFacing;
    }

    public void setIsFacing(facing isFacing) {
        this.isFacing = isFacing;
    }

    //MUST DO!!
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void update() {
        if(!world.isRemote){
            //only server-side
            if(progress > 0){
                //smelting has started
                if(hasInput()){
                    progress --;
                    markDirty();
                    if(progress == 0){
                        //smelting has finished
                        attemptSmelt();
                        markDirty();
                    }
                }else{
                    progress = 0;
                }

            }else{
                startSmelt();
                markDirty();
            }
        }
    }

    public enum facing{
        UNDEFINED,
        NORTH,  //negative z
        SOUTH,  //positive z
        EAST,   //positive x
        WEST    //negative x
    }

    private void attemptSmelt() {
        if(hasInput()){
            //.copy() is important....
            if(insertOutput(new ItemStack(ModItems.itemSteelIngot), false)){
                inputItemStackHandler.extractItem(0, 1, false);
                inputFuelStackHandler.extractItem(0,1,false);
            }
        }
    }

    private boolean hasInput(){
        return inputItemStackHandler.getStackInSlot(0).getUnlocalizedName().equals(Blocks.IRON_ORE.getUnlocalizedName()) &&
                inputFuelStackHandler.getStackInSlot(0).getUnlocalizedName().equals(Items.COAL.getUnlocalizedName());
    }

    private void startSmelt(){
        if(hasInput()){
            if(insertOutput(new ItemStack(ModItems.itemSteelIngot),true)){
                setProgress(MAX_PROGRESS);
                markDirty();
            }
        }
    }

    private boolean insertOutput(ItemStack output, boolean simulate) {
        ItemStack remaining = outputItemStackHandler.insertItem(0, output, simulate);
        if(remaining != null){
            return true;
        }
        return false;
    }

    // This item handler will hold the inventory slots
    private ItemStackHandler inputItemStackHandler = new ItemStackHandler(INPUT_ITEM) {
        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            TileSteelFurnace.this.markDirty();
        }
    };

    private ItemStackHandler inputFuelStackHandler = new ItemStackHandler(INPUT_FUEL) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            TileSteelFurnace.this.markDirty();
        }
    };

    private ItemStackHandler outputItemStackHandler = new ItemStackHandler(OUTPUT_ITEM) {
        @Override
        protected void onContentsChanged(int slot) {
            // We need to tell the tile entity that something has changed so
            // that the chest contents is persisted
            TileSteelFurnace.this.markDirty();
        }
    };

    private CombinedInvWrapper combineHandler = new CombinedInvWrapper(inputItemStackHandler, inputFuelStackHandler, outputItemStackHandler);





    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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
        if (compound.hasKey("inputItem")) {
            inputItemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("inputItem"));
        }
        if (compound.hasKey("inputFuel")) {
            inputFuelStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("inputFuel"));
        }
        if (compound.hasKey("outputItem")) {
            outputItemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("outputItem"));
        }
        if (compound.hasKey("progress")) {
            outputItemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("outputItem"));
        }
        progress = compound.getInteger("progress");
        isFormed = compound.getBoolean("isFormed");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("inputItem", inputItemStackHandler.serializeNBT());
        compound.setTag("inputFuel", inputFuelStackHandler.serializeNBT());
        compound.setTag("outputItem", outputItemStackHandler.serializeNBT());
        compound.setInteger("progress", progress);
        compound.setBoolean("isFormed", isFormed);
        return compound;

    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(facing == null){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(combineHandler);
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(facing == null){
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(combineHandler);
            }else if(facing == EnumFacing.UP){
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputItemStackHandler);
            }else if(facing == EnumFacing.DOWN){
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputItemStackHandler);
            }else{
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputFuelStackHandler);
            }

        }
        return super.getCapability(capability, facing);
    }

}
