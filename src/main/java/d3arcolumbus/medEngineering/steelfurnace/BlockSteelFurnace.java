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

    public static final int GUI_ID = 2;

    public BlockSteelFurnace() {
        super(Material.IRON, "blocksteelfurnace", "blocksteelfurnace");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //is true on client world
        if(!worldIn.isRemote){
            if(worldIn.getTileEntity(pos) instanceof TileSteelFurnace){
                TileSteelFurnace te = (TileSteelFurnace) worldIn.getTileEntity(pos);
                if(playerIn.getHeldItem(hand).getItem() == ModItems.itemHammer) {
                    MultiBlockSteelFurnace.toggleMultiblock(te, worldIn, playerIn, pos);
                    return true;
                }else{
                    if(te.isFormed()){
                        playerIn.openGui(MedEngineering.instance, GUI_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
                    }
                }
            }
        }
        return false;
    }



    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSteelFurnace();
    }
}
