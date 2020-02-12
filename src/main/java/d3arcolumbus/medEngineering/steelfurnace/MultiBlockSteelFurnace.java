package d3arcolumbus.medEngineering.steelfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MultiBlockSteelFurnace {

    public static void formMultiblock(){

    }

    public static boolean validMultiblock(World world, BlockPos pos, TileSteelFurnace te){
        //4 Possibilities

        //1. x + 2
        if(world.getBlockState(pos.add(2,0,0)).getBlock() == Blocks.BRICK_BLOCK){
            te.setIsFacing(TileSteelFurnace.facing.EAST);
        }
        //2. x - 2
        else if(world.getBlockState(pos.add(-2,0,0)).getBlock() == Blocks.BRICK_BLOCK){
            te.setIsFacing(TileSteelFurnace.facing.WEST);
        }
        //3. z + 2
        else if(world.getBlockState(pos.add(0,0,2)).getBlock() == Blocks.BRICK_BLOCK){
            te.setIsFacing(TileSteelFurnace.facing.SOUTH);
        }
        //4. z - 2
        else if(world.getBlockState(pos.add(-2,0,-2)).getBlock() == Blocks.BRICK_BLOCK){
            te.setIsFacing(TileSteelFurnace.facing.NORTH);
        }






        /*for(int )
        world.getBlockState()

         */
        return false;
    }

    public static void destroyMutliblock(){

    }

    public static void toggleMultiblock(TileSteelFurnace te, World world, EntityPlayer player, BlockPos pos) {
        if (!world.isRemote) {
            if (te.isFormed()) {
                destroyMutliblock();
                te.setFormed(false);
            } else {
                if (validMultiblock(world, pos, te)) {
                    formMultiblock();
                    te.setFormed(true);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.GREEN + "Steel furnace created!"), false);
                } else {
                    player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Could not form steel furnace!"), false);
                }
            }

        }
    }

}

