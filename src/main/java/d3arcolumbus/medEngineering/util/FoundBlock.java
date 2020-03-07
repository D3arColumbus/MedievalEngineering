package d3arcolumbus.medEngineering.util;

import net.minecraft.block.Block;

/**
 * Stores a block and how many times it was found
 */
public class FoundBlock {
    private Block block;

    private int amount;

    FoundBlock(Block block, int amount){
        this.block = block;
        this.amount = amount;
    }

    public void add(int value){
        amount += value;
    }

    public Block getBlock() {
        return block;
    }

    public int getAmount() {
        return amount;
    }
}
