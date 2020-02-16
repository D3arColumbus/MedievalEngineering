package d3arcolumbus.medEngineering.util;


import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MultiBlock {


    //returns the lower left corner of an square shaped multiblock
    public static BlockPos lowerLeftCorner(World world, BlockPos pos, int length, Block block){
        length -= 1;

        //1. x + 2
        if(world.getBlockState(pos.add(length,0,0)).getBlock() == block){
           for(int i = length - 1; i > 0; i--){
               MedEngineering.logger.info(world.getBlockState(pos.add(0,0,-i)).getBlock());
               MedEngineering.logger.info((world.getBlockState(pos.add(0,0,-i)).getBlock() == block));
               if(world.getBlockState(pos.add(0,0,-i)).getBlock() == block)
                   return new BlockPos(pos.add(0,0,-i));
           }
        }
        //2. x - 2
        else if(world.getBlockState(pos.add(-length,0,0)).getBlock() == block){
            for(int i = length - 1; i > 0; i--){
                if(world.getBlockState(pos.add(-length,0,-i)).getBlock() == block)
                    return pos.add(-length,0,-i);
            }

        }
        //3. z + 2
        else if(world.getBlockState(pos.add(0,0,length)).getBlock() == block){
            for(int i = length - 1; i > 0; i--){
                if(world.getBlockState(pos.add(-i,0,0)).getBlock() == block)
                    return pos.add(-i,0,0);
            }
        }
        //4. z - 2
        else if(world.getBlockState(pos.add(0,0,-length)).getBlock() == block){
            for(int i = length - 1; i > 0; i--){
                if(world.getBlockState(pos.add(-i,0,-length + 1)).getBlock() == block)
                    return pos.add(-1,0,-length);
            }
        }
        return null;
    }
}
