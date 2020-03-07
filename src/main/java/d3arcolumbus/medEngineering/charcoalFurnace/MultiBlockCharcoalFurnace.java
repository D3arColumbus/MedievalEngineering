package d3arcolumbus.medEngineering.charcoalFurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.steelfurnace.TileSteelFurnace;
import d3arcolumbus.medEngineering.util.FoundBlocks;
import d3arcolumbus.medEngineering.util.MultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;


public class MultiBlockCharcoalFurnace {

    //how many controller were found
    private static int foundController = 0;


    public static void formMultiblock(){

    }

    /**
     * @param world World
     * @param pos Position of the lower left corner of the specific layer.
     * @param te TileEntity of the specific multiblock.
     * @param player Player that tries to form multiblock.
     * @return True if the multiblock is valid.
     */
    public static boolean validMultiblock(World world, BlockPos pos, TileCharcoalFurnace te, EntityPlayer player){
        foundController = 0;

        te.setPosWood(null);

        //if lower left corner was not found
        if(MultiBlock.lowerLeftCorner(world,te,pos,ModBlocks.blockHardenedClay,5) == null)
            return false;
        //lower left corner where the first wood log is
        BlockPos woodPos = MultiBlock.lowerLeftCorner(world,te,pos,ModBlocks.blockHardenedClay,5);
        woodPos = woodPos.add(1,1,1);

        pos = MultiBlock.lowerLeftCorner(world,te,pos,ModBlocks.blockHardenedClay,5);

        //checks bottom layer
        if(!checkFirstLayer(pos,world,te)){
            return false;
        }

        //checks remaining layers
        pos = pos.add(0, 1 , 0);
        for(int i = 0; i < 8; i++){
            if(!checkLayer(pos, world, te)){
                pos = pos.add(0, -1 , 0);
                if(i > 2){
                    if(checkTopLayer(pos, world, te, player)){
                        te.setHeight(i);
                        if(foundController == 1){
                            te.setPosWood(woodPos);

                            return true;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
            pos = pos.add(0, 1 , 0);
        }
        return false;
    }

    /**
     * @param world World
     * @param pos Position of the lower left corner of the specific layer.
     * @param te TileEntity of the specific multiblock.
     * @param player Player that tries to form multiblock.
     * @return True if the top layer is valid.
     */
    private static boolean checkTopLayer(BlockPos pos, World world, TileCharcoalFurnace te, EntityPlayer player) {
        //all blocks that were found on the top layer
        FoundBlocks found = MultiBlock.checkFullLayer(world, pos, te.getSize(), te.getSize());
        //amount of valid blocks
        int count = 0;
        count += found.getAmount(ModBlocks.blockHardenedClay);

        //at least 2 vents required
        if(found.getAmount(ModBlocks.blockHardenedClayVent) > 1){
            count += found.getAmount(ModBlocks.blockHardenedClayVent);
        }else{
            player.sendStatusMessage(new TextComponentString(TextFormatting.WHITE + "To few vents"), false);
        }

        //true if amount of valid blocks equals needed blocks
        return count == te.getSize() * te.getSize();
    }


    /**
     * @param world World
     * @param pos Position of the lower left corner of the specific layer.
     * @param te TileEntity of the specific multiblock.
     * @return True if the first layer is valid.
     */
    private static boolean checkFirstLayer(BlockPos pos, World world, TileCharcoalFurnace te){
        return MultiBlock.checkFullLayer(world, pos, te.getSize(), te.getSize()).getAmount(ModBlocks.blockHardenedClay) == te.getSize() * te.getSize();
    }

    /**
     * Checks hollow layer by going around the outside of the multiblock.
     * No check if the inside of the multiblock is empty!
     * @param world World
     * @param pos Position of the lower left corner of the specific layer.
     * @param te TileEntity of the specific multiblock.
     * @return True if the specific middle layer is valid.
     */
    private static boolean checkLayer(BlockPos pos, World world, TileCharcoalFurnace te){

        for(int xx = 0; xx < te.getSize(); xx++) {
            if (!(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace){
                    foundController += 1;
                }
            }
        }

        pos = pos.add(te.getSize() - 1, 0 ,0);
        for (int zz = 0; zz < te.getSize(); zz++) {
            //if not hardened clay or steel furnace
            if (!(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockCharcoalFurnace){
                    foundController += 1;
                }

            }
        }
        pos = pos.add(0, 0 ,te.getSize() - 1);

        for(int xx = 0; xx < te.getSize(); xx++) {
            if (!(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace){
                    foundController += 1;
                }

            }
        }
        pos = pos.add(-te.getSize() + 1, 0 ,0);

        for (int zz = 0; zz < te.getSize(); zz++) {
            //if not hardened clay or steel furnace
            if (!(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockCharcoalFurnace){
                    foundController += 1;
                }

            }
        }

        return true;
    }


    public static void destroyMutliblock(){

    }

    /**
     * Either forms or destroys the multiblock
     * @param te TileEntity of the specific multiblock
     * @param world World
     * @param player Player that tries to form/destroy the multiblock.
     * @param pos Position of the controller
     */
    public static void toggleMultiblock(TileCharcoalFurnace te, World world, EntityPlayer player, BlockPos pos) {
        if (!world.isRemote) {
            if (te.isFormed()) {
                destroyMutliblock();
                te.setFormed(false);
            } else {
                if (validMultiblock(world, pos, te, player)) {
                    formMultiblock();
                    te.setFormed(true);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Charcoal furnace created!"), false);
                } else {
                    player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Could not form charcoal furnace!"), false);
                }
            }

        }
    }

}

