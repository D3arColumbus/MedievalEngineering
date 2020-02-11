package d3arcolumbus.medEngineering.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

public class TileSteelFurnace extends TileEntity implements ITickable {

    public static final int SIZE = 3;
    public static final int INPUT_ITEM = 1;
    public static final int INPUT_FUEL = 1;
    public static final int OUTPUT_ITEM = 1;
    public static final int MAX_PROGRESS = 40;
    private int progress = 0;
    private int coalCount = 0;

    @Override
    public void update() {
        if(!world.isRemote){
            //only server-side
            if(progress > 0){
                //smelting has started
                if(hasValidInputs()){
                    progress --;
                    if(progress == 0){
                        //smelting has finished
                        attemptSmelt();
                    }
                }else{
                    progress = 0;
                }
            }else{
                startSmelt();
            }
        }
        markDirty();
    }

    private void attemptSmelt() {
        if(hasValidInputs()){
            ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputItemStackHandler.getStackInSlot(0));
            //.copy() is important....
            if(insertOutput(getSmeltingResult().copy(), false)){
                inputItemStackHandler.extractItem(0, 1, false);
                inputFuelStackHandler.extractItem(0,1,false);
            }
        }
    }


    //returns true if the system found a valid smelting recipe for the input item
    private boolean hasValidItemInput(){
        ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputItemStackHandler.getStackInSlot(0));
        return result != ItemStack.EMPTY;
    }

    private boolean hasValidInputs(){
        ItemStack result = inputFuelStackHandler.extractItem(0,1,true);
        if(result.getItem().equals(Items.COAL)){
            return hasValidItemInput();
        }
        return false;
    }

    //returns the smelting result
    private ItemStack getSmeltingResult(){
        if(hasValidItemInput()){
            return FurnaceRecipes.instance().getSmeltingResult(inputItemStackHandler.getStackInSlot(0));
        }
        return null;
    }

    private void startSmelt(){
        if(hasValidInputs())
            if(insertOutput(getSmeltingResult().copy(), true)){

                setProgress(MAX_PROGRESS);
            }
    }

    private boolean insertOutput(ItemStack output, boolean simulate) {
        ItemStack remaining = outputItemStackHandler.insertItem(0, output, simulate);
        //return ItemStack.EMPTY if the invSlot is either empty or filled with the same item
        return remaining == ItemStack.EMPTY;
    }


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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
        progress = compound.getInteger("progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("inputItem", inputItemStackHandler.serializeNBT());
        compound.setTag("inputFuel", inputFuelStackHandler.serializeNBT());
        compound.setTag("outputItem", outputItemStackHandler.serializeNBT());
        compound.setInteger("progress", progress);
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
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
