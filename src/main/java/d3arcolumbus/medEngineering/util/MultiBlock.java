package d3arcolumbus.medEngineering.util;


import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Helper methods to streamline multiblock programming
 */
public class MultiBlock {
//returns the lower left corner of an square shaped multiblock

    /**
     * Checks on which side the Controller is an then calculates the lower left corner of the Multiblock.
     * The Controller has to be in the second layer and not in any corner!
     * The Multiblock has to have a square bottom layer.
     * @param world World
     * @param pos Position of the controller.
     * @param block Building block of the multiblock. For example: hardened clay
     * @param maxLength Maximal length of the multiblock.
     * @param tile TileEntity of the Multiblock
     * @return Position of the lower left corner of the multiblock or null if there is non.
     */
    @SuppressWarnings("DuplicatedCode")
    public static BlockPos lowerLeftCorner(World world, TileEntityMultiblock tile, BlockPos pos, Block block, int maxLength){

        //checks what is the
        for(int y = maxLength - 1; y >= 2; y--) {
         //1. x + 2
         if (world.getBlockState(pos.add(y, 0, 0)).getBlock() == block) {
             for (int i = y - 1; i > 0; i--) {
                 if (world.getBlockState(pos.add(0, 0, -i)).getBlock() == block){
                     tile.setSize(y + 1);
                     return new BlockPos(pos.add(0, -1, -i));
                 }
             }
         }

         //2. x - 2
         else if (world.getBlockState(pos.add(-y, 0, 0)).getBlock() == block) {
             for (int i = y - 1; i > 0; i--) {
                 if (world.getBlockState(pos.add(-y, 0, -i)).getBlock() == block){
                     tile.setSize(y + 1);
                     return pos.add(-y, -1, -i);
                 }
             }
         }

         //3. z + 2
         else if (world.getBlockState(pos.add(0, 0, y)).getBlock() == block) {
             for (int i = y - 1; i > 0; i--) {
                 if (world.getBlockState(pos.add(-i, 0, 0)).getBlock() == block){
                     tile.setSize(y + 1);
                     return pos.add(-i, -1, 0);
                 }
             }
         }

         //4. z - 2
         else if (world.getBlockState(pos.add(0, 0, -y)).getBlock() == block) {
             for (int i = y - 1; i > 0; i--) {
                 if (world.getBlockState(pos.add(-i, 0, -y)).getBlock() == block){
                     tile.setSize(y + 1);
                     return pos.add(-i, -1, -y);
                 }
             }
         }
         }
        return null;
    }

    //length == x cooardinate

    /**
     * Returns all blocks found in an full multiblock. For example the sealing or floor.
     * Layer does not need to be a square!
     * @param world World
     * @param pos Position of the lower left block of the layer
     * @param length Length of the layer = x Coordinate.
     * @param with With of the layer = z Coordinate
     * @return FoundBlocks -> all blocks that were found in the layer.
     */
    public static FoundBlocks checkFullLayer(World world, BlockPos pos, int length, int with){
        FoundBlocks found = new FoundBlocks();
        for(int x = 0; x < length; x++) {
            for (int z = 0; z < with; z++) {
                MedEngineering.logger.info(world.getBlockState(pos.add(x, 0, z)).getBlock());
                found.add(world.getBlockState(pos.add(x, 0, z)).getBlock());

            }
        }
        return found;
    }

    /**
     * Returns if all blocks in a layer are from the specified block
     * Layer does not need to be a square!
     * @param world World
     * @param pos Position of the lower left block of the layer
     * @param length Length of the layer = x Coordinate.
     * @param with With of the layer = z Coordinate
     * @param block
     * @return FoundBlocks -> all blocks that were found in the layer.
     */
    public static boolean checkFullLayer(World world, BlockPos pos, int length, int with, Block block){
        FoundBlocks found = checkFullLayer(world, pos, length, with);
        return found.getAmount(block) == length*with;
    }
}
