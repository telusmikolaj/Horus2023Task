package pl.telusmikolaj.implementations;

import pl.telusmikolaj.interfaces.Block;
import pl.telusmikolaj.interfaces.CompositeBlock;

import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private final List<Block> blocks;

    public CompositeBlockImpl(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
