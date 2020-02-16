package d3arcolumbus.medEngineering.charcoalFurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
import d3arcolumbus.medEngineering.steelfurnace.TileSteelFurnace;
import d3arcolumbus.medEngineering.util.MultiBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class MultiBlockCharcoalFurnace {

    private static int foundController = 0;


    public static void formMultiblock(){

    }

    public static boolean validMultiblock(World world, BlockPos pos, TileCharcoalFurnace te){
        foundController = 0;
        boolean foundLeftCorner = false;

        //finds lower left corner
        for(int i = 5; i >= 3; i--){

            if(MultiBlock.lowerLeftCorner(world, pos, i, ModBlocks.blockHardenedClay) != null){
                pos = MultiBlock.lowerLeftCorner(world, pos, i, ModBlocks.blockHardenedClay);
                te.setSize(i);
                foundLeftCorner = true;

                break;

            }
        }
        if(!foundLeftCorner){
            return false;
        }
        MedEngineering.logger.info("Found corner");

        for(int i = 0; i < 8; i++){
            if(!checkLayer(pos, world, te)){
                if(i > 3){
                    if(checkTopLayer(pos, world, te)){
                        te.setHeight(i);
                        MedEngineering.logger.info("Height: " + i);
                        return true;
                    }
                }
            }

        }
        return false;

    }

    private static boolean checkTopLayer(BlockPos pos, World world, TileCharcoalFurnace te) {
        return true;
    }


    private static boolean checkLayer(BlockPos pos, World world, TileCharcoalFurnace te){
        MedEngineering.logger.info("Start  check layer");
        for(int xx = 0; xx < te.getSize(); xx++) {
            MedEngineering.logger.info("X: " + pos.add(xx, 0, 0).getX() + " Y: " + pos.add(xx, 0, 0).getY() + " Z: " + pos.add(xx, 0, 0).getZ());
            MedEngineering.logger.info(world.getBlockState(pos.add(xx, 0, 0)).getBlock());
            MedEngineering.logger.info(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockHardenedClay);

            if (!(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace)
                    foundController += 1;
            }
        }
        MedEngineering.logger.info("Found 1");
        pos = pos.add(te.getSize() - 1, 0 ,0);
        for (int zz = 0; zz < te.getSize(); zz++) {
            //if not hardened clay or steel furnace
            if (!(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(0, 0, zz)).getBlock() == ModBlocks.blockCharcoalFurnace)
                    foundController += 1;
            }
        }
        pos = pos.add(0, 0 ,te.getSize() - 1);
        MedEngineering.logger.info("Found 2");
        for(int xx = 0; xx < te.getSize(); xx++) {
            if (!(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(-xx, 0, 0)).getBlock() == ModBlocks.blockCharcoalFurnace)
                    foundController += 1;
            }
        }
        pos = pos.add(-te.getSize() + 1, 0 ,0);
        MedEngineering.logger.info("Found 3");
        for (int zz = 0; zz < te.getSize(); zz++) {
            //if not hardened clay or steel furnace
            if (!(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockHardenedClay) &&
                    !(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockCharcoalFurnace))
                return false;
            else {
                if(world.getBlockState(pos.add(0, 0, -zz)).getBlock() == ModBlocks.blockCharcoalFurnace)
                    foundController += 1;
            }
        }
        MedEngineering.logger.info("Found 4");
        return true;
    }


    public static void destroyMutliblock(){

    }

    public static void toggleMultiblock(TileCharcoalFurnace te, World world, EntityPlayer player, BlockPos pos) {
        if (!world.isRemote) {
            if (te.isFormed()) {
                destroyMutliblock();
                te.setFormed(false);
            } else {
                if (validMultiblock(world, pos, te)) {
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

