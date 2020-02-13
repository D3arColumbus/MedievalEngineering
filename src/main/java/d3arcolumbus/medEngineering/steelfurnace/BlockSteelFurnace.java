package d3arcolumbus.medEngineering.steelfurnace;

import com.sun.org.apache.xpath.internal.operations.Mult;
import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.BlockBase;
import d3arcolumbus.medEngineering.item.ModItems;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSteelFurnace extends BlockBase implements ITileEntityProvider {

    public BlockSteelFurnace() {
        super(Material.IRON, "blocksteelfurnace", "blocksteelfurnace");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileSteelFurnace te = (TileSteelFurnace) worldIn.getTileEntity(pos);
        MedEngineering.logger.info("OnAct: " + pos.getX() + " " + pos.getY() + " "+ pos.getZ());

        if(playerIn.getHeldItem(hand).getItem() == ModItems.itemHammer) {
            MultiBlockSteelFurnace.toggleMultiblock(te, worldIn, playerIn, pos);
            return true;
        }
        return false;
    }



    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSteelFurnace();
    }
}
