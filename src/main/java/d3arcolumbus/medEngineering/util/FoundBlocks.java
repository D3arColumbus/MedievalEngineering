package d3arcolumbus.medEngineering.util;

import d3arcolumbus.medEngineering.MedEngineering;
import net.minecraft.block.Block;

import java.util.ArrayList;

/**
 * Class for storing which blocks where found in an specific layer of a multiblock
*/

public class FoundBlocks {

    private ArrayList<FoundBlock> list = new ArrayList<FoundBlock>();

    public void add(Block block){

        if(getBlock(block) != null){

            getBlock(block).add(1);
        }

        else
            list.add(new FoundBlock(block, 1));
    }

    public int getAmount(Block block) {

        for(FoundBlock found : list){


            if(found.getBlock() == block){
                return found.getAmount();
            }
        }
        return -1;
    }

    public FoundBlock getBlock(Block block) {
        for(FoundBlock found : list){
            if(found.getBlock() == block){
                return found;
            }
        }
        return null;
    }

}
