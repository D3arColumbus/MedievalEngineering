package d3arcolumbus.medEngineering.steelfurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
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

        MedEngineering.logger.info(pos.getX() + " " + pos.getY() + " "+ pos.getZ());
        int foundController = 0;
        //4 Possibilities

        //1. x + 2
        if(world.getBlockState(pos.add(2,0,0)).getBlock() == ModBlocks.blockHardenedClay){
            te.setIsFacing(TileSteelFurnace.facing.EAST);
            pos = pos.add(1,  0 , 0);

        }
        //2. x - 2
        else if(world.getBlockState(pos.add(-2,0,0)).getBlock() == ModBlocks.blockHardenedClay){
            te.setIsFacing(TileSteelFurnace.facing.WEST);
            pos = pos.add(-1,  0 , 0);

        }
        //3. z + 2
        else if(world.getBlockState(pos.add(0,0,2)).getBlock() == ModBlocks.blockHardenedClay){
            te.setIsFacing(TileSteelFurnace.facing.SOUTH);
            pos = pos.add(0,  0 , 1);


        }
        //4. z - 2
        else if(world.getBlockState(pos.add(-2,0,-2)).getBlock() == ModBlocks.blockHardenedClay){
            te.setIsFacing(TileSteelFurnace.facing.NORTH);
            pos = pos.add(0,  0 , -1);

        }
        MedEngineering.logger.info(pos.getX() + " " + pos.getY() + " "+ pos.getZ());
        pos = pos.add(-1,  0 , -1);
        MedEngineering.logger.info(pos.getX() + " " + pos.getY() + " "+ pos.getZ());
        //first layer

        for(int xx = 0; xx < 3; xx++){
            for(int zz = 1; zz <= 3; zz++){
                MedEngineering.logger.info(pos.add(xx, 0, zz));
                MedEngineering.logger.info(world.getBlockState(pos.add(xx, 0, zz)).getBlock());


                if(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockHardenedClay){

                }


                else if(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockSteelFurnace){
                    foundController += 1;

                }else{
                    MedEngineering.logger.info(world.getBlockState(pos.add(xx, 0, zz)).getBlock() == ModBlocks.blockHardenedClay);
                    MedEngineering.logger.info(world.getBlockState(pos.add(xx, 0, zz)).getBlock());
                    return false;
                }

            }
        }

        if(foundController > 1){
            return false;
        }



        MedEngineering.logger.info(te.getIsFacing());

      //  for(int x = 1; x < )





        /*for(int )
        world.getBlockState()

         */
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

