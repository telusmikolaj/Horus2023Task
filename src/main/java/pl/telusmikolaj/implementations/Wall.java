package pl.telusmikolaj.implementations;

import pl.telusmikolaj.interfaces.Block;
import pl.telusmikolaj.interfaces.CompositeBlock;
import pl.telusmikolaj.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByPredicate(
                block -> color.equals(block.getColor()),
                this.blocks
        ).findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlockByPredicate(
                block -> material.equals(block.getMaterial()),
                this.blocks
        ).toList();
    }

    private Stream<Block> findBlockByPredicate(Predicate<Block> predicate, List<Block> blocks) {
        return blocks.stream()
                .flatMap(block -> isBlockComposite(block)
                        ? Stream.concat(Stream.of(block), findBlockByPredicate(predicate, ((CompositeBlock) block).getBlocks()))
                        : Stream.of(block))
                .filter(predicate);
    }


    private boolean isBlockComposite(Block block) {
        return block instanceof CompositeBlock;
    }

    @Override
    public int count() {
        return this.blocks
                .stream()
                .mapToInt(Block::getCount)
                .sum();
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }
}
