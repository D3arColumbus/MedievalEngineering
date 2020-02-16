package d3arcolumbus.medEngineering.charcoalFurnace;

import d3arcolumbus.medEngineering.MedEngineering;
import d3arcolumbus.medEngineering.block.BlockBase;
import d3arcolumbus.medEngineering.item.ModItems;
import d3arcolumbus.medEngineering.steelfurnace.MultiBlockSteelFurnace;
import d3arcolumbus.medEngineering.steelfurnace.TileSteelFurnace;
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

public class BlockCharcoalFurnace extends BlockBase implements ITileEntityProvider {

    private static final int GUI_ID = 3;
    private static String name = "blockcharcoalfurnace";
    public BlockCharcoalFurnace() {
        super(Material.CLAY, name, name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCharcoalFurnace();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //is true on client world
        if(!worldIn.isRemote){
            if(worldIn.getTileEntity(pos) instanceof TileCharcoalFurnace){
                TileCharcoalFurnace te = (TileCharcoalFurnace) worldIn.getTileEntity(pos);
                if(playerIn.getHeldItem(hand).getItem() == ModItems.itemHammer) {
                    MultiBlockCharcoalFurnace.toggleMultiblock(te, worldIn, playerIn, pos);
                    return true;
                }
            }
        }
        return false;
    }


}
