package d3arcolumbus.medEngineering.steelfurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.util.MultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.Objects;

public class MultiBlockSteelFurnace {

    private static int foundController = 0;


    public static void formMultiblock(){

    }

    public static boolean validMultiblock(World world, BlockPos pos, TileSteelFurnace te){
        foundController = 0;

        if(MultiBlock.lowerLeftCorner(world, te, pos, ModBlocks.blockHardenedClay, 3) == null){
            return false;
        }

        pos = MultiBlock.lowerLeftCorner(world, te, pos, ModBlocks.blockHardenedClay, 3);


        //first layer
        if(!checkLayer(pos, world))
            return false;

        //second layer
        pos = pos.add(0,  1 , 0);
        if(!checkLayer(pos, world))
            return false;

        //3 & 4 Layer
        pos = pos.add(0,  1 , 0);
        if(world.getBlockState(pos.add(1, 0, 0)).getBlock() == ModBlocks.blockHardenedClay)
            if(world.getBlockState(pos.add(0, 0, 1)).getBlock() == ModBlocks.blockHardenedClay)
                if(world.getBlockState(pos.add(2, 0, 1)).getBlock() == ModBlocks.blockHardenedClay)
                    if(world.getBlockState(pos.add(1, 0, 2)).getBlock() == ModBlocks.blockHardenedClay)
                        if(world.getBlockState(pos.add(1, 0, 1)).getBlock() == ModBlocks.blockHardenedClay)
                            if(world.getBlockState(pos.add(1, 1, 1)).getBlock() == ModBlocks.blockHardenedClay)
                                return foundController == 1;
       return false;
    }


    private static boolean checkLayer(BlockPos pos, World world){
        for(int xx = 0; xx < 3; xx++) {
            for (int zz = 0; zz < 3; zz++) {
                //if not hardened clay or steel furnace
                if (!(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockHardenedClay) &&
                        !(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockSteelFurnace))
                            return false;
                 else {
                    if(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockSteelFurnace)
                        foundController += 1;
                }
            }
        }
        return true;
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

