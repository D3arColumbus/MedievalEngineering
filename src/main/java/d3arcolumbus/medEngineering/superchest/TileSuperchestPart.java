package d3arcolumbus.medEngineering.superchest;

import d3arcolumbus.medEngineering.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
public class TileSuperchestPart extends TileEntity {

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperchestPart || state.getValue(BlockSuperchest.FORMED) == SuperchestPartIndex.UNFORMED) {
            return false;
        }

        BlockPos controllerPos = BlockSuperchest.getControllerPos(world, pos);
        if (controllerPos != null) {
            TileEntity te = world.getTileEntity(controllerPos);
            if (te instanceof TileSuperchest) {
                return te.hasCapability(capability, facing);
            }
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != ModBlocks.blockSuperchestPart || state.getValue(BlockSuperchest.FORMED) == SuperchestPartIndex.UNFORMED) {
            return null;
        }

        BlockPos controllerPos = BlockSuperchest.getControllerPos(world, pos);
        if (controllerPos != null) {
            TileEntity te = world.getTileEntity(controllerPos);
            if (te instanceof TileSuperchest) {
                return te.getCapability(capability, facing);
            }
        }
        return super.getCapability(capability, facing);
    }
}
