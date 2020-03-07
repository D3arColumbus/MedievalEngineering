package d3arcolumbus.medEngineering.charcoalFurnace;

import com.google.common.base.Optional;
import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.Random;

public class BlockHardenedClayVent extends BlockBase {
    private static String name = "blockhardenedclayvent";

    private static final PropertyBool SMOKING = PropertyBool.create("smoking");

    public BlockHardenedClayVent() {
        super(Material.CLAY, name, name);
        setDefaultState(this.blockState.getBaseState().withProperty(SMOKING, true));

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        //double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed,
        MedEngineering.logger.info(stateIn.getValue(SMOKING));
        if(stateIn.getValue(SMOKING)){
            double f1 = pos.getX() + 0.5;
            double f2 = pos.getY() + 1.1;
            double f3 = pos.getZ() + 0.5;
            double f4 = rand.nextDouble() * 0.5 - 0.3;
            double f5 = rand.nextDouble() * -0.6 + 0.3;
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE,(f1 + f4), f2, (f3 + f5), 0.0D, 0.1D, 0.0D);
        }

    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SMOKING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        MedEngineering.logger.info(meta);
        if(meta == 1)
            return getDefaultState().withProperty(SMOKING, true);
        return getDefaultState().withProperty(SMOKING, false);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        if(state.getValue(SMOKING))
            return 1;
        return 0;
    }

    public void toggleParticle(boolean shouldSmoke){
        if(shouldSmoke)
            blockState.getBaseState().withProperty(SMOKING,true);
        else
            blockState.getBaseState().withProperty(SMOKING,false);
    }
}
